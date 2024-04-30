package br.com.magna.exception;

public class IdiomaException extends RuntimeException{
    
    private static final long serialVersionUID = 1L;

    public IdiomaException (String mensagem) {
        super(mensagem);
    }
    
    public IdiomaException (String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}