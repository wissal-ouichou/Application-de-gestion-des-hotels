package dao;

import java.util.List;

import entities.Hotel;
import entities.Ville;
import jakarta.ejb.Local;

@Local
public interface IDaoLocale <T> {
	
	public T create(T o);
	public boolean delete(T o);
	public T update(T o);
	public T findById(int id);
	public List<T> findAll();
	public List<Hotel> filterHotelsByVille(Ville ville);

}
