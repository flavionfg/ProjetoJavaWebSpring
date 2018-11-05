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

import model.Aluno;
import model.Professor;

public class AlunoDAO {
	
	private BancoDados db = null;
	
	public AlunoDAO() {
		try {
			db = new BancoDados("curso_java");
		} catch (NamingException e) {
			System.out.println("Erro ao instanciar o Banco de Dados: " + e);
		}
	}
	
	
	public void cadastrarPessoaNoBanco(Aluno aluno) {
		
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
			
			stmt.setString(1, aluno.getNome());
			stmt.setString(2, aluno.getCpf());
			java.sql.Date d = new java.sql.Date(aluno.getDataNascimento().getTime());
			stmt.setDate(3, d); // NAO ESTA GRAVANDO O CAMPO DE DATA NASCIMENTO NO BANCO!!!!
			stmt.setString(4, aluno.getEndereco());
			stmt.setString(5, aluno.getSexo());
			stmt.setString(6, aluno.getTelefone());
			stmt.setString(7, aluno.getEmail());
			stmt.execute();
			conn.commit();
			
			cadastrarAluno(aluno);
			
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
	
	
	
	
	public void cadastrarAluno(Aluno aluno) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);

			StringBuffer sql = new StringBuffer();
			
			sql.append("INSERT INTO aluno(fk_cpf,curso)");
			sql.append("VALUES(?,?)");

			stmt = conn.prepareStatement(sql.toString());

			stmt.setString(1, aluno.getCpf());
			stmt.setString(2, aluno.getCurso());

			stmt.execute();
			conn.commit();
			
			JOptionPane.showMessageDialog(null, "Sucesso ao cadastrar aluno");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar.");	
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
		
//		System.out.println("Professor " + professor.getNome() + " da disciplina " + professor.getDisciplina() + " cadastrado com sucesso!");
	}
	
	public List<Aluno> listarAluno() {
		List<Aluno> listaAluno = new ArrayList<Aluno>();
		
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = db.obterConexao();

			String sql = "select fk_cpf, curso " + "from aluno ";

			
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Aluno aluno = new Aluno();
				String cpf = rs.getString("fk_cpf");
				aluno.setCpf(cpf);
				String curso = rs.getString("curso");
				aluno.setCurso(curso);
				listaAluno.add(aluno);
				// listaAluno - CONTEM O RETORNO DO SELECT NO BANCO!
			}

		} catch (SQLException e) {
			System.out.println("Erro ao listar aluno");
			e.printStackTrace();
		}
		
		return listaAluno;
	}
	
	
	public List<Professor> consultarListaProfessor() {

		List<Professor> listaProfessor = new ArrayList<Professor>();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();

			String sql = "SELECT  "
					+ "FROM  "
					+ "ORDER BY nivel ASC";

			stmt = conn.prepareStatement(sql.toString());

			rs = stmt.executeQuery();

			while (rs.next()) {
				Professor professor = new Professor();
				listaProfessor.add(professor);
			}

		} catch (SQLException e) {
			System.out.println("Erro no método consultarListaProfessor");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
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
			
			sql.append("UPDATE nome_tabela SET nome_campo = ? ");
			sql.append("WHERE nome_campo_id = ?;");

			stmt = conn.prepareStatement(sql.toString());
			
			//stmt.setInt(1, professor.getId());

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