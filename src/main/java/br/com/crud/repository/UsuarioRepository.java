package br.com.crud.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import br.com.crud.model.Usuario;
import br.com.crud.repository.filter.UsuarioFilter;
import br.com.crud.service.NegocioException;

public class UsuarioRepository implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Usuario salvar(Usuario usuario) {
		return this.manager.merge(usuario);
	}

	public Usuario findById(Long id) {
		return manager.find(Usuario.class, id);
	}

	@SuppressWarnings("unchecked")
	public Usuario findByEmailAndSenha(String email, String senha) {
		List<Usuario> usuarios = manager.createQuery("from Usuario u where u.email = :email and u.senha = :senha")
				.setParameter("email", email).setParameter("senha", senha).getResultList();
		return usuarios.size() > 0 ? usuarios.get(0) : null;
	}

	public List<Usuario> filtrados(UsuarioFilter filtro) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
		Root<Usuario> root = criteria.from(Usuario.class);

		Predicate[] predicates = criarRestrincoes(filtro, builder, root);

		criteria.where(predicates);
		TypedQuery<Usuario> query = manager.createQuery(criteria);
		return query.getResultList().stream().sorted(Comparator.comparing(Usuario::getNome))
				.collect(Collectors.toList());
	}

	private Predicate[] criarRestrincoes(UsuarioFilter filtro, CriteriaBuilder builder, Root<Usuario> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(filtro.getNome())) {
			predicates.add(builder.like(builder.lower(root.get("nome")), "%" + filtro.getNome().toLowerCase() + "%"));
		}

		if (!StringUtils.isEmpty(filtro.getEmail())) {
			predicates.add(builder.equal(builder.lower(root.get("email")), filtro.getEmail().toLowerCase()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	public void excluir(Usuario usuario) {
		try {
			usuario = findById(usuario.getId()); // É preciso recuperar o Objeto para que ele faça parte desta
													// Transactional
			this.manager.remove(usuario);
			this.manager.flush(); // Aqui garante que o banco vai atualizar depois que excluir o objeto.
		} catch (Exception e) {
			e.getStackTrace();
			throw new NegocioException(
					"O Usuário não pode ser excluído! Entre em contato com o Administrador do Sistema.");
		}
	}

}
