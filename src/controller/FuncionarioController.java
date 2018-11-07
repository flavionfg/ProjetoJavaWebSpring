package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FuncionarioController {
	
	@RequestMapping("novoFuncionario")
		public String form(){
			return "cadastrarFuncionario";
	}
}



