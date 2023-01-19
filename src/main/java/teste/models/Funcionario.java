package teste.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Funcionario extends Pessoa{
	public BigDecimal salario;
	public String funcao;
	
	public Funcionario(String nome, LocalDate dtNascimento,
					   BigDecimal salario, String funcao) {
		super();
		
		this.setNome(nome);
		this.setDtNascimento(dtNascimento);
		this.salario = salario;
		this.funcao = funcao;
	}

	public Funcionario() {
		// TODO Auto-generated constructor stub
	}
	
	
}
