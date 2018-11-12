package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
			stmt.setDate(3, d);
			stmt.setString(4, professor.getEndereco());
			stmt.setString(5, professor.getSexo());
			stmt.setString(6, professor.getTelefone());
			stmt.setString(7, professor.getEmail());
			stmt.execute();
			conn.commit();
			
			cadastrarprofessor(professor);
			
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
		System.out.println("passou do metodo");
	}
	
	public void cadastrarprofessor(Professor professor) {
		System.out.println("cadastrar professor");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);

			StringBuffer sql = new StringBuffer();
			
			sql.append("INSERT INTO professor(fk_cod_cadastro,disciplina)");
			sql.append("VALUES(?,?)");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, professor.getCpf());
			stmt.setString(2, professor.getDisciplina());
			stmt.execute();
			conn.commit();
			
		} catch (SQLException e) {
			System.out.println("Erro ao Cadastrar um professor");
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}	
	}
	
	public void editarPessoa(Professor professor) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE pessoa SET nome = ?,endereco = ?,email = ?,telefone = ?,data_nascimento = ?,sexo = ? ");
			sql.append("WHERE cpf = ?;");

			
			stmt = conn.prepareStatement(sql.toString());
			
			stmt.setString(1, professor.getNome());
			stmt.setString(2, professor.getEndereco());
			stmt.setString(3, professor.getEmail());
			stmt.setString(4, professor.getTelefone());
			java.sql.Date d = new java.sql.Date(professor.getDataNascimento().getTime());
			stmt.setDate(5, d);
			stmt.setString(6, professor.getSexo());
			stmt.setString(7, professor.getCpf());
			
	
			stmt.execute();
			conn.commit();

			editarprofessor(professor);
		
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.out.println("Erro no método Alterarprofessor - rollback");
				}
			}
			System.out.println("Erro no método alterarprofessor");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
	}	
	
	public void editarprofessor(Professor professor) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);
			StringBuffer sql = new StringBuffer();
			
			sql.append("UPDATE professor SET disciplina = ?");
			sql.append("WHERE fk_cod_cadastro = ?;");
			
			stmt = conn.prepareStatement(sql.toString());
			
			stmt.setString(1, professor.getDisciplina());
			stmt.setString(2, professor.getCpf());
		
			stmt.execute();
			conn.commit();
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.out.println("Erro no método Alterarprofessor - rollback");
				}
			}
			System.out.println("Erro no método alterarprofessor");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
	}	
	
	public List<Professor> listarprofessor() {
		List<Professor> listaprofessor = new ArrayList<Professor>();
		
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = db.obterConexao();

			String sql = "select a.numero_matricula, a.fk_cpf, a.curso, p.nome, p.endereco, p.email, p.telefone,p.data_nascimento,p.sexo from professor a inner join pessoa p on p.cpf = a.fk_cpf ";
			// ver essa qury aqui
			//
			//
			//
			//
			//
			//
			
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Professor professor = new Professor();
			//  nao tem codigo de cadastro ainda
				
				professor.setNome(rs.getString("nome"));
				professor.setCpf(rs.getString("fk_cpf"));
				professor.setDisciplina(rs.getString("disciplina"));
				professor.setEmail(rs.getString("email"));
				professor.setSexo(rs.getString("sexo"));
				professor.setEndereco(rs.getString("endereco"));
				professor.setTelefone(rs.getString("telefone"));
				professor.setDataNascimento(rs.getDate("data_nascimento"));
				
				listaprofessor.add(professor);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao listar professor");
			e.printStackTrace();
		}
		
		return listaprofessor;
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
	
	public void excluirPessoa(String cpf) {
		
		// ver oq vai chegar aqui

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);

			StringBuffer sql = new StringBuffer();
			sql.append("Delete From pessoa ");
			sql.append("WHERE cpf = ?;");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, cpf);
			stmt.execute();
			conn.commit();
		
			
		} catch (SQLException e) {
			System.out.println("Erro no método de excluir pessoa");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
		
	}

	public void excluirprofessor(Professor professor) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);

			StringBuffer sql = new StringBuffer();
			sql.append("DELETE from professor ");
			sql.append("WHERE fk_cod_cadastro = ?;");
			stmt = conn.prepareStatement(sql.toString());
//			stmt.setString(1, professor.getCpf());
			// arruamar esse stmt , ver como vai ligar as tabelas
			
			stmt.execute();
			conn.commit();
			
			excluirPessoa(professor.getCpf());
			
		} catch (SQLException e) {
			System.out.println("Erro no método excluir professor ");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
	}
}