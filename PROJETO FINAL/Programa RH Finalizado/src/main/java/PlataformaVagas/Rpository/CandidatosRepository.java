package PlataformaVagas.Rpository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import PlataformaVagas.Model.Candidatos;
import PlataformaVagas.Model.Vagas;

@Repository
public interface CandidatosRepository extends CrudRepository<Candidatos, Long> {
	Iterable<Candidatos> findByVaga(Vagas vagas);

	Candidatos findByCpf(String cpf);

	Candidatos findById(long id);

	List<Candidatos> findByNome(String nome);

	void delete(Candidatos can);

	@Query("SELECT c FROM Candidatos c WHERE c.cpf = :cpf")
	List<Candidatos> findByCpfCustomQuery(@Param("cpf") String cpf);
}
