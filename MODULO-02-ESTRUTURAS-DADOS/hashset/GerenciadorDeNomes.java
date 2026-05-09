import java.util.HashSet;

import javax.swing.JOptionPane;

public class GerenciadorDeNomes {

    private HashSet<String> hashNomes;

    public GerenciadorDeNomes() {
        hashNomes = new HashSet<>();
    }

    public boolean existeNome(final String nome) {
        return hashNomes.contains(nome);
    }

    public void adicionarNome(final String nome) {
        if (!existeNome(nome.toLowerCase())) {
            hashNomes.add(nome.toLowerCase());
            System.out.println("Nome adicionado: " + nome);
        } else {
            System.out.println("O nome '" + nome + "' já existe no conjunto.");
        }
    }

    public void removerNome(final String nome) {
        if (existeNome(nome.toLowerCase())) {
            hashNomes.remove(nome.toLowerCase());
            System.out.println("Nome removido: " + nome);
        } else {
            System.out.println("O nome '" + nome + "' não existe no conjunto.");
        }
    }

    public void exibirNomes() {
        System.out.println("Nomes no conjunto:");
        if (hashNomes.isEmpty()) {
            System.out.println("Nenhum nome cadastrado.");
            return;
        } else
            for (String nome : hashNomes) {
                System.out.println(nome);
            }
    }

    public void limparNomes() {
        hashNomes.clear();
        System.out.println("Todos os nomes foram removidos do conjunto.");
    }

    public static void main(String[] args) {

        GerenciadorDeNomes gerenciador = new GerenciadorDeNomes();

        int opt;

        do { 
            opt = Integer.parseInt(JOptionPane.showInputDialog(
                    "Escolha uma opção:\n1. Adicionar nome\n2. Remover nome\n3. Exibir nomes\n4. Limpar nomes\n5. Sair"));

            switch (opt) {
                case 1:
                    String nomeAdicionar = JOptionPane.showInputDialog("Digite o nome para adicionar:");
                    gerenciador.adicionarNome(nomeAdicionar);
                    break;
                case 2:
                    String nomeRemover = JOptionPane.showInputDialog("Digite o nome para remover:");
                    gerenciador.removerNome(nomeRemover);
                    break;
                case 3:
                    gerenciador.exibirNomes();
                    break;
                case 4:
                    gerenciador.limparNomes();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opt != 5);
        

    }

}
