package controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;


import dao.AlunoDAO;
import model.Aluno;

@Controller
public class AlunoController {
	
	@RequestMapping("novoAluno")
		public String form(){
			return "cadastrarAluno"; 
		}
	
	@RequestMapping("adicionaAluno")
	public String cadastrarPessoaNoBanco(@Valid Aluno aluno, BindingResult result, Model model) {
	

		AlunoDAO alunodao = new AlunoDAO();
		System.out.println("passou do aluno dao");
		alunodao.cadastrarPessoaNoBanco(aluno);
		return "adicionada";
		
	}
}
