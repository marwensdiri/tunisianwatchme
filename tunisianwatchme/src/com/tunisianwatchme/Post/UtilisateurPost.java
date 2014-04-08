/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunisianwatchme.Post;

import com.tunisianwatchme.Entity.Utilisateur;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

/**
 *
 * @author MarwenSdiri <marwen.sdiri@esprit.tn>
 */
public class UtilisateurPost extends Thread {

    private Utilisateur utilisateur;
    char aux;
    String url = null;
    
    public UtilisateurPost(Utilisateur utilisateur, char aux) {
        this.utilisateur = utilisateur;
        this.aux = aux;
    }

    public void run() {
        HttpConnection hc;
        DataInputStream dc;
        StringBuffer str = new StringBuffer("");
        if (aux == 'A') {
             url = "http://localhost/tw_mobile/utilisateurs.php?type=add&nom=" + utilisateur.getNom() + "&prenom=" + utilisateur.getPrenom() + "&sexe=" + utilisateur.getSexe() + "&login=" + utilisateur.getLogin() + "&mdp=" + utilisateur.getMdp() + "&mail=" + utilisateur.getMail();
            System.out.println("test"+" a: "+ aux +" url :"+url  );
        } else if (aux == 'U') {

             url = "http://localhost/tw_mobile/utilisateurs.php?type=update&id="+ utilisateur.getId() + "nom=" + utilisateur.getNom() + "&prenom=" + utilisateur.getPrenom() + "&sexe=" + utilisateur.getSexe() + "&login=" + utilisateur.getLogin() + "&mdp=" + utilisateur.getMdp() + "&mail=" + utilisateur.getMail();

        } else if (aux == 'D') {

             url = "http://localhost/tw_mobile/utilisateurs.php?type=delete&id="+ utilisateur.getId();

        }

        try {
            hc = (HttpConnection) Connector.open(url);
            dc = new DataInputStream(hc.openInputStream());
            int ch;
            while ((ch = dc.read()) != -1) {
                str.append((char) ch);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
