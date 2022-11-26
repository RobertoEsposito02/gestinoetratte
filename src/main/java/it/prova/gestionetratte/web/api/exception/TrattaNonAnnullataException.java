package it.prova.gestionetratte.web.api.exception;

public class TrattaNonAnnullataException extends RuntimeException{
	public TrattaNonAnnullataException(String message) {
		super(message);
	}
}
