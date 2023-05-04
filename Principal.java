package JDBC;
import java.io.File;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import br.com.loggin.*;


public class Principal {

	public static void main(String[] args) throws IOException {
		
		Log meuLogger = new Log("Log.txt");
		
		try (Scanner leitura = new Scanner(System.in)) {
			
			meuLogger.logger.setLevel(Level.INFO);
			meuLogger.logger.info("Iniciando Banco de dados ");
		
		
		
		
	}
        BancoDados bd = new BancoDados();
        String db_url = "JDBC:mysql://localhost:3306/reuniao";
        String db_user = "root";
        String db_password = "";
        
        bd.conectar(db_url, db_user, db_password);
        
        String query = "INSERT INTO pessoa (cargo, email, id, nome) " +
                "VALUES ('Analista', 'analista@gmail.com','24','manu')";
        
      
        bd.inserirAlterarExcluir(query);
        
        meuLogger.logger.info("Dados inseridos com sucesso ");
        
     
     
 
        query = "SELECT * FROM pessoa";
        bd.consultar(query);
        
        meuLogger.logger.info("Exibindo resultado das pesquisas ");
 


        
      
        
      
        
        bd.desconectar();
        meuLogger.logger.info(" Programa finalizado ");
	}
}
