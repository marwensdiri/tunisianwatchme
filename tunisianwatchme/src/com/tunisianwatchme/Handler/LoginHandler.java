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
 * @author asd
 */
public class LoginHandler extends DefaultHandler implements Runnable{

    
     private Utilisateur currentUtilisateur = null;
    String idTag = "close";
    String nomTag = "close";
    String prenomTag = "close";
    String sexeTag = "close";
    String adressTag = "close";
    String dateNaissanceTag = "close";
    //String photoTag = "close";
    String loginTag = "close";
    String mdpTag = "close";
    String mailTag = "close";
    String typeTag = "close";
    
    String login;
    String password;

    public Utilisateur getCurrentUtilisateur() {
        return currentUtilisateur;
    }
    
    
    
    public LoginHandler(String login,String password) {
        try {
            System.out.println(login);
            this.login = login;
            this.password = password;
            //this.login = "a";
            //this.password = "f";
            Thread thr = new Thread(this);
            thr.start();
            thr.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
     public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("utilisateur")) {

            if (currentUtilisateur != null) {
                throw new IllegalStateException("already processing a UtilisateurVector");
            }
            
        } else if (qName.equals("id")) {
            currentUtilisateur = new Utilisateur();
            idTag = "open";
        } else if (qName.equals("nom")) {
            nomTag = "open";
        } else if (qName.equals("prenom")) {
            prenomTag = "open";
        } else if (qName.equals("sexe")) {
            sexeTag = "open";
        } else if (qName.equals("adress")) {
            adressTag = "open";
        } else if (qName.equals("dateNaissance")) {
            dateNaissanceTag = "open";
        }/* else if (qName.equals("photo")) {
            photoTag = "open";
        }*/ else if (qName.equals("login")) {
            loginTag = "open";
        } else if (qName.equals("mdp")) {
            mdpTag = "open";
        } else if (qName.equals("mail")) {
            mailTag = "open";
        } else if (qName.equals("type")) {
            typeTag = "open";
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("utilisateur")) {
            // we are no longer processing a <reg.../> tag
            
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
        } else if (qName.equals("dateNaissance")) {
            dateNaissanceTag = "close";
        }/* else if (qName.equals("photo")) {
            photoTag = "close";
        }*/ else if (qName.equals("login")) {
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
                System.out.println("cccc");
            } else if (nomTag.equals("open")) {
                String nom = new String(ch, start, length);
                currentUtilisateur.setNom(nom);
            } else if (prenomTag.equals("open")) {
                String prenom = new String(ch, start, length);
                currentUtilisateur.setPrenom(prenom);
            } else if (sexeTag.equals("open")) {
                String sexe = new String(ch, start, length);
                currentUtilisateur.setSexe(sexe.charAt(0));
            } else if (adressTag.equals("open")) {
                String adress = new String(ch, start, length);
                currentUtilisateur.setAdress(adress);
            } else if (dateNaissanceTag.equals("open")) {
                String dateNaissance = new String(ch, start, length);
                currentUtilisateur.setDateNaissance(dateNaissance);
            } else if (loginTag.equals("open")) {
                String login = new String(ch, start, length);
                currentUtilisateur.setLogin(login);
            } else if (mdpTag.equals("open")) {
                String mdp = new String(ch, start, length);
                currentUtilisateur.setMdp(mdp);
            } else if (mailTag.equals("open")) {
                String mail = new String(ch, start, length);
                currentUtilisateur.setMail(mail);
            } else if (typeTag.equals("open")) {
                String type = new String(ch, start, length);
                currentUtilisateur.setType(type.charAt(0));
            }
        }
    }

    public void run() {
        try {
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1/tw_mobile/login.php?login="+login+"&password="+password);
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
