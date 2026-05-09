public class VirtualThreadsDemo {

    public static void main(String[] args) throws InterruptedException {
        int quantidade = 500_000; // Tente 10k, 50k, 100k, 500k

        long inicio = System.currentTimeMillis();

        /* // Cria e inicia as virtual threads
        for (int i = 0; i < quantidade; i++) {
            // Thread.startVirtualThread() → Java 21+
            Thread.startVirtualThread(() -> {
                try {
                    // Simula trabalho real (I/O bound - ex: chamada API, banco, etc)
                    Thread.sleep(1000); // 1 segundo de "espera"
                    System.out.println("Tarefa finalizada por: " +
                            Thread.currentThread() + " | Total threads ativas: " +
                            Thread.activeCount());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        } */

        // Threads tradicionais (para comparação)
        for (int i = 0; i < quantidade; i++) {
            new Thread(() -> {
                try {
                    // Simula trabalho real (I/O bound - ex: chamada API, banco, etc)
                    Thread.sleep(1000); // 1 segundo de "espera"
                    System.out.println("Tarefa finalizada por: " +
                            Thread.currentThread() + " | Total threads ativas: " +
                            Thread.activeCount());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }

        // Aguarda todas terminarem (simples)
        Thread.sleep(5000); // Ajuste conforme quantidade

        long fim = System.currentTimeMillis();
        System.out.println("Tempo total com " + quantidade +
                " Virtual Threads: " + (fim - inicio) + "ms");
    }
}