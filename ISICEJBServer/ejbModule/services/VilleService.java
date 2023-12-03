package services;

import java.util.List;

import dao.IDaoLocale;
import dao.IDaoRemote;
import entities.Hotel;
import entities.Ville;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless(name = "kenza")
public class VilleService implements IDaoRemote<Ville>, IDaoLocale<Ville> {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@PermitAll
	public Ville create(Ville o) {
		em.persist(o);
		return o;
	}

	@Override
	@PermitAll
	public boolean delete(Ville o) {
	    try {
	        em.remove(em.contains(o) ? o : em.merge(o));
	        System.out.println("Ville supprimée avec succès : " + o.getId());
	        return true;
	    } catch (Exception e) {
	        System.out.println("Erreur lors de la suppression de la ville : " + e.getMessage());
	        return false;
	    }
	}

	@Override
	@PermitAll
	public Ville update(Ville o) {
	    Ville updatedVille = em.merge(o);
	    return updatedVille;
	}

	@Override
	@PermitAll
	public Ville findById(int id) {
		// TODO Auto-generated method stub
		return em.find(Ville.class, id);
	}

	@Override
	@PermitAll
	public List<Ville> findAll() {
		Query query = em.createQuery("select v from Ville v");
		return query.getResultList();
	}
	public List<Hotel> filterHotelsByVille(Ville ville){
		Query query = em.createQuery("select h from Hotel h");
		return query.getResultList();
	}
}