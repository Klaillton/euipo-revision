
import java.time.Duration;

public class Processo {

    private static final long SLEEP_FIXO_SEGUNDOS = 2;

    public Runnable executar() {
        return () -> {
            try {
                Thread.sleep(Duration.ofSeconds(SLEEP_FIXO_SEGUNDOS).toMillis());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };
    }

}
