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
    String texteTag = "close";
    String userTag = "close";
    String daterTag = "close";
    String idReclamationTag = "close";
    int  idReclamation ;
    
    public CommentaireHandler(int idReclamation) {
        try {
            this.idReclamation = idReclamation ;
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
            texteTag = "open";
        }  else if (qName.equals("date")) {
            daterTag = "close";
        } else if (qName.equals("user")) {
            userTag = "close";
        } else if (qName.equals("idReclamation")) {
            idReclamationTag = "close";
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
            texteTag = "close";
        } else if (qName.equals("date")) {
            daterTag = "close";
        } else if (qName.equals("user")) {
            userTag = "close";
        } else if (qName.equals("idReclamation")) {
            idReclamationTag = "close";
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
                if (texteTag.equals("open")) {
                String texte = new String(ch, start, length);
                currentCommentaire.setTexte(texte);
            }  else
                if (userTag.equals("open")) {
                String user = new String(ch, start, length);
                currentCommentaire.setUser(user);
            }  else
                if (daterTag.equals("open")) {
                String date = new String(ch, start, length);
                currentCommentaire.setDate(date);
            }  else
                if (idReclamationTag.equals("open")) {
                String idReclamation = new String(ch, start, length);
                currentCommentaire.setIdReclamation(idReclamation);
            } 
        }
    }
     
    public void run() {
        try {
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/tw_mobile/commentaires.php?type=select&id="+idReclamation);
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
