package com.tunisianwatchme.Entity;

import java.util.Date;

public class Commentaire {

    private int id;
    private String texte;
    private Utilisateur user;
    private String date;
    private int idReclamation;

    public Commentaire() {

    }

    public Commentaire(int id, String texte, Utilisateur user, String date, int idReclamation) {
        this.id = id;
        this.texte = texte;
        this.user = user;
        this.date = date;
        this.idReclamation = idReclamation;
    }

    public Commentaire(String texte, Utilisateur user, String date, int idReclamation) {
        this.texte = texte;
        this.user = user;
        this.date = date;
        this.idReclamation = idReclamation;
    }

    public int getId() {
        return id;
    }

    public String getTexte() {
        return texte;
    }

    public Utilisateur getUser() {
        return user;
    }

    public String getDate() {
        return date;
    }

    public int getIdReclamation() {
        return idReclamation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setIdReclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }

    public String toString() {
        return "id: " +id+" texte: "+texte   ;
    }
}
