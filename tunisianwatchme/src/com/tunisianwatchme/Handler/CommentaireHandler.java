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
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Khadija
 */
public class CommentaireHandler extends DefaultHandler implements Runnable{

    Vector commentaireVector;
    private Commentaire currentCommentaire;
    String idTag = "close";
    String nomTag = "close";
    String prenTag = "close";
    
    public CommentaireHandler(String URL) {
        try {
            this.commentaireVector = new Vector();
            Thread thr = new Thread(this);
            thr.start();
            thr.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public Vector getCommentaireVector() {
        return commentaireVector;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("commentaire")) {

            if (currentCommentaire != null) {
                throw new IllegalStateException("already processing a CommentaireVector");
            }
            currentCommentaire = new Commentaire();
        } else if (qName.equals("id")) {
            idTag = "open";
        } else if (qName.equals("texte")) {
            nomTag = "open";
        }
    }
    
     public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("commentaire")) {
            // we are no longer processing a <reg.../> tag
            commentaireVector.addElement(currentCommentaire);
            currentCommentaire = null;
        } else if (qName.equals("id")) {
            idTag = "close";
        } else if (qName.equals("texte")) {
            nomTag = "close";
        }
    }

     public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentCommentaire != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (idTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                currentCommentaire.setId(Integer.parseInt(id));
            } else
                if (nomTag.equals("open")) {
                String texte = new String(ch, start, length);
                currentCommentaire.setTexte(texte);
            } 
        }
    }
     
    public void run() {
        try {
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/tw_mobile/commentaires.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, this);
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
