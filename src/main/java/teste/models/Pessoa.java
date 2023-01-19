package teste.models;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pessoa {
	private String nome;
	private LocalDate dtNascimento;
}
