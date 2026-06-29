import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EcommerceDAO {
    //CATEGORIA
    public void inserirCategoria(Categoria c) {
        String sql = "INSERT INTO Categoria (nome, descricao) VALUES (?, ?)";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getDescricao());
            stmt.executeUpdate();
            System.out.println("Categoria cadastrada!");
        } catch (SQLException e) { System.out.println("Erro: " + e.getMessage()); }
    }

    public void listarCategorias() {
        String sql = "SELECT * FROM Categoria";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_categoria") + " | Nome: " + rs.getString("nome"));
            }
        } catch (SQLException e) { System.out.println("Erro: " + e.getMessage()); }
    }

    public void deletarCategoria(int id) {
        String sql = "DELETE FROM Categoria WHERE id_categoria = ?";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Categoria removida!");
        } catch (SQLException e) { System.out.println("Erro (Pode haver produtos atrelados): " + e.getMessage()); }
    }

    // CLIENTE
    public void inserirCliente(Cliente c) {
        String sql = "INSERT INTO Cliente (nome, email, cpf) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getEmail());
            stmt.setString(3, c.getCpf());
            stmt.executeUpdate();
            System.out.println("Cliente cadastrado!");
        } catch (SQLException e) { System.out.println("Erro: " + e.getMessage()); }
    }

    public void listarClientes() {
        String sql = "SELECT * FROM Cliente";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_cliente") + " | Nome: " + rs.getString("nome") + " | CPF: " + rs.getString("cpf"));
            }
        } catch (SQLException e) { System.out.println("Erro: " + e.getMessage()); }
    }

    public void deletarCliente(int id) {
        String sql = "DELETE FROM Cliente WHERE id_cliente = ?";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Cliente removido!");
        } catch (SQLException e) { System.out.println("Erro: " + e.getMessage()); }
    }

    // PRODUTO
    public void inserirProduto(Produto p) {
        String sql = "INSERT INTO Produto (id_categoria, nome, descricao, preco, estoque) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, p.getIdCategoria());
            stmt.setString(2, p.getNome());
            stmt.setString(3, p.getDescricao());
            stmt.setDouble(4, p.getPreco());
            stmt.setInt(5, p.getEstoque());
            stmt.executeUpdate();
            System.out.println("Produto cadastrado!");
        } catch (SQLException e) { System.out.println("Erro: " + e.getMessage()); }
    }

    public void listarProdutos() {
        String sql = "SELECT * FROM Produto";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_produto") + " | Nome: " + rs.getString("nome") + " | R$ " + rs.getDouble("preco") + " | Estoque: " + rs.getInt("estoque"));
            }
        } catch (SQLException e) { System.out.println("Erro: " + e.getMessage()); }
    }

    public void deletarProduto(int id) {
        String sql = "DELETE FROM Produto WHERE id_produto = ?";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Produto removido!");
        } catch (SQLException e) { System.out.println("Erro: " + e.getMessage()); }
    }

    // PEDIDO
    public void inserirPedido(Pedido p) {
        String sql = "INSERT INTO Pedido (id_cliente, id_endereco, valor_total) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, p.getIdCliente());
            stmt.setInt(2, p.getIdEndereco());
            stmt.setDouble(3, p.getValorTotal());
            stmt.executeUpdate();
            System.out.println("Pedido gerado!");
        } catch (SQLException e) { System.out.println("Erro: " + e.getMessage()); }
    }

    public void listarPedidos() {
        String sql = "SELECT * FROM Pedido";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID Pedido: " + rs.getInt("id_pedido") + " | ID Cliente: " + rs.getInt("id_cliente") + " | Total: R$ " + rs.getDouble("valor_total") + " | Data: " + rs.getTimestamp("data_pedido"));
            }
        } catch (SQLException e) { System.out.println("Erro: " + e.getMessage()); }
    }

    public void inserirEndereco(Endereco e) {
        String sql = "INSERT INTO Endereco (id_cliente, logradouro, numero, cep, bairro, cidade, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, e.getIdCliente()); stmt.setString(2, e.getLogradouro()); stmt.setString(3, e.getNumero());
            stmt.setString(4, e.getCep()); stmt.setString(5, e.getBairro()); stmt.setString(6, e.getCidade()); stmt.setString(7, e.getEstado());
            stmt.executeUpdate();
            System.out.println("Endereço salvo!");
        } catch (SQLException ex) { System.out.println("Erro: " + ex.getMessage()); }
    }

    public void inserirItemPedido(ItemPedido ip) {
        String sql = "INSERT INTO Item_Pedido (id_pedido, id_produto, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ip.getIdPedido()); stmt.setInt(2, ip.getIdProduto());
            stmt.setInt(3, ip.getQuantidade()); stmt.setDouble(4, ip.getPrecoUnitario());
            stmt.executeUpdate();
            System.out.println("Item adicionado!");
        } catch (SQLException e) { System.out.println("Erro: " + e.getMessage()); }
    }

    public void inserirPagamento(Pagamento pg) {
        String sql = "INSERT INTO Pagamento (id_pedido, metodo, estatus) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pg.getIdPedido()); stmt.setString(2, pg.getMetodo()); stmt.setString(3, pg.getEstatus());
            stmt.executeUpdate();
            System.out.println("Pagamento registrado!");
        } catch (SQLException e) { System.out.println("Erro: " + e.getMessage()); }
    }
}