package br.com.magna.exception;


public class PibException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public PibException(String mensagem) {
		super(mensagem);
    }

    public PibException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
