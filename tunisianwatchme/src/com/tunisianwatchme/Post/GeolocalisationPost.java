/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunisianwatchme.Post;

import com.tunisianwatchme.Entity.Geolocalisation;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;

/**
 *
 * @author asd
 */
public class GeolocalisationPost extends Thread {

    private Geolocalisation geolocalisation;

    public GeolocalisationPost(Geolocalisation geolocalisation) {
        this.geolocalisation = geolocalisation;
    }

    public void run() {
        HttpConnection hc;
        DataInputStream dc;
        StringBuffer str = new StringBuffer("");
        String url = "http://localhost/tw_mobile/geolocalisations.php?type=add&lon=" + geolocalisation.getLon() + "&lat=" + geolocalisation.getLat();
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