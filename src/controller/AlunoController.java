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
			System.out.println("o que tem no nmero de matricula na controller : " + aluno.getNumero_matricula());
			alunodao.editarPessoa(aluno);
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
	public String editarPessoa(@Valid Aluno aluno, BindingResult result, Model model) { 
		AlunoDAO alunodao = new AlunoDAO();
		System.out.println("controller antes de chamar");
		alunodao.editarPessoa(aluno);
		
		return "forward:listaAlunos";
	}
	
	@RequestMapping("excluirAluno")
	public String excluirAluno(Aluno aluno, Model model) { 
		AlunoDAO alunodao = new AlunoDAO();
		alunodao.excluirAluno(aluno);
		listarAluno(model);
		
		return "forward:listaAlunos";
	}
	
	
	
	
}
