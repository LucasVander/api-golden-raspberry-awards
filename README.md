# Golden Raspberry Awards
Esta API fornece informações sobre os intervalos mínimos e máximos de prêmios ganhos por produtores nos Prêmios Golden Raspberry.

### Endpoints

GET /api/golden-raspberry-awards/movie
`Retorna a lista de todos os filmes indicados ao prêmio.`

GET /api/golden-raspberry-awards/producer
`Retorna a lista de todos os produtores e seus filmes indicados.`

GET /api/golden-raspberry-awards/interval
`Retorna os intervalos mínimos e máximos entre os prêmios ganhos por produtores.`


### Estrutura do Projeto

- GoldenRaspberryAwardsController: Controlador REST que lida com as requisições para os intervalos de prêmios.
- MovieService: Serviço para consultar os filmes (contém a lógica de negócio para calcular os intervalos mínimos e máximos de prêmios dos produtores).
- ProducerService: Serviço para consultar os produtores.
- MovieRepository: Repositório de persistência e leitura do BD.
- ProducerRepository: Repositório de persistência e leitura do BD.
- GlobalExceptionHandler: Classe que controla e padroniza todas as exceções lançadas.
- ErrorDetails: Classe que padroniza um erro.
- FileNotFoundException: Exceção para quando o arquivo da lista de permiações não é econtrado.
- MoviesNotFoundException: Exceção para quando não é encontrado nenhum filme.
- ProducerIntervalsResponse: Classe de resposta que encapsula os intervalos mínimos e máximos.
- ProducerInterval: Classe que representa um intervalo de prêmios para um produtor.
- Movie: Entidade que representa um filme no sistema.
- Producer: Entidade que representa um produtor no sistema.
- MovieDTO: Objeto para transferência da entidade filme.
- ProducerDTO: Objeto para transferênciia da entidade produtor.
- WinnerProducerMovies: Objeto que representa os filmes vencedores da premiação de um produtor.

### Executando a Aplicação

A aplicação utiliza um arquivo com a lista de premiações em [movielist.csv](https://github.com/LucasVander/api-golden-raspberry-awards/blob/main/golden-raspberry-awards/src/main/resources/movielist.csv),
para outros casos de teste alterar o conteúdo do arquivo. **Caso for substituido deve-se manter o mesmo nome para o funcionamento normal da API**

Pré-requisitos:

- Java 11+
- Maven

### Passos para Execução

- Clone o repositório:

```
git clone https://github.com/seu-usuario/golden-raspberry-awards.git
cd golden-raspberry-awards
```

- Compile o projeto e rode os testes unitários:

```
mvn clean install
````

- Execute a aplicação:

```
mvn spring-boot:run
```

### Testando a API
Para testar o endpoint, você pode usar ferramentas como curl, `Postman`, ou Insomnia. Aqui está um exemplo usando curl`:

```
curl -X GET http://localhost:8080/api/golden-raspberry-awards/movie/interval -H "Accept: application/json"
```

- Exemplo de Resposta

```json
{
  "min": [
    {
      "producer": "ProducerName",
      "interval": 1,
      "previousWin": 2000,
      "followingWin": 2001
    }
  ],
  "max": [
    {
      "producer": "ProducerName",
      "interval": 10,
      "previousWin": 1990,
      "followingWin": 2000
    }
  ]
}
```

### Licença
Este projeto está licenciado sob a Licença MIT. Veja o arquivo LICENSE para mais detalhes.
