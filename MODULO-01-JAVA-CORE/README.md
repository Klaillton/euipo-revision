# Módulo 01 - Java Core e Avançado

Este módulo reúne conceitos centrais da linguagem Java com foco em entrevistas técnicas e prática diária.

## 1. Java Streams

**Pergunta comum:** O que são Java Streams e como usar (`map`, `filter`, `collect`)?

**Resposta curta:**

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

List<String> nomes = Arrays.asList("Carlos", "Ana", "Joao");

List<String> resultado = nomes.stream()
	.filter(n -> n.length() > 3)
	.map(String::toUpperCase)
	.collect(Collectors.toList());
```

**Resumo:**

- `filter` aplica um critério.
- `map` transforma os elementos.
- `collect` materializa o resultado em uma coleção.

## 2. Tópicos Recomendados

- Tipos primitivos, wrappers e autoboxing.
- Classes, interfaces, herança e polimorfismo.
- `equals`, `hashCode` e contratos de objetos.
- Collections (`List`, `Set`, `Map`) e complexidade básica.
- Exceptions e boas práticas de tratamento.
- Programação funcional com lambdas e referências de método.
- Concorrência básica com `Thread`, `ExecutorService` e `CompletableFuture`.

## 3. Prática Sugerida

- Resolver 2 problemas de lógica por dia.
- Implementar estruturas simples sem frameworks.
- Revisar código com foco em legibilidade e naming.

