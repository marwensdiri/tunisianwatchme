/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunisianwatchme.Post;

import com.tunisianwatchme.Entity.Commentaire;
import com.tunisianwatchme.util.EncodeURL;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

/**
 *
 * @author Khadija
 */
public class CommentairePost extends Thread {

    private Commentaire commentaire;
    int type;
    String url;

    public CommentairePost(Commentaire commentaire, int type) {
        this.commentaire = commentaire;
        this.type = type;
    }

    public void run() {
        HttpConnection hc;
        DataInputStream dc;
        StringBuffer str = new StringBuffer("");
        if (type == 1) {
            String url = "http://localhost/tw_mobile/commentaires.php?type=add&texte=" + EncodeURL.encode(commentaire.getTexte()) + "&idutilisateur=" + commentaire.getUser().getId()+"$idreclamation"+commentaire.getIdReclamation()+"$date"+commentaire.getDate();
        }else if(type ==2) {
            String url = "http://localhost/tw_mobile/commentaires.php?type=delete"+commentaire.getId();
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
