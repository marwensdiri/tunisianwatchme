/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tunisianwatchme.Gui;

import javax.microedition.midlet.*;
import com.tunisianwatchme.Handler.lieuHundler;


/**
 * @author Khadija
 */
public class testLieuHundler extends MIDlet {
    int id;
    public void startApp() {
        lieuHundler dh=new lieuHundler(5);

        dh.start();
        try {
            dh.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println(dh.getLieuVector());

    

    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
}
