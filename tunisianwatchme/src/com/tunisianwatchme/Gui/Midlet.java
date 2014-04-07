package com.tunisianwatchme.Gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.tunisianwatchme.Entity.Commentaire;
import com.tunisianwatchme.Entity.Reclamation;
import com.tunisianwatchme.Handler.ReclamationHandler;
import javax.microedition.midlet.*;

/**
 * @author MarwenSdiri <marwen.sdiri@esprit.tn>
 */
public class Midlet extends MIDlet {

    public void startApp() {

        ReclamationHandler dh = new ReclamationHandler();
        System.out.println(dh.getReclamation());

        for (int i = 0; i < dh.getReclamation().size(); i++) {
            Reclamation rec = (Reclamation) dh.getReclamation().elementAt(i);
            System.out.println(rec.getId() + " " + rec.getListCommentaire().size());
            for(int j=0;j<rec.getListCommentaire().size();j++){
                Commentaire comm = (Commentaire) rec.getListCommentaire().elementAt(j);
                System.out.println(comm.getTexte());
            }
        }
        System.out.println(dh.getReclamation().size());
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
}
