
/*
Tarefas práticas (rode com diferentes JDKs):

Java 8: Use Optional, Streams e lambdas.
Java 17: record, sealed classes, instanceof pattern matching.
Java 21: Virtual Threads + String templates (preview).
*/

public class VirtualThreadsExercicio {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Thread.startVirtualThread(() -> {
                System.out.println("Virtual Thread: " + Thread.currentThread());
                try { Thread.sleep(1000); } catch (Exception e) { e.getMessage(); }
            });
        }
    }
}