<h1 align="center">JPA, consultas SQL e JPQL</h1>
</br>
<h2>📝 Descrição</h2>
<p align="center">Esse repositório tem como objetivo praticar a criação de um projeto SpringBoot contendo a implementação de consultas no banco de Dados, usando JPA e consultas JPQL.</p>
</br>
<p>Trata-se de um sistema de vendas (Sale) e vendedores (Seller). Cada venda está para um vendedor, e um
vendedor pode ter várias vendas.</p>

![image](https://github.com/LopezTw/desafio-consulta-vendas/assets/55853613/8b0e5d11-5239-4b27-8717-5ea36dd0a0c0)

</br>

<h2>🔧 Tecnologias utilizadas</h2>
<p>Foi utilizando o Spring Boot + Hibernate/JPA e o banco H2 </p>

<h2>🚀 Rodando o projeto</h2>

<p><b>Relátorio de Vendas:</b></p>

1. [IN] O usuário informa, opcionalmente, data inicial, data final e um trecho do nome do vendedor.
2. [OUT] O sistema informa uma listagem paginada contendo id, data, quantia vendida e nome do vendedor, das vendas que se enquadrem nos dados informados.
   
Informações complementares:
- Se a data final não for informada, será considerado a data atual do sistema.
- Se a data inicial não for informada, será considerado a data de 1 ano antes da data final.
- Se o nome não for informado, será considerado o texto vazio.

<p><b>Relatório de vendas por vendedor:</b></p>

1. [IN] O usuário informa, opcionalmente, data inicial, data final.
2. [OUT] O sistema informa uma listagem contendo nome do vendedor e soma de vendas deste vendedor no período informado.
   
Informações complementares:
- Se a data final não for informada, será considerado a data atual do sistema.
- Se a data inicial não for informada, será considerado a data de 1 ano antes da data final.
- Se o nome não for informado, será considerado o texto vazio.
- Utilizando o POSTMAN, abaixo estão as consultas que podem ser feitas pra testar o programa:

Basta importar as consultas prontas no LINK:

https://www.getpostman.com/collections/dea7904f994cb87c3d12

OU SE PREFERIR, pode copiar manualmente:


GET /sales/summary?minDate=2022-01-01&maxDate=2022-06-30</br>
GET /sales/summary</br>
GET /sales/report</br>
GET /sales/report?minDate=2022-05-01&maxDate=2022-05-31&name=odinson</br>



