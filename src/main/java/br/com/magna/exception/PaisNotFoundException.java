package br.com.magna.exception;

public class PaisNotFoundException extends RuntimeException{
    
    private static final long serialVersionUID = 1L;

    public PaisNotFoundException (String mensagem) {
        super(mensagem);
    }
    
    public PaisNotFoundException (String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}