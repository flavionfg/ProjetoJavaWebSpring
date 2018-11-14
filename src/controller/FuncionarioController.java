package controller;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.AlunoDAO;
import dao.FuncionarioDAO;
import model.Aluno;
import model.Funcionario;

@Controller
public class FuncionarioController {
	
	@RequestMapping("novoFuncionario")
		public String form(){
		return "forward:listaFuncionarios";
	}
	
	@RequestMapping("adicionaFuncionario")
	public String cadastrarPessoaNoBanco(@Valid Funcionario funcionario, BindingResult result, Model model) {
		FuncionarioDAO funcionariodao = new FuncionarioDAO();
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



