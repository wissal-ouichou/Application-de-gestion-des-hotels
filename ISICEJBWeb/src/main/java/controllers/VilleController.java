package controllers;

import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import dao.IDaoLocale;
import entities.Ville;

@WebServlet("/VilleController")
public class VilleController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private IDaoLocale<Ville> ejb;

    public VilleController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer la liste des villes
        List<Ville> villes = ejb.findAll();
        request.getSession().setAttribute("villes", villes);

        // Rediriger vers la page de gestion des villes
        RequestDispatcher dispatcher = request.getRequestDispatcher("ville.jsp");
        dispatcher.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("Action : " + action);
        String idParameter = request.getParameter("id");

        if ("delete".equals(action)) {
            if (idParameter != null) {
                int villeId = Integer.parseInt(idParameter);
                Ville villeToDelete = ejb.findById(villeId);

                if (villeToDelete != null) {
                    boolean deleted = ejb.delete(villeToDelete);

                    if (deleted) {
                        System.out.println("Ville supprimée avec succès");
                    } else {
                        System.out.println("La suppression de la ville a échoué");
                    }
                } else {
                    System.out.println("Ville non trouvée avec l'ID : " + villeId);
                }
            } else {
                System.out.println("ID de la ville non spécifié");
            }
        
        } else if ("edit".equals(action)) {
            int villeId = Integer.parseInt(request.getParameter("id"));
            Ville villeToEdit = ejb.findById(villeId);

            if (villeToEdit != null) {
                // Modifier la ville
            	String property = request.getParameter("property");
                String newNom = request.getParameter("newNom");
                villeToEdit.setNom(newNom);
                ejb.update(villeToEdit);
            }
        } else {
            // Ajouter une nouvelle ville
            String nom = request.getParameter("ville");
            Ville nouvelleVille = new Ville(nom);
            ejb.create(nouvelleVille);
            System.out.println("Ville ajoutée avec succès: " + nouvelleVille.getNom());
        }

        // Mettre à jour la liste des villes
        List<Ville> villes = ejb.findAll();
        request.getSession().setAttribute("villes", villes);
        System.out.println("Liste des villes après l'ajout : " + villes);

        // Rediriger vers la page de gestion des villes
        response.sendRedirect(request.getContextPath() + "/VilleController");
    }
}