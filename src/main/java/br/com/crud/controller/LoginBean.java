package br.com.crud.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import br.com.crud.model.Usuario;
import br.com.crud.repository.UsuarioRepository;
import br.com.crud.util.SessionUtils;
import br.com.crud.util.jsf.FacesUtil;

@Named
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioRepository usuarioRepository;

	private String email;
	
	private String senha;

	public void login() throws ServletException, IOException {
		
		Usuario usuario = usuarioRepository.findByEmailAndSenha(email, senha);
		
		if (usuario != null) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("usuario", usuario);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/CrudLogin/usuario/PesquisaUsuario.xhtml");
		} else {
			FacesUtil.addErrorMessage("Login inválido!");
		}
	}
	
	public void sair() throws IOException {
		HttpSession session = SessionUtils.getSession();
		session.removeAttribute("usuario");
		FacesContext.getCurrentInstance().getExternalContext().redirect("/CrudLogin/login.xhtml");
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

}
