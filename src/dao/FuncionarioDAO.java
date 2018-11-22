package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
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
			System.out.println("Cadastrou uma pessoa - antes de chamar o cadastrarFuncionario");
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
			
			sql.append("INSERT INTO funcionario(cargo,salario,vale_alimentacao,vale_refeicao,vale_transporte,disciplina,fk_cpf)"); //aqui ele pega uma primary key de funcionario
			sql.append("VALUES(?,?,?,?,?,?,?)");
			//tenho que cadastrar fk_cpf
			//disciplina tem que ter alguma condiçao?
			
			stmt = conn.prepareStatement(sql.toString());

			stmt.setString(1, funcionario.getCargo());
			stmt.setDouble(2, funcionario.getSalario());
			stmt.setString(3, funcionario.getValeAlimentacao());
			stmt.setString(4, funcionario.getValeRefeicao());
			stmt.setString(5, funcionario.getValeTransporte());
			stmt.setString(6, funcionario.getDisciplina());
			stmt.setString(7, funcionario.getCpf()); // pega o cpf ?
		
			stmt.execute();
			conn.commit();
			
			System.out.println("deu commit no CadastrarFuncionario");
			
			if (!funcionario.getFilhos().isEmpty()) {
	
				
				String sqlLastInsert = "SELECT LAST_INSERT_ID()";
				
				db.finalizaObjetos(null, stmt, null);
				stmt = conn.prepareStatement(sqlLastInsert);
				rs = stmt.executeQuery();

				if (rs.next()) {
					
					funcionario.setCodCadastro(rs.getInt(1));
					

					db.finalizaObjetos(null, stmt, null);
				}
				//selectLastIntid				
				//for setando o id para cada fillho do array existente no funcionario
	
				FilhosDAO filhos = new FilhosDAO();
				filhos.cadastrarFilhos(funcionario);
				
				
			}
			
			
			System.out.println("Sucesso ao cadastrar um Funcionario.");
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar um Funcionario.");
			e.printStackTrace();
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
			
			sql.append("UPDATE funcionario SET cargo = ?,salario = ?,vale_alimentacao = ?,vale_refeicao = ?,vale_transporte = ?,cpf = ?");
			sql.append("WHERE cod_cadastro = ?;");

			stmt = conn.prepareStatement(sql.toString());
			
			stmt.setString(1, funcionario.getCargo()); 
			stmt.setDouble(2, funcionario.getSalario());
			stmt.setString(3, funcionario.getValeAlimentacao());
			stmt.setString(3, funcionario.getValeRefeicao());
			stmt.setString(4, funcionario.getValeTransporte());
			stmt.setString(5, funcionario.getCpf());
			stmt.setInt(6, funcionario.getCodCadastro());
			
			
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

			String sql = "select f.cod_cadastro,p.nome,f.disciplina,f.fk_cpf,p.email,p.sexo,p.endereco,p.telefone,p.data_nascimento,f.cargo,f.salario,f.vale_alimentacao,f.vale_refeicao,f.vale_transporte "
					+ "from funcionario f inner join pessoa p on p.cpf = f.fk_cpf ";
			
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Funcionario funcionario = new Funcionario();

				funcionario.setCodCadastro(rs.getInt("cod_cadastro"));
				funcionario.setNome(rs.getString(("nome")));
				funcionario.setDisciplina(rs.getString("disciplina"));
				funcionario.setCpf(rs.getString(("fk_cpf")));
				funcionario.setEmail(rs.getString("email"));
				funcionario.setSexo(rs.getString("sexo"));
				funcionario.setEndereco(rs.getString("endereco"));
				funcionario.setTelefone(rs.getString("telefone"));
				funcionario.setDataNascimento(rs.getDate("data_nascimento"));
				funcionario.setCargo(rs.getString("cargo"));
				funcionario.setSalario(rs.getDouble("salario"));
				funcionario.setValeAlimentacao(rs.getString("vale_alimentacao"));
				funcionario.setValeRefeicao(rs.getString("vale_refeicao"));
				funcionario.setValeTransporte(rs.getString("vale_transporte"));
			
				//FilhosDAO filhosdao = new FilhosDAO();
				//funcionario.setFilhos(filhosdao.listarFilhos(funcionario.getCodCadastro())); // vai executar de dentro para fora
		
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
		System.out.println("comit no excluir pessoa");
			
		} catch (SQLException e) {
			System.out.println("Erro no método de excluir pessoa na classe FuncionarioDAO.");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
		
	}
	
	public void excluirFuncionario(Funcionario funcionario) {

		//if (!funcionario.getFilhos().isEmpty()) {
			
			System.out.println("Entrou na condição de exclusao do filho");
			
			FilhosDAO filhosdao = new FilhosDAO();
			filhosdao.excluirFilhos(funcionario);
			
		//}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);

			StringBuffer sql = new StringBuffer();
			sql.append("DELETE from funcionario ");
			sql.append("WHERE fk_cpf = ?;");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, funcionario.getCpf());
			stmt.execute();
			conn.commit();
			
			excluirPessoa(funcionario.getCpf());
			System.out.println("Entrou no metodo de excluir PESSOA");
		} catch (SQLException e) {
			System.out.println("Erro no método excluir Funcionario ");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
	}
}	
