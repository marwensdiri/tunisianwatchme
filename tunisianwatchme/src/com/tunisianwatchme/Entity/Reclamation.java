package com.tunisianwatchme.Entity;

import java.util.Date;
import java.util.Vector;


public class Reclamation {

    private int id;
    private Lieu lieu;
    private int idgeolocalisation;
    private String date;
    private String heure;
    private String description;
    private String titre;
    private Utilisateur citoyen;
    private Domaine domaine;
    private int etat;
    private Vector listEvaluation = new Vector();
    private Vector listDocument = new Vector();

    public Reclamation() {
        
    }

   

    public Vector getListDocument() {
        return listDocument;
    }

    public void setListDocument(Vector listDocument) {
        this.listDocument = listDocument;
    }

    public Vector getImages() {
        System.out.println(listDocument.size());
        Vector listPhoto = new Vector();
        for (int i = 0; i< listDocument.size();i++) {
            if (((Document)listDocument.elementAt(i)).getType() == 1) {
                listPhoto.addElement(((Document)listDocument.elementAt(i)).getImage());
            }
        }
        return listPhoto;
    }

    public void addEvaluation(Evaluation ev) {
        listEvaluation.addElement(ev);
    }

    public void removeEvaluation(Evaluation ev) {
        listEvaluation.removeElement(ev);
    }

    public int getidGeolocalisation() {
        return idgeolocalisation;
    }

    public void setidGeolocalisation(int geolocalisation) {
        this.idgeolocalisation = geolocalisation;
    }

    
    

    public int getId() {
        return id;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public String getDate() {
        return date;
    }

    public String getHeure() {
        return heure;
    }

    public String getDescription() {
        return description;
    }

    public String getTitre() {
        return titre;
    }

    public Utilisateur getCitoyen() {
        return citoyen;
    }

    public Domaine getDomaine() {
        return domaine;
    }

    public int getEtat() {
        return etat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHeure(String heure) {
         this.heure=heure;

    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setCitoyen(Utilisateur citoyen) {
        this.citoyen = citoyen;
    }

    public void setDomaine(Domaine domaine) {
        this.domaine = domaine;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Vector getListEvaluation() {
        return listEvaluation;
    }

    public void setListEvaluation(Vector listEvaluation) {
        this.listEvaluation = listEvaluation;
    }

    
    public String toString() {
        return titre;
    }

    
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this.id;
        return hash;
    }

    
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reclamation other = (Reclamation) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
