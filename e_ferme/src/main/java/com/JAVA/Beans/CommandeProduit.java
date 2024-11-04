package com.JAVA.Beans;

/**
 * CommandeProduit.java
 * Cette classe modèle représente une entité CommandeProduit.
 */
public class CommandeProduit {
    protected Long idCommandeProduit; // Identifiant de la commande produit
    protected Long idCommande;        // Identifiant de la commande
    protected Long idProduit;         // Identifiant du produit
    protected int quantite;           // Quantité du produit commandé

    // Constructeur par défaut
    public CommandeProduit() {
        super();
    }

    // Constructeur avec paramètres (sans identifiant)
    public CommandeProduit(Long idCommande, Long idProduit, int quantite) {
        super();
        this.idCommande = idCommande;
        this.idProduit = idProduit;
        this.quantite = quantite;
    }

    // Constructeur avec tous les paramètres
    public CommandeProduit(Long idCommandeProduit, Long idCommande, Long idProduit, int quantite) {
        super();
        this.idCommandeProduit = idCommandeProduit;
        this.idCommande = idCommande;
        this.idProduit = idProduit;
        this.quantite = quantite;
    }

    // Getters et Setters
    public Long getIdCommandeProduit() {
        return idCommandeProduit;
    }

    public void setIdCommandeProduit(Long idCommandeProduit) {
        this.idCommandeProduit = idCommandeProduit;
    }

    public Long getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Long idCommande) {
        this.idCommande = idCommande;
    }

    public Long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    // Méthode toString pour afficher les informations de la commande produit
    @Override
    public String toString() {
        return "CommandeProduit [idCommandeProduit=" + idCommandeProduit + ", idCommande=" + idCommande 
                + ", idProduit=" + idProduit + ", quantite=" + quantite + "]";
    }
}
