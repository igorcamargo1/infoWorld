package br.com.magna.exception;

public class ContinenteException extends RuntimeException{
    
    private static final long serialVersionUID = 1L;

    public ContinenteException (String mensagem) {
        super(mensagem);
    }
    
    public ContinenteException (String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}