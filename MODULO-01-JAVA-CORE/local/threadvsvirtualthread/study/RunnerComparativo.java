import java.util.function.DoubleSupplier;

public class RunnerComparativo {

    private static final int REPETICOES = 5;

    public static void main(String[] args) {
        System.out.println("Comparativo de modelos de concorrencia");
        System.out.println("Repeticoes por cenario: " + REPETICOES);
        System.out.println();

        executarCenario("Thread tradicional", ExecucaoNormal::runOnceMillis);
        executarCenario("Thread pool", ExecucaoThreadPool::runOnceMillis);
        executarCenario("Virtual threads", ExecucaoVirtualThreads::runOnceMillis);
    }

    private static void executarCenario(String nome, DoubleSupplier runner) {
        double[] tempos = new double[REPETICOES];
        System.out.println("=== " + nome + " ===");

        for (int i = 0; i < REPETICOES; i++) {
            tempos[i] = runner.getAsDouble();
            System.out.printf("Rodada %d: %.2f ms%n", i + 1, tempos[i]);
        }

        double media = media(tempos);
        double desvioPadrao = desvioPadraoPopulacional(tempos, media);
        double minimo = minimo(tempos);
        double maximo = maximo(tempos);

        System.out.printf("Resumo -> media: %.2f ms | desvio: %.2f ms | min: %.2f ms | max: %.2f ms%n",
                media, desvioPadrao, minimo, maximo);
        System.out.println();
    }

    private static double media(double[] valores) {
        double soma = 0;
        for (double valor : valores) {
            soma += valor;
        }
        return soma / valores.length;
    }

    private static double desvioPadraoPopulacional(double[] valores, double media) {
        double somaQuadrados = 0;
        for (double valor : valores) {
            double delta = valor - media;
            somaQuadrados += delta * delta;
        }
        return Math.sqrt(somaQuadrados / valores.length);
    }

    private static double minimo(double[] valores) {
        double min = valores[0];
        for (double valor : valores) {
            if (valor < min) {
                min = valor;
            }
        }
        return min;
    }

    private static double maximo(double[] valores) {
        double max = valores[0];
        for (double valor : valores) {
            if (valor > max) {
                max = valor;
            }
        }
        return max;
    }
}