import static java.util.concurrent.Executors.newFixedThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ExecucaoThreadPool {

    private static final int TOTAL_TAREFAS = 5000;
    private static final int TAMANHO_POOL = TOTAL_TAREFAS;
    
    public static double runOnceMillis() {
        List<Future<?>> futures = new ArrayList<>();

        long tempoInicial = System.nanoTime();

        try (ExecutorService executor = newFixedThreadPool(TAMANHO_POOL)) {
         
            for (int i = 0; i < TOTAL_TAREFAS; i++) {
                futures.add(executor.submit(new Processo().executar()));
            }

            for (Future<?> future : futures) {
                try {
                    future.get();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Execucao interrompida", e);
                } catch (Exception e) {
                    throw new RuntimeException("Falha em tarefa do thread pool", e);
                }
            }

        }

        long tempoFinal = System.nanoTime();

        return (tempoFinal - tempoInicial) / 1_000_000.0;
    }

    public static void main(String[] args) {
        System.out.println("ThreadPool");
        System.out.println("Executando " + TOTAL_TAREFAS + " tarefas com pool de " + TAMANHO_POOL + " threads...");
        double tempoTotalMs = runOnceMillis();

        System.out.printf("Tempo total: %.2f ms - ThreadPool%n", tempoTotalMs);
    }

}
