package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfessorController {
	
	@RequestMapping("novoProfessor")
	public String form(){
		return "cadastrarProfessor";
		
	}

}
