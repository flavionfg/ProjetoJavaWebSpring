package dao;

import java.sql.Array;
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
import model.Filhos;
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
			stmt.setDate(3, d); 
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
				System.out.println("Cpf já existente.");
			} else {
				System.out.println("Erro ao cadastrar.");
			}
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
	
	}
	
	public void cadastrarFuncionario(Funcionario funcionario) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);

			StringBuffer sql = new StringBuffer();
			
			sql.append("INSERT INTO funcionario(cargo,salario,vale_alimentacao,vale_refeicao,vale_trasnporte,fk_cpf)"); //aqui ele pega uma primary key de funcionario
			sql.append("VALUES(?,?,?,?,?,?)");

			stmt = conn.prepareStatement(sql.toString());

			stmt.setString(1, funcionario.getCargo());
			stmt.setDouble(2, funcionario.getSalario());
			stmt.setString(3, funcionario.getValeAlimentacao());
			stmt.setString(4, funcionario.getValeRefeicao());
			stmt.setString(5, funcionario.getValeTransporte());
			stmt.setString(6, funcionario.getFk_cpf());
			// não deveria setar no fk_cpf o cpf da pessoa?
			
			stmt.execute();
			conn.commit();
			
			if (!funcionario.getFilhos().isEmpty()) {
				
				String sqlLastInsert = "SELECT LAST_INSERT_ID()";
				
				db.finalizaObjetos(null, stmt, null);
				stmt = conn.prepareStatement(sqlLastInsert);
				rs = stmt.executeQuery();

				if (rs.next()) {
					
					funcionario.setCodCadastro(rs.getInt(1));
//					
//					programacaoDuplicataCotacaoVO.setId(rs.getInt(1));
//					registrarParcelasDuplicatasCotacao(programacaoDuplicataCotacaoVO);
					db.finalizaObjetos(null, stmt, null);
				}

				//selectLastIntid
				
				//for setando o id para cada fillho do array existente no funcionario
	
				cadastrarFilhos(funcionario);
			}
			
			
			System.out.println("Sucesso ao cadastrar um Funcionario.");
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar um Funcionario.");
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
		
	}
	
	
	public void cadastrarFilhos(Funcionario funcionario) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);

			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO filhos(nome,data_nascimento,fk_cod_cadastro)"); 
			sql.append("VALUES(?,?,?)");

			stmt = conn.prepareStatement(sql.toString());
			
			
			for(Filhos filho : funcionario.getFilhos()){
				stmt.setString(1, filho.getNome());
				
				// CONVERTENDO DE DATA JAVA PARA DATE SQL
				java.sql.Date dataSql = new java.sql.Date(filho.getData_nascimento().getTime());
				stmt.setDate(2, dataSql);
				
				
				stmt.setInt(3, funcionario.getCodCadastro());

				stmt.addBatch();
			}
		
			// como vou linkar o filho com o funcionario

			stmt.executeBatch();
			conn.commit();
			
			System.out.println("Sucesso ao cadastrar um Filho.");
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar um Filho.");
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
		
	}
	
	public void editarPessoa(Funcionario funcionario) {
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
			
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getEndereco());
			stmt.setString(3, funcionario.getEmail());
			stmt.setString(4, funcionario.getTelefone());
			java.sql.Date d = new java.sql.Date(funcionario.getDataNascimento().getTime());
			stmt.setDate(5, d);
			stmt.setString(6, funcionario.getSexo());
			stmt.setString(7, funcionario.getCpf());
		
	
			stmt.execute();
			conn.commit();

			alterarFuncionario(funcionario);
		
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.out.println("Erro no método Alterarfuncionario - rollback");
				}
			}
			System.out.println("Erro no método alterarfuncionario");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
	}	
	

	public void alterarFuncionario(Funcionario funcionario) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);

			StringBuffer sql = new StringBuffer();
			
			sql.append("UPDATE funcionario SET cargo = ?,salario = ?,vale_alimentacao = ?,vale_refeicao = ?,vale_transporte = ?,cpf = ?,filho_nome = ?,filho_dataNascimento");
			sql.append("WHERE cod_cadastro = ?;");

			stmt = conn.prepareStatement(sql.toString());
			
			stmt.setString(1, funcionario.getCargo()); 
			stmt.setDouble(2, funcionario.getSalario());
			stmt.setString(3, funcionario.getValeAlimentacao());
			stmt.setString(3, funcionario.getValeRefeicao());
			stmt.setString(4, funcionario.getValeTransporte());
			stmt.setString(5, funcionario.getCpf());
			stmt.setString(6, funcionario.getFilho_nome());
			java.sql.Date d = new java.sql.Date(funcionario.getFilho_dataNascimento().getTime());
			stmt.setDate(7, d);
			stmt.setInt(8, funcionario.getCodCadastro());
			
			
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
	
	
	public List<Funcionario> listarFuncionario() {
		List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
		
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = db.obterConexao();

			String sql = "select f.codCadastro, f.fk_cpf,f.email,f.sexo,f.endereco,f.telefone,f.data_nascimento,f.cargo,f.salario,f.valeAlimentaca,f.valeRefeicao,f.valeTransporte,f.filho_nome,f.filho_dataNascimento from funcionario a inner join pessoa p on p.cpf = f.fk_cpf ";
			
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Funcionario funcionario = new Funcionario();
				
				funcionario.setNome(rs.getString(("nome")));
				funcionario.setCpf(rs.getString(("fk_cpf")));
				funcionario.setEmail(rs.getString("email"));
				funcionario.setSexo(rs.getString("sexo"));
				funcionario.setEndereco(rs.getString("endereco"));
				funcionario.setTelefone(rs.getString("telefone"));
				funcionario.setDataNascimento(rs.getDate("data_nascimento"));
				funcionario.setCargo(rs.getString("cargo"));
				funcionario.setSalario(rs.getDouble("salario"));
				funcionario.setValeAlimentacao(rs.getString("valeAlimentaca"));
				funcionario.setValeRefeicao(rs.getString("valeRefeicao"));
				funcionario.setValeTransporte(rs.getString("valeTransporte"));
				funcionario.setFilho_nome(rs.getString("filho_nome"));
				funcionario.setFilho_dataNascimento(rs.getDate("filho_dataNascimento"));
				
			
				listaFuncionario.add(funcionario);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao listar Funcionario");
			e.printStackTrace();
		}
		
		return listaFuncionario;
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
			System.out.println("Erro no método de excluir pessoa na classe FuncionarioDAO.");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
		
	}
	
	public void excluirFuncionario(Funcionario funcionario) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);

			StringBuffer sql = new StringBuffer();
			sql.append("DELETE from funcionario ");
			sql.append("WHERE cpf = ?;");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, funcionario.getCpf());
			stmt.execute();
			conn.commit();
			
			excluirPessoa(funcionario.getCpf());
			
		} catch (SQLException e) {
			System.out.println("Erro no método excluir Funcionario ");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
	}
}	
