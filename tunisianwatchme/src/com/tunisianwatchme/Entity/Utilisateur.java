/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tunisianwatchme.Entity;


import javax.microedition.lcdui.Image;

/**
 *
 * @author MarwenSdiri <marwen.sdiri@esprit.tn>
 */
public class Utilisateur {

    private int id;
    private String nom;
    private String prenom;
    private char sexe;
    private String adress;
    private String dateNaissance;
    private Image photo;
    private String path;
    private String login;
    private String mdp;
    private String mail;
    private char type;

    public Utilisateur() {
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Image getPhoto() {
        return photo;
    }

    public void setPhoto(Image photo) {
        this.photo = photo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public char getSexe() {
        return sexe;
    }

    public String getAdress() {
        return adress;
    }

    public void setSexe(char sexe) {
        this.sexe = sexe;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String toString() {
        return "id: " +id+" nom: "+nom   ;
    }
}
