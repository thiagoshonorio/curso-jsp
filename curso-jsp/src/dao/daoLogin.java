package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnection;

public class daoLogin {
	private Connection conn;

	public daoLogin() {
		conn = SingleConnection.getConnection();

	}

	public boolean validarLogin(String usuario, String senha) throws Exception {
		
		String sql = "SELECT * FROM usuarios WHERE login='" + usuario + "' AND senha='" + senha + "'";

		PreparedStatement sta = conn.prepareStatement(sql);
		ResultSet rs = sta.executeQuery();

		if (rs.next()) {
			return true; //encontrou um usuario
		} else {
			return false; //nao encontrou o usuario
		}
	}
}
