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
			return "forward:listaAlunos";
		}
	
	@RequestMapping("adicionaAluno")
	public String cadastrarPessoaNoBanco(@Valid Aluno aluno, BindingResult result, Model model) {
		AlunoDAO alunodao = new AlunoDAO();
		if(aluno.getNumero_matricula() > 0){
			
			alunodao.editarAluno(aluno);
		}else{
			alunodao.cadastrarPessoaNoBanco(aluno);
		}
		
		return "forward:listaAlunos";
	}
	

	@RequestMapping("listaAlunos")
	public String listarAluno(Model model) {
		AlunoDAO aluno = new AlunoDAO();
		model.addAttribute("alunos", aluno.listarAluno());
		return "cadastrarAluno"; // retorna para ca mesmo?
	}
	
	@RequestMapping("editaAlunos")
	public String editarAluno(@Valid Aluno aluno, BindingResult result, Model model) { 
		AlunoDAO alunodao = new AlunoDAO();
		alunodao.editarAluno(aluno);
		return "forward:listaAlunos";
	}
	
	
	
}
