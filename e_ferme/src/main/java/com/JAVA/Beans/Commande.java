package com.JAVA.Beans;

/**
 * Commande.java
 * Cette classe modèle représente une entité Commande.
 */
public class Commande {
    protected Long idCommande;   // Identifiant de la commande
    protected Long idConsommateur; // Identifiant du consommateur
    protected String dateCommande; // Date de la commande
    protected Double total;      // Total de la commande

    // Constructeur par défaut
    public Commande() {
        super();
    }

    // Constructeur avec paramètres (sans identifiant)
    public Commande(Long idConsommateur, String dateCommande, Double total) {
        super();
        this.idConsommateur = idConsommateur;
        this.dateCommande = dateCommande;
        this.total = total;
    }

    // Constructeur avec tous les paramètres
    public Commande(Long idCommande, Long idConsommateur, String dateCommande, Double total) {
        super();
        this.idCommande = idCommande;
        this.idConsommateur = idConsommateur;
        this.dateCommande = dateCommande;
        this.total = total;
    }

    // Getters et Setters
    public Long getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Long idCommande) {
        this.idCommande = idCommande;
    }

    public Long getIdConsommateur() {
        return idConsommateur;
    }

    public void setIdConsommateur(Long idConsommateur) {
        this.idConsommateur = idConsommateur;
    }

    public String getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(String dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    // Méthode toString pour afficher les informations de la commande
    @Override
    public String toString() {
        return "Commande [idCommande=" + idCommande + ", idConsommateur=" + idConsommateur + ", dateCommande=" + dateCommande
                + ", total=" + total + "]";
    }
}
