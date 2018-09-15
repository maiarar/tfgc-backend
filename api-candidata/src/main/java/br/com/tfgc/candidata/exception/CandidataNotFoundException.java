package br.com.tfgc.candidata.exception;

public class CandidataNotFoundException extends Exception {

    private static final long serialVersionUID = 700731374149270622L;

    public CandidataNotFoundException(String message, Throwable reason) {
        super(message, reason);
    }

    public CandidataNotFoundException(String message) {
        super(message);
    }

    public CandidataNotFoundException(Throwable reason) {
        super(reason);
    }
}
