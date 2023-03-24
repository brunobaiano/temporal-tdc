# temporal-tdc

Fontes para apresentação no TDC 2023

Requisitos
JDK 17
Temporal Server - autosetup (temporal.io)


Rodar os microserviços

*generatePerson* e *generateCreditCard*

Você pode usar a linha de comando.
Na pasta raiz de cada um execute:
````
mvnw spring-boot:run 
````

Rodar o serviço createAccount (pode ser da mesma forma) e 
realizar um post em localhost:18080/v1/account para ver o
worflow demonstrado na apresentação.