# Car Protection API
O projeto consiste em disponibilizar uma API REST que permite a manutenção em clientes e também de apólices. Ambos cadastros permitem realizar o a inclusão, alteração, listagem de todas, listagem por id e exclusão.

## Tecnologias
As seguintes ferramentas foram usadas na construção do projeto:

- [Java 8](https://java.com/pt-BR/download/help/java8.html)
- [Spring](https://spring.io/)
- [MongoDB](https://www.mongodb.com/)
- [Maven](https://maven.apache.org/)
- [Swagger2](https://swagger.io/)
- [Docker](https://www.docker.com/)
- [Vagrant](https://www.vagrantup.com/)
- [Digital Ocean](https://www.digitalocean.com/)

## Operações
### Clientes
* GET /api/client - Lista todos os clientes
* GET /api/client/{id} - Busca um cliente por id
* POST /api/client - Cria um novo cliente
* PUT /api/client/{id} - Edita um cliente
* DELETE /api/client/{id} - Deleta um cliente

### Apólices
* GET /api/insurance-policy - Lista todos as apólices
* GET /api/insurance-policy/{id} - Busca uma apólice por id
* POST /api/insurance-policy - Cria uma nova apólice
* PUT /api/insurance-policy/{id} - Edita uma apólice
* DELETE /api/insurance-policy/{id} - Deleta uma apólice

## Documentação
A documentação da API foi gerada via Swagger e está disponivel no link abaixo:
[DOCUMENTAÇÃO](http://www.carprotection.xyz/swagger-ui.html) 

## Inicializando no Docker
Para iniciar o projeto em um container, basta seguir os seguintes passos:

* Gere o deploy (.JAR) da aplicação e armazene-o dentro da pasta /docker/target
* Navegue até o diretório /docker e execute o seguinte comando:
```
docker-compose up
```

## Demo
O deploy foi colocado em produção em um container criado dentro de um droplet da Digital Ocean. O gerenciamento de configuração foi realizado com o Vagrant.

[www.carprotection.xyz](http://www.carprotection.xyz/api/client) 

## Licença
A licença do projeto é MIT License - olhar [LICENSE](LICENSE) para mais detalhes.
