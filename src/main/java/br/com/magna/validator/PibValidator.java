package br.com.magna.validator;

import br.com.magna.dto.EconomiaDto;
import br.com.magna.exception.PibException;

public class PibValidator {

	public static void validate(EconomiaDto dto) {
		if(dto.getPib()<=0) {
			throw new PibException("O PIB não pode ser menor ou igual a 0.");
		}
	}
}
