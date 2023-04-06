package dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Livro;

public class DAO {
	EntityManagerFactory emf;
	EntityManager em;
	
	public DAO() {
		try {
			emf = Persistence.createEntityManagerFactory("dev");
			em = emf.createEntityManager();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	//inserindo dados por demanda
	public boolean Insert (Livro livro) {
		boolean inseriu = false;
		try {
			em.getTransaction().begin();
			
			em.persist(livro);
			
			em.getTransaction().commit();
			
			inseriu = em.contains(livro);
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		return inseriu;
		
	}
	public Livro searchById (int id) {
		Livro livro = new Livro();
		try {
			 livro = em.find(Livro.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return livro;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Livro> list(){
		ArrayList<Livro> livros = new ArrayList<>();
		try {
			Query query = em.createQuery("From Livro livro");
			livros = (ArrayList<Livro>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return livros;
	}
	
	public boolean  update (Livro livro) {
		boolean atualizou = false;
		try {
			em.getTransaction().begin();
			
			em.merge(livro);
			
			em.getTransaction().commit();
			
			livro = em.find(Livro.class, livro.getId());
			if(livro != null) {
				atualizou = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		return atualizou;
	}
	public boolean delete (int id) {
		boolean deletou = true;
		try {
			em.getTransaction().begin();
			
			Livro livro = searchById(id);
			
			if(livro != null) {
				em.remove(livro);
				em.getTransaction().commit();
				deletou = em.contains(livro);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		return deletou;
	}

}
