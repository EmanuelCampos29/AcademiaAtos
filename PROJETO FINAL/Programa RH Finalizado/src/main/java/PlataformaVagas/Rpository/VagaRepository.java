package PlataformaVagas.Rpository;

import org.springframework.stereotype.Repository;

import java.util.List;



import org.springframework.data.repository.CrudRepository;


import PlataformaVagas.Model.Vagas;


@Repository
public interface VagaRepository extends CrudRepository<Vagas, Long> {

	
	Vagas findByCodigo(long codigo);
	List<Vagas> findByNome(String nome);
	Vagas getOne(long codigo);
	
	
}
