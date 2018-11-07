<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
  
<form>
  <div class="row">
    <div class="form-group col col-sm-6 col-md-4">
      <label for="nome">Nome</label>
      <input type="text" class="form-control" id="nome" placeholder="Nome completo" name="nome">
    </div>
    <div class="form-group col col-sm-6 col-md-4">
      <label for="cpf">CPF</label>
      <input type="text" class="form-control" id="cpf" placeholder="000.000.000-00" name="cpf">
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
      <label for="cargo">Cargo</label>
      <select id="cargo" class="form-control" name="cargo">
        <option selected></option>
        <option>Professor</option>
        <option>...</option>
        <option>...</option>
        <option>...</option>
        <option>...</option>
        <option>...</option>
      </select>
    </div>
  </div>


  <div class="form-row">
    <div class="form-group col-md-4">
      <label for="dataDeNascimento">Data de Nascimento</label>
      <input type="text" class="form-control" id="dataDeNascimento" placeholder="dia/mes/ano"name="dataDeNascimento">
    </div>
    <div class="form-group col-md-4">
      <label for="sexo">Sexo</label>
      <select id="sexo" class="form-control" name="sexo">
        <option selected></option>
        <option>Masculino</option>
     <option>Feminino</option>
      </select>
    </div>
        <div class="form-group col-md-4">
      <label for="valeAlimentacao">Vale Alimentacao</label>
      <select id="valeAlimentacao" class="form-control" name="valeAlimentacao">
        <option selected></option>
        <option>Sim</option>
        <option>Não</option>
      </select>
    </div>
</div>
<div class="form-row">
    <div class="form-group col-md-4">
    <label for="valeRefeicao">Vale Refeição</label>
    <select id="valeRefeicao" class="form-control" name="valeRefeicao">
        <option selected></option>
        <option>Sim</option>
        <option>Não</option>
    </select>
    </div>
        <div class="form-group col-md-4">
    <label for="valeTransporte">Vale Transporte</label>
    <select id="valeTransporte" class="form-control" name="valeTransporte">
        <option selected></option>
        <option>Sim</option>
        <option>Não</option>
    </select>
    </div>
        <div class="form-group col-md-4">
    <label for="filhos">Quantidade de Filhos</label>
    <select id="filhos" class="form-control" name="filhos">
        <option selected></option>
        <option>1</option>
        <option>2</option>
        <option>3</option>
        <option>4</option>
        <option>5</option>
        <option>6</option>
        <option>7</option>
        <option>8</option>
        <option>9</option>
        <option>10</option>
    </select>
    </div>
</div>
<div class="form-row">
      <div class="form-group col col-sm-6 col-md-4">
      <label for="salario">Salário</label>
      <input type="text" class="form-control" id="salario" placeholder="R$ 0000.00" name="salario">
    </div>
 
</div>

  <button type="submit" class="btn btn-outline-primary" id="botao">Cadastrar</button>
  <button type="submit" class="btn btn-outline-primary" id="botaoEditar">Editar</button>
  <button type="submit" class="btn btn-outline-primary" id="botaoDeletar">Deletar</button>
</form>
  
       <div class="row mt-4">
         <div class="col-sm-12">
             <h3 class="page-header">Dados do Funcionario</h3>
            <table class="table table-striped table-bordered table-hover">
              <thead>
              <tr>
                <th>#</th>
                <th>Nome</th>
                <th>CPF</th>
                <th>Endereço</th>
                <th>E-mail</th>
                <th>Telefone</th>
                <th>Cargo</th>
                <th>Nascimento</th>
                <th>Sexo</th>
                <th>Vale Alimentacao</th>
                <th>Vale Refeição</th>
                <th>Vale Transporte</th>
                <th>Filhos</th>
                <th>Salário</th>
              </tr>
              </thead>
              <tbody>
                <tr>
                  <th>1</th>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                </tr>
                <tr>
                  <th>2</th>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                </tr>
                <tr>
                  <th>3</th>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                  <td>Conteudo</td>
                </tr>
              </tbody>
            </table>
        </div>
  </div>
</div>

  <script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
  <script type="text/javascript" src="js/validarCadastro.js"></script>

</body>
</html>