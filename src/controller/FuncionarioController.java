package controller;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import dao.AlunoDAO;
import dao.FilhosDAO;
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
		
			Filhos filho = new Filhos();
			filho.setNome(nomeFilho[i]);
			filho.setData_nascimentoStr(dataNascimentoFilho[i]);
			
			System.out.println(nomeFilho[i]);
			System.out.println(dataNascimentoFilho[i]);
			
			filhos.add(filho);
		}
		
		funcionario.setFilhos(filhos);
		System.out.println("Setou filhos no metodo de adicionaFuncionario na FuncionarioController");
		
		if(funcionario.getCodCadastro()> 0){
			funcionariodao.editarPessoa(funcionario);
		}else{
			funcionariodao.cadastrarPessoaNoBanco(funcionario);
		}
		
		return "forward:listaFuncionarios";
	}
	
	@RequestMapping("listaFuncionarios")
	public String listarFuncionario(Model model) {
		FuncionarioDAO funcionariodao = new FuncionarioDAO();
		model.addAttribute("listaFuncionario", funcionariodao.listarFuncionario());

		return "cadastrarFuncionario"; 
	}
	
	
	@RequestMapping("editaFuncionarios")
	public String editarPessoa(@Valid Funcionario funcionario, BindingResult result, Model model) { 
		FuncionarioDAO funcionariodao = new FuncionarioDAO();
		funcionariodao.editarPessoa(funcionario);
		
		return "forward:listaFuncionarios";
	}
	
	@RequestMapping("excluirFuncionario")
	public String excluirFuncionario(String codCadastro , String cpf, Model model) { 
		FuncionarioDAO funcionariodao = new FuncionarioDAO();
		Funcionario funcionario = new Funcionario();
		funcionario.setCodCadastro(Integer.parseInt(codCadastro));
		funcionario.setCpf(cpf);
		System.out.println(funcionario.getCodCadastro());
		funcionariodao.excluirFuncionario(funcionario);
		listarFuncionario(model);
		
		return "forward:listaFuncionarios";
	}
	
	@RequestMapping(value="obterFilhosViaJson", method=RequestMethod.GET, produces="application/json")
	@ResponseStatus(code= HttpStatus.OK)
	public @ResponseBody ArrayList<Filhos> obterFilhosViaJson(String codDoFuncionario) {
		System.out.println("parametro = " + codDoFuncionario);
		FilhosDAO filhosdao = new FilhosDAO();
		Util util = new Util();
		ArrayList<Filhos> filhos = filhosdao.listarFilhos(Integer.parseInt(codDoFuncionario));

		for (int i = 0; i < filhos.size(); i++) {
			
			filhos.get(i).setData_nascimentoStr(util.transformaDataJson(filhos.get(i).getData_nascimento()));
			
		}
		return filhos;
		
	}
	

}
