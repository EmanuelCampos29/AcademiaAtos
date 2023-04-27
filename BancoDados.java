package JDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class BancoDados implements InterfaceBancoDados {
    
    private Connection connection;
    
    @Override
    public void conectar(String db_url, String db_user, String db_password) {
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
    
    public static void main(String[] args) {
        BancoDados bd = new BancoDados();
        String db_url = "jdbc:mysql://localhost:3306/reuniao";
        String db_user = "root";
        String db_password = "";
        
        bd.conectar(db_url, db_user, db_password);
        
        String query = "INSERT INTO pessoa (cargo, email, id, nome) " +
                       "VALUES ('Gerente', 'teste@gmail.com,'1','emanuel')";
        
        bd.inserirAlterarExcluir(query);
        
        query = "INSERT INTO pessoa (cargo, email, id, nome) " +
                "VALUES ('Coordenador', 'coordenador@teste2', '2','dengo')";
        bd.inserirAlterarExcluir(query);
        
        query = "INSERT INTO pessoa (cargo, email, id, nome) " +
            "VALUES ('Analista', 'analista@teste3.com','3','jefinho')";
        bd.inserirAlterarExcluir(query);
        
        query = "SELECT * FROM pessoa";
        bd.consultar(query);
        
        bd.desconectar();
    }
}