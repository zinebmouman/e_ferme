package com.JAVA.DAO;

import com.JAVA.Beans.Produit;
import com.JAVA.Beans.Promotion;
import com.JAVA.Beans.Categorie;
import com.JAVA.Beans.Offre;
import com.JAVA.utils.DAOFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAOImp {
    private DAOFactory daoFactory;

    public ProduitDAOImp(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public List<Produit> getAllProduits() throws SQLException {
        List<Produit> produits = new ArrayList<>();
        String query = "SELECT * FROM produit";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Produit produit = new Produit();
                produit.setIdProduit((long) resultSet.getInt("id"));
                produit.setNom(resultSet.getString("nom"));
                produit.setPrix(resultSet.getDouble("prix"));
                produit.setQuantite(resultSet.getInt("quantite"));
                produit.setDescription(resultSet.getString("descreption"));
                produit.setImage(resultSet.getString("image"));
                produit.setDateRecolte(resultSet.getDate("date_recolte"));
                produit.setUserId((long) resultSet.getInt("user_id"));

                produits.add(produit);
            }
        }

        return produits;
    }

    public List<Produit> getProduitsAvecPromotion() throws SQLException {
        List<Produit> produits = new ArrayList<>();
        String query = "SELECT p.*, promo.taux, promo.date_debut, promo.date_fin, promo.description AS promo_description " +
                       "FROM produit p JOIN promotion promo ON p.id = promo.id_produit";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Produit produit = new Produit();
                produit.setIdProduit((long) resultSet.getInt("id"));
                produit.setNom(resultSet.getString("nom"));
                produit.setPrix(resultSet.getDouble("prix"));
                produit.setQuantite(resultSet.getInt("quantite"));
                produit.setDescription(resultSet.getString("descreption"));
                produit.setImage(resultSet.getString("image"));
                produit.setDateRecolte(resultSet.getDate("date_recolte"));
                produit.setUserId((long) resultSet.getInt("user_id"));

                Promotion promotion = new Promotion();
                promotion.setDateDebut(resultSet.getDate("date_debut"));
                promotion.setDateFin(resultSet.getDate("date_fin"));
                promotion.setTaux(resultSet.getDouble("taux"));
                promotion.setDescription(resultSet.getString("promo_description"));

                produit.setPromotion(promotion);

                produits.add(produit);
            }
        }

        return produits;
    }

    public List<Produit> getProduitsAvecOffre() throws SQLException {
        List<Produit> produits = new ArrayList<>();
        String query = "SELECT p.*, o.nom AS offre_nom, o.prix_pack, o.taux_reduction, o.date_debut, o.date_fin, o.description AS offre_description " +
                       "FROM produit p " +
                       "JOIN offre_produit op ON p.id = op.produit_id " +
                       "JOIN offre o ON op.offre_id = o.id";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Produit produit = new Produit();
                produit.setIdProduit((long) resultSet.getInt("id"));
                produit.setNom(resultSet.getString("nom"));
                produit.setPrix(resultSet.getDouble("prix"));
                produit.setQuantite(resultSet.getInt("quantite"));
                produit.setDescription(resultSet.getString("descreption"));
                produit.setImage(resultSet.getString("image"));
                produit.setDateRecolte(resultSet.getDate("date_recolte"));
                produit.setUserId((long) resultSet.getInt("user_id"));

                Offre offre = new Offre();
                offre.setNom(resultSet.getString("offre_nom"));
                offre.setPrixPack(resultSet.getDouble("prix_pack"));
                offre.setTauxReduction(resultSet.getDouble("taux_reduction"));
                offre.setDateDebut(resultSet.getDate("date_debut"));
                offre.setDateFin(resultSet.getDate("date_fin"));
                offre.setDescription(resultSet.getString("offre_description"));

                produit.setOffre(offre);

                produits.add(produit);
            }
        }

        return produits;
    }
    public List<Categorie> getAllCategories() throws SQLException {
        List<Categorie> categories = new ArrayList<>();
        String query = "SELECT * FROM categorie"; // Assurez-vous que cette requête correspond à votre base de données

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Categorie categorie = new Categorie();
                categorie.setIdCategorie(resultSet.getLong("id_categorie"));
                categorie.setNom(resultSet.getString("nom_categorie"));
                categorie.setdescription_categorie(resultSet.getString("description_categorie"));
                categories.add(categorie);
            }
        }

        System.out.println("Catégories récupérées: " + categories); // Ajoutez cette ligne pour vérifier
        return categories;
    }

    public List<Produit> getProduitsParCategorie(long idCategorie) throws SQLException {
        List<Produit> produits = new ArrayList<>();
        String query = "SELECT * FROM produit WHERE id_categorie = ?";
        System.out.println("Requête SQL: " + query);  // Ajoutez cette ligne pour vérifier la requête

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, idCategorie);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Produit produit = new Produit();
                    produit.setIdProduit(resultSet.getLong("id"));
                    produit.setNom(resultSet.getString("nom"));
                    produit.setPrix(resultSet.getDouble("prix"));
                    produit.setQuantite(resultSet.getInt("quantite"));
                    produit.setDescription(resultSet.getString("descreption"));
                    produit.setImage(resultSet.getString("image"));
                    produit.setDateRecolte(resultSet.getDate("date_recolte"));
                    produit.setUserId(resultSet.getLong("user_id"));

                    produits.add(produit);
                }
            }
        }
        System.out.println("Produits par catégorie: " + produits); // Ajoutez cette ligne pour vérifier les résultats
        return produits;
    }


    
}
