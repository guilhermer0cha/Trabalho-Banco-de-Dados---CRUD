import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static EcommerceDAO dao = new EcommerceDAO();

    public static void main(String[] args) {
        int opcao = 0;
        while (opcao != 6) {
            System.out.println("\n=== SISTEMA DE E-COMMERCE ===");
            System.out.println("1. Gestão de Clientes e Endereços");
            System.out.println("2. Gestão de Categorias");
            System.out.println("3. Gestão de Produtos");
            System.out.println("4. Gestão de Pedidos e Pagamentos");
            System.out.println("5. Adicionar Item ao Pedido");
            System.out.println("6. Sair do Sistema");
            System.out.print("Escolha um módulo: ");

            opcao = lerInteiro();

            switch (opcao) {
                case 1: menuClientes(); break;
                case 2: menuCategorias(); break;
                case 3: menuProdutos(); break;
                case 4: menuPedidos(); break;
                case 5: menuItens(); break;
                case 6: System.out.println("Encerrando..."); break;
                default: System.out.println("Opção inválida!");
            }
        }
        sc.close();
    }

    private static void menuClientes() {
        System.out.println("\n[1] Novo Cliente | [2] Listar Clientes | [3] Excluir Cliente | [4] Cadastrar Endereço");
        int op = lerInteiro();
        if (op == 1) {
            Cliente c = new Cliente();
            System.out.print("Nome: "); c.setNome(sc.nextLine());
            System.out.print("E-mail: "); c.setEmail(sc.nextLine());
            System.out.print("CPF: "); c.setCpf(sc.nextLine());
            dao.inserirCliente(c);
        } else if (op == 2) {
            dao.listarClientes();
        } else if (op == 3) {
            System.out.print("ID do Cliente para deletar: ");
            dao.deletarCliente(lerInteiro());
        } else if (op == 4) {
            Endereco e = new Endereco();
            System.out.print("ID do Cliente dono do endereço: "); e.setIdCliente(lerInteiro());
            System.out.print("Rua: "); e.setLogradouro(sc.nextLine());
            System.out.print("Número: "); e.setNumero(sc.nextLine());
            System.out.print("Bairro: "); e.setBairro(sc.nextLine());
            System.out.print("Cidade: "); e.setCidade(sc.nextLine());
            System.out.print("Estado (UF): "); e.setEstado(sc.nextLine());
            System.out.print("CEP: "); e.setCep(sc.nextLine());
            dao.inserirEndereco(e);
        }
    }

    private static void menuCategorias() {
        System.out.println("\n[1] Nova Categoria | [2] Listar Categorias | [3] Excluir Categoria");
        int op = lerInteiro();
        if (op == 1) {
            Categoria cat = new Categoria();
            System.out.print("Nome: "); cat.setNome(sc.nextLine());
            System.out.print("Descrição: "); cat.setDescricao(sc.nextLine());
            dao.inserirCategoria(cat);
        } else if (op == 2) {
            dao.listarCategorias();
        } else if (op == 3) {
            System.out.print("ID da Categoria para deletar: ");
            dao.deletarCategoria(lerInteiro());
        }
    }

    private static void menuProdutos() {
        System.out.println("\n[1] Novo Produto | [2] Listar Produtos | [3] Excluir Produto");
        int op = lerInteiro();
        if (op == 1) {
            Produto p = new Produto();
            System.out.print("ID da Categoria: "); p.setIdCategoria(lerInteiro());
            System.out.print("Nome: "); p.setNome(sc.nextLine());
            System.out.print("Descrição: "); p.setDescricao(sc.nextLine());
            System.out.print("Preço (ex: 99,90): "); p.setPreco(sc.nextDouble()); sc.nextLine();
            System.out.print("Estoque: "); p.setEstoque(lerInteiro());
            dao.inserirProduto(p);
        } else if (op == 2) {
            dao.listarProdutos();
        } else if (op == 3) {
            System.out.print("ID do Produto para deletar: ");
            dao.deletarProduto(lerInteiro());
        }
    }

    private static void menuPedidos() {
        System.out.println("\n[1] Abrir Pedido | [2] Listar Pedidos | [3] Registrar Pagamento do Pedido");
        int op = lerInteiro();
        if (op == 1) {
            Pedido p = new Pedido();
            System.out.print("ID do Cliente: "); p.setIdCliente(lerInteiro());
            System.out.print("ID do Endereço de Entrega: "); p.setIdEndereco(lerInteiro());
            System.out.print("Valor Total do Pedido: "); p.setValorTotal(sc.nextDouble()); sc.nextLine();
            dao.inserirPedido(p);
        } else if (op == 2) {
            dao.listarPedidos();
        } else if (op == 3) {
            Pagamento pg = new Pagamento();
            System.out.print("ID do Pedido: "); pg.setIdPedido(lerInteiro());
            System.out.print("Método (Pix, Cartão, Boleto): "); pg.setMetodo(sc.nextLine());
            System.out.print("Status (Aprovado, Pendente): "); pg.setEstatus(sc.nextLine());
            dao.inserirPagamento(pg);
        }
    }

    private static void menuItens() {
        System.out.println("\n-- ADICIONAR PRODUTO AO PEDIDO --");
        ItemPedido ip = new ItemPedido();
        System.out.print("ID do Pedido: "); ip.setIdPedido(lerInteiro());
        System.out.print("ID do Produto: "); ip.setIdProduto(lerInteiro());
        System.out.print("Quantidade: "); ip.setQuantidade(lerInteiro());
        System.out.print("Preço Unitário na hora da compra: "); ip.setPrecoUnitario(sc.nextDouble()); sc.nextLine();
        dao.inserirItemPedido(ip);
    }

    private static int lerInteiro() {
        while (!sc.hasNextInt()) {
            System.out.print("Por favor, digite um número válido: ");
            sc.next();
        }
        int num = sc.nextInt();
        sc.nextLine();
        return num;
    }
}