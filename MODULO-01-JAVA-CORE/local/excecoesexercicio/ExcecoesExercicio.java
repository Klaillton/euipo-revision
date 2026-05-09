import java.io.*;

public class ExcecoesExercicio {
    public static void main(String[] args) {
        try {
            lerArquivo();
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }

        dividir(10, 0);
    }

    // Checked: deve ser tratada ou declarada
    static void lerArquivo() throws IOException {
        new BufferedReader(new FileReader("arquivo.txt")).readLine();
    }

    // Unchecked: RuntimeException
    static void dividir(int a, int b) {
        if (b == 0) throw new ArithmeticException("Divisão por zero");
        System.out.println(a / b);
    }
}