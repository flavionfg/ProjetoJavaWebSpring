package model;

import java.util.Date;

import javax.validation.constraints.Size;

import controller.Util;

public class Pessoa {

	private String nome;
	private String cpf;
	private Date dataNascimento;
	private String dataNascimentoStr;
	private String endereco;
	private String sexo;
	private String telefone;
	private String email;
	
	
	public Pessoa(){
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
//////////////////////
	public Date getDataNascimento() {

		Util util = new Util();
		if (getDataNascimentoStr() != null) {
			dataNascimento = util.transformaData(getDataNascimentoStr());
			System.out.println("o que tem no dataNascimento : " + dataNascimento);
			return dataNascimento;
		} else {
			return dataNascimento;
		}
	}
	//////////////////////
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDataNascimentoStr() {
		return dataNascimentoStr;
	}

	public void setDataNascimentoStr(String dataNascimentoStr) {
		this.dataNascimentoStr = dataNascimentoStr;
	}

}
