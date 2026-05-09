import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadComparison {

    private static final int NUMERO_TAREFAS = 1_000; // Aumente para testar escalabilidade
    private static final int TEMPO_SIMULADO_IO = 500; // milissegundos (simula I/O: banco, API etc.)

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== COMPARAÇÃO DE THREADS JAVA ===\n");

        // 1. TRADICIONAL (Platform Thread) - Java 8/17/21
        executarTradicional();

        // 2. POOL com ExecutorService (Platform Threads) - Java 8/17/21
        executarPoolTradicional();

        // 3. VIRTUAL THREADS - Apenas Java 21+
        executarVirtualThreads();
    }

    // ====================== 1. TRADICIONAL (Platform Thread)
    // ======================
    private static void executarTradicional() throws InterruptedException {
        System.out.println("1. TRADICIONAL (new Thread().start())");
        long inicio = System.currentTimeMillis();

        for (int i = 0; i < NUMERO_TAREFAS; i++) {
            // Cada thread ocupa uma thread do SO → pesado e limitado (~milhares)
            new Thread(() -> {
                try {
                    Thread.sleep(TEMPO_SIMULADO_IO);
                } catch (InterruptedException e) {
                }
            }).start();
        }

        Thread.sleep(3000); // aguarda término aproximado
        long duracao = System.currentTimeMillis() - inicio;
        System.out.printf("   → %d tarefas em %d ms (Platform Threads)\n\n", NUMERO_TAREFAS, duracao);
    }

    // ====================== 2. POOL com ExecutorService (Platform)
    // ======================
    private static void executarPoolTradicional() throws InterruptedException {
        System.out.println("2. POOL TRADICIONAL (ExecutorService + Platform Threads)");
        long inicio = System.currentTimeMillis();

        // Pool fixo de 10 threads do SO (padrão recomendado antes do Java 21)
        try (ExecutorService executor = Executors.newFixedThreadPool(10)) {
            for (int i = 0; i < NUMERO_TAREFAS; i++) {
                executor.submit(() -> {
                    try {
                        Thread.sleep(TEMPO_SIMULADO_IO);
                    } catch (InterruptedException e) {
                    }
                });
            }
        } // auto-close do pool (shutdown)

        long duracao = System.currentTimeMillis() - inicio;
        System.out.printf("   → %d tarefas em %d ms (Pool de 10 Platform Threads)\n\n", NUMERO_TAREFAS, duracao);
    }

    // ====================== 3. VIRTUAL THREADS (Java 21+) ======================
    private static void executarVirtualThreads() throws InterruptedException {
        System.out.println("3. VIRTUAL THREADS (Java 21)");
        long inicio = System.currentTimeMillis();

        // Forma 1: ExecutorService com Virtual Threads (recomendado)
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < NUMERO_TAREFAS; i++) {
                executor.submit(() -> {
                    try {
                        Thread.sleep(TEMPO_SIMULADO_IO); // blocking I/O → virtual thread é "desmontada"
                    } catch (InterruptedException e) {
                    }
                });
            }
        }

        long duracao = System.currentTimeMillis() - inicio;
        System.out.printf("   → %d tarefas em %d ms (Virtual Threads)\n", NUMERO_TAREFAS, duracao);
        System.out.println("   → Muito mais leve e escalável! Pode chegar a milhões de threads.\n");
    }
}