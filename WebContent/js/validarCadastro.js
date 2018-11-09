$(document).ready(function() {
	
	console.log("Recarregando a página");
	
	$("input").blur(function() {
		if ($(this).val() == "") {
			$(this).css({
				"border" : "1px solid #F00",
				"padding" : "2px"
			});
		}
	});
	$("#botao, #botaoEditar").click(function()  {
		var cont = 0;
		$("#form input").each(function() {
			if ($(this).val() == "") {
				
				$(this).css({
					"border" : "1px solid #F00",
					"padding" : "2px"
				});
				cont++;
			}else{
				if(!validarCadastro($(this))){
					cont++;
				} 
			}
		});

		if (cont == 0) {
			$("#form").submit();
			alert("Aluno Cadastrado com Sucesso");
			
		}
	});
	
});

function validarCadastro(elemento) {
	 var retorno = true;
	
	 
		if ($(elemento).prop('name') == "nome") {
			if(!validaNome($(elemento).val())){
				alert("Digite um Nome valido");
				retorno = false;
			}
		}
		if ($(elemento).prop('name') == "cpf") {
			if(!testaCPF($(elemento).val())){
				alert("Digite um CPF valido");
				retorno = false;
			}
		}
		
		if($(elemento).prop('name') == "dataNascimentoStr"){
			console.log($(elemento).val());
			if(!validarData($(elemento).val())){
				alert("Digite um data Valida.");
				retorno = false;
			}

		}
		
		if ($(elemento).prop('name') == "sexo") {
			if(!validaSexo($(elemento).val())){
				
			}
		}

		
		if($(elemento).prop('name') == "telefone"){
			if(!validaTelefone($(elemento).val())){
				alert("Digite um Telefone Valido");
				retorno = false;
			};
			
		}
		
		return retorno;
}

function obterDadosViaJson() {
	var html = "";
	var data = {
		   idTeste: 0,
		   parametroTeste: 'parametroAserEnviado'
			};   
				   
	$.ajax({
		url:"obterDadosViaJson",
		type: "GET",
		async:false,
		data: data,
		dataType:"json",
	    cache: true,
		contentType: 'application/x-www-form-urlencoded; charset=iso-8859-1;', 
		success: function (data) {
			console.log(data.length);
			if (data.length > 0) {
				html = "<table border='1' cellpadding='0' cellspacing='0' width='100%'>";
				html = html + "<thead><tr><th><span class=''>Id</span></th>";
				html = html + "<th><span class=''>Descricao</span></th>";
				html = html + "<th><span class=''>Finalizado</span></th>";
				html = html + "<th><span class=''>Data finalização</span></th>";
				html = html + "</tr></thead>";
			} else {
			   	html = "<div><span class=''>Dados não encontrados.</span></div>";
			}
	         $.each(data, function(index, tarefa) {
			   	html = html + "<tbody><tr><td><span style='text-align: center;'>" + tarefa.id + "</span></td>";
				html = html + "<td><span class=''>" + tarefa.descricao + "</span></td>";
				html = html + "<td><span style='text-align: center;'>" + tarefa.finalizado + "</span></td>";
				html = html + "<td><span style='text-align: center;'>" + tarefa.dataFinalizacao + "</span></td></tr></tbody>";
	         });    
	       $(".listaTarefasDinamica").empty().html(html);
	    }				
	});
}	

function testaCPF(strCPF) {
	var Soma;
	var Resto;
	Soma = 0;
	if (strCPF == "00000000000")
		
		return false;

	for (i = 1; i <= 9; i++)
		Soma = Soma + parseInt(strCPF.substring(i - 1, i)) * (11 - i);
	Resto = (Soma * 10) % 11;

	if ((Resto == 10) || (Resto == 11))
		Resto = 0;
	if (Resto != parseInt(strCPF.substring(9, 10)))
		return false;
	
	Soma = 0;
	for (i = 1; i <= 10; i++)
		Soma = Soma + parseInt(strCPF.substring(i - 1, i)) * (12 - i);
	Resto = (Soma * 10) % 11;

	if ((Resto == 10) || (Resto == 11))
		Resto = 0;
	if (Resto != parseInt(strCPF.substring(10, 11)))
		return false;
	return true;
}


function validarData(data) {
	
	  var expReg = /^((0[1-9]|[12]\d)\/(0[1-9]|1[0-2])|30\/(0[13-9]|1[0-2])|31\/(0[13578]|1[02]))\/(19|20)?\d{2}$/;
	  var aRet = true;
	  console.log(data);
	  if ((data) && (data.match(expReg)) && (data != '')) {

		var dia = data.substring(0,2);
		var mes = data.substring(3,5);
		var ano = data.substring(6,10);
   	  console.log(dia +  '-' + mes + '-' + ano);
		if (mes == 4 || mes == 6 || mes == 9 || mes == 11 && dia > 30) 
		  aRet = false;
		else 
		  if ((ano % 4) != 0 && mes == 2 && dia > 28) 
			aRet = false;
		  else
			if ((ano%4) == 0 && mes == 2 && dia > 29)
			  aRet = false;
	  }  else 
		aRet = false;  
	  return aRet;
	}


function validaSexo(sexo){
	console.log(sexo);
//	if(sexo == "masculino" || sexo == "feminimo")
		return true;
	
}


function validaTelefone(telefone){
	
    telefone = telefone.replace(/\D/g,'');
    
    if(!(telefone.length >= 10 && telefone.length <= 11)) return false;
    
    if (telefone.length == 11 && parseInt(telefone.substring(2, 3)) != 9) return false;
   
    for(var n = 0; n < 10; n++){
   
    	if(telefone == new Array(11).join(n) || telefone == new Array(12).join(n)) return false;
    }
    return true;
}


function validaNome(nome){
	
	if(nome.length <=1){
		return false;
	}
	
	return true;
}



function validaSexo(sexo){
	return !!sexo;
}

function Cadastrar(){
	alert("Entrou na funcao de cadastrar");
	
}

function editarAluno(elemento){
	
	$("input[name=numero_matricula]").val($(elemento).closest(".linhaAluno").find(".matricula").html());
	$("input[name=nome]").val($(elemento).closest(".linhaAluno").find(".nome").html());
	$("input[name=cpf]").val($(elemento).closest(".linhaAluno").find(".cpf").html());
	$("input[name=endereco]").val($(elemento).closest(".linhaAluno").find(".endereco").html());
	$("input[name=email]").val($(elemento).closest(".linhaAluno").find(".email").html());
	$("input[name=telefone]").val($(elemento).closest(".linhaAluno").find(".telefone").html());
	$("input[name=curso]").val($(elemento).closest(".linhaAluno").find(".curso").html());
	$("select[name=sexo]").val($(elemento).closest(".linhaAluno").find(".sexo").html());
	$("input[name=dataNascimentoStr]").val($(elemento).closest(".linhaAluno").find(".dataNascimento").html().trim());

}



