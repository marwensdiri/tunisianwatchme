package com.tunisianwatchme.Gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.tunisianwatchme.Entity.Geolocalisation;
import com.tunisianwatchme.Handler.GeolocalisationHandler;
import javax.microedition.midlet.*;

/**
 * @author MarwenSdiri <marwen.sdiri@esprit.tn>
 */
public class Midlet extends MIDlet {

    public void startApp() {

        GeolocalisationHandler dh = new GeolocalisationHandler(1);
        System.out.println(dh.getGeolocalisationVector());
        for (int i = 0 ; i < dh.getGeolocalisationVector().size(); i++){
            System.out.println(((Geolocalisation)dh.getGeolocalisationVector().elementAt(i)).getId());
            System.out.println(((Geolocalisation)dh.getGeolocalisationVector().elementAt(i)).getLon());
            System.out.println(((Geolocalisation)dh.getGeolocalisationVector().elementAt(i)).getLat());
            //System.out.println(Double.parseDouble("3,3"));
            System.out.println(Double.parseDouble("3.3"));
        }
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
}
