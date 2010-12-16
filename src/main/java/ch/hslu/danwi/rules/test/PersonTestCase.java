package ch.hslu.danwi.rules.test;

import static org.junit.Assert.assertEquals;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.hslu.danwi.rules.Person;
import ch.hslu.danwi.rules.ThreeValuedBoolean;

public class PersonTestCase {

	private KnowledgeBase kbase;
	private StatefulKnowledgeSession ksession;
	private KnowledgeRuntimeLogger logger;
	private Person person;

	@Before
	public void startKB() throws Exception {
		kbase = readKnowledgeBase();
		ksession = kbase.newStatefulKnowledgeSession();
		logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "test");

		person = new Person();
	}

	@After
	public void close() {
		logger.close();
	}

	@Test
	public void testErwerbstaetig30plus() {
		person.setAufenthaltsTage(35);
		person.setErwerbstaetig(ThreeValuedBoolean.TRUE);
		runRules();
		assertEquals("LU", person.getSteuerRechtlicherKanton());
	}

	@Test
	public void testErwerbstaetig30minus() {
		person.setAufenthaltsTage(25);
		person.setErwerbstaetig(ThreeValuedBoolean.TRUE);
		runRules();
		assertEquals(null, person.getSteuerRechtlicherKanton());
	}

	@Test
	public void testNichtErwerbstaetig90minus() {
		person.setAufenthaltsTage(35);
		person.setErwerbstaetig(ThreeValuedBoolean.FALSE);
		runRules();
		assertEquals(null, person.getSteuerRechtlicherKanton());
	}

	@Test
	public void testNichtErwerbstaetig90plus() {
		person.setAufenthaltsTage(97);
		person.setErwerbstaetig(ThreeValuedBoolean.FALSE);
		runRules();
		assertEquals("LU", person.getSteuerRechtlicherKanton());
	}
	
	@Test
	public void testPartnerWohnsitzSeparat() {
		person.setWohnsitz("Horw");
		Person partner = new Person();
		partner.setWohnsitz("Kernenried");
		person.setPartner(partner);
		runRules();
		assertEquals(ThreeValuedBoolean.TRUE, person.isVermoegenZusammenrechnen());
	}
	
	@Test
	public void testPartnerWohnsitzGemeinsam() {
		person.setWohnsitz("Horw");
		Person partner = new Person();
		partner.setWohnsitz("Horw");
		person.setPartner(partner);
		runRules();
		assertEquals(ThreeValuedBoolean.UNKNOWN, person.isVermoegenZusammenrechnen());
	}
	
	@Test
	public void testKeinPartner() {
		person.setWohnsitz("Horw");
		runRules();
		assertEquals(ThreeValuedBoolean.UNKNOWN, person.isVermoegenZusammenrechnen());
	}
	
	@Test
	public void testPartnerInland() {
		person.setWohnsitz("Horw");
		Person partner = new Person();
		partner.setWohnsitz("Horw");
		partner.setEinkommen(50000);
		partner.setVermoegen(100000);
		partner.setLand("CH");
		partner.setErwerbstaetig(ThreeValuedBoolean.FALSE);
		person.setPartner(partner);
		person.setLand("CH");
		person.setVermoegen(200000);
		person.setEinkommen(100000);
		
		runRules();
		assertEquals(75000, person.getEinkommen());
		assertEquals(150000, person.getVermoegen());
	}
	
	@Test
	public void testPartnerAusland() {
		person.setWohnsitz("Horw");
		Person partner = new Person();
		partner.setWohnsitz("New York");
		partner.setEinkommen(50000);
		partner.setVermoegen(100000);
		partner.setLand("US");
		partner.setErwerbstaetig(ThreeValuedBoolean.FALSE);
		person.setPartner(partner);
		person.setLand("CH");
		person.setVermoegen(200000);
		person.setEinkommen(100000);
		
		runRules();
		assertEquals(100000, person.getEinkommen());
		assertEquals(200000, person.getVermoegen());
	}
	
	@Test
	public void testPartnerInlandErwebstaetig() {
		person.setWohnsitz("Horw");
		Person partner = new Person();
		partner.setWohnsitz("Horw");
		partner.setEinkommen(50000);
		partner.setVermoegen(100000);
		partner.setLand("CH");
		partner.setErwerbstaetig(ThreeValuedBoolean.TRUE);
		person.setPartner(partner);
		person.setLand("CH");
		person.setVermoegen(200000);
		person.setEinkommen(100000);
		
		runRules();
		assertEquals(100000, person.getEinkommen());
		assertEquals(200000, person.getVermoegen());
	}
	
	@Test
	public void testPartnerErwebstaetig() {
		Person partner = new Person();
		partner.setErwerbstaetig(ThreeValuedBoolean.TRUE);
		person.setPartner(partner);
		
		runRules();
		assertEquals(ThreeValuedBoolean.TRUE, person.getIndividuelleVermoegensZuordnung());
	}
	
	@Test
	public void testPartnerNichtErwebstaetig() {
		Person partner = new Person();
		partner.setErwerbstaetig(ThreeValuedBoolean.FALSE);
		person.setPartner(partner);
		
		runRules();
		assertEquals(ThreeValuedBoolean.UNKNOWN, person.getIndividuelleVermoegensZuordnung());
	}
	
	

	private void runRules() {
		ksession.insert(person);
		ksession.fireAllRules();
	}

	private static KnowledgeBase readKnowledgeBase() throws Exception {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory
				.newClassPathResource("steuerrechtlicherWohnsitz.drl"),
				ResourceType.DRL);
		KnowledgeBuilderErrors errors = kbuilder.getErrors();
		if (errors.size() > 0) {
			for (KnowledgeBuilderError error : errors) {
				System.err.println(error);
			}
			throw new IllegalArgumentException("Could not parse knowledge.");
		}
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		return kbase;
	}

}
