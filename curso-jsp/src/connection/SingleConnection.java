package connection;
/**
 * Responsavel por fazer a conexao com o banco de dados
 * @author THIAGO
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {

	private static String banco = "jdbc:postgresql://127.0.0.1:5432/curso-jsp";
	private static String password = "admin";
	private static String user = "postgres";
	private static Connection conn = null;

	static {
		conectar(); // para garantir que o banco estaja sempre ativo
	}

	public SingleConnection() {
		conectar();
	}

	private static void conectar() {
		try {
			if (conn == null) {
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection(banco, user, password);
				conn.setAutoCommit(false);
			} 

		} catch (Exception e) {
			throw new RuntimeException("Erro ao conectar com o banco de dados");
		}
	}
	
	public static Connection getConnection() {
		return conn;
	}

}
