/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunisianwatchme.Handler;

import com.tunisianwatchme.Entity.Commentaire;
import org.kxml2.io.*;
import org.xmlpull.v1.*;

import java.io.*;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

/**
 *
 * @author Khadija
 */
public class CommentaireHundler extends Thread {

    Vector commentaireVector;

    public CommentaireHundler(String URL) {
        this.commentaireVector = new Vector();
    }

    public Vector getCommentaireVector() {
        return commentaireVector;
    }
    
        
    public void run() {
        try {
            HttpConnection httpConnection = (HttpConnection) Connector.open("http://localhost/tw_mobile/commentaire.php");
            KXmlParser parser = new KXmlParser();
            parser.setInput(new InputStreamReader(httpConnection.openInputStream()));
            parser.nextTag();
            parser.require(XmlPullParser.START_TAG, null, "commentaires");
            while (parser.nextTag() != XmlPullParser.END_TAG) {
                readXMLData(parser);
            }
            parser.require(XmlPullParser.END_TAG, null, "commentaires");
            parser.next();
            parser.require(XmlPullParser.END_DOCUMENT, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readXMLData(KXmlParser parser)
            throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, null, "commentaire");
        Commentaire commentaire = new Commentaire();
        while (parser.nextTag() != XmlPullParser.END_TAG) {
            parser.require(XmlPullParser.START_TAG, null, null);
            String name = parser.getName();
            String text = parser.nextText();
            if (name.equals("id")) {
                commentaire.setId(Integer.parseInt(text));
            } else if (name.equals("texte")) {
                commentaire.setTexte(text);
            } else if (name.equals("idutilisateur")) {
                
//                commentaire.setUser(Integer.parseInt(text));
            } else if (name.equals("idreclamation")) {
                commentaire.setIdReclamation(Integer.parseInt(text));
            } else if (name.equals("date")) {
                commentaire.setDate(text);
            }
            parser.require(XmlPullParser.END_TAG, null, name);
        }
        commentaireVector.addElement(commentaire);
        parser.require(XmlPullParser.END_TAG, null, "commentaire");

    }
}
