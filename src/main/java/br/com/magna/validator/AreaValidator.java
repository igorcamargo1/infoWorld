package br.com.magna.validator;

import br.com.magna.dto.GeografiaDto;
import br.com.magna.exception.AreaException;

public class AreaValidator {

	public static void validate(GeografiaDto dto) {
		if(dto.getArea()<=0) {
			throw new AreaException("A área não pode ser menor ou igual a 0.");
		}
	}
}
