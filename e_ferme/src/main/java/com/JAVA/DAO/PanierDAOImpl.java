package com.JAVA.DAO;

import com.JAVA.Beans.Panier;
import com.JAVA.utils.DAOFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PanierDAOImpl implements PanierDAO {
    private DAOFactory daoFactory;

    public PanierDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void ajouterAuPanier(Panier panier) throws SQLException {
        String query = "INSERT INTO panier (consommateur_id, produit_id, quantite) VALUES (?, ?, ?)";
        
        // Vérifier que les paramètres ne sont pas nuls ou invalides avant de préparer la requête
        if (panier == null || panier.getConsommateurId() == null || panier.getProduitId() == null) {
            throw new SQLException("Les informations du panier sont invalides.");
        }
        
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            // Préparer et exécuter la requête SQL
            statement.setLong(1, panier.getConsommateurId());  // Consommateur ID
            statement.setLong(2, panier.getProduitId());       // Produit ID
            statement.setInt(3, panier.getQuantite());         // Quantité
            
            int rowsAffected = statement.executeUpdate();
            
            if (rowsAffected == 0) {
                throw new SQLException("L'ajout au panier a échoué.");
            }
        } catch (SQLException e) {
            // Log de l'erreur pour le débogage
            e.printStackTrace();
            throw e;  // Rethrow l'exception pour la gestion dans la servlet
        }
    }


    @Override
    public void modifierQuantite(Long produitId, Long consommateurId, int nouvelleQuantite) throws SQLException {
        String query = "UPDATE panier SET quantite = ? WHERE produit_id = ? AND consommateur_id = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, nouvelleQuantite);
            statement.setLong(2, produitId);
            statement.setLong(3, consommateurId);
            statement.executeUpdate();
        }
    }

    @Override
    public void supprimerDuPanier(Long produitId, Long consommateurId) throws SQLException {
        String query = "DELETE FROM panier WHERE produit_id = ? AND consommateur_id = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, produitId);
            statement.setLong(2, consommateurId);
            statement.executeUpdate();
        }
    }


    @Override
    public List<Panier> getPanierParConsommateur(Long consommateurId) throws SQLException {
        List<Panier> paniers = new ArrayList<>();
        String query = "SELECT * FROM panier WHERE consommateur_id = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, consommateurId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Panier panier = new Panier();
                    panier.setId(resultSet.getLong("id"));
                    panier.setConsommateurId(resultSet.getLong("consommateur_id"));
                    panier.setProduitId(resultSet.getLong("produit_id"));
                    panier.setQuantite(resultSet.getInt("quantite"));
                    paniers.add(panier);
                }
            }
        }
        return paniers;
    }
}
