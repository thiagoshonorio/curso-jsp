package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import beans.BeanProduto;

public class daoProduto {

	private Connection conn;

	public daoProduto() {
		conn = SingleConnection.getConnection();
	}

	public void insert(BeanProduto produto) {
		String sql = "INSERT INTO produtos (codigo, nome, quantidade, preco) VALUES (?,?,?,?)";
		try {
			PreparedStatement insert = conn.prepareStatement(sql);
			insert.setString(1, produto.getCodigo());
			insert.setString(2, produto.getNome());
			insert.setInt(3, produto.getQuantidade());
			insert.setDouble(4, produto.getPreco());
			insert.execute();
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void update(BeanProduto produto) {
		try {
			String sql = "UPDATE produtos SET codigo=?, nome=?, quantidade=?, preco=? WHERE id=" + produto.getId() + "";

			PreparedStatement update = conn.prepareStatement(sql);
			update.setString(1, produto.getCodigo());
			update.setString(2, produto.getNome());
			update.setInt(3, produto.getQuantidade());
			update.setDouble(4, produto.getPreco());
			update.execute();
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void delete(String id) {
		try {
			String sql = "DELETE FROM produtos WHERE id=" + id + "";

			PreparedStatement delete = conn.prepareStatement(sql);
			delete.execute();
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public List<BeanProduto> list() throws SQLException {
		List<BeanProduto> listProduct = new ArrayList<BeanProduto>();

		String sql = "SELECT * FROM produtos";

		PreparedStatement listproduct = conn.prepareStatement(sql);
		ResultSet resultproduct = listproduct.executeQuery();

		while (resultproduct.next()) {
			BeanProduto produto = new BeanProduto();
			produto.setId(resultproduct.getLong("id"));
			produto.setCodigo(resultproduct.getString("codigo"));
			produto.setNome(resultproduct.getString("nome"));
			produto.setQuantidade(resultproduct.getInt("quantidade"));
			produto.setPreco(resultproduct.getDouble("preco"));
			listProduct.add(produto);
		}

		return listProduct;
	}

	public BeanProduto consult(String id) throws SQLException {

		String sql = "SELECT * FROM produtos WHERE id=" + id + "";
		PreparedStatement consult = conn.prepareStatement(sql);
		ResultSet consultResult = consult.executeQuery();

		if (consultResult.next()) {
			BeanProduto produto = new BeanProduto();
			produto.setId(consultResult.getLong("id"));
			produto.setCodigo(consultResult.getString("codigo"));
			produto.setNome(consultResult.getString("nome"));
			produto.setQuantidade(consultResult.getInt("quantidade"));
			produto.setPreco(consultResult.getDouble("preco"));
			return produto;
		}

		return null;
	}

	public boolean validateName(String nome) throws SQLException {

		String sql = "SELECT COUNT(1) AS QTD FROM produtos WHERE nome='" + nome + "'";

		PreparedStatement validate = conn.prepareStatement(sql);
		ResultSet resultValidate = validate.executeQuery();

		if (resultValidate.next()) {
			return resultValidate.getInt("QTD") <= 0;
		}

		return false;
	}

	public boolean validateCod(String codigo) throws SQLException {

		String sql = "SELECT COUNT(1) AS QTD FROM produtos WHERE codigo=" + codigo + "";

		PreparedStatement validate = conn.prepareStatement(sql);
		ResultSet resultValidate = validate.executeQuery();

		if (resultValidate.next()) {
			return resultValidate.getInt("QTD") <= 0;
		}

		return false;
	}

}
