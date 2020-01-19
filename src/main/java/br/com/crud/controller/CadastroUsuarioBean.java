package br.com.crud.controller;

import java.io.Serializable;
import java.util.Arrays;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.com.crud.model.Telefone;
import br.com.crud.model.Usuario;
import br.com.crud.service.CadastroUsuarioService;
import br.com.crud.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private CadastroUsuarioService cadastroUsuarioService;

	private Usuario usuario;
	private Telefone telefone;
	private boolean editandoTelefone;

	@Inject
	public CadastroUsuarioBean(CadastroUsuarioService cadastroUsuarioService) {
		this.cadastroUsuarioService = cadastroUsuarioService;
		this.usuario = new Usuario();
		limparDados();
	}

	public void salvar() {
		try {
			this.usuario = cadastroUsuarioService.salvar(usuario);
			FacesUtil.addInfoMessage("Usuário: '" + this.usuario.getNome() + "' - foi salvo com sucesso!");
			limparDados();
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}

	}

	public void prepararNovoTelefone() {
		this.telefone = new Telefone();
		this.editandoTelefone = false;
	}

	public void adicionarTelefone() {
		if (!this.usuario.getTelefones().contains(this.telefone)) {
			this.usuario.addTelefone(this.telefone);
			FacesUtil.addInfoMessage("Telefone foi adicionado com sucesso!");
			PrimeFaces.current().ajax().update(Arrays.asList(":frm:msg"));
		} else if (this.usuario.getTelefones().contains(this.telefone)) {
			FacesUtil.addInfoMessage("Telefone foi atualizado com sucesso!");
			PrimeFaces.current().ajax().update(Arrays.asList(":frm:msg"));
		}
	}

	public void excluirTelefone(Telefone telefone) {
		this.usuario.removerTelefone(telefone);

		if (telefone != null && telefone.getId() != null)
			this.cadastroUsuarioService.removerTelefone(telefone);

		FacesUtil.addInfoMessage("Telefone foi removido com sucesso!");
	}

	public void editarTelefone(Telefone telefone) {
		this.telefone = telefone;
		this.editandoTelefone = true;
	}

	public void inicializar() {
		if (this.usuario == null) {
			limparDados();
		}
	}

	private void limparDados() {
		usuario = new Usuario();
	}

	public boolean isEditando() {
		return usuario != null && usuario.getId() == null;
	}

	public String getDetalheUserEdit() {
		return usuario != null && usuario.getId() == null ? " Novo usuário "
				: "Edição do usuário: " + usuario.getNome();
	}

	// Getters and Setters

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public boolean isEditandoTelefone() {
		return editandoTelefone;
	}

}
