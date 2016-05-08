# Exploração de Marte

## Objetivo
Exercício cujo objetivo é desenvolver um projeto para que se possa implantar sondas em um terreno pré-definido de marte e depois
controlá-las via uma série de comandos.

Foi criada uma API Restful para execução de cada uma destas etapas. 

## Plataforma e Frameworks utilizados
+ Java 1.7
+ Spring Boot 1.3.3
+ Apache Commons Lang 3.4
+ FasterXml Jackson Databind 2.6.3
+ Mockito 1.10.19
+ Springfox Swagger 2.2.2

[![Build Status](https://api.travis-ci.org/emarineli/elo7-exploracao-marte.svg?branch=dev-sec)]

## Documentação da API
http://ec2-52-67-15-216.sa-east-1.compute.amazonaws.com:8181/exploracao/v1/swagger-ui.html

## Passo a passo
Este passo a passo irá guiá-lo com simples comandos cURL para sua primeira missão em Marte. Divirta-se! 

1. Crie um terreno de exploração. Você não conseguirar implantar e movimentar sondas se elas não
sabem por onde andar;

```
curl -X POST --header "Content-Type: application/json" --header "Accept: */*" -d "{
  \"extensao\": {
    \"eixoX\": 10,
    \"eixoY\": 10
  }
}" "http://ec2-52-67-15-216.sa-east-1.compute.amazonaws.com:8181/exploracao/v1/terreno"
```

2. Implante uma sonda nos limites do terreno que você estabeleceu. Sondas fora deste limite não
podem ser implantadas. Você pode implantar quantas sondas quiser, desde que também nunca tente
implantá-las sobre as outras.

```
curl -X POST --header "Content-Type: application/json" --header "Accept: application/json" -d "{
  \"type\": \"sondaSimples\",
  \"identificadorVeiculoExploracao\": \"novaSonda\",
  \"posicaoAtual\": {
    \"eixoX\": 4,
    \"eixoY\": 3
  },
  \"direcaoAtual\": \"NORTE\"
}" "http://ec2-52-67-15-216.sa-east-1.compute.amazonaws.com:8181/exploracao/v1/sondas"
```

3. Movimente as sondas em Marte baseado em simples comandos. Mas cuidado para não trombar uma
contra a outra;

```
curl -X POST --header "Content-Type: application/json" --header "Accept: application/json" -d "[\"D\",\"M\",\"E\", \"M\", \"M\"]" "http://ec2-52-67-15-216.sa-east-1.compute.amazonaws.com:8181/exploracao/v1/sondas/novaSonda/instrucoes"
```

4. Verifique na documentação que existe outras operações que você pode executar com os recursos de Sonda e Terreno. Você poderá localizar
sua sonda a qualquer momento, removê-la de marte, verificar o tamanho do terreno de exploração, etc.
