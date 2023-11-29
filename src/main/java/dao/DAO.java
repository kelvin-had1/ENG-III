package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DAO implements IDAO {
	private static Connection conexao = null;
	
	public DAO(){
	}
	
	public Connection getConexao() {
		if(conexao != null) {
			return conexao;
		}
		try {
			String url = "jdbc:postgresql://localhost:5432/eng3";
			String user = "postgres";
			String password = "1234";
			Class.forName("org.postgresql.Driver");		
			DAO.conexao = DriverManager.getConnection(url, user, password);
			
			
		}catch(SQLException ex) {
			System.out.println("Conexão ao banco falhou");
			System.out.println(ex.getMessage());			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conexao;
	}
	
	public void close() {
		try {
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
