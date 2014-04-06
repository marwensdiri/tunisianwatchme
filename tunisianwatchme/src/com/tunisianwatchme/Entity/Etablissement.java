package com.tunisianwatchme.Entity;

import java.util.Vector;
import javax.microedition.lcdui.Image;



public class Etablissement {

	private int id;
	private String nom;
	private String description;
	private Image image;
	private Lieu lieu;
        private Utilisateur responsable;
        private Vector listDomaine= new Vector() ;

    public Etablissement() {
    }

        
        
    public Etablissement(String nom, String description, Image image, Lieu lieu,Utilisateur responsable) {
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.lieu = lieu;
        this.responsable=responsable;
    }

    public Etablissement(int id, String nom, String description, Image image, Lieu lieu,Utilisateur responsable) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.lieu = lieu;
        this.responsable=responsable;
    }
    
    public void addDomaine(Domaine d){
        listDomaine.addElement(d);
    }
    
    public void removeDomaine(Domaine d){
        listDomaine.removeElement(d);
    }
    
    public void setListDomaine(Vector listDomaine){
        this.listDomaine=listDomaine;
    }
    
    public Vector getListDomaine(){
        return listDomaine;
    }

    public String toString() {
        return  nom ;
    }

    
    
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public Image getImage() {
        return image;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public Utilisateur getResponsable() {
        return responsable;
    }

    public void setResponsable(Utilisateur responsable) {
        this.responsable = responsable;
    }

    

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }        

}