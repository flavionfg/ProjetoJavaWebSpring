package model;
import java.util.Date;

import controller.Util;

public class Funcionario extends Pessoa{

	int codCadastro;
	String cargo;
	double salario;
	String valeAlimentacao;
	String valeRefeicao;
	String valeTransporte;
	String filho_nome;
	private Date filho_dataNascimento;
	private String filho_dataNascimentoStr;
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

	public String getFilho_nome() {
		return filho_nome;
	}
	public void setFilho_nome(String filho_nome) {
		this.filho_nome = filho_nome;
	}
	public Date getFilho_dataNascimento() {
		Util util = new Util();
		if (getDataNascimentoStr() != null) {
			filho_dataNascimento = util.transformaData(getFilho_dataNascimentoStr());
			return filho_dataNascimento;
		} else {
			return filho_dataNascimento;
		}
	}
	public void setFilho_dataNascimento(Date filho_dataNascimento) {
		this.filho_dataNascimento = filho_dataNascimento;
	}
	public String getFilho_dataNascimentoStr() {
		return filho_dataNascimentoStr;
	}
	public void setFilho_dataNascimentoStr(String filho_dataNascimentoStr) {
		this.filho_dataNascimentoStr = filho_dataNascimentoStr;
	}
	public String getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
	
	
	



}
