package ch.hslu.danwi.rules;

import java.util.ArrayList;
import java.util.List;

public class Person {
	private ThreeValuedBoolean erwerbstaetig = ThreeValuedBoolean.UNKNOWN;
	private ThreeValuedBoolean inObersterDirektion = ThreeValuedBoolean.UNKNOWN;
	private ThreeValuedBoolean hatVerantwortung = ThreeValuedBoolean.UNKNOWN;

	private int aufenthaltsTage;

	private int alter;

	private String steuerRechtlicherKanton;

	private String wohnsitz;
	private String wochenaufenthaltsort;
	private String Hauptsteuerdomizil;
	private List<String> Nebensteuerdomizil = new ArrayList<String>();

	private Person partner;

	private Zivilstand zivilstand;

	public ThreeValuedBoolean getErwerbstaetig() {
		return erwerbstaetig;
	}

	public void setErwerbstaetig(ThreeValuedBoolean erwerbstaetig) {
		this.erwerbstaetig = erwerbstaetig;
	}

	public ThreeValuedBoolean getInObersterDirektion() {
		return inObersterDirektion;
	}

	public void setInObersterDirektion(ThreeValuedBoolean inObersterDirektion) {
		this.inObersterDirektion = inObersterDirektion;
	}

	public ThreeValuedBoolean getHatVerantwortung() {
		return hatVerantwortung;
	}

	public void setHatVerantwortung(ThreeValuedBoolean hatVerantwortung) {
		this.hatVerantwortung = hatVerantwortung;
	}

	public List<String> getNebensteuerdomizil() {
		return Nebensteuerdomizil;
	}

	public void setNebensteuerdomizil(List<String> nebensteuerdomizil) {
		Nebensteuerdomizil = nebensteuerdomizil;
	}

	public void setAlter(int alter) {
		this.alter = alter;
	}

	/**
	 * @return the erwerbsTage
	 */
	public int getAufenthaltsTage() {
		return aufenthaltsTage;
	}

	/**
	 * @param erwerbsTage
	 *            the erwerbsTage to set
	 */
	public void setAufenthaltsTage(int erwerbsTage) {
		this.aufenthaltsTage = erwerbsTage;
	}

	public int getAlter() {
		return alter;
	}

	public void setSteuerRechtlicherKanton(String steuerRechtlicherKanton) {
		this.steuerRechtlicherKanton = steuerRechtlicherKanton;
	}

	public String getSteuerRechtlicherKanton() {
		return steuerRechtlicherKanton;
	}

	public void setWohnsitz(String wohnsitz) {
		this.wohnsitz = wohnsitz;
	}

	public String getWohnsitz() {
		return wohnsitz;
	}

	public void setWochenaufenthaltsort(String wochenaufenthaltsort) {
		this.wochenaufenthaltsort = wochenaufenthaltsort;
	}

	public String getWochenaufenthaltsort() {
		return wochenaufenthaltsort;
	}

	public void setHauptsteuerdomizil(String hauptsteuerdomizil) {
		Hauptsteuerdomizil = hauptsteuerdomizil;
	}

	public String getHauptsteuerdomizil() {
		return Hauptsteuerdomizil;
	}

	public void setPartner(Person partner) {
		this.partner = partner;
	}

	public Person getPartner() {
		return partner;
	}

	public void setZivilstand(Zivilstand zivilstand) {
		this.zivilstand = zivilstand;
	}

	public Zivilstand getZivilstand() {
		return zivilstand;
	}

}