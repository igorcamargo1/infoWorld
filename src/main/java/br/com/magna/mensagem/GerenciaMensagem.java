package br.com.magna.mensagem;

import java.util.logging.Logger;

public class GerenciaMensagem {
	// Inicializa o logger para a classe atual
    private static final Logger LOGGER = Logger.getLogger(GerenciaMensagem.class.getName());

    public void logInfo(String mensagem) {
        LOGGER.info(mensagem);
    }

    public void logWarning(String mensagem) {
        LOGGER.warning(mensagem);
    }

    public void logDebug(String mensagem) {
        LOGGER.finest(mensagem);
    }
}
