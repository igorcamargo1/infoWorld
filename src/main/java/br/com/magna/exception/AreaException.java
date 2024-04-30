package br.com.magna.exception;

public class AreaException extends RuntimeException {


	private static final long serialVersionUID = 1L;

	public AreaException(String mensagem) {
		super(mensagem);
    }

    public AreaException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
