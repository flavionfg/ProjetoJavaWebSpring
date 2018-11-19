package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.swing.JOptionPane;

import model.Aluno;
import model.Filhos;

public class FilhosDAO {
	
	
	private BancoDados db = null;
	
	public FilhosDAO() {
		try {
			db = new BancoDados("curso_java");
		} catch (NamingException e) {
			System.out.println("Erro ao instanciar o Banco de Dados: " + e);
		}
	}
	
	public void cadastrarFilhos(Filhos filho) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);
			
			StringBuffer sql = new StringBuffer();
			
			sql.append("INSERT INTO filhos(nome,data_nascimento,fk_cod_cadastro)");
			sql.append("VALUES(?,?,?,)");
			
			stmt = conn.prepareStatement(sql.toString());
		
			
			//acabar
			
			
//			stmt.setString(1, filhos.getNome());
//			stmt.setString(2, aluno.getCpf());
//			java.sql.Date d = new java.sql.Date(aluno.getDataNascimento().getTime());
//			stmt.setDate(3, d);
//			stmt.setString(4, aluno.getEndereco());
//			stmt.setString(5, aluno.getSexo());
//			stmt.setString(6, aluno.getTelefone());
//			stmt.setString(7, aluno.getEmail());
//			stmt.execute();
//			conn.commit();
//			
//			cadastrarAluno(aluno);
//			
		} catch (SQLException e) {
			String error = e.getMessage();
			e.printStackTrace();	
			System.out.println(e);
//			if (error.matches("(.*)Duplicate entry(.*)")) {
//				JOptionPane.showMessageDialog(null, "Cpf já existente.");	
//			} else {
//				JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
//			}
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
		System.out.println("passou do metodo");
	}

}
