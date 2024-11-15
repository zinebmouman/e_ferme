package com.JAVA.DAO;

import com.JAVA.Beans.Panier;
import java.sql.SQLException;
import java.util.List;

public interface PanierDAO {
    void ajouterAuPanier(Panier panier) throws SQLException;
    
    List<Panier> getPanierParConsommateur(Long consommateurId) throws SQLException;
	void modifierQuantite(Long produitId, Long consommateurId, int nouvelleQuantite) throws SQLException;
	void supprimerDuPanier(Long produitId, Long consommateurId) throws SQLException;
}
