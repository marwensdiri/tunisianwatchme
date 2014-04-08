/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunisianwatchme.Post;

import com.tunisianwatchme.Entity.Reclamation;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

/**
 *
 * @author asd
 */
public class ReclamationPost extends Thread {

    private Reclamation reclamation;
    private int type;

    public ReclamationPost(Reclamation reclamation, int type) {
        this.reclamation = reclamation;
        this.type = type;
    }

    public void run() {
        HttpConnection hc;
        DataInputStream dc;
        StringBuffer str = new StringBuffer("");
        String url = "";
        if (type == 1) {
            url = "http://localhost/tw_mobile/reclamations.php?type=add&titre=" + reclamation.getTitre() + "&description=" + reclamation.getDescription() + "&idcitoyen=" + reclamation.getCitoyen().getId() + "&date=" + reclamation.getDate() + "&heure=" + reclamation.getHeure() + "&etat=0&idlieu=" + reclamation.getLieu().getId() + "&iddomaine=" + reclamation.getDomaine().getId();
        }
        if (reclamation.getGeolocalisation() != null) {
            url += "&lon=" + reclamation.getGeolocalisation().getLon() + "&lat=" + reclamation.getGeolocalisation().getLat();
        } else if (type == 2) {
            url = "http://localhost/tw_mobile/reclamations.php?type=delete&id=" + reclamation.getId();
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
