package JDBC;

public interface InterfaceBancoDados {

	void conectar(String db_url, String db_user, String db_password);

	int inserirAlterarExcluir(String db_query);

	void consultar(String db_query);

	void desconectar();



	

}
