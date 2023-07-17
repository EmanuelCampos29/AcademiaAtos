package PlataformaVagas.Controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import PlataformaVagas.Model.Candidatos;
import PlataformaVagas.Model.Vagas;
import PlataformaVagas.Rpository.CandidatosRepository;
import PlataformaVagas.Rpository.VagaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class VagasController {

	@Autowired
	private VagaRepository vagaRepository;

	@Autowired
	private CandidatosRepository candidatosRepository;

	@GetMapping("/")
	public String t2() {
		return "Vagas/index";
	}

	@GetMapping("/deletarCandidato")
	public String deletarCandidato(@RequestParam("cpf") String cpf, RedirectAttributes attributes) {
		Candidatos candidato = candidatosRepository.findByCpf(cpf);
		if (candidato == null) {
			attributes.addFlashAttribute("erro", "Candidato não encontrado.");
		} else {
			candidatosRepository.delete(candidato);
			attributes.addFlashAttribute("mensagemSucesso", "Candidato excluído com sucesso.");
		}

		if (candidato != null) {
			return "redirect:/detalheVaga/" + candidato.getVaga().getCodigo();
		} else {
			// Caso o candidato não seja encontrado
			return "redirect:/paginaNaoEncontrada";
		}
	}

	@PostMapping("/adicionarCandidato")
	public String adicionarCandidato(@ModelAttribute("candidato") @Valid Candidatos candidato, BindingResult result,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("erro", "Erro ao adicionar candidato. Verifique os campos.");
			return "redirect:/detalheVaga/" + candidato.getVaga().getCodigo();
		}

		Vagas vaga = vagaRepository.findByCodigo(candidato.getVaga().getCodigo());
		if (vaga == null) {
			attributes.addFlashAttribute("erro", "Vaga não encontrada.");
			return "redirect:/detalheVaga/" + candidato.getVaga().getCodigo();
		}

		candidato.setVaga(vaga);
		candidatosRepository.save(candidato);

		attributes.addFlashAttribute("mensagemSucesso", "Candidato adicionado com sucesso.");
		return "redirect:/detalheVaga/" + vaga.getCodigo();
	}

	// CADASTRO DA VAGA

	@GetMapping(value = "/NovoCadastroVaga")
	public String t1(Model model) {
		model.addAttribute("vaga", new Vagas());
		return "Vagas/cadastroVaga";
	}

	@PostMapping(value = "/cadastrarVagasPost")
	public String form(@Valid @ModelAttribute("vaga") Vagas vagas, BindingResult result,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("aviso", "Valide os campos");
			return "redirect:/NovoCadastroVaga";
		}

		vagaRepository.save(vagas);
		attributes.addFlashAttribute("mensagem", "Vaga cadastrada com sucesso");
		return "redirect:/NovoCadastroVaga";
	}

	// LISTAR VAGAS
	@GetMapping("/ListarVaga")
	public String listarVagas(Model model) {
		List<Vagas> vagas = (List<Vagas>) vagaRepository.findAll();
		model.addAttribute("vagas", vagas);
		return "Vagas/listaVaga";
	}

	// DELETAR VAGA
	@GetMapping("/deletarVaga/{codigo}")
	public String delete(@PathVariable("codigo") long codigo, RedirectAttributes attributes,
			Authentication authentication) {
		boolean hasAdminRole = authentication.getAuthorities().stream()
				.anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

		if (hasAdminRole) {
			Vagas vagas = vagaRepository.findByCodigo(codigo);
			if (vagas != null) {
				vagaRepository.delete(vagas);
				attributes.addFlashAttribute("mensagem", "Vaga excluída com sucesso.");
			}
		} else {
			attributes.addFlashAttribute("erro", "Você não tem permissão para excluir a vaga.");
		}

		return "redirect:/ListarVaga";
	}

	@GetMapping(value = "/detalheVaga/{codigo}")
	public ModelAndView detalhesVaga(@PathVariable("codigo") long codigo) {
		Vagas vagas = vagaRepository.findByCodigo(codigo);
		List<Candidatos> candidatos = (List<Candidatos>) candidatosRepository.findByVaga(vagas);
		ModelAndView mv = new ModelAndView("Vagas/detalheVaga");
		mv.addObject("vaga", vagas);
		mv.addObject("candidatos", candidatos);
		return mv;
	}

	@GetMapping("/editar-vaga/{codigo}")
	public ModelAndView editarVaga(@PathVariable("codigo") long codigo) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		boolean hasAdminRole = authentication.getAuthorities().stream()
				.anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

		if (!hasAdminRole) {

			ModelAndView mv = new ModelAndView("acesso-negado");
			mv.addObject("mensagem", "Você não tem permissão para editar esta vaga.");
			return mv;
		}

		Vagas vagas = vagaRepository.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("Vagas/editarVaga");
		mv.addObject("vaga", vagas);
		return mv;
	}

	@PostMapping("/atualizarVaga")
	public String atualizarVaga(@Valid @ModelAttribute("vaga") Vagas vagas, BindingResult result,
			RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("aviso", "Valide os campos");
			return "redirect:/editar-vaga/" + vagas.getCodigo();
		}

		Vagas vagaExistente = vagaRepository.findByCodigo(vagas.getCodigo());
		if (vagaExistente == null) {
			attributes.addFlashAttribute("erro", "Vaga não encontrada");
			return "redirect:/ListarVaga";
		}

		vagaExistente.setNome(vagas.getNome());
		vagaExistente.setDescricao(vagas.getDescricao());
		vagaExistente.setCidade(vagas.getCidade());
		vagaExistente.setSalario(vagas.getSalario());

		vagaRepository.save(vagaExistente);

		attributes.addFlashAttribute("mensagem", "Vaga alterada com sucesso");
		return "redirect:/ListarVaga";
	}

}
