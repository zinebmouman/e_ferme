
package com.JAVA.Beans;

public class Panier {
    private Long id;
    private Long consommateurId;
    private Long produitId;
    private int quantite;

    // Constructeur par défaut
    public Panier() {}

    // Constructeur avec paramètres
    public Panier(Long consommateurId, Long produitId, int quantite) {
        this.consommateurId = consommateurId;
        this.produitId = produitId;
        this.quantite = quantite;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getConsommateurId() {
        return consommateurId;
    }

    public void setConsommateurId(Long consommateurId) {
        this.consommateurId = consommateurId;
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
