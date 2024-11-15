package com.JAVA.Servlet;

import com.JAVA.DAO.ProduitDAO;
import com.JAVA.DAO.ProduitDAOImp;
import com.JAVA.utils.DAOFactory;
import com.JAVA.Beans.Categorie;
import com.JAVA.Beans.Produit;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ListerProduits")
public class ListerProduitsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProduitDAOImp produitDAO;

    @Override
    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        produitDAO = new ProduitDAOImp(daoFactory);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Produit> produits = produitDAO.getAllProduits();
            List<Produit> produitsAvecPromotion = produitDAO.getProduitsAvecPromotion();
            List<Produit> produitsAvecOffre = produitDAO.getProduitsAvecOffre();
            List<Categorie> categories = produitDAO.getAllCategories();
            request.setAttribute("categories", categories);

            // Récupérer l'ID de catégorie à partir du paramètre 'idc'
            String categorieId = request.getParameter("idc");
            List<Produit> produitsParCategorie = new ArrayList<>();
            if (categorieId != null && !categorieId.isEmpty() && !categorieId.equals("0")) {
                produitsParCategorie = produitDAO.getProduitsParCategorie(Long.parseLong(categorieId));
                request.setAttribute("produitsParCategorie", produitsParCategorie);
            } else {
                // Si idc est 0, afficher tous les produits
                request.setAttribute("produits", produits);
            }

            // Passer les autres données à la vue
            request.setAttribute("produitsAvecPromotion", produitsAvecPromotion);
            request.setAttribute("produitsAvecOffre", produitsAvecOffre);
            System.out.println("Produits récupérés : " + produits);  // Ajoutez cette ligne pour vérifier

            // Vérifier le paramètre 'page' pour savoir quelle vue afficher
            String page = request.getParameter("page");
            if ("index".equals(page)) {
                request.getRequestDispatcher("/Client/views/index.jsp").forward(request, response);
            } else if ("home".equals(page)) {
                request.getRequestDispatcher("/Client/views/homme.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/Client/views/listeProduits.jsp").forward(request, response);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
