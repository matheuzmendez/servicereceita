# Service Receita

## Cenário de Negócio:

Todo dia útil por volta das 6 horas da manhã um colaborador da retaguarda do Sicredi recebe e organiza as informações de 
contas para enviar ao Banco Central. Todas agências e cooperativas enviam arquivos Excel à Retaguarda. Hoje o Sicredi 
já possiu mais de 4 milhões de contas ativas.
Esse usuário da retaguarda exporta manualmente os dados em um arquivo CSV para ser enviada para a Receita Federal, 
antes as 10:00 da manhã na abertura das agências.

## Requisito:

Usar o "serviço da receita" (fake) para processamento automático do arquivo.

## Funcionalidade:

0. Criar uma aplicação SpringBoot standalone. Exemplo: java -jar SincronizacaoReceita <input-file>
1. Processa um arquivo CSV de entrada com o formato abaixo.
2. Envia a atualização para a Receita através do serviço (SIMULADO pela classe ReceitaService).
3. Retorna um arquivo com o resultado do envio da atualização da Receita. Mesmo formato adicionando o resultado em uma 
nova coluna.

## Praticando:

Para utilizar a aplicação de forma prática, basta baixar a aplicação .jar e o teste.csv localizado aqui na pasta /exec no projeto. Na prática,
a entrada seria da seguinte forma como planilha:

Agência|Conta|Saldo|Status
-------|-----|-----|-----
0101|12225-6|"100,00"|A
0101|12226-8|"3200,50"|A
3202|40011-1|"-35,12"|I
3202|54001-2|"0,00"|P
3202|00321-2|"34500,00"|B
320|00321-2|"34500,00"|B
3201|0032-2|"34500,00"|B
3201|00321-2|"34500,00"|C
1110|00321-2|"34500,00"|B

Após rodar aplicação, adicionamos (de forma automatizada) a coluna "Atualizou", ficando da seguinte forma:

Agência|Conta|Saldo|Status|Atualizou
-------|-----|-----|-----|-------
0101|12225-6|"100,00"|A|VERDADEIRO
0101|12226-8|"3200,50"|A|VERDADEIRO
3202|40011-1|"-35,12"|I|VERDADEIRO
3202|54001-2|"0,00"|P|VERDADEIRO
3202|00321-2|"34500,00"|B|VERDADEIRO
320|00321-2|"34500,00"|B|FALSO
3201|0032-2|"34500,00"|B|FALSO
3201|00321-2|"34500,00"|C|FALSO
1110|00321-2|"34500,00"|B|VERDADEIRO
