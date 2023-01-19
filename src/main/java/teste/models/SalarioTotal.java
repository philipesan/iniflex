package teste.models;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalarioTotal {
	private BigDecimal salarioTotal;

	public SalarioTotal(BigDecimal salarioTotal) {
		super();
		this.salarioTotal = salarioTotal;
	}
	
	public void soma(BigDecimal valor) {
		this.salarioTotal = this.salarioTotal.add(valor);
	}
}
