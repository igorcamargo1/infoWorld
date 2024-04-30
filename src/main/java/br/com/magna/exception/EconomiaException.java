package br.com.magna.exception;

public class EconomiaException extends RuntimeException{
    
    private static final long serialVersionUID = 1L;

    public EconomiaException (String mensagem) {
        super(mensagem);
    }
    
    public EconomiaException (String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}