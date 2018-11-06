<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/style.css">


<title>Cadastrar Professor</title>
</head>
<body>

<form id="form" name="form">

    <div>
        <label for="name">Nome:</label>
        <input type="text" id="nome" name="nome" value=""/>
    </div>
    
     <div>
        <label for="cpf">CPF:</label>
        <input type="text" id="cpf" name="cpf"/> 
    </div>
    
    <div>
        <label for="mail">E-mail:</label>
        <input type="text" id="email" name="email"/>
    </div>
     
    <div>
        <label for="dataDeNascimento">Data de Nascimento:</label>
        <input type="text" id="dataDeNascimento" name="dataDeNascimento"></input>
    </div>
    
     <div>
        <label for="endereco">Endereço:</label>
        <input type="text" id="endereco" name="endereco"></input>
    </div>
    
      <div>
        <label for="sexo">Sexo:</label>
        <input type="text" id="sexo" name="sexo"></input>
    </div>
    
     <div>
        <label for="telefone">Telefone:</label>
        <input type="text" id="telefone" name="telefone"></input>
    </div>
    
    <div>
        <label for="curso">Curso:</label>
        <input type="text" id="curso" name="curso"></input>
    </div>
    
      <div>
        <label for="disciplina">Disciplina:</label>
        <input type="text" id="disciplina" name="disciplina"></input>
    </div>
   
   
    <div class="button">
        <button type="button" id="botao" value="enviar">Cadastar</button>
    </div>
</form>

 	<script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
	 <script type="text/javascript" src="js/validarCadastro.js"></script>

</body>
</html>