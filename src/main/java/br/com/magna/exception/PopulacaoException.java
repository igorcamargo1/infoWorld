package br.com.magna.exception;

public class PopulacaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PopulacaoException(String mensagem) {
		super(mensagem);
    }

    public PopulacaoException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
