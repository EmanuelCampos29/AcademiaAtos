package PlataformaVagas.Model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.lang.NonNull;

@Entity
public class Candidatos {

	@Id
	@GeneratedValue
	private long id;

	@NonNull
	private String telefone;

	@NonNull
	private String nome;

	@Column(unique = true)
	private String cpf;

	@NonNull
	private String email;

	@ManyToOne
	private Vagas vaga;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Vagas getVaga() {
		return vaga;
	}

	public void setVaga(Vagas vaga) {
		this.vaga = vaga;
	}

}
