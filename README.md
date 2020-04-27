# Projeto de Avalição - Jokenpo

Jogo de Jokenpo

## Execução do projeto

Executar o comando `gradlew.bat bootRun` na raiz do repositório. 

A aplicação será inicializada na porta 8080.

A API está com Swagger configurado, para visualizar, acessar via [SwaggerUI](http://localhost:8080/swagger-ui.html).

### Registrar jogadores
Executar o comando: 
```
curl -X POST "http://localhost:8080/jokenpo/jogadores/registrar" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"id\": \"Jogador 1\"}";
```

### Iniciar partida
Executar o comando: 
```
curl -X POST "http://localhost:8080/jokenpo/partidas/iniciar" -H "accept: */*"
```

### Registrar jogada (Informar o código da partida, obtido na etapa anterior e o ID do jogador registrado)
Executar o comando: 
```
curl -X POST "http://localhost:8080/jokenpo/partidas/registar?aposta=TESOURA&idJogador=Jogador%201&idPartida=1" -H "accept: */*"
```

### Encerrar partida (Informar o código da partida)
Executar o comando: 
```
http://localhost:8080/jokenpo/partidas/encerrar?idPartida=1
```

### Obter resultado da partida (Informar o código da partida)
Executar o comando: 
```
http://localhost:8080/jokenpo/partidas/resultado?idPartida=1
```

Outros serviços estão disponíveis na aplicação, coloquei aqui os principais serviços.