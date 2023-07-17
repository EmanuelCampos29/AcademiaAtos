package PlataformaVagas.Model;

import java.io.Serializable;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("unused")
@Entity
public class Vagas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;

	@NotNull
	private String nome;

	@NotNull
	@Column(length = 5000)
	private String descricao;

	@NotNull
	private String cidade;

	@NotNull
	private double salario;

	@OneToMany(mappedBy = "vaga", cascade = CascadeType.REMOVE)
	private List<Candidatos> candidatos;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public List<Candidatos> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(List<Candidatos> candidatos) {
		this.candidatos = candidatos;
	}
}
