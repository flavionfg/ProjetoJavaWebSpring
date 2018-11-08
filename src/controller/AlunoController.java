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
		alunodao.cadastrarPessoaNoBanco(aluno);
		return "adicionada";
	}
	

	@RequestMapping("listaAlunos")
	public String listarAluno(Model model) {
		AlunoDAO alunodao = new AlunoDAO();
		model.addAttribute("alunos", alunodao.listarAluno());
		return "cadastrarAluno"; // retorna para ca mesmo?
	}
	
	
}
