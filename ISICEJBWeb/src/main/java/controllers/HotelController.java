package controllers;

import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import entities.Ville; 
import controllers.VilleController;
import dao.IDaoLocale;
import dao.IDaoLocale2;
import entities.Hotel;

@WebServlet("/HotelController")
public class HotelController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private IDaoLocale2<Hotel> ejb1;
    @EJB
    private IDaoLocale<Ville> ejb;

    public HotelController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer la liste des hotels
        List<Hotel> hotels =ejb1.findAll();
        request.getSession().setAttribute("hotels", hotels);

        
        // Rediriger vers la page de gestion des hotels
        RequestDispatcher dispatcher = request.getRequestDispatcher("hotel.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("Action : " + action);
        String idParameter = request.getParameter("id");

        if ("delete".equals(action)) {
            if (idParameter != null) {
                int hotelId = Integer.parseInt(idParameter);
                Hotel hotelToDelete =ejb1.findById(hotelId);

                if (hotelToDelete != null) {
                    boolean deleted =ejb1.delete(hotelToDelete);

                    if (deleted) {
                        System.out.println("Hotel supprimée avec succès");
                    } else {
                        System.out.println("La suppression de la hotel a échoué");
                    }
                } else {
                    System.out.println("Hotel non trouvée avec l'ID : " + hotelId);
                }
            } else {
                System.out.println("ID de la hotel non spécifié");
            }
        
        } else if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String nom = request.getParameter("nom");
            String adresse = request.getParameter("adresse");
            String telephone = request.getParameter("telephone");
            Hotel hotelToEdit =ejb1.findById(id);
            if (hotelToEdit != null) {
                hotelToEdit.setId(id);
               // hotelToEdit.setAdresse(adresse);
               // hotelToEdit.setTelephone(telephone);
                
               ejb1.update(hotelToEdit);
                   
	         // Mettre à jour la liste des hotels
	        	List<Hotel> hotelsToEdit =ejb1.findAll();
	        	request.getSession().setAttribute("hotels", hotelsToEdit);
	        	System.out.println("Liste des hotels après modification : " + hotelsToEdit);
		        response.sendRedirect(request.getContextPath() + "/HotelController");
                
	        }
	     
        } else if ("filterByVille".equals(action)) {
            String villeIdParam = request.getParameter("filterVille");

            if (villeIdParam != null && !villeIdParam.isEmpty()) {
                int villeId = Integer.parseInt(villeIdParam);

                if (villeId == 0) {
                    // Afficher tous les hôtels si l'ID de la ville est 0
                    List<Hotel> hotelList =ejb1.findAll();
                    request.setAttribute("Hotels", hotelList);
                } else {
                    
					// Filtrer les hôtels par l'ID de la ville
                    Ville ville = ejb.findById(villeId);
                    
                    if (ville != null) {
                        List<Hotel> hotelList = ejb.filterHotelsByVille(ville);
                        request.setAttribute("Hotels", hotelList);
                   
                
            
                } else {
                        System.out.println("Ville non trouvée avec l'ID : " + villeId);
                    }
                }
            } else {
                System.out.println("ID de la ville non spécifié");
            }
            List<Hotel> filtrage =ejb1.findAll();
        	request.getSession().setAttribute("hotels", filtrage);
        	System.out.println("Liste des hotels après filtrage : " + filtrage);
            response.sendRedirect(request.getContextPath() + "/HotelController");
        
        }
    else {
	    String nom = request.getParameter("nom");
	    String adresse = request.getParameter("adresse");
	    String telephone = request.getParameter("telephone");
	    Hotel hotel = new Hotel(nom, adresse, telephone);
		    ejb1.create(hotel);
    }
    	// Mettre à jour la liste des hotels
    	List<Hotel> hotels = ejb1.findAll();
    	request.getSession().setAttribute("hotels", hotels);
    	System.out.println("Liste des hotels après l'ajout : " + hotels);

       // Rediriger vers la page de gestion des hotels
         response.sendRedirect(request.getContextPath() + "/HotelController");

     }
    }

