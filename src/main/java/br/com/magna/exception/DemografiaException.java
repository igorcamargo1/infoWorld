package br.com.magna.exception;

public class DemografiaException extends RuntimeException{
    
    private static final long serialVersionUID = 1L;

    public DemografiaException (String mensagem) {
        super(mensagem);
    }
    
    public DemografiaException (String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}