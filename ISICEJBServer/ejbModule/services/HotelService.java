package services;

import java.util.List;

import dao.IDaoLocale;
import dao.IDaoLocale2;
import dao.IDaoRemote;
import entities.Hotel;
import entities.Ville;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

@Stateless(name = "wissal")
public class HotelService implements IDaoRemote<Hotel>, IDaoLocale2<Hotel> {
		
		@PersistenceContext
		private EntityManager em;

		@Override
		@PermitAll
		public Hotel create(Hotel o) {
			em.persist(o);
			return o;
		}

		@Override
		@PermitAll
		public boolean delete(Hotel o) {
		    try {
		        em.remove(em.contains(o) ? o : em.merge(o));
		        System.out.println("Hotel supprimée avec succès : " + o.getId());
		        return true;
		    } catch (Exception e) {
		        System.out.println("Erreur lors de la suppression de l'hotel : " + e.getMessage());
		        return false;
		    }
		}
		
		@Override
		@PermitAll
		public Hotel update(Hotel o) {
		    Hotel updatedHotel = em.merge(o);
		    return updatedHotel;
		}


		@Override
		@PermitAll
		public Hotel findById(int id) {
			// TODO Auto-generated method stub
			return em.find(Hotel.class, id);
		}

		@Override
		@PermitAll
		public List<Hotel> findAll() {
			Query query = em.createQuery("select h from Hotel h");
			return query.getResultList();
		}
		public List<Hotel> filterHotelsByVille(Ville ville){
			Query query = em.createQuery("select h from Hotel h");
			return query.getResultList();
		}
	}



