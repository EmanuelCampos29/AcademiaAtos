package JDBC;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;






public class BancoDados implements InterfaceBancoDados {
    
    private Connection connection;
	
    
    @Override
    public void conectar(String db_url, String db_user, String db_password)  {
    	
    	
    	
        try {
            connection = DriverManager.getConnection(db_url, db_user, db_password);
            System.out.println("Conexão com o banco de dados estabelecida!");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }
    
    @Override
    public void desconectar() {
        try {
            connection.close();
            System.out.println("Conexão com o banco de dados encerrada!");
        } catch (SQLException e) {
            System.out.println("Erro ao encerrar a conexão com o banco de dados: " + e.getMessage());
        }
    }
    
    
    @Override
    public void consultar(String db_query) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(db_query);
            
            while (resultSet.next()) {
                String cargo = resultSet.getString("cargo");
                String email = resultSet.getString("email");
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                
                System.out.println("Cargo: " + cargo);
                System.out.println("Email: " + email);
                System.out.println("ID: " + id);
                System.out.println("Nome: " + nome);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao executar a consulta: " + e.getMessage());
        }
    }
    
    @Override
    public int inserirAlterarExcluir(String db_query) {
        int numRowsAffected = 0;
        try {
            Statement statement = connection.createStatement();
            numRowsAffected = statement.executeUpdate(db_query);
            System.out.println(numRowsAffected + " linhas foram afetadas.");
        } catch (SQLException e) {
            System.out.println("Erro ao executar a operação: " + e.getMessage());
        }
        return numRowsAffected;
    }
    
 
    
}