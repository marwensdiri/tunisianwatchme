package com.tunisianwatchme.Gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.tunisianwatchme.Entity.Reclamation;
import com.tunisianwatchme.Handler.LieuHandler;
import com.tunisianwatchme.Handler.ReclamationHandler;
import javax.microedition.midlet.*;

/**
 * @author MarwenSdiri <marwen.sdiri@esprit.tn>
 */
public class Midlet extends MIDlet {

    public void startApp() {

        ReclamationHandler dh = new ReclamationHandler();
        System.out.println(dh.getReclamation());
        Reclamation rec = (Reclamation)dh.getReclamation().elementAt(0);
        System.out.println(rec.getId());
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
}
