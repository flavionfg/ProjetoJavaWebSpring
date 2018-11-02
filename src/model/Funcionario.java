package model;

import java.util.ArrayList;

public class Funcionario extends Pessoa{
	
	// pode colocar atributos de funcionario la na classe do professor
	
	int codCadastro;
	String cargo;
	double salario;
	double valeAlimentacao;
	double valeRefeicao;
	double valeTransporte;
	ArrayList<String> filhos = new ArrayList<String>();
	private String disciplina;
	
	
	public ArrayList<String> getFilhos() {
		return filhos;
	}
	public void setFilhos(ArrayList<String> filhos) {
		this.filhos = filhos;
	}
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
	public double getValeAlimentacao() {
		return valeAlimentacao;
	}
	public void setValeAlimentacao(double valeAlimentacao) {
		this.valeAlimentacao = valeAlimentacao;
	}
	public double getValeRefeicao() {
		return valeRefeicao;
	}
	public void setValeRefeicao(double valeRefeicao) {
		this.valeRefeicao = valeRefeicao;
	}
	public double getValeTransporte() {
		return valeTransporte;
	}
	public void setValeTransporte(double valeTransporte) {
		this.valeTransporte = valeTransporte;
	}
	public String getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}



}
