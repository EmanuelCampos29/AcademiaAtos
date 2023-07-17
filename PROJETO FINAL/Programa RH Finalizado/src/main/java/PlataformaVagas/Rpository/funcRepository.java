package PlataformaVagas.Rpository;



import org.springframework.data.repository.CrudRepository;


import PlataformaVagas.Model.Funcionario;

public interface funcRepository extends CrudRepository<Funcionario, String> {

	Funcionario findById(long id);

	Funcionario findByNome(String nome);

}
