package ch.hslu.danwi.rules;

import java.util.ArrayList;
import java.util.List;

public class Person {
	private int alter;
	private int aufenthaltsTage;
	private ThreeValuedBoolean erwerbstaetig = ThreeValuedBoolean.UNKNOWN;
	private ThreeValuedBoolean hatVerantwortung = ThreeValuedBoolean.UNKNOWN;
	
	private String Hauptsteuerdomizil;

	private ThreeValuedBoolean inObersterDirektion = ThreeValuedBoolean.UNKNOWN;

	private List<String> Nebensteuerdomizil = new ArrayList<String>();

	private Person partner;

	private String steuerRechtlicherKanton;

	private ThreeValuedBoolean VermoegenZusammenrechnen = ThreeValuedBoolean.UNKNOWN;
	private String wochenaufenthaltsort;
	private String wohnsitz;
	private Zivilstand zivilstand;

	public int getAlter() {
		return alter;
	}

	/**
	 * @return the erwerbsTage
	 */
	public int getAufenthaltsTage() {
		return aufenthaltsTage;
	}

	public ThreeValuedBoolean getErwerbstaetig() {
		return erwerbstaetig;
	}

	public ThreeValuedBoolean getHatVerantwortung() {
		return hatVerantwortung;
	}

	public String getHauptsteuerdomizil() {
		return Hauptsteuerdomizil;
	}

	public ThreeValuedBoolean getInObersterDirektion() {
		return inObersterDirektion;
	}

	public List<String> getNebensteuerdomizil() {
		return Nebensteuerdomizil;
	}

	public Person getPartner() {
		return partner;
	}

	public String getSteuerRechtlicherKanton() {
		return steuerRechtlicherKanton;
	}

	public String getWochenaufenthaltsort() {
		return wochenaufenthaltsort;
	}

	public String getWohnsitz() {
		return wohnsitz;
	}

	public Zivilstand getZivilstand() {
		return zivilstand;
	}

	/**
	 * @return the vermoegenZusammenrechnen
	 */
	public ThreeValuedBoolean isVermoegenZusammenrechnen() {
		return VermoegenZusammenrechnen;
	}

	public void setAlter(int alter) {
		this.alter = alter;
	}

	/**
	 * @param erwerbsTage
	 *            the erwerbsTage to set
	 */
	public void setAufenthaltsTage(int erwerbsTage) {
		this.aufenthaltsTage = erwerbsTage;
	}

	public void setErwerbstaetig(ThreeValuedBoolean erwerbstaetig) {
		this.erwerbstaetig = erwerbstaetig;
	}

	public void setHatVerantwortung(ThreeValuedBoolean hatVerantwortung) {
		this.hatVerantwortung = hatVerantwortung;
	}

	public void setHauptsteuerdomizil(String hauptsteuerdomizil) {
		Hauptsteuerdomizil = hauptsteuerdomizil;
	}

	public void setInObersterDirektion(ThreeValuedBoolean inObersterDirektion) {
		this.inObersterDirektion = inObersterDirektion;
	}

	public void setNebensteuerdomizil(List<String> nebensteuerdomizil) {
		Nebensteuerdomizil = nebensteuerdomizil;
	}

	public void setPartner(Person partner) {
		this.partner = partner;
	}

	public void setSteuerRechtlicherKanton(String steuerRechtlicherKanton) {
		this.steuerRechtlicherKanton = steuerRechtlicherKanton;
	}

	/**
	 * @param vermoegenZusammenrechnen the vermoegenZusammenrechnen to set
	 */
	public void setVermoegenZusammenrechnen(ThreeValuedBoolean vermoegenZusammenrechnen) {
		VermoegenZusammenrechnen = vermoegenZusammenrechnen;
	}

	public void setWochenaufenthaltsort(String wochenaufenthaltsort) {
		this.wochenaufenthaltsort = wochenaufenthaltsort;
	}

	public void setWohnsitz(String wohnsitz) {
		this.wohnsitz = wohnsitz;
	}

	public void setZivilstand(Zivilstand zivilstand) {
		this.zivilstand = zivilstand;
	}

}