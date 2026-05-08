import java.util.List;

public class WaitForThreadsToFinish {

    public static void wait(List<Thread> threads) {
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
}
