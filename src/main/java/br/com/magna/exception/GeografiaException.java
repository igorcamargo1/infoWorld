package br.com.magna.exception;

public class GeografiaException extends RuntimeException{
    
    private static final long serialVersionUID = 1L;

    public GeografiaException (String mensagem) {
        super(mensagem);
    }
    
    public GeografiaException (String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}