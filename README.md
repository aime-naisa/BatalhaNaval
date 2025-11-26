# 🛳️ Projeto Final da Disciplina de Introdução à Programação – Batalha Naval (FURB – 1º Semestre)

Este repositório contém o projeto Batalha Naval 8×8, desenvolvido como trabalho final da disciplina **Introdução à Programação**, do **primeiro semestre do curso da FURB (Universidade Regional de Blumenau)**.
O projeto aplica os conceitos fundamentais de **lógica de programação, uso de matrizes, estruturas de repetição,** e **controle de fluxo,** utilizando apenas os recursos permitidos nas unidades 1 a 6 da disciplina.

---

## 💻 Linguagem Utilizada

O jogo foi desenvolvido em **Linguagem Java**, aplicando exclusivamente os conteúdos básicos trabalhados em sala, incluindo:

- Entrada e saída de dados
- Matrizes bidimensionais
- Laços while
- Condicionais if e switch/case
- Classe Random
- Controle manual de fluxo
- Execução sequencial sem métodos avançados
- Código implementado totalmente em uma única classe
- Sem atributos globais, conforme exigido no enunciado

---

## 🎮 Sobre o Projeto – Batalha Naval 8×8

O jogo simula o clássico **Batalha Naval**, atendendo todas as regras definidas pelo professor:

- Tabuleiro **8×8** representado por matrizes
  
- Posicionamento de **10 navios aleatórios**, sendo:
  - 1 Porta-aviões (4 células)
  - 2 Cruzadores (3 células)
  - 3 Destroyers (2 células)
  - 4 Submarinos (1 célula)
 
- Uso exclusivo de **Random** para gerar posições dinâmicas a cada execução
- Garantia de que **nenhum navio encosta**, nem lateral, superior, inferior ou diagonalmente
- Feedback completo a cada jogada
- Controle de tentativas (máximo de 30)

- Exibição de:
  - acertos
  - erros
  - taxa de acerto em **tempo real**
  - navios afundados
  - resumo final por tipo
 
---
## 🧠 Estruturas utilizadas

| Estrutura | Utilização no Projeto | Justificativa | Observação |
|-----------|------------------------|----------------|-------------|
| **Matrizes (`char[][]`, `int[][]`)** | Representação do tabuleiro visível, tabuleiro real e IDs dos navios | Permitem organizar dados de forma bidimensional, essencial para jogos de grade como Batalha Naval | São o núcleo lógico do programa. Sem matrizes não seria possível implementar o tabuleiro. |
| **Laços `while`** | Navegação pelas matrizes, repetição de tentativas, validações e posicionamento aleatório dos navios | A Unidade 5 exige domínio de estruturas de repetição; o trabalho proíbe `for`, então `while` é obrigatório | Usado em **100% dos loops** do programa. |
| **Condicionais `if/else`** | Verificação de acertos/erros, limites do tabuleiro, áreas livres e controle de jogadas | Permitem decisões críticas na lógica do jogo, como validar posições e classificar ações do jogador | Fundamentais para evitar sobreposição de navios. |
| **`switch/case`** | Exibição dos nomes dos navios (porta-aviões, cruzador etc.) | Exigência direta da disciplina e do enunciado do trabalho | Reduz código repetido e organiza melhor condições específicas. |
| **Classe `Random`** | Sorteio de orientação e posição inicial de cada navio | Garante que cada partida seja única e atende ao requisito de aleatoriedade | Evita padrões previsíveis e deixa o jogo realista. |
| **Classe `Scanner`** | Leitura das jogadas do usuário (linha e coluna) | Permite interação direta no console, requisito da disciplina | Tratamento de erros garante entradas válidas. |
| **Vetor `int[] celulasRestantes`** | Controle de quantas partes de cada navio ainda restam | Essencial para detecção de navios afundados e controle do progresso | Integra matriz de IDs com a lógica de afundamento. |
| **Métodos auxiliares** | Inicialização das matrizes, posicionamento de navios, leitura de coordenadas, impressão do tabuleiro | Organizam a lógica, reduzem repetição e deixam o código legível | Seguem o princípio de modularização e boas práticas. |

---

## 📄 Documentação do Projeto
A documentação completa, contendo:
- Explicação do posicionamento dos navios
- Lógica de detecção de afundamentos
- Controle estatístico do jogo
- Estruturas utilizadas
- Demonstrações e análises

-> está disponível no link: [Documentação](https://github.com/aime-naisa/BatalhaNaval/blob/main/Documenta%C3%A7%C3%A3o%20Batalha%20Naval%20-%20Aim%C3%AA%20Na%C3%ADsa%20de%20Souza.pdf)

-> imagens do console no link: [Imagens Console](https://github.com/aime-naisa/BatalhaNaval/blob/main/Imagens%20-%20Batalha%20Naval.pdf)

---

## 📚 Referência do professor

VOIGT, Ricardo. *Disciplina IP 2025/2 B*. Repositório GitHub.  
Disponível em: <https://github.com/ricardovoigt/disciplina_IP_2025_2_B>.  

---

## 🏫 Universidade 
**FURB – Universidade Regional de Blumenau**
**Curso:** Bacharelado em Sistemas de Informação 
**Disciplina:** Introdução à Programação (1º semestre)



