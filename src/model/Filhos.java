package model;

import java.util.Date;
import controller.Util;

public class Filhos {
	
	private int filho_id;
	private String nome;
	private Date data_nascimento;
	private String data_nascimentoStr;
	private int fk_cod_cadastro;
	
	public int getFilho_id() {
		return filho_id;
	}
	public void setFilho_id(int filho_id) {
		this.filho_id = filho_id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getData_nascimento() {
		
		// retornar um DATA DO JAVA
		
		Util util = new Util();
		if (getData_nascimentoStr() != null) {
			data_nascimento = util.transformaData(getData_nascimentoStr());
			return data_nascimento;
		} else {
			return data_nascimento;
		}
	}
	public void setData_nascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
	public String getData_nascimentoStr() {
		return data_nascimentoStr;
	}
	public void setData_nascimentoStr(String data_nascimentoStr) {
		this.data_nascimentoStr = data_nascimentoStr;
	}
	public int getFk_cod_cadastro() {
		return fk_cod_cadastro;
	}
	public void setFk_cod_cadastro(int fk_cod_cadastro) {
		this.fk_cod_cadastro = fk_cod_cadastro;
	}



}