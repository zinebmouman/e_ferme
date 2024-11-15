package com.JAVA.Servlet;

import com.JAVA.Beans.Panier;
import com.JAVA.Beans.Produit;
import com.JAVA.DAO.PanierDAO;
import com.JAVA.DAO.PanierDAOImpl;
import com.JAVA.DAO.ProduitDAOImp;
import com.JAVA.utils.DAOFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/PanierServlet")
public class PanierServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PanierDAO panierDAO;
    private ProduitDAOImp produitDAO;

    @Override
    public void init() {
        this.panierDAO = new PanierDAOImpl(DAOFactory.getInstance());
        this.produitDAO = new ProduitDAOImp(DAOFactory.getInstance());
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String userIdParam = request.getParameter("user_id");
            if (userIdParam == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Le paramètre 'user_id' est requis.");
                return;
            }
            Long userId = Long.parseLong(userIdParam);
            List<Panier> paniers = panierDAO.getPanierParConsommateur(userId);

            List<Produit> produits = new ArrayList<>();
            for (Panier panier : paniers) {
                Produit produit = produitDAO.getProduitByID(panier.getProduitId());
                if (produit != null) {
                    produits.add(produit);
                }
            }
            System.out.println("Produits récupérés : " + produits); 
            System.out.println("Panier récupérés : " + paniers); 
            
            request.setAttribute("produits", produits);
            request.setAttribute("paniers", paniers);
            request.getRequestDispatcher("Client/views/Panier.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Le paramètre 'user_id' n'est pas valide.");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la récupération du panier.");
        }
    }
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long produitId = Long.parseLong(request.getParameter("produit_id"));
            Long consommateurId = Long.parseLong(request.getParameter("user_id"));
            int nouvelleQuantite = Integer.parseInt(request.getParameter("quantite"));

            // Mise à jour de la quantité
            panierDAO.modifierQuantite(produitId, consommateurId, nouvelleQuantite);
            response.sendRedirect(request.getContextPath() + "/PanierServlet?user_id=" + consommateurId );
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Les paramètres ne sont pas valides.");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la mise à jour de la quantité.");
        }
    }



	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        Long produitId = Long.parseLong(request.getParameter("produit_id"));
	        Long consommateurId = Long.parseLong(request.getParameter("user_id"));
	
	        // Suppression du produit du panier
	        panierDAO.supprimerDuPanier(produitId, consommateurId);
	        response.sendRedirect(request.getContextPath() + "/PanierServlet?user_id=" + consommateurId );
	    } catch (NumberFormatException e) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Le paramètre 'produit_id' ou 'consommateur_id' n'est pas valide.");
	    } catch (SQLException e) {
	        e.printStackTrace();
	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la suppression du produit du panier.");
	    }
	}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String methodOverride = request.getParameter("_method");
        if (methodOverride != null && methodOverride.equalsIgnoreCase("PUT")) {
            // Rediriger vers la méthode doPut() pour traiter la mise à jour
            doPut(request, response);
            return;
        } else if (methodOverride != null && methodOverride.equalsIgnoreCase("delete")) {
            doDelete(request, response);
            return;
        }
    	
        try {
            // Récupérer les paramètres du formulaire
            String userIdParam = request.getParameter("user_id");
            String idcParam = request.getParameter("idc");  // Paramètre 'idc' dans l'URL
            String produitIdParam = request.getParameter("produit_id");

            // Vérification de la validité des paramètres
            if (userIdParam == null || produitIdParam == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Les paramètres 'user_id' et 'produit_id' sont requis.");
                return;
            }

            // Parse des IDs
            Long userId = Long.parseLong(userIdParam);
            Long produitId = Long.parseLong(produitIdParam);

            // Si 'idc' n'est pas présent dans l'URL, attribuer la valeur '0' par défaut
            Long idc = (idcParam != null && !idcParam.isEmpty()) ? Long.parseLong(idcParam) : 0L;

            // Log des paramètres pour vérifier leur présence
            System.out.println("User ID: " + userId);
            System.out.println("Produit ID: " + produitId);
            System.out.println("Categorie ID: " + idc);

            // Ajout logique du produit au panier
            int quantite = 1; // Quantité par défaut
            Panier panier = new Panier(userId, produitId, quantite);
            panierDAO.ajouterAuPanier(panier);

            // Ajouter un message de succès à la requête
            request.setAttribute("successMessage", "Produit ajouté au panier avec succès.");

            // Redirection vers la page de liste des produits avec le paramètre 'idc' (ou '0' par défaut)
            response.sendRedirect(request.getContextPath() + "/ListerProduits?page=home&user_id=" + userIdParam + "&idc=" + idc + "#products-section");

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Les paramètres 'user_id' ou 'produit_id' ne sont pas des nombres valides.");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de l'ajout au panier.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Une erreur est survenue.");
        }
    }
}
