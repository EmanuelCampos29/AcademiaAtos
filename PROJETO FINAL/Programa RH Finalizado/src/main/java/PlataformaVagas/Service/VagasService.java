package PlataformaVagas.Service;




import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import PlataformaVagas.Model.Vagas;
import PlataformaVagas.Rpository.*;


@Service
public class VagasService {

    private final VagaRepository vagaRepository;

    @Autowired
    public VagasService(VagaRepository vagaRepository) {
        this.vagaRepository = vagaRepository;
    }

    public Vagas encontrarPorCodigo(long codigo) {
        return vagaRepository.findByCodigo(codigo);
    }

    public Iterable<Vagas> listarTodas() {
        return vagaRepository.findAll();
    }

    public Vagas salvarVaga(Vagas vagas) {
        return vagaRepository.save(vagas);
    }

    public void excluirVaga(long codigo) {
        vagaRepository.deleteById(codigo);
    }

    public Vagas editarVaga(Vagas vagas) {
        Vagas vagaExistente = vagaRepository.findByCodigo(vagas.getCodigo());
        if (vagaExistente == null) {
            // Lógica de tratamento para vaga inexistente
            return null;
        }

        vagaExistente.setNome(vagas.getNome());
        vagaExistente.setCidade(vagas.getCidade());
        vagaExistente.setSalario(vagas.getSalario());
        vagaExistente.setDescricao(vagas.getDescricao());

        return vagaRepository.save(vagaExistente);
    }


    // Adicione outros métodos conforme necessário para a lógica de negócio das vagas

}
