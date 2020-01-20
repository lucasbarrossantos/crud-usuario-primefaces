package br.com.crud.controller;

import javax.inject.Named;

import br.com.crud.model.Usuario;
import br.com.crud.util.SessionUtils;

@Named
public class UsuarioSistema {

	public Usuario getUsuarioLogado() {
		return SessionUtils.getUsuarioSistema();
	}
	
}
