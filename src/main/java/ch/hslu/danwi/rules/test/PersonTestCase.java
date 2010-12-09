package ch.hslu.danwi.rules.test;

import static org.junit.Assert.*;

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
		person.setErwerbstaetig(true);
		runRules();
		assertEquals("LU", person.getSteuerRechtlicherKanton());
	}
	
	@Test
	public void testErwerbstaetig30minus() {
		person.setAufenthaltsTage(25);
		person.setErwerbstaetig(true);
		runRules();
		assertEquals(null, person.getSteuerRechtlicherKanton());
	}
	
	@Test
	public void testNichtErwerbstaetig90minus() {
		person.setAufenthaltsTage(35);
		person.setErwerbstaetig(false);
		runRules();
		assertEquals(null, person.getSteuerRechtlicherKanton());
	}
	
	@Test
	public void testNichtErwerbstaetig90plus() {
		person.setAufenthaltsTage(97);
		person.setErwerbstaetig(false);
		runRules();
		assertEquals("LU", person.getSteuerRechtlicherKanton());
	}

	private void runRules() {
		ksession.insert(person);
		ksession.fireAllRules();
	}
	
	
	private static KnowledgeBase readKnowledgeBase() throws Exception {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource("steuerrechtlicherWohnsitz.drl"), ResourceType.DRL);
		KnowledgeBuilderErrors errors = kbuilder.getErrors();
		if (errors.size() > 0) {
			for (KnowledgeBuilderError error: errors) {
				System.err.println(error);
			}
			throw new IllegalArgumentException("Could not parse knowledge.");
		}
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		return kbase;
	}

}
