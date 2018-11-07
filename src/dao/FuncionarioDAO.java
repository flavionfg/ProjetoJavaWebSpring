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

import model.Funcionario;

public class FuncionarioDAO {
	
	private BancoDados db = null;
	
	public FuncionarioDAO() {
		try {
			db = new BancoDados("curso_java");
		} catch (NamingException e) {
			System.out.println("Erro ao instanciar o Banco de Dados: " + e);
		}
	}
	
	
	public void cadastrarPessoaNoBanco(Funcionario funcionario) {
		
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
			
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getCpf());
			java.sql.Date d = new java.sql.Date(funcionario.getDataNascimento().getTime());
			stmt.setDate(3, d); // NAO ESTA GRAVANDO O CAMPO DE DATA NASCIMENTO NO BANCO!!!!
			stmt.setString(4, funcionario.getEndereco());
			stmt.setString(5, funcionario.getSexo());
			stmt.setString(6, funcionario.getTelefone());
			stmt.setString(7, funcionario.getEmail());
			stmt.execute();
			conn.commit();
			
			cadastrarFuncionario(funcionario);
			
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
	
	
	
	
	public void cadastrarFuncionario(Funcionario funcionario) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);

			StringBuffer sql = new StringBuffer();
			
			sql.append("INSERT INTO funcionario(cargo,salario,vale_alimentacao,vale_refeicao,vale_trasnporte)"); //aqui ele pega uma primary key de funcionario
			sql.append("VALUES(?,?,?,?,?)");

			stmt = conn.prepareStatement(sql.toString());

			stmt.setString(1, funcionario.getCargo());
			stmt.setDouble(2, funcionario.getSalario());
			stmt.setDouble(3, funcionario.getValeAlimentacao());
			stmt.setDouble(4, funcionario.getValeRefeicao());
			stmt.setDouble(5, funcionario.getValeTransporte());
			stmt.execute();
			conn.commit();
			
			JOptionPane.showMessageDialog(null, "Sucesso ao cadastrar funcionario");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar.");	
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
		
//		System.out.println("Professor " + professor.getNome() + " da disciplina " + professor.getDisciplina() + " cadastrado com sucesso!");
	}
	
//	public List<Funcionario> listarFuncionario() {
//		List<Funcionario> listarFuncionario = new ArrayList<Funcionario>();
//		
//		Connection conn = null;
//		Statement stmt = null;
//
//		try {
//			conn = db.obterConexao();
//
//			String sql = "select cargo,salario,vale_alimentacao,vale_refeicao,vale_trasnporte " + "from funcinario ";
//
//			
//			stmt = conn.createStatement();
//
//			ResultSet rs = stmt.executeQuery(sql);
//
//			while (rs.next()) {
//				Funcionario funcionario = new Funcionario();
//				String cargo = rs.getString(cargo);
//				funcionario.setCargo(cargo);
//				double salario = rs.getDouble(salario); //pq o metodo getdouble nao recebe double???????????????
//				funcionario.setSalario(salario);
//				
//				double vale_alimentacao = rs.getDouble(vale_alimentacao); 
//				funcionario.setValeAlimentacao(vale_alimentacao);
//				
//				double vale_refeicao = rs.getDouble(vale_refeicao);
//				funcionario.setValeRefeicao(valeRefeicao);
//				
//				double vale_trasnporte = rs.getDouble(vale_trasnporte);
//				funcionario.setValeTransporte(valeTransporte);
//				
//				listarFuncionario.add(funcionario);
//
//			}
//
//		} catch (SQLException e) {
//			System.out.println("Erro ao listar funcionario");
//			e.printStackTrace();
//		}
//		
//		return listaProfessor;
//	}
//	
	

	public void alterarFuncionario(Funcionario funcionario) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);

			StringBuffer sql = new StringBuffer();
			
			
			
			sql.append("UPDATE funcionario SET cargo = ?,salario = ?,vale_alimentacao = ?,vale_refeicao = ?,vale_trasnporte = ?");
			sql.append("WHERE cod_cadastro = ?;");

			stmt = conn.prepareStatement(sql.toString());
			
			stmt.setString(1, funcionario.getCargo()); // ta certo?
	
			stmt.execute();
			conn.commit();
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.out.println("Erro no método alterarFuncionario - rollback");
				}
			}
			System.out.println("Erro no método alterarFuncionario");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
	}	
}	
