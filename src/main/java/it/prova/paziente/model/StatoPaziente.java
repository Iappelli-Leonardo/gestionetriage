package it.prova.paziente.model;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum StatoPaziente {
IN_ATTESA_VISITA("In Attesa Visita"), IN_VISITA("In Visita"), RICOVERATO("Ricoverato"), DISMESSO("Dismesso");
	
	private String abbreviazione;

	StatoPaziente(String abbreviazione) {
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

