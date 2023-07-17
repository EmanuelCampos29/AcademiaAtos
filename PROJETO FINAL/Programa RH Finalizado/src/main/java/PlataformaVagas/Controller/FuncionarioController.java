package PlataformaVagas.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import PlataformaVagas.Model.Funcionario;

import PlataformaVagas.Rpository.funcRepository;

@Controller
public class FuncionarioController {

	@Autowired
	private funcRepository fr;

	// chama formulario para cadastro dos maluco
	@GetMapping(value = "/cadastroFunc")
	public String form() {
		return "Funcionarios/novoFuncionario";
	}

	// cadastra os funcionario
	@PostMapping(value = "/cadastroFun")
	public String form(@Valid Funcionario funcionario, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("mensagem", "Preencha os campos por favor");
			return "Funcionarios/novoFuncionario";
		}

		fr.save(funcionario);
		model.addAttribute("mensagem", "Funcionário Cadastrado com Sucesso!");
		return "Funcionarios/novoFuncionario";
	}

	// deleta o funcionario
	@GetMapping(value = "/deleteFunc")
	public String delete(long id) {
		Funcionario funcionario = fr.findById(id);
		fr.delete(funcionario);
		return "redirect:/ListaFunc";

	}

	/// Método para carregar a página de edição de funcionário
	@GetMapping("/editarFuncionario")
	public String editarFuncionario(@RequestParam("id") long id, Model model) {
		Funcionario funcionario = fr.findById(id);
		if (funcionario == null) {
			model.addAttribute("mensagem", "Funcionário não encontrado");
			return "redirect:/ListaFunc";
		}
		model.addAttribute("funcionario", funcionario);
		return "Funcionarios/editarFuncionarios";
	}

	@PostMapping(value = "/updateFuncionario")
	public String updateFunc(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Preencha os campos corretamente");
			return "redirect:/editarFuncionario?id=" + funcionario.getId();
		}

		Funcionario funcionarioExistente = fr.findById(funcionario.getId());
		if (funcionarioExistente == null) {
			attributes.addFlashAttribute("mensagem", "Funcionário não encontrado");
			return "redirect:/ListaFunc";
		}

		funcionarioExistente.setNome(funcionario.getNome());
		funcionarioExistente.setData(funcionario.getData());
		funcionarioExistente.setEmail(funcionario.getEmail());
		funcionarioExistente.setCpf(funcionario.getCpf());
		funcionarioExistente.setTelefone(funcionario.getTelefone());
		funcionarioExistente.setSalario(funcionario.getSalario());

		fr.save(funcionarioExistente);
		attributes.addFlashAttribute("mensagem", "Funcionário atualizado com sucesso");
		return "redirect:/ListaFunc";
	}

	// listar os funcionario
	@GetMapping(value = "/ListaFunc")
	public ModelAndView listaFunc() {
		ModelAndView modelv = new ModelAndView("Funcionarios/ListaFunc");
		Iterable<Funcionario> funcionarios = fr.findAll();
		modelv.addObject("Funcionarios", funcionarios);
		return modelv;
	}

	@GetMapping(value = "/detalheFuncionario/{codigo}")
	public ModelAndView detalhesf(@PathVariable("codigo") long codigo) {
		Funcionario funcionario = fr.findById(codigo);
		if (funcionario == null) {
			// Funcionário não encontrado, redireciona para a página ListaFunc
			return new ModelAndView("redirect:/ListaFunc");
		}

		ModelAndView mv = new ModelAndView("Funcionarios/detalhesFunc");
		mv.addObject("funcionario", funcionario);
		return mv;
	}

}