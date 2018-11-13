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
  <title>Cadastrar Aluno</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
  
  
<div class="container">
  
<form id="form" action="adicionaAluno" method="post">
<input type="hidden" name="numero_matricula" value="${aluno.numero_matricula}">
	
  <div class="row">
    <div class="form-group col col-sm-6 col-md-4">
      <label for="nome">Nome</label>
      <input type="text" class="form-control" id="nome" placeholder="Nome completo" name="nome">
    </div> 
    <div class="form-group col col-sm-6 col-md-4">
      <label for="cpf">CPF</label>
      <input type="text" class="form-control" id="cpf" placeholder="000.000.000-00" name="cpf" <c:if test="${aluno.numero_matricula > 0}">readonly</c:if>>
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
    <div class="form-group col col-sm-6  col-md-4">
      <label for="curso">Curso</label>
      <input type="text" class="form-control" id="curso" placeholder="" name="curso">
    </div>
  </div>

  <div class="form-row">
    <div class="form-group col-md-4">
      <label for="dataNascimentoStr">Data de Nascimento</label>
      <input type="text" class="form-control" id="dataNascimentoStr" placeholder="dia/mes/ano"name="dataNascimentoStr">
    </div>
    <div class="form-group col-md-4">
      <label for="sexo">Sexo</label>
      <select id="sexo" class="form-control" name="sexo">
        <option value="">Selecione</option>
        <option value="m">M</option>
     	<option value="f">F</option>
      </select>
    </div>
</div>
  <button type="submit" class="btn btn-outline-primary" id="botao" value="adicionaAluno" >Cadastrar</button>
  <button type="submit" class="btn btn-outline-primary" >Limpar Campos</button>
</form>
       <div class="row mt-4">
         <div class="col-sm-12">
           <h3 class="page-header">Dados do Aluno</h3>
            <table class="table table-striped table-bordered table-hover">
              <thead>
              <tr>
            	<th>Matricula</th>
                <th>Nome</th>
                <th>CPF</th>
                <th>Endereço</th>
                <th>E-mail</th>
                <th>Telefone</th>
                <th>Curso</th>
                <th>Data De Nascimento</th>
                <th>Sexo</th>
                <th colspan="2" style="text-align:center">Opções</th>         
              </tr>
              </thead>
	              <tbody>
					<c:forEach items="${alunos}" var="aluno">
						<tr class="linhaAluno">	
							<td class="matricula">${aluno.numero_matricula}</td>
							<td class="nome">${aluno.nome}</td>
							<td class="cpf">${aluno.cpf}</td>
							<td class="endereco">${aluno.endereco}</td>
							<td class="email">${aluno.email}</td>
							<td class="telefone">${aluno.telefone}</td>
							<td class="curso">${aluno.curso}</td>
							<td class="dataNascimento">
								<fmt:formatDate value="${aluno.dataNascimento}" pattern="dd/MM/yyyy"/>
							</td>
							<td class="sexo">${aluno.sexo}</td>
							<td><button type="submit" class="btn btn-outline-primary" id="botaoEditarNaTabela" value="" onclick="editarAluno(this)">Editar</button></td>
							<td><a href="excluirAluno?cpf=${aluno.cpf}" class="btn btn-outline-primary" >Deletar</a></td>
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