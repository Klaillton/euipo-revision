import java.util.List;

public class ExecucaoNormal {

    private static final int TOTAL_TAREFAS = 5000;
    

    public static double runOnceMillis() {

        List<Thread> threads = new java.util.ArrayList<>();

        long tempoInicial = System.nanoTime();

        for (int i = 0; i < TOTAL_TAREFAS; i++) {
            Thread t = new Thread(new Processo().executar());
            threads.add(t);
            t.start();
        }

        
        WaitForThreadsToFinish.wait(threads);
        
        long tempoFinal = System.nanoTime();

        return (tempoFinal - tempoInicial) / 1_000_000.0;
    }

    public static void main(String[] args) {
        System.out.println("Thread tradicional");
        System.out.println("Criando " + TOTAL_TAREFAS + " threads tradicionais...");
        double tempoTotalMs = runOnceMillis();

        System.out.printf("Tempo total: %.2f ms - Thread tradicional%n", tempoTotalMs);
    }
    
}
