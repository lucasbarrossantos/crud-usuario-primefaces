package br.com.crud.service;

import java.io.Serializable;
import javax.inject.Inject;

import br.com.crud.model.Telefone;
import br.com.crud.model.Usuario;
import br.com.crud.repository.TelefoneRepository;
import br.com.crud.repository.UsuarioRepository;
import br.com.crud.util.TransactionalOperation;

public class CadastroUsuarioService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioRepository usuarioRepository;

	@Inject
	private TelefoneRepository telefoneRepository;

	@TransactionalOperation
	public Usuario salvar(Usuario usuario) {
		return this.usuarioRepository.salvar(usuario);
	}

	@TransactionalOperation
	public void removerTelefone(Telefone telefone) {
		telefoneRepository.removerTelefone(telefone);
	}

}
