package ch.hslu.danwi.rules;

import java.util.ArrayList;
import java.util.List;

public class Person {
	private boolean erwerbstaetig;
	private boolean inObersterDirektion;
	private boolean hatVerantwortung;
	private boolean VermoegenZusammenrechnen;
	
	/**
	 * @return the vermoegenZusammenrechnen
	 */
	public boolean isVermoegenZusammenrechnen() {
		return VermoegenZusammenrechnen;
	}

	/**
	 * @param vermoegenZusammenrechnen the vermoegenZusammenrechnen to set
	 */
	public void setVermoegenZusammenrechnen(boolean vermoegenZusammenrechnen) {
		VermoegenZusammenrechnen = vermoegenZusammenrechnen;
	}

	private int aufenthaltsTage;
	
	private int alter;
	
	private String steuerRechtlicherKanton;
	
	private String wohnsitz;
	private String wochenaufenthaltsort;
	private String Hauptsteuerdomizil;
	private List<String> Nebensteuerdomizil = new ArrayList<String>();

	private Person partner;
	
	private Zivilstand zivilstand;

	public void setErwerbstaetig(boolean erwerbstaetig) {
		this.erwerbstaetig = erwerbstaetig;
	}

	public boolean isErwerbstaetig() {
		return erwerbstaetig;
	}

	public void setInObersterDirektion(boolean inObersterDirektion) {
		this.inObersterDirektion = inObersterDirektion;
	}

	public boolean isInObersterDirektion() {
		return inObersterDirektion;
	}

	public void setVerantwortung(boolean hatVerantwortung) {
		this.hatVerantwortung = hatVerantwortung;
	}

	public boolean isVerantwortung() {
		return hatVerantwortung;
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
	 * @param erwerbsTage the erwerbsTage to set
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