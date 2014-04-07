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

    public UtilisateurPost(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public void run() {
        HttpConnection hc;
        DataInputStream dc;
        StringBuffer str = new StringBuffer("");
        String url = "http://localhost/tw_mobile/utlisateurs.php?type=add&nom=" + utilisateur.getNom()+ "&prenom=" + utilisateur.getPrenom();
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