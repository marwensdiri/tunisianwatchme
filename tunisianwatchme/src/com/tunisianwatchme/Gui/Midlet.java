package com.tunisianwatchme.Gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.tunisianwatchme.Entity.Commentaire;
import com.tunisianwatchme.Entity.Reclamation;
import com.tunisianwatchme.Entity.Utilisateur;
import com.tunisianwatchme.Handler.ReclamationHandler;
import com.tunisianwatchme.Post.ReclamationPost;
import com.tunisianwatchme.Post.UtilisateurPost;
import javax.microedition.midlet.*;

/**
 * @author MarwenSdiri <marwen.sdiri@esprit.tn>
 */
public class Midlet extends MIDlet {

    public void startApp() {

        Reclamation rec = new Reclamation();
        rec.setId(47);
        ReclamationPost recP = new ReclamationPost(rec, 2);
        recP.start();
        
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
}
