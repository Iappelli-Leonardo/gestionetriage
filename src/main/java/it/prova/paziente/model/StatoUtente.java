package it.prova.paziente.model;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum StatoUtente {
	ATTIVO("Attivo"), DISABILITATO("Disabilitato"), CREATO("Creato");
	
	private String abbreviazione;

	StatoUtente(String abbreviazione) {
		this.abbreviazione = abbreviazione;
	}

	public String getAbbreviazione() {
		return abbreviazione;
	}

	@JsonCreator
	public static StatoPaziente getStatoFromCode(String input) {
		if(StringUtils.isBlank(input))
			return null;
		
		for (StatoPaziente statoItem : StatoPaziente.values()) {
			if (statoItem.equals(StatoPaziente.valueOf(input))) {
				return statoItem;
			}
		}
		return null;
	}
}
