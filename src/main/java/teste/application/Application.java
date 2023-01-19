package teste.application;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import teste.models.Funcionario;
import teste.models.SalarioTotal;

public class Application {

	public static void main(String[] args) {
		
		final double SALARIO_MINIMO = 1212.00;
		SalarioTotal somaSalarios = new SalarioTotal(new BigDecimal(0L));
		ArrayList<Funcionario> funcionarios = geraTabela();
		ArrayList<Funcionario> aniversariantes =  new ArrayList<>();
		Funcionario funcionarioMaisVelho = new Funcionario();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		NumberFormat formatoNumero = NumberFormat.getInstance(new Locale("pt_BR"));
		HashMap<String, ArrayList<Funcionario>> funcaoFuncionario = new HashMap<>();
		
		funcionarios = removeUsuarios(funcionarios);
		System.out.println("==Print das Tarefas 3.3 e 3.12==");
		//Tarefas 3.4 e 3.11 - Calcula o aumento e soma os Salários
		funcionarios.forEach(funcionario -> {
			int mesAniversario = funcionario.getDtNascimento().getMonthValue();
			funcionario.setSalario(funcionario.getSalario().multiply(BigDecimal.valueOf(1.10)));
			somaSalarios.soma(funcionario.getSalario());
			
			//Aproveita o Loop para pegar o funcionário mais velho
			if(funcionarioMaisVelho.getDtNascimento() == null || 
			   funcionarioMaisVelho.getDtNascimento()
			   					   .isAfter(funcionario.getDtNascimento())) {
				
				funcionarioMaisVelho.setNome(funcionario.getNome());
				funcionarioMaisVelho.setDtNascimento(funcionario.getDtNascimento());
			}
			
			//3.5 Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
			if(funcaoFuncionario.containsKey(funcionario.getFuncao())) {
				funcaoFuncionario.computeIfAbsent(funcionario.getFuncao(),
				k -> new ArrayList<>()).add(funcionario);
			} else {
				funcaoFuncionario.put(funcionario.getFuncao(), new ArrayList<Funcionario>(List.of(funcionario)));
			}
			
			//Aproveita para ver o mes do aniversário
			if(mesAniversario == 10 || mesAniversario == 12) {
				aniversariantes.add(funcionario);
			}
			
			//3.3 Imprime a lista formatada
			System.out.println("Funcionario: " + funcionario.getNome() + 
					" - Data de Nascimento: " + funcionario.getDtNascimento().format(formato) +
					" - Salario: " + formatoNumero.format(funcionario.getSalario()) +
					" - Funçao: " + funcionario.getFuncao() +
					" - Renda em Salarios Minimos: " + funcionario.getSalario().divide(BigDecimal.valueOf(SALARIO_MINIMO), 2, RoundingMode.HALF_UP));
			
		});
		
		imprimeMapa(funcaoFuncionario);
		imprimeAniversario(aniversariantes, formato);
		imprimeMaisVelho(funcionarioMaisVelho);
		imprimeSomaSalarios(somaSalarios, formatoNumero);
		imprimeEmOrdem(funcionarios);
		
	}
	
	//Tarefa 3.1 - Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
	private static ArrayList<Funcionario> geraTabela() {
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
		funcionarios.add(new Funcionario("Maria"	, LocalDate.of(2000, 10, 18), BigDecimal.valueOf(2009.44)	, "Operador"));
		funcionarios.add(new Funcionario("João"		, LocalDate.of(1990, 05, 12), BigDecimal.valueOf(2284.38)	, "Operador"));
		funcionarios.add(new Funcionario("Caio"		, LocalDate.of(1961, 05, 02), BigDecimal.valueOf(9836.14)	, "Coordenador"));
		funcionarios.add(new Funcionario("Miguel"	, LocalDate.of(1988, 10, 14), BigDecimal.valueOf(19119.88)	, "Diretor"));
		funcionarios.add(new Funcionario("Alice"	, LocalDate.of(1995, 01, 05), BigDecimal.valueOf(2234.68)	, "Recepcionista"));
		funcionarios.add(new Funcionario("Heitor"	, LocalDate.of(1999, 11, 19), BigDecimal.valueOf(1582.72)	, "Operador"));
		funcionarios.add(new Funcionario("Arthur"	, LocalDate.of(1993, 03, 31), BigDecimal.valueOf(4071.84)	, "Contador"));
		funcionarios.add(new Funcionario("Laura"	, LocalDate.of(1994, 07, 8)	, BigDecimal.valueOf(3017.45)	, "Gerente"));
		funcionarios.add(new Funcionario("Heloisa"	, LocalDate.of(2003, 05, 24), BigDecimal.valueOf(1606.85)	, "Eletricista"));
		funcionarios.add(new Funcionario("Helena"	, LocalDate.of(1996, 9, 02)	, BigDecimal.valueOf(2799.93)	, "Gerente"));

		return funcionarios;
	}
	
	//Tarefa 3.2 - Remover o funcionário “João” da lista.
	private static ArrayList<Funcionario> removeUsuarios(ArrayList<Funcionario> funcionarios) {
			funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));
		return funcionarios;
	}

	//3.6  Imprimir os funcionários, agrupados por função.
	private static void imprimeMapa(HashMap<String, ArrayList<Funcionario>> mapa) {
		System.out.println("-----------------------");
		System.out.println("==Print da Tarefa 3.6==");
		
		for(Map.Entry m: mapa.entrySet()) {
			System.out.println("Função: " + m.getKey());
			List<Funcionario> funcAux = (List<Funcionario>) m.getValue();
			for(Funcionario funcionario : funcAux) {
				System.out.println(" " + funcionario.getNome() + " ");
			}
		}
		System.out.println("-----------------------");
	}
	
	//3.8  Imprimir os funcionários nascidos no mes 10 e 12.
	private static void imprimeAniversario(ArrayList<Funcionario> aniversariantes, DateTimeFormatter formato) {
		System.out.println("==Print da Tarefa 3.8==");
		for(Funcionario funcionario: aniversariantes) {
			System.out.println("Nome: " + funcionario.getNome() + 
							  " Nascimento: "+funcionario.getDtNascimento().format(formato));
		}
		System.out.println("-----------------------");
	}
	
	private static void imprimeMaisVelho(Funcionario funcionario) {
		LocalDate hoje = LocalDate.now();
		System.out.println("==Print da Tarefa 3.9==");
		System.out.println("Seu funcionário mais velho é :" + funcionario.getNome() + 
				" com " + Period.between(funcionario.getDtNascimento(), hoje).getYears());
		System.out.println("-----------------------");
	}
	
	private static void imprimeSomaSalarios(SalarioTotal somaSalarios, NumberFormat formatoNumero) {
		System.out.println("==Print da Tarefa 3.11==");
		System.out.println("O total dos Salarios é: " + formatoNumero.format(somaSalarios.getSalarioTotal()));
		System.out.println("-----------------------");
	}
	
	private static void imprimeEmOrdem(ArrayList<Funcionario> funcionarios) {
		System.out.println("==Print da Tarefa 3.12==");
		funcionarios.sort(Comparator.comparing(Funcionario::getNome));
		funcionarios.forEach(funcionario -> 			
		System.out.println("Funcionario: " + funcionario.getNome()));
	}
}
