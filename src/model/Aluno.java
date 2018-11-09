package model;

public class Aluno extends Pessoa{
	
	String curso;
	int numero_matricula;
	
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public int getNumero_matricula() {
		return numero_matricula;
	}
	public void setNumero_matricula(int numero_matricula) {
		this.numero_matricula = numero_matricula;
	}
	
}