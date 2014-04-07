/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tunisianwatchme.Handler;

import com.tunisianwatchme.Entity.Utilisateur;
import java.io.DataInputStream;
import java.io.IOException;
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
 * @author MarwenSdiri <marwen.sdiri@esprit.tn>
 */
public class UtilisateurHandler extends DefaultHandler implements Runnable {
Vector utilisateurVector;
    private Utilisateur currentUtilisateur;
    String idTag = "close";
    String nomTag = "close";
    String prenomTag = "close";
    String sexeTag = "close";
    String adressTag = "close";
    String dateNaissanceTag = "close";
    String photoTag = "close";
    String pathTag = "close";
    String loginTag = "close";
    String mdpTag = "close";
    String mailTag = "close";
    String typeTag = "close";
    int  idUtilisateur ;
    
    public UtilisateurHandler(int idReclamation) {
        try {
           this.idUtilisateur = idUtilisateur ;
            this.utilisateurVector = new Vector();
            Thread thr = new Thread(this);
            thr.start();
            thr.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public Vector getUtilisateurVector() {
        return utilisateurVector;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("utilisateur")) {

            if (currentUtilisateur != null) {
                throw new IllegalStateException("already processing a UtilisateurVector");
            }
            currentUtilisateur = new Utilisateur();
        } else if (qName.equals("id")) {
            idTag = "close";
        } else if (qName.equals("nom")) {
            nomTag = "close";
        } else if (qName.equals("prenom")) {
            prenomTag = "close";
        } else if (qName.equals("sexe")) {
            sexeTag = "close";
        } else if (qName.equals("adress")) {
            adressTag = "close";
        }else if (qName.equals("dateNaissance")) {
            dateNaissanceTag = "close";
        } else if (qName.equals("photo")) {
            photoTag = "close";
        } else if (qName.equals("path")) {
            pathTag = "close";
        } else if (qName.equals("login")) {
            loginTag = "close";
        } else if (qName.equals("mdp")) {
            mdpTag = "close";
        } else if (qName.equals("mail")) {
            mailTag = "close";
        } else if (qName.equals("type")) {
            typeTag = "close";
        }
    }
    
     public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("commentaire")) {
            // we are no longer processing a <reg.../> tag
            utilisateurVector.addElement(currentUtilisateur);
            currentUtilisateur = null;
        } else if (qName.equals("id")) {
            idTag = "close";
        } else if (qName.equals("nom")) {
            nomTag = "close";
        } else if (qName.equals("prenom")) {
            prenomTag = "close";
        } else if (qName.equals("sexe")) {
            sexeTag = "close";
        } else if (qName.equals("adress")) {
            adressTag = "close";
        }else if (qName.equals("dateNaissance")) {
            dateNaissanceTag = "close";
        } else if (qName.equals("photo")) {
            photoTag = "close";
        } else if (qName.equals("path")) {
            pathTag = "close";
        } else if (qName.equals("login")) {
            loginTag = "close";
        } else if (qName.equals("mdp")) {
            mdpTag = "close";
        } else if (qName.equals("mail")) {
            mailTag = "close";
        } else if (qName.equals("type")) {
            typeTag = "close";
        }
    }

     public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentUtilisateur != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (idTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                currentUtilisateur.setId(Integer.parseInt(id));
            } else
                if (nomTag.equals("open")) {
                String texte = new String(ch, start, length);
                currentUtilisateur.setNom(texte);
            }  else
                if (prenomTag.equals("open")) {
                String user = new String(ch, start, length);
                currentUtilisateur.setPrenom(user);
            }  else
                if (sexeTag.equals("open")) {
                String date = new String(ch, start, length);
                currentUtilisateur.setSexe(date);
            }  else
                if (adressTag.equals("open")) {
                String idReclamation = new String(ch, start, length);
                currentUtilisateur.setAdress(idReclamation);
            } else
                if (dateNaissanceTag.equals("open")) {
                String texte = new String(ch, start, length);
                currentUtilisateur.setDateNaissance(texte);
            }  else
                if (photoTag.equals("open")) {
                String user = new String(ch, start, length);
                currentUtilisateur.setPhoto(user);
            }  else
                if (pathTag.equals("open")) {
                String date = new String(ch, start, length);
                currentUtilisateur.setPath(date);
            }  else
                if (loginTag.equals("open")) {
                String idReclamation = new String(ch, start, length);
                currentUtilisateur.setLogin(idReclamation);
            } else
                if (mdpTag.equals("open")) {
                String user = new String(ch, start, length);
                currentUtilisateur.setMdp(user);
            }  else
                if (mailTag.equals("open")) {
                String date = new String(ch, start, length);
                currentUtilisateur.setMail(date);
            }  else
                if (typeTag.equals("open")) {
                String idReclamation = new String(ch, start, length);
                currentUtilisateur.setType(idReclamation);
            } 
        }
    }
     
    public void run() {
        try {
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/tw_mobile/utilisateurs.php?type=select&id="+idUtilisateur);
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

