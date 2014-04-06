package com.tunisianwatchme.Gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.tunisianwatchme.Handler.DomaineHundler;
import javax.microedition.midlet.*;

/**
 * @author MarwenSdiri <marwen.sdiri@esprit.tn>
 */
public class Midlet extends MIDlet {

    
    
    public void startApp() {
        
        new tht().start();
        
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public class tht extends Thread {

        public void run() {
            DomaineHundler dh = new DomaineHundler("http://localhost/tw_mobile/domaines.php");
            System.out.println("hahahahahahahkdlfhskjfmkjgmslj"+dh.getDomaineVector().size());
        }
    }
}

