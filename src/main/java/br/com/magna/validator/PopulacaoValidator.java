package br.com.magna.validator;

import br.com.magna.dto.EconomiaDto;
import br.com.magna.dto.GeografiaDto;
import br.com.magna.exception.PibException;
import br.com.magna.exception.PopulacaoException;

public class PopulacaoValidator {

	public static void validate(GeografiaDto dto) {
		if(dto.getPopulacao()<=0) {
			throw new PopulacaoException("A população não pode ser menor ou igual a 0.");
		}
	}
}
