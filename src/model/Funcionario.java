package model;

import java.util.ArrayList;

public class Funcionario extends Pessoa{
	
	// pode colocar atributos de funcionario la na classe do professor
	
	int codCadastro;
	String cargo;
	double salario;
	String valeAlimentacao;
	String valeRefeicao;
	String valeTransporte;
	ArrayList<String> filhos = new ArrayList<String>();
	private String disciplina;
	
	public int getCodCadastro() {
		return codCadastro;
	}
	public void setCodCadastro(int codCadastro) {
		this.codCadastro = codCadastro;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public String getValeAlimentacao() {
		return valeAlimentacao;
	}
	public void setValeAlimentacao(String valeAlimentacao) {
		this.valeAlimentacao = valeAlimentacao;
	}
	public String getValeRefeicao() {
		return valeRefeicao;
	}
	public void setValeRefeicao(String valeRefeicao) {
		this.valeRefeicao = valeRefeicao;
	}
	public String getValeTransporte() {
		return valeTransporte;
	}
	public void setValeTransporte(String valeTransporte) {
		this.valeTransporte = valeTransporte;
	}
	public ArrayList<String> getFilhos() {
		return filhos;
	}
	public void setFilhos(ArrayList<String> filhos) {
		this.filhos = filhos;
	}
	public String getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
	
	
	



}
