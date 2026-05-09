import java.util.HashMap;

import javax.swing.JOptionPane;

public class GerenciadorDeEstoque {

    private static final String PRODUTO_PREFIX = "Produto : '";
    private HashMap<String, Integer> estoque;

    public GerenciadorDeEstoque() {
        estoque = new HashMap<>();
    }

    public boolean produtoExiste(final String produto) {
        return estoque.containsKey(produto);
    }

    public void exibirEstoque() {
        System.out.println("Estoque atual:");
        if (estoque.isEmpty()) {
            System.out.println("Nenhum produto em estoque.");
            return;
        }
        for (String produto : estoque.keySet()) {
            System.out.println(produto + ": " + estoque.get(produto) + " unidades");
        }
    }

    public void adicionarouatualizarProduto(final String produto, final int quantidade) {
        if (produtoExiste(produto) && quantidade > 0) {
            int quantidadeAtual = estoque.get(produto);
            estoque.put(produto, quantidadeAtual + quantidade);
            System.out.println(PRODUTO_PREFIX + produto + "' atualizado. Quantidade total: " + estoque.get(produto));
        } else {
            estoque.put(produto, quantidade);
            System.out.println(PRODUTO_PREFIX + produto + "' adicionado ao estoque com quantidade: " + quantidade);
        }
    }

    public void removerProduto(final String produto) {
        if (produtoExiste(produto)) {
            estoque.remove(produto);
            System.out.println(PRODUTO_PREFIX + produto + "' removido do estoque.");
        } else {
            System.out.println("O produto '" + produto + "' não existe no estoque.");
        }
    }

    public void pesquisarProduto(final String produto) {
        if (produtoExiste(produto)) {
            System.out.println(PRODUTO_PREFIX + produto + "' encontrado. Quantidade: " + estoque.get(produto));
        } else {
            System.out.println("O produto '" + produto + "' não existe no estoque.");
        }
    }

    public void removerQuantidadeProduto(final String produto, final int quantidade) {
        if (produtoExiste(produto) && quantidade > 0) {
            int quantidadeAtual = estoque.get(produto);
            if (quantidadeAtual >= quantidade) {
                estoque.put(produto, quantidadeAtual - quantidade);
                System.out.println("Quantidade removida do " + PRODUTO_PREFIX + produto + "'. Quantidade restante: "
                        + estoque.get(produto));
            } else {
                System.out.println("Não há quantidade suficiente do " + PRODUTO_PREFIX + produto
                        + "' para remover. Quantidade atual: " + quantidadeAtual);
            }
        } else {
            System.out.println("O produto '" + produto + "' não existe no estoque.");
        }
    }

    public void limparEstoque() {
        estoque.clear();
        System.out.println("Todos os produtos foram removidos do estoque.");
    }

    public static void main(String[] args) {

        GerenciadorDeEstoque gerenciador = new GerenciadorDeEstoque();

        int opt;

        do {

            opt = Integer.parseInt(JOptionPane.showInputDialog(
                    "Escolha uma opção:\n1. Adicionar/Atualizar produto\n2. Remover produto\n3. Exibir estoque\n4. Limpar estoque\n5. Pesquisar produto\n6. Remover quantidade de produto\n7. Sair"));

            switch (opt) {
                case 1 -> {
                    String produto = JOptionPane.showInputDialog("Digite o nome do produto:");
                    int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade:"));
                    gerenciador.adicionarouatualizarProduto(produto, quantidade);
                    break;
                }
                case 2 -> {
                    String produto = JOptionPane.showInputDialog("Digite o nome do produto:");
                    gerenciador.removerProduto(produto);
                    break;
                }
                case 3 -> {
                    gerenciador.exibirEstoque();
                    break;
                }
                case 4 -> {
                    gerenciador.limparEstoque();
                    break;
                }
                case 5 -> {
                    String produto = JOptionPane.showInputDialog("Digite o nome do produto:");
                    gerenciador.pesquisarProduto(produto);
                    break;
                }
                case 6 -> {
                    String produto = JOptionPane.showInputDialog("Digite o nome do produto:");
                    int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade a remover:"));
                    gerenciador.removerQuantidadeProduto(produto, quantidade);
                    break;
                }
                case 7 -> {
                    System.out.println("Saindo...");
                    break;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opt != 7);

    }

}
