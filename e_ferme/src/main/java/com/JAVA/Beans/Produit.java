package com.JAVA.Beans;

/**
 * Produit.java
 * Cette classe modèle représente une entité Produit.
 */
public class Produit {
    protected Long idProduit;     // Identifiant du produit
    protected String nom;         // Nom du produit
    protected String description; // Description du produit
    protected Double prix;        // Prix du produit
    protected Long idCategorie;   // Identifiant de la catégorie
    protected Long idPromotion;   // Identifiant de la promotion

    // Constructeur par défaut
    public Produit() {
        super();
    }

    // Constructeur avec paramètres (sans identifiant)
    public Produit(String nom, String description, Double prix, Long idCategorie, Long idPromotion) {
        super();
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.idCategorie = idCategorie;
        this.idPromotion = idPromotion;
    }

    // Constructeur avec tous les paramètres
    public Produit(Long idProduit, String nom, String description, Double prix, Long idCategorie, Long idPromotion) {
        super();
        this.idProduit = idProduit;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.idCategorie = idCategorie;
        this.idPromotion = idPromotion;
    }

    // Getters et Setters
    public Long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Long getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Long idCategorie) {
        this.idCategorie = idCategorie;
    }

    public Long getIdPromotion() {
        return idPromotion;
    }

    public void setIdPromotion(Long idPromotion) {
        this.idPromotion = idPromotion;
    }

    // Méthode toString pour afficher les informations du produit
    @Override
    public String toString() {
        return "Produit [idProduit=" + idProduit + ", nom=" + nom + ", description=" + description + ", prix=" + prix
                + ", idCategorie=" + idCategorie + ", idPromotion=" + idPromotion + "]";
    }
}
