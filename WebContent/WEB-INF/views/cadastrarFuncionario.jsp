<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html>
<head>
  <meta charset="utf-8">
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet" href="css/style.css">
  <title>Cadastrar Funcionario</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
  
  
<div class="container">
  
<form id="form" action="adicionaFuncionario" method="post">
<input type="hidden" name="cod_cadastro" value="${funcionario.cod_cadastro}">
	
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
      <label for="sexo">Sexo</label>
      <select id="sexo" class="form-control" name="sexo">
        <option value="">Selecione</option>
        <option>M</option>
     	<option>F</option>
      </select>
    </div>
    <div class="form-group col-md-4">
      <label for="cargo">Cargo</label>
      <select id="cargo" class="form-control" name="cargo">
        <option value="">Selecione</option>
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
        <option>Selecione</option>
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
      <label for="ValeAlimentacao">Vale Alimentação</label>
      <select id="ValeAlimentacao" class="form-control" name="ValeAlimentacao">
        <option value="">Selecione</option>
        <option>Sim</option>
     	<option>Nao</option>
      </select>
    </div>
    <div class="form-group col-md-4">
      <label for="valeRefeicao">Vale Refeição</label>
      <select id="valeRefeicao" class="form-control" name="valeRefeicao">
        <option value="">Selecione</option>
        <option>Sim</option>
     	<option>Não</option>
      </select>
    </div>
    <div class="form-group col-md-4"> 
      <label for="valeTransporte" >Vale Transporte</label>
      <select id="valeTransporte" class="form-control" name="valeTransporte" >
        <option value="">Selecione</option>
        <option>Sim</option>
        <option>Não</option>
      </select>
    </div>
</div>



  <button type="submit" class="btn btn-outline-primary" id="botao" value="adicionafuncionario" >Cadastrar</button>
  <button type="submit" class="btn btn-outline-primary" >Limpar Campos</button>
</form>
       <div class="row mt-4">
         <div class="col-sm-12">
           <h3 class="page-header">Dados do Funcionario</h3>
            <table class="table table-striped table-bordered table-hover">
              <thead>
              <tr>
            	<th>Codigo de Cadastro</th>
                <th>Nome</th>
                <th>CPF</th>
                <th>Endereço</th>
                <th>E-mail</th>
                <th>Telefone</th>
                <th>Disciplina</th>
                <th>Data De Nascimento</th>
                <th>Sexo</th>
                <th colspan="2" style="text-align:center">Opções</th>
               
              </tr>
              </thead>
	              <tbody>
					<c:forEach items="${funcionario}" var="funcionario">
						<tr class="linhafuncionario">	
						
							<td class="fk_cod_cadastro">${funcionario.fk_cod_cadastro}</td>
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
							<td><button type="submit" class="btn btn-outline-primary" id="botaoEditarNaTabela" value="" onclick="editarfuncionario(this)">Editar</button></td> 
							<td><a href="excluirfuncionario?cpf=${funcionario.cpf}" class="btn btn-outline-primary" >Deletar</a></td>
						</tr>																							
					</c:forEach>
              </tbody>
            </table>
        </div>
  </div>
</div>

  <script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
  <script type="text/javascript" src="js/validarCadastro.js"></script>

</body>
</html>