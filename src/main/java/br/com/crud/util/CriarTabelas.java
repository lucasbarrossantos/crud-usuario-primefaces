package br.com.crud.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.crud.model.Telefone;
import br.com.crud.model.Usuario;

public class CriarTabelas {

	private static EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public static void main(String... args) {
	    EntityManagerFactory entityManagerFactory 
	        = Persistence.createEntityManagerFactory("db-dev");
	    entityManager = entityManagerFactory.createEntityManager();
	 
	    Usuario usuario = new Usuario();
	    usuario.setEmail("lucas-barros28@hotmail.com");
	    usuario.setNome("Lucas Barros Santos");
	    usuario.setSenha("123456");
	    
	    Telefone telefone1 = new Telefone();
	    telefone1.setDdd("87");
	    telefone1.setNumero("87996421975");
	    telefone1.setTipo("Celular");
	    usuario.addTelefone(telefone1);
	    
	    Telefone telefone2 = new Telefone();
	    telefone2.setDdd("87");
	    telefone2.setNumero("32124003");
	    telefone2.setTipo("Fixo");
	    usuario.addTelefone(telefone2);
	    
	    entityManager.getTransaction().begin();
	    entityManager.merge(usuario);
	    entityManager.flush();
	    entityManager.getTransaction().commit();
	    
	    
		List<Telefone> telefones = (List<Telefone>) entityManager.createQuery("from Telefone").getResultList();
	    
	    System.out.println("\n 1º Listagem");
	    telefones.forEach(t -> {
	    	System.out.println(t.toString());
	    });
	    
	    entityManager.getTransaction().begin();
	    Usuario usuario2 = entityManager.find(Usuario.class, 1L);
	    Telefone telefone3 = entityManager.find(Telefone.class, 1L);
	    usuario2.removerTelefone(telefone3);
	    
	    entityManager.remove(telefone3);
	    entityManager.merge(usuario2);
	    entityManager.flush();
	    
	    entityManager.getTransaction().commit();
	    
	    List<Telefone> telefones2 = (List<Telefone>) entityManager.createQuery("from Telefone").getResultList();
		 
	    System.out.println("\n 2º Listagem");
	    telefones2.forEach(t -> {
	    	System.out.println(t.toString());
	    });
	    
	    entityManager.close();
	    entityManagerFactory.close();
	  }
	
}
