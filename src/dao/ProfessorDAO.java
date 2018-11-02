package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.swing.JOptionPane;

import model.Professor;

public class ProfessorDAO {
	
	private BancoDados db = null;
	
	public ProfessorDAO() {
		try {
			db = new BancoDados("curso_java");
		} catch (NamingException e) {
			System.out.println("Erro ao instanciar o Banco de Dados: " + e);
		}
	}
	
	
	public void cadastrarPessoaNoBanco(Professor professor) {
		
		System.out.println("teste git");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);

			StringBuffer sql = new StringBuffer();
			
			sql.append("INSERT INTO pessoa(nome,cpf,data_nascimento,endereco,sexo,telefone,email)");
			sql.append("VALUES(?,?,?,?,?,?,?)");
			
			stmt = conn.prepareStatement(sql.toString());
			
			stmt.setString(1, professor.getNome());
			stmt.setString(2, professor.getCpf());
			java.sql.Date d = new java.sql.Date(professor.getDataNascimento().getTime());
			stmt.setDate(3, d); // NAO ESTA GRAVANDO O CAMPO DE DATA NASCIMENTO NO BANCO!!!!
			stmt.setString(4, professor.getEndereco());
			stmt.setString(5, professor.getSexo());
			stmt.setString(6, professor.getTelefone());
			stmt.setString(7, professor.getEmail());
			stmt.execute();
			conn.commit();
			
			cadastrarProfessor(professor);
			
		} catch (SQLException e) {
			String error = e.getMessage();
			e.printStackTrace();	
			System.out.println(e);
			if (error.matches("(.*)Duplicate entry(.*)")) {
				JOptionPane.showMessageDialog(null, "Cpf já existente.");	
			} else {
				JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
			}
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
		
//		System.out.println("Aluno " + pessoa.getNome() + "CPF  " + pessoa.getCpf() + "Data de Nascimento :" + pessoa.getDataNascimento() + "Endereço :" + pessoa.getEndereco() + "Sexo" + pessoa.getSexo() + "Telefone : " + pessoa.getTelefone() + "Email : "+ pessoa.getEmail() + "cadastrado com sucesso!");
	}
	
	
	
	
	public void cadastrarProfessor(Professor professor) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);

			StringBuffer sql = new StringBuffer();
			
			sql.append("INSERT INTO professor(disciplina)"); //aqui ele pega uma primary key de funcionario
			sql.append("VALUES(?)");

			stmt = conn.prepareStatement(sql.toString());

			stmt.setString(1, professor.getDisciplina());

			stmt.execute();
			conn.commit();
			
			JOptionPane.showMessageDialog(null, "Sucesso ao cadastrar profesor");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar.");	
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
		
//		System.out.println("Professor " + professor.getNome() + " da disciplina " + professor.getDisciplina() + " cadastrado com sucesso!");
	}
	
	public List<Professor> listarProfessor() {
		List<Professor> listaProfessor = new ArrayList<Professor>();
		
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = db.obterConexao();

			String sql = "select fk_cod_cadastro, disciplina " + "from professor ";

			
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Professor professor = new Professor();
				String disciplina = rs.getString("disciplina");
				professor.setDisciplina(disciplina);
				listaProfessor.add(professor);
				// listaAluno - CONTEM O RETORNO DO SELECT NO BANCO!
			}

		} catch (SQLException e) {
			System.out.println("Erro ao listar Professor");
			e.printStackTrace();
		}
		
		return listaProfessor;
	}
	
	

	public void alterarProfessor(Professor professor) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);

			StringBuffer sql = new StringBuffer();
			
			sql.append("UPDATE professor SET disciplina = ? ");
			sql.append("WHERE fk_cod_cadastro = ?;");

			stmt = conn.prepareStatement(sql.toString());
			
			stmt.setString(1, professor.getDisciplina()); // ta certo?
	
			stmt.execute();
			conn.commit();
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.out.println("Erro no método alterarProfessor - rollback");
				}
			}
			System.out.println("Erro no método alterarProfessor");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
	}	
}	
