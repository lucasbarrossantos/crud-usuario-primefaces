package br.com.crud.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.crud.model.Usuario;
import br.com.crud.repository.UsuarioRepository;
import br.com.crud.repository.filter.UsuarioFilter;
import br.com.crud.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaUsuarioBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private UsuarioRepository usuarioRepository;
	
	private UsuarioFilter filtro; // Filtro dinamico
	private List<Usuario> usuariosFiltrados = new ArrayList<Usuario>(); // Lista para guardar os Usuarios filtrados
	private Usuario usuarioSelecionado; // Usuário para ser editado
	
	@Inject
	public PesquisaUsuarioBean(UsuarioRepository usuarioRepository) {
		this.filtro = new UsuarioFilter();
		this.usuarioRepository = usuarioRepository;
	}
	
	public void pesquisar() {
		this.usuariosFiltrados = usuarioRepository.filtrados(filtro);
	}
	
	public void excluir() {
		this.usuarioRepository.excluir(this.usuarioSelecionado);
		this.usuariosFiltrados.remove(this.usuarioSelecionado);
		
		FacesUtil.addInfoMessage("Usuário '" + usuarioSelecionado.getNome() + "' foi excluído com sucesso.");
	}
	
	// Getters e Setters
	
	public UsuarioFilter getFiltro() {
		return filtro;
	}
	
	public List<Usuario> getUsuariosFiltrados() {
		return usuariosFiltrados;
	}
	
	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}
	
	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

}
