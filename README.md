# Back-T3T4

JDK: openjdk-16 versão 16.0.2

# Projeto
Trata-se do uso do framework Ktor para linguagem Kotlin para criação de uma API que será utilizada na criação de um site de review para filmes

# Como iniciar
1. Baixar VM do DB modelo em: https://drive.google.com/file/d/1CeS3kiVsx_JEfJUNd6kVmlfN6rMJTlDx/view?usp=sharing
2. Importar a VM no VirtualBox
3. Iniciar a VM do banco de dados
4. Criar arquivo MariaDB.kt em src/main/java/config com o template de MariaDB.kt.template para conexão com o Banco de Dados
5. Preencher o arquivo com as seguintes informações:
```
    const val user = "admin"    
    const val password = "admin"    
    const val host = "{IP fornecido pela VM}"    
    const val port = "3306"
```
    
6. Iniciar a aplicação pelo arquivo main.kt

# Arquitetura
## [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
### Estrutura:
    -src/
        |-main/
            |-java/
                |-config/ : configurações de projeto
                |-controllers/ : Onde é definido como o framework utiliza o usecase
                |-fabrics/ : fábricas de controladores, usecases, etc
                |-interfaces/ : qualquer interface usada globalmente na aplicação
                |-models/ : models usados na aplicação
                |-repositories/ : Conexões com DB, métodos de DBs, etc
                |-usecases/ : Onde são definidas as regras de negócio
                |-Init.kt : Configurações para inicialização da aplicação
                |-main.kt : Inicializa a aplicação
### Banco de dados
![image](https://user-images.githubusercontent.com/70296502/140964760-e4c882f8-28fe-421a-a8d2-3cb5209f19f0.png)
### Ordem de desenvolvimento
#### ANTES: Criar arquivo MariaDB.kt em src/main/java/config com o template de MariaDB.kt.template para conexão com o Banco de Dados
1. Criar método na interface de repositório (IRepo.kt)
2. Implementar o método nos DBs
3. Criar usecase (UCExemplo.kt)
4. Criar método na interface de controller (ICtrl.kt)
5. Implementar o método em um controlador (CExemplo.kt)
6. Adicionar o controlador do item 5. à fábrica de controladores (KtorControllerFabric.kt)
7. Criar rota que use o controlador em controllers.ktor.routers.routes
8. Implementar a rota no roteador (router.kt)

### Padronização de nomenclatura
* Arquivos: lowercase. ex: src
* Padrão: UpperCamelCase
* Classes: UpperCamelCase ex: Ktor
* Interfaces: I maiúsculo seguido do nome em UpperCamelCase. ex: IExemplo.kt
* Usecase: UC maiúsculo seguido do nome em UpperCamelCase. ex: UCExemplo.kt
* Controlador: C maiúsculo segudo do nome em UpperCamelCase. ex CExemplo.kt
