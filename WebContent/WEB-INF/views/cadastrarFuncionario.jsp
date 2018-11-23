<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
  <meta charset="utf-8">
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet" href="css/style.css">
  <title>Cadastrar Funcionario</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

	<style>
	.hidden {
	    display: none;
	}
	</style>

</head>
<body>
  
<div class="container">
  
<form id="form" action="adicionaFuncionario" method="post">
<input type="hidden" name="codCadastro" value="${funcionario.codCadastro}">  <!-- COLOCAR NO VALUE O FUNCUIONARIO.CODCASTRO QUANDO FOR EDITAR -->
	<!-- o value do hidden eu coloquei 23/11/18 -->
  <div class="row">
    <div class="form-group col col-sm-6 col-md-4">
      <label for="nome">Nome</label>
      <input type="text" class="form-control" id="nome" placeholder="Nome completo" name="nome">
    </div> 
    <div class="form-group col col-sm-6 col-md-4">
      <label for="cpf">CPF</label>
      <input type="text" class="form-control" id="cpf" placeholder="000.000.000-00" name="cpf" >
    </div>
    <div class="form-group col col-sm-6 col-md-4">
      <label for="endereco">Endereço</label>
      <input type="text" class="form-control" id="endereco" placeholder="" name="endereco">
    </div>
    <div class="form-group col col-sm-6 col-md-4">
      <label for="email">E-mail</label>
      <input type="text" class="form-control" id="email" placeholder="" name="email">
    </div>
        <div class="form-group col col-sm-6 col-md-4">
      <label for="telefone">Telefone</label>
      <input type="text" class="form-control" id="telefone" placeholder="(00) 0000-00000" name="telefone">
    </div>
    <div class="form-group col-md-4">
      <label for="dataNascimentoStr">Data de Nascimento</label>
      <input type="text" class="form-control" id="dataNascimentoStr" placeholder="dia/mes/ano"name="dataNascimentoStr">
    </div>
  </div>
  <div class="form-row">
    <div class="form-group col-md-4">
      <label for="valeAlimentacao">Vale Alimentação</label>
      <select id="valeAlimentacao" class="form-control" name="valeAlimentacao">
        <option value="" disabled hidden>Selecione</option>
        <option>Sim</option>
     	<option>Nao</option>
      </select>
    </div>
    <div class="form-group col-md-4">
      <label for="valeRefeicao">Vale Refeição</label>
      <select id="valeRefeicao" class="form-control" name="valeRefeicao">
        <option value="" disabled hidden>Selecione</option>
        <option>Sim</option>
     	<option>Não</option>
      </select>
    </div>
    <div class="form-group col-md-4"> 
      <label for="valeTransporte" >Vale Transporte</label>
      <select id="valeTransporte" class="form-control" name="valeTransporte" >
        <option value="" disabled hidden>Selecione</option>
        <option>Sim</option>
        <option>Não</option>
      </select>
    </div>
</div>
  <div class="form-row">
    <div class="form-group col-md-4">
      <label for="sexo">Sexo</label>
      <select id="sexo" class="form-control" name="sexo">
        <option value="" disabled hidden>Selecione</option>
        <option value="m">M</option>
     	<option value="f">F</option>
      </select>
    </div>
    <div class="form-group col-md-4">
      <label for="cargo">Cargo</label>
      <select id="cargo" class="form-control" name="cargo">
        <option value="" disabled hidden>Selecione</option>
        <option value="professor">Professor</option>
     	<option>Analista Mainframe</option>
     	<option>Analista Baixa Plataforma</option>
     	<option>Programador Mainframe</option>
     	<option>Programador Baixa Plataforma</option>     	
     	<option>Lider de Projeto</option>     	
     	<option>Gerente</option> 
      </select>
    </div>
    <div class="form-group col-md-4 disciplina" style="display:none;">  <!-- a classe disciplina é que vai esconder toda a div -->
      <label for="disciplina" >Disciplina</label>
      <select id="disciplina" class="form-control" name="disciplina" >
        <option disabled hidden>Selecione</option>
        <option>Banco de Dados</option>
        <option>Front-end</option>
     	<option>Java WEB</option>
     	<option>Linguagem de Programação Java</option>
     	<option>Outros</option>
      </select>
    </div>
</div>

  <div class="form-row">
    <div class="form-group col-md-4">
      <label for="salario">Salario</label>
      <input type="text" class="form-control" id="salario" placeholder="0000.00" name="salario">
    </div>
</div>

  <div class="form-row" style="display: block;" method="post">
   <input type="button" class="adicionar" title="Adicionar linha" style="cursor: pointer;" value="adicionar Filhos" />
   
   <br>
   
     <div class="form-group col col-sm-6 col-md-4, modelo">  <!-- duas classes , esta é a sintaxe -->
      <label for="nomeFilho">Nome</label>
      <input type="text" class="form-control nomeFilho" placeholder="Nome completo" name="nomeFilho">
     
      <label for="dataNascimentoFilho">Data de Nascimento</label>
      <input type="text" class="form-control dataNascimentoFilho" placeholder="dia/mes/ano" name="dataNascimentoFilho">
      <a href="#" class="linkExcluir hidden" onclick="excluirLinha(this)">Excluir linha</a>
      
    </div> 
    <div class="fim"></div>
    <br /> 
</div>
  <button type="submit" class="btn btn-outline-primary" id="botao" value="adicionafuncionario" >Cadastrar</button>
  <button type="reset" class="btn btn-outline-primary" >Limpar Campos</button>
</form>
       <div class="row mt-4">
         <div class="col-sm-12">
           <h3 class="page-header">Dados do Funcionario</h3>
            <table class="table table-striped table-bordered table-hover">
              <thead>
              <tr>
            	<th>ID</th>
                <th>Nome</th>
                <th>CPF</th>
                <th>Endereço</th>
                <th>E-mail</th>
                <th>Telefone</th>
                <th>Disciplina</th>
                <th>Data De Nascimento</th>
                <th>Sexo</th>
                <th>Cargo</th>
                <th>Salario</th>
                <th>Vale Alimentacao</th>
                <th>Vale Refeicao</th>
                <th>Vale Transporte</th>

                <th colspan="3" style="text-align:center">Opções</th>
               
              </tr>
              </thead>
	              <tbody>
					<c:forEach items="${listaFuncionario}" var="funcionario">
						
						<tr class="linhafuncionario">					
							<td class="codCadastro">${funcionario.codCadastro}</td>
							<td class="nome">${funcionario.nome}</td>
							<td class="cpf">${funcionario.cpf}</td>
							<td class="endereco">${funcionario.endereco}</td>
							<td class="email">${funcionario.email}</td>
							<td class="telefone">${funcionario.telefone}</td>
							<td class="disciplina">${funcionario.disciplina}</td>
							<td class="dataNascimento">
								<fmt:formatDate value="${funcionario.dataNascimento}" pattern="dd/MM/yyyy"/>
							</td>
							<td class="sexo">${funcionario.sexo}</td>
							<td class="cargo">${funcionario.cargo}</td>
							<td class="salario">${funcionario.salario}</td>
							<td class="valeAlimentacao">${funcionario.valeAlimentacao}</td>
							<td class="valeRefeicao">${funcionario.valeRefeicao}</td>
							<td class="valeTransporte">${funcionario.valeTransporte}</td>
												
							<td><button type="submit" class="btn btn-outline-primary" id="botaoEditarNaTabela" value="" onclick="editarfuncionario(this)">Editar</button></td> 
							<td><a href="excluirFuncionario?codCadastro=${funcionario.codCadastro}&cpf=${funcionario.cpf}" class="btn btn-outline-primary" >Deletar</a></td>
							<!--  "excluirFuncionario?codCadastro=  o codCadastro é um parametro  || &cpf=${funcionario.cpf} e o .cpf é o valor do parametro       --> 
						</tr>																							
					</c:forEach>
              </tbody>
            </table>
        </div>
  </div>
</div>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script type="text/javascript" src="js/validarCadastro.js"></script>
  
  	<script type="text/javascript">

		$(document).ready(function(e) {
			
			$(".adicionar").click(function(e){
						
				var novaLinha = $(".modelo").clone();
				novaLinha.removeClass("modelo").addClass("nova").find("input[name=nomeFilho]").focus();
				novaLinha.find(".linkExcluir").removeClass("hidden");
				
				novaLinha.insertBefore(".fim");
				
				return false;
			});
		});
		
		function excluirLinha(elemento) {			
			elemento.closest(".nova").remove();
		}
			
		function editarfuncionario(elemento){
			
			$("input[name=codCadastro]").val($(elemento).closest(".linhafuncionario").find(".codCadastro").html());
			$("input[name=nome]").val($(elemento).closest(".linhafuncionario").find(".nome").html());
			$("input[name=cpf]").val($(elemento).closest(".linhafuncionario").find(".cpf").html());
			$("input[name=endereco]").val($(elemento).closest(".linhafuncionario").find(".endereco").html());
			$("input[name=email]").val($(elemento).closest(".linhafuncionario").find(".email").html());
			$("input[name=telefone]").val($(elemento).closest(".linhafuncionario").find(".telefone").html());
			$("select[name=sexo]").val($(elemento).closest(".linhafuncionario").find(".sexo").html());
			$("select[name=cargo]").val($(elemento).closest(".linhafuncionario").find(".cargo").html());
				
				if($('#cargo').val() == "professor"){
					$(".disciplina").show();
				}else{
					$(".disciplina").hide();
				}
				
			$("select[name=disciplina]").val($(elemento).closest(".linhafuncionario").find(".disciplina").html());
			$("input[name=salario]").val($(elemento).closest(".linhafuncionario").find(".salario").html());
			$("select[name=valeAlimentacao]").val($(elemento).closest(".linhafuncionario").find(".valeAlimentacao").html());
			$("select[name=valeRefeicao]").val($(elemento).closest(".linhafuncionario").find(".valeRefeicao").html());
			$("select[name=valeTransporte]").val($(elemento).closest(".linhafuncionario").find(".valeTransporte").html());
			$("input[name=dataNascimentoStr]").val($(elemento).closest(".linhafuncionario").find(".dataNascimento").html().trim());
			
			
			carregarFilhos($("input[name=codCadastro]").val());

		}
	
		function carregarFilhos(codCadastro) {	
			var data = {codDoFuncionario : codCadastro};   

			$.ajax({
				url:"obterFilhosViaJson",
				type: "GET",
				async:false,
				data: data,
				dataType:"json",
			    cache: true,
				contentType:'application/x-www-form-urlencoded;', 

				success: function (data) {
					console.log("teste " + data.length);
					
					var pv = true;
					
			         $.each(data, function(index, filho) {
					   	
						if (pv) {
							$(".nomeFilho").val(filho.nome);
							$(".dataNascimentoFilho").val(filho.data_nascimentoStr);
							pv = false;
						} else {
							var novaLinha = $(".modelo").clone();
							novaLinha.removeClass("modelo").addClass("nova").find("input[name=nomeFilho]").focus();
							novaLinha.find(".linkExcluir").removeClass("hidden");
							
							novaLinha.insertBefore(".fim");
							
							novaLinha.find(".nomeFilho").val(filho.nome);
							novaLinha.find(".dataNascimentoFilho").val(filho.data_nascimentoStr);
						}
			         }); 
			      
			    }
			});
			
		}
	
	</script>
</body>
</html>