package controller;



import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.AlunoDAO;
import dao.ProfessorDAO;
import model.Aluno;
import model.Professor;

@Controller
public class ProfessorController {
	
	@RequestMapping("novoProfessor")
		public String form(){
			return "forward:listaProfessores";
		}
	
	@RequestMapping("adicionaProfessor")
	public String cadastrarPessoaNoBanco(@Valid Professor professor, BindingResult result, Model model) {
		ProfessorDAO professordao = new ProfessorDAO();
		if(professor.fk_cod_cadastro() > 0){
			professordao.editarPessoa(professor);
		}else{
			professordao.cadastrarPessoaNoBanco(professor);
		}
		
		return "forward:listaProfessores";
	}
	

	@RequestMapping("listaProfessores")
	public String listarProfessor(Model model) {
		ProfessorDAO professor = new ProfessorDAO();
		model.addAttribute("professores", professor.listarProfessor());
		return "cadastrarProfessor"; // retorna para ca mesmo?
	}
	
	@RequestMapping("editaProfessores")
	public String editarPessoa(@Valid Professor professor, BindingResult result, Model model) { 
		ProfessorDAO professordao = new ProfessorDAO();
		professordao.editarPessoa(professor);
		
		return "forward:listaProfessores";
	}
	
	@RequestMapping("excluirProfessor")
	public String excluirAluno(Professor professor, Model model) { 
		ProfessorDAO professordao = new ProfessorDAO();
		professordao.excluirProfessor(professor);
		listarProfessor(model);
		
		return "forward:listaProfessores";
	}
	
	
	
	
}
