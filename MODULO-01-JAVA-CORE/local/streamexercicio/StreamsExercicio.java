import java.util.*;
import java.util.logging.Logger;

record Pessoa(String nome, int idade, double salario) {}

public class StreamsExercicio {
    private static final Logger logger = Logger.getLogger(StreamsExercicio.class.getName());

    public static void main(String[] args) {
        List<Pessoa> pessoas = List.of(
            new Pessoa("Ana", 25, 3500),
            new Pessoa("Bruno", 30, 5200),
            new Pessoa("Carla", 22, 2800),
            new Pessoa("Daniel", 35, 6500)
        );

        pessoas.stream()
            .filter(p -> p.idade() > 25)
            .map(filtered -> filtered.nome().toUpperCase())
            .forEach(logger::info);

        double mediaSalarios = pessoas.stream()
            .mapToDouble(Pessoa::salario)
            .average()
            .orElse(0);

        logger.info(() -> "Média Salarial: " + mediaSalarios);


        List<Integer> numeros = Arrays.asList(1,21,21,3,4,5,6,7,8,9,10,3,3,9,5,4,3,2,1,0,11,11,11,11,13,9,9,6,5,5,7,8,2,2,3,4,5,6,7,8,9,10);
        
        var listaUnicaOrdenada = numeros.stream()
            .distinct()
            .sorted()
            .toList();

        logger.info(() -> "Lista única ordenada: " + listaUnicaOrdenada);
        logger.info(() -> "Tamanho da lista única ordenada: " + listaUnicaOrdenada.size());
        logger.info(() -> "Terceiro elemento da lista única ordenada: " + listaUnicaOrdenada.get(2));

    }
}
