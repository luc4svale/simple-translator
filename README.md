# Aquecimento: um simples tradutor

Um tradutor de expressões aritméticas da notação infixa para pós-fixa, com suporte a variáveis, impressão e um pequeno interpretador que executa o resultado.

Baseado no tutorial <a href="https://profsergiocosta.notion.site/site/Tradu-o-dirigida-por-sintaxe-bc590c67d8234f81bee5cfdb505f2dd1?pvs=74">Tradução dirigida por sintaxe</a>.

## Como rodar

```bash
javac -d bin src/*.java
java -cp bin Main
```

## O que faz

- Reconhece números, identificadores e as palavras reservadas `let` e `print`;
- Suporta as operações `+`, `-`, `*` e `/`, com precedência correta;
- Traduz para a notação pós-fixa e interpreta o resultado sobre uma pilha, com variáveis.

### Exemplo de entrada:

```
let a = 10;
print a + 2 * 3;
```

### Saída:

```
push 10
pop a
push a
push 2
push 3
mul
add
print
16
```

## Estrutura
```
src/
├── Main.java            # ponto de entrada
├── Scanner.java         # analisador léxico
├── Token.java           # tipo e lexema de um token
├── TokenType.java       # categorias sintáticas
├── Parser.java          # analisador sintático
├── Command.java         # representa um comando na notação pós-fixa
└── Interpreter.java     # executa os comandos sobre pilha e variáveis
```
