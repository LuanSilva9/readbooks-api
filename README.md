> Um sistema simples e direto ao ponto para me ajudar a criar e manter o **hábito da leitura ativa**.

---

## 🎯 Sobre o Projeto

A ideia principal do **Readbooks** é incentivar não apenas a quantidade de páginas lidas, mas a **absorção do conteúdo**. 

Diferente de outros leitores de progresso, aqui o registro de progresso anda lado a lado com a **leitura ativa**: para cada atualização de página lida, é obrigatório registrar um resumo diário sobre o que foi assimilado.

---

## 🚀 Funcionalidades

* 📖 **Cadastro de Livros:** Registre livros informando título, descrição, link e quantidade total de páginas.
* 📈 **Progresso de Leitura:** Acompanhe o número de páginas lidas e datas de início/fim da leitura.
* 📝 **Resumos Diários (Leitura Ativa):** Registro obrigatório de resumo a cada novo progresso registrado.
* 🔄 **Upsert Diário de Resumos:** Apenas um resumo por dia por livro — se ler mais de uma vez no mesmo dia, o sistema atualiza o resumo existente.

---

## 🛠️ Regras de Negócio Básicas

* Não é possível registrar o mesmo livro mais de uma vez.
* Não é possível definir data de término (`endReadDate`) sem uma data de início (`startReadDate`).
* O número de páginas lidas nunca pode ultrapassar o total de páginas do livro.
* Todo progresso salvo exige um conteúdo de resumo associado.

---

## 📂 Arquitetura e Modelagem

O projeto foi modelado focando em simplicidade e baixo acoplamento:

* **Book:** Entidade principal com metadados do livro e status do progresso.
* **SummaryDaily:** Entidade vinculada ao livro para armazenar as anotações e reflexões diárias de leitura.

---

## 💻 Tecnologias Utilizadas

* **Linguagem:** Java
* **Framework:** Spring Boot
* **Banco de Dados:** PostgreSQL

---

## 🏁 Como Rodar o Projeto

```bash
# 1. Clone o repositório
git clone [https://github.com/LuanSilva9/readbooks-api.git](https://github.com/LuanSilva9/readbooks-api.git)

# 2. Entre na pasta do projeto
cd readbooks-api

# 3. Instale as dependências (Exemplo Node/Java)
./mvnw install

# 4. Execute a aplicação
./mvnw spring-boot:run