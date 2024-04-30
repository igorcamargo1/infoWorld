package br.com.magna.exception;

public class GovernoException extends RuntimeException{
    
    private static final long serialVersionUID = 1L;

    public GovernoException (String mensagem) {
        super(mensagem);
    }
    
    public GovernoException (String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}