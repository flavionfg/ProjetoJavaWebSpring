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
import model.Filhos;
import model.Funcionario;
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

			stmt.executeBatch();
			conn.commit();
			
			System.out.println("Sucesso ao cadastrar um Filho.");
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar um Filho.");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
		
	}

public ArrayList<Filhos> listarFilhos(int codigoCadastroFuncionario) {
	// o codigo do funcionario esta sendo passado por parametro para poder trazer apenas o filho deste funcionario,vai ser usada no Where
	ArrayList<Filhos> listaFilhos = new ArrayList<Filhos>();
	
	Connection conn = null;
	PreparedStatement stmt = null;

	try {
		conn = db.obterConexao();

		String sql = "select data_nascimento, nome from filhos where fk_cod_cadastro = ?";
		
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, codigoCadastroFuncionario);
		System.out.println(stmt.toString());
		
		ResultSet rs = stmt.executeQuery();
		
		
		while (rs.next()) {
			Filhos filhos = new Filhos();

			filhos.setNome(rs.getString("nome"));
			filhos.setData_nascimento(rs.getDate("data_nascimento"));
			
			System.out.println(" Filhos DAO " + filhos.getNome() + filhos.getData_nascimento());
			
			listaFilhos.add(filhos);		
		}

	} catch (SQLException e) {
		System.out.println("Erro ao listar Filhos");
		e.printStackTrace();
	}

	System.out.println("qtde de filhos = " + listaFilhos.size());
	return listaFilhos;
}
	
public void excluirFilhos(Funcionario funcionario) {
	
	System.out.println("entrou no metodo excluirFilhos");
	
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	try {
		conn = db.obterConexao();
		conn.setAutoCommit(false);

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE from filhos ");
		sql.append("WHERE fk_cod_cadastro = ?;");
		stmt = conn.prepareStatement(sql.toString());
		stmt.setInt(1, funcionario.getCodCadastro());
		stmt.execute();
		conn.commit();
		
		System.out.println("Commit no excluir Filhos");
		
				
	} catch (SQLException e) {
		System.out.println("Erro no método excluir Funcionario ");
		e.printStackTrace();
	} finally {
		db.finalizaObjetos(rs, stmt, conn);
	}
}

}
