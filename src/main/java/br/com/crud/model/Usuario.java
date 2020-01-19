package br.com.crud.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false, length = 60)
	private String nome;
	
	@Email
	@NotBlank
	@Column(nullable = false, length = 100)
	private String email;

	@Column(nullable = false, length = 60)
	private String senha;
	
	/**
	 * OrphanRemoval = true - Porque não pode ter telefone sem ter um usuário para ele. Dessa maneira se tiver o próprio Hibernate excluir esse registro
	 * CascadeType.MERGE - Vai salvar os filhos quando o pai for salvo.
	 * @NotFound(action = NotFoundAction.IGNORE) - Usei para remover o erro "javax.persistence.EntityNotFoundException" pois ao tentar remover um telefone da lista de Telefones do Usuário  
	 * e tentar Atualizar o Objeto usuário novamente, então apresentava esse erro porque o Hibernate tenta acessar o fiilho desse relacionamento, mas ele foi removido. (Esse foi o jeito que resolvi)
	 * A outra maneira seria trocar o modelo cascade por ALL para executar todas as operações em cascata, mas esse modelo não é muito recomendado.
	 */
	
	@NotFound(action = NotFoundAction.IGNORE)
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.MERGE)
	private Set<Telefone> telefones = new HashSet<Telefone>(); // Com o Set<?> é garantido que não tenha telefones duplicados na lista.
	
	public void addTelefone(Telefone telefone) {
		this.telefones.add(telefone);
		telefone.setUsuario(this);
	}
	
	public void removerTelefone(Telefone telefone) {
		this.telefones.remove(telefone);
		telefone.setUsuario(null);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Set<Telefone> getTelefones() {
		return telefones;
	}
	
	public void setTelefones(Set<Telefone> telefones) {
		this.telefones = telefones;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + "]";
	}

}
