<h1>Desafio DSMovie RestAssured</h1>
Você deve implementar todos os testes de API conforme solicitado abaixo:

<h3>Sobre o projeto DSMOvie:</h3>
<p>Este é um projeto de filmes e avaliações de filmes. A visualização dos dados dos filmes é pública (não necessita login), porém as alterações de filmes (inserir, atualizar, deletar) são permitidas apenas para usuários ADMIN. As avaliações de filmes podem ser registradas por qualquer usuário logado CLIENT ou ADMIN. A entidade Score armazena uma nota de 0 a 5 (score) que cada usuário deu a cada filme. Sempre que um usuário registra uma nota, o sistema calcula a média das notas de todos usuários, e armazena essa nota média (score) na entidade Movie, juntamente com a contagem de votos (count).  Veja vídeo explicativo.</p>
<img width="889" height="267" alt="image" src="https://github.com/user-attachments/assets/7284e1e7-54c3-4e09-aba7-f41853ecd317" />
<p>Abaixo estão os testes de API que você deverá implementar utilizando o RestAssured. O minímo para aprovação no desafio são 8 dos 10 testes.</p>

<h4>MovieControllerRA:</h4>
<ul>
  <li>findAllShouldReturnOKWhenMovieNoArgumentsGiven</li>  
  <li>findAllShouldReturnPagedMoviesWhenMovieTitleParamIsNotEmpty</li>  
  <li>findByIdShouldReturnMovieWhenIdExists</li>  
  <li>findByIdShouldReturnNotFoundWhenIdDoesNotExist</li>     
  <li>insertShouldReturnUnprocessableEntityWhenAdminLoggedAndBlankTitle</li>  
  <li>insertShouldReturnForbiddenWhenClientLogged</li> 
  <li>insertShouldReturnUnauthorizedWhenInvalidToken</li>
</ul>
<h4>ScoreControllerRA:</h4>
<ul>
  <li>saveScoreShouldReturnNotFoundWhenMovieIdDoesNotExist</li> 
  <li>saveScoreShouldReturnUnprocessableEntityWhenMissingMovieId</li>  
  <li>saveScoreReturnUnprocessableEntityWhenScoreIsLessThanZero</li>  
</ul>
