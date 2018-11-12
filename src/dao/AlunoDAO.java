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
			System.out.println("entrou no metodo de cadastrar");
			stmt.setString(1, aluno.getNome());
			stmt.setString(2, aluno.getCpf());
			java.sql.Date d = new java.sql.Date(aluno.getDataNascimento().getTime());
			stmt.setDate(3, d);
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
		System.out.println("passou do metodo");
	}
	
	public void cadastrarAluno(Aluno aluno) {
		System.out.println("cadastrar aluno");
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
			
		} catch (SQLException e) {
			System.out.println("Erro ao Cadastrar");
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}	
	}
	
	public void editarPessoa(Aluno aluno) {
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
			
			stmt.setString(1, aluno.getNome());
			stmt.setString(2, aluno.getEndereco());
			stmt.setString(3, aluno.getEmail());
			stmt.setString(4, aluno.getTelefone());
			java.sql.Date d = new java.sql.Date(aluno.getDataNascimento().getTime());
			stmt.setDate(5, d);
			stmt.setString(6, aluno.getSexo());
			stmt.setString(7, aluno.getCpf());
			System.out.println("cpf" + aluno.getCpf());
	
			stmt.execute();
			conn.commit();

			editarAluno(aluno);
		
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.out.println("Erro no método AlterarAluno - rollback");
				}
			}
			System.out.println("Erro no método alterarAluno");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
	}	
	
	public void editarAluno(Aluno aluno) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);
			StringBuffer sql = new StringBuffer();
			
			sql.append("UPDATE aluno SET curso = ?");
			sql.append("WHERE fk_cpf = ?;");
			
			stmt = conn.prepareStatement(sql.toString());
			
			stmt.setString(1, aluno.getCurso());
			stmt.setString(2, aluno.getCpf());
		
			stmt.execute();
			conn.commit();
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.out.println("Erro no método AlterarAluno - rollback");
				}
			}
			System.out.println("Erro no método alterarAluno");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
	}	
	
	public List<Aluno> listarAluno() {
		List<Aluno> listaAluno = new ArrayList<Aluno>();
		
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = db.obterConexao();

			String sql = "select a.numero_matricula, a.fk_cpf, a.curso, p.nome, p.endereco, p.email, p.telefone,p.data_nascimento,p.sexo from aluno a inner join pessoa p on p.cpf = a.fk_cpf ";
			
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setNumero_matricula(rs.getInt("numero_matricula"));
				aluno.setNome(rs.getString("nome"));
				aluno.setCpf(rs.getString("fk_cpf"));
				aluno.setCurso(rs.getString("curso"));
				aluno.setEmail(rs.getString("email"));
				aluno.setSexo(rs.getString("sexo"));
				aluno.setEndereco(rs.getString("endereco"));
				aluno.setTelefone(rs.getString("telefone"));
				aluno.setDataNascimento(rs.getDate("data_nascimento"));
				
				listaAluno.add(aluno);
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
	
	public void excluirPessoa(String cpf) {

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
			System.out.println("Erro no método ");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
		
	}

	public void excluirAluno(Aluno aluno) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);

			StringBuffer sql = new StringBuffer();
			sql.append("DELETE from aluno ");
			sql.append("WHERE fk_cpf = ?;");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, aluno.getCpf());
			
			stmt.execute();
			conn.commit();
			
			excluirPessoa(aluno.getCpf());
			
		} catch (SQLException e) {
			System.out.println("Erro no método excluir aluno ");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
	}
}