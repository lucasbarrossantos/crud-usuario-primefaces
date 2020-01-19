package br.com.crud.repository;

import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.crud.model.Telefone;
import br.com.crud.service.NegocioException;
import br.com.crud.util.TransactionalOperation;

public class TelefoneRepository implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Telefone findById(Long id) {
		return this.manager.find(Telefone.class, id);
	}
	
	@TransactionalOperation
	public void removerTelefone(Telefone telefone) {
		try {
			telefone = findById(telefone.getId());
			this.manager.remove(telefone);
		} catch (Exception e) {
			e.getStackTrace();
			throw new NegocioException("Não foi possível remover o telefone!!");
		}
	}

}
