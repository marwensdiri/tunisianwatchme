package com.tunisianwatchme.Gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.tunisianwatchme.Handler.CommentaireHandler;
import com.tunisianwatchme.Handler.DomaineHandler;
import javax.microedition.midlet.*;

/**
 * @author MarwenSdiri <marwen.sdiri@esprit.tn>
 */
public class Midlet extends MIDlet {

    public void startApp() {

        DomaineHandler dh = new DomaineHandler();
        System.out.println(dh.getDomaineVector());
//        CommentaireHandler dh = new CommentaireHandler(null);
//        System.out.println(dh.getCommentaireVector());
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
}
