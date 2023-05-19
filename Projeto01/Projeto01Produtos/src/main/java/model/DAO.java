package model;

import java.sql.Connection;



import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
	
	/** modulo de conex]ao **/
	//parametro de conexao
	
	private  String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "JDBC:mysql://localhost:3306/produto?useimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "";
	
	//metodo de conexao
	private Connection conectar () {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	
	//CRUD CRIAR
	public void inserirproduto(JavaBeans cadastro) {
		String create = "insert into produtos (codigo,nome,categoria,valor,quantidade) values (?,?,?,?,?)";
		try {
			//abrir a conexao
			Connection con = conectar();
			//prepara a query para execucao no banco
			PreparedStatement pst = con.prepareStatement(create);
			pst.setInt(1, cadastro.getCodigo());
			pst.setString(2, cadastro.getNome());
			pst.setString(3, cadastro.getCategoria());
			pst.setFloat(4, cadastro.getValor());
			pst.setInt(5, cadastro.getQuantidade());
			
			//executar
			pst.executeUpdate();
			// fechar conexao
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
//teste de conexao
	public void testeConexao() {
		try {
			Connection con = conectar();
			System.out.println("CONECADOTO COM SUCESO ");
			con.close();
		} catch (Exception e) {
			System.out.println("NAO DEU CERTO CONECAT ");
		}
	}
	
	// LISTAR PRODUTOS
	
	public ArrayList<JavaBeans> listarprodutos() {
		// criando obejto par acessar a outr classe javabeans
		ArrayList<JavaBeans> produtos = new ArrayList<>();
		String read = "select * from produtos order by nome";
		try {
			// abrir banco
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			// laaço enquando houver contato
			while (rs.next()) {
				//variaiveis de apoio que recebem os dados do banco
				String id = rs.getString(1);
				int codigo = rs.getInt(2);
				String nome = rs.getString(3);
				String categoria = rs.getString(4);
				float valor = rs.getFloat(5);
				int quantidade = rs.getInt(6);
				// armazenar oque chegou no banco no vetor array
				produtos.add(new JavaBeans(id,codigo,nome,categoria,valor,quantidade));
			}
			con.close();
			return produtos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	// ATUALIZAR PRODUTO
	//PRIMEIRO SELECIONAR DEPOIS EDITAR.
	public void selecionarproduto(JavaBeans cadastro) {
		String read2 = "select * from produtos where id = ?";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, cadastro.getId());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				//setar variaveis java
				cadastro.setId(rs.getString(1));
				cadastro.setCodigo(rs.getInt(2));
				cadastro.setNome(rs.getString(3));
				cadastro.setCategoria(rs.getString(4));
				cadastro.setValor(rs.getFloat(5));
				cadastro.setQuantidade(rs.getInt(6));
				
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	//Editar produto
	
	public void editarproduto(JavaBeans cadastro) {
		String criar = "update produtos set codigo=?, nome=?, categoria=?, valor=?, quantidade=? where id=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(criar);
					pst.setInt(1, cadastro.getCodigo());
					pst.setString(2, cadastro.getNome());
					pst.setString(3, cadastro.getCategoria());
					pst.setFloat(4, cadastro.getValor());
					pst.setInt(5, cadastro.getQuantidade());
					pst.setString(6, cadastro.getId());
					pst.executeUpdate();
					con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	
	}
	
	//Delete
	
	public void deletarproduto(JavaBeans cadastro) {
		String delete = "delete from produtos where id=?";
        try {
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(delete);
            pst.setString(1, cadastro.getId());
            pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
	
}
