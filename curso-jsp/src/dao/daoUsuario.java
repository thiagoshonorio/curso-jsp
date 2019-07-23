package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Bean;
import connection.SingleConnection;

public class daoUsuario {
	private Connection conn;

	public daoUsuario() {
		conn = SingleConnection.getConnection();
	}

	public void salvar(Bean usuario) {
		String sql = "INSERT INTO usuarios (login, senha, nome, telefone) VALUES (?,?,?,?)";

		try {
			PreparedStatement insert = conn.prepareStatement(sql);
			insert.setString(1, usuario.getUsuario());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
			insert.setString(4, usuario.getTelefone());
			insert.execute();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public List<Bean> listar() throws Exception {

		List<Bean> usuariosListados = new ArrayList<Bean>();
		String sql = "SELECT * FROM usuarios";
		PreparedStatement stm = conn.prepareStatement(sql);
		ResultSet result = stm.executeQuery();

		while (result.next()) {
			Bean bean = new Bean();
			bean.setId(result.getLong("id"));
			bean.setUsuario(result.getString("login"));
			bean.setSenha(result.getString("senha"));
			bean.setNome(result.getString("nome"));
			bean.setTelefone(result.getString("telefone"));
			usuariosListados.add(bean);
		}
		return usuariosListados;
	}

	public void delete(Integer id) {
		String sql = "DELETE FROM usuarios WHERE id=?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.execute();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void editar(Bean usuario) {
		String sql = "UPDATE usuarios SET login= ?, senha=?, nome=?, telefone=? WHERE id= " + usuario.getId() + "";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, usuario.getUsuario());
			pst.setString(2, usuario.getSenha());
			pst.setString(3, usuario.getNome());
			pst.setString(4, usuario.getTelefone());
			pst.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public Bean consultar(String id) throws Exception {
		String sql = "SELECT * FROM usuarios WHERE id='" + id + "'";

		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet resultSet = pst.executeQuery();

		if (resultSet.next()) {
			Bean bean = new Bean();
			bean.setId(resultSet.getLong("id"));
			bean.setUsuario(resultSet.getString("login"));
			bean.setSenha(resultSet.getString("senha"));
			bean.setNome(resultSet.getString("nome"));
			bean.setTelefone(resultSet.getString("telefone"));
			return bean;
		} else {
			return null;
		}
	}

	public Boolean validarLogin(String login) throws Exception {
		String sql = "SELECT COUNT(1) AS qtd FROM usuarios WHERE login='" + login + "'";

		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet resultSet = pst.executeQuery();

		if (resultSet.next()) {
			return resultSet.getInt("qtd") <= 0; // return true
		} else {
			return false;
		}
	}

	public Boolean validarUpdate(String login, String id) throws Exception {
		String sql = "SELECT COUNT(1) AS qtd FROM usuarios WHERE login='" + login + "' AND ID <> " + id + "";

		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet resultSet = pst.executeQuery();

		if (resultSet.next()) {
			return resultSet.getInt("qtd") <= 0; // return true
		}
		return false;
	}
	
	public Boolean validarSenha(String senha) throws Exception{
		String sql = "SELECT COUNT(1) AS qtd FROM usuarios WHERE senha='"+senha+"'";
		
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet resultSet = pst.executeQuery();
		
		if (resultSet.next()) {
			return resultSet.getInt("qtd") <= 0;
		}
			
		return false;
	}

}
