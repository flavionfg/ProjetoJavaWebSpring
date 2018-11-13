package controller;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import dao.FuncionarioDAO;
import model.Funcionario;

@Controller
public class FuncionarioController {
	
	@RequestMapping("novoFuncionario")
		public String form(){
			return "cadastrarFuncionario";
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
}



