import java.util.concurrent.StructuredTaskScope;

@SuppressWarnings("preview")
public class StructuredConcurrencyDemo {

    public static void main(String[] args) throws Exception {
        System.out.println("=== STRUCTURED CONCURRENCY (Java 21 - preview) ===\n");

        // Ative preview: java --enable-preview --source 21
        // StructuredConcurrencyDemo.java
        long inicio = System.currentTimeMillis();

        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) { // escopo estruturado
            // Submete tarefas como se fossem "filhos" do escopo
            StructuredTaskScope.Subtask<String> tarefa1 = scope.fork(() -> {
                Thread.sleep(800);
                return "Resultado da tarefa 1";
            });

            StructuredTaskScope.Subtask<String> tarefa2 = scope.fork(() -> {
                Thread.sleep(1200);
                return "Resultado da tarefa 2";
            });

            // Aguarda TODAS as tarefas terminarem (sem precisar de join manual)
            scope.join(); // espera conclusão
            scope.throwIfFailed(); // propaga exceções automaticamente

            // Resultados prontos
            System.out.println("Tarefa 1: " + tarefa1.get());
            System.out.println("Tarefa 2: " + tarefa2.get());
        }

        long duracao = System.currentTimeMillis() - inicio;
        System.out.printf("Tempo total: %d ms (Structured Concurrency)%n", duracao);
        System.out.println("Vantagem: código mais limpo, escopo claro e propagação automática de erros.");
    }
}