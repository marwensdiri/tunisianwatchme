/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunisianwatchme.Handler;

import com.tunisianwatchme.Entity.Commentaire;


import java.io.*;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Khadija
 */
public class CommentaireHundler extends DefaultHandler implements Runnable{

    Vector commentaireVector;

    public CommentaireHundler(String URL) {
        this.commentaireVector = new Vector();
    }

    public Vector getCommentaireVector() {
        return commentaireVector;
    }

    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 

  
}
