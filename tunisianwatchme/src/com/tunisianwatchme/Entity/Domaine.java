package com.tunisianwatchme.Entity;

public class Domaine {

	private int id;
	private String nom;

    public Domaine() {
    }

    public Domaine(Domaine d){
        this.id=d.id;
        this.nom=d.nom;
    }
    
    public Domaine(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Domaine(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
    public String toString() {
        return nom;
    }

    
    public int hashCode() {
        return id;
    }

    
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Domaine other = (Domaine) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

        
        
}
