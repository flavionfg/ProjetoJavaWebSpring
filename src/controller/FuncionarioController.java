package controller;

import java.util.ArrayList;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.AlunoDAO;
import dao.FuncionarioDAO;
import model.Aluno;
import model.Filhos;
import model.Funcionario;

@Controller
public class FuncionarioController {
	
	@RequestMapping("novoFuncionario")
		public String form(){
		return "forward:listaFuncionarios";
	}
	
	@RequestMapping("adicionaFuncionario")
	public String cadastrarPessoaNoBanco(@Valid Funcionario funcionario, BindingResult result, Model model, String[] nomeFilho,String[] dataNascimentoFilho) {
		FuncionarioDAO funcionariodao = new FuncionarioDAO();
		
		ArrayList<Filhos> filhos = new ArrayList();
	
		for (int i = 0; i < nomeFilho.length; i++) {
			// vai criar um objeto para cada Filho
			Filhos filho = new Filhos();
			filho.setNome(nomeFilho[i]);
			filho.setNome(dataNascimentoFilho[i]);
			System.out.println(nomeFilho[i]);
			filhos.add(filho);
		}
		
		funcionario.setFilhos(filhos);
		
		if(funcionario.getCodCadastro()> 0){
			funcionariodao.editarPessoa(funcionario);
		}else{
			funcionariodao.cadastrarPessoaNoBanco(funcionario);
		}
		
		return "forward:listaFuncionarios";
	}
	
	@RequestMapping("listaFuncionarios")
	public String listarFuncionario(Model model) {
		FuncionarioDAO funcionario = new FuncionarioDAO();
		model.addAttribute("funcionario", funcionario.listarFuncionario());
		return "cadastrarFuncionario"; // retorna para ca mesmo?
	}
	
	
	@RequestMapping("editaFuncionarios")
	public String editarPessoa(@Valid Funcionario funcionario, BindingResult result, Model model) { 
		FuncionarioDAO funcionariodao = new FuncionarioDAO();
		funcionariodao.editarPessoa(funcionario);
		
		return "forward:listaFuncionarios";
	}
	
	@RequestMapping("excluirFuncionario")
	public String excluirFuncionario(Funcionario funcionario, Model model) { 
		FuncionarioDAO funcionariodao = new FuncionarioDAO();
		funcionariodao.excluirFuncionario(funcionario);
		listarFuncionario(model);
		
		return "forward:listaFuncionarios";
	}
	
}



