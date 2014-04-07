/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunisianwatchme.Handler;

import com.tunisianwatchme.Entity.Domaine;
import com.tunisianwatchme.Entity.Lieu;
import com.tunisianwatchme.Entity.Reclamation;
import com.tunisianwatchme.Entity.Utilisateur;
import gov.nist.core.StringTokenizer;
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
public class ReclamationHandler extends DefaultHandler implements Runnable {

    private Vector ReclamationVector;
    String idTag = "close";
    String titreTag = "close";
    String dateTag = "close";
    String heureTag = "close";
    String citoyenTag = "close";
    String domaineTag = "close";
    String etatTag = "close";
    String idgeolocalisationTag = "close";
    String descriptionTag = "close";
    String villeTag = "close";
    

    public ReclamationHandler() {
        try {
            ReclamationVector = new Vector();
            Thread thr = new Thread(this);
            thr.start();
            thr.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public Vector getReclamation() {
        return ReclamationVector;
    }
    
    
    
     private Reclamation currentReclamation;
    
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("reclamation")) {
            if (currentReclamation != null) {
                throw new IllegalStateException("already processing a ReclamationHandler");
            }
            currentReclamation = new Reclamation();
        } else if (qName.equals("id")) {
            idTag = "open";
        } else if (qName.equals("titre")) {
            titreTag = "open";
        } else if (qName.equals("date")) {
            dateTag = "open";
        } else if (qName.equals("heure")) {
            heureTag = "open";
        } else if (qName.equals("citoyen")) {
            citoyenTag = "open";
        } else if (qName.equals("domaine")) {
            domaineTag = "open";
        } else if (qName.equals("etat")) {
            etatTag = "open";
        } else if (qName.equals("idgeolocalisation")) {
            idgeolocalisationTag = "open";
        } else if (qName.equals("description")) {
            descriptionTag = "open";
        } else if (qName.equals("ville")) {
            villeTag = "open";
        }
    }
    
     public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("reclamation")) {
            // we are no longer processing a <reg.../> tag
            ReclamationVector.addElement(currentReclamation);
            currentReclamation = null;
        } else if (qName.equals("id")) {
            idTag = "close";
        } else if (qName.equals("titre")) {
            titreTag = "close";
        } else if (qName.equals("date")) {
            dateTag = "close";
        } else if (qName.equals("heure")) {
            heureTag = "close";
        } else if (qName.equals("citoyen")) {
            citoyenTag = "close";
        } else if (qName.equals("domaine")) {
            domaineTag = "close";
        } else if (qName.equals("etat")) {
            etatTag = "close";
        } else if (qName.equals("idgeolocalisation")) {
            idgeolocalisationTag = "close";
        } else if (qName.equals("description")) {
            descriptionTag = "close";
        } else if (qName.equals("ville")) {
            villeTag = "close";
        }
    }

     public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        // System.out.println("ab");
        if (currentReclamation != null) {
            System.out.println("zzz");
            // don't forget to trim excess spaces from the ends of the string
            if (idTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                currentReclamation.setId(Integer.parseInt(id));
            } else if (titreTag.equals("open")) {
                String titre = new String(ch, start, length);
                currentReclamation.setTitre(titre);
            } else if (dateTag.equals("open")) {
                String date = new String(ch, start, length);
                currentReclamation.setDate(date);
            }  else if (heureTag.equals("open")) {
                String heure = new String(ch, start, length);
                currentReclamation.setHeure(heure);
            }  else if (citoyenTag.equals("open")) {
                String citoyen = new String(ch, start, length);
                Utilisateur user = new Utilisateur();
                StringTokenizer st = new StringTokenizer(citoyen,' ');
                
                user.setNom(st.nextToken());
                user.setPrenom(st.nextToken());
                currentReclamation.setCitoyen(user);
            }   else if (domaineTag.equals("open")) {
                String domaine = new String(ch, start, length);
                currentReclamation.setDomaine(new Domaine(domaine));
            }  else if (etatTag.equals("open")) {
                String etat = new String(ch, start, length);
                currentReclamation.setEtat(Integer.parseInt(etat));
            }  else if (idgeolocalisationTag.equals("open")) {
                String idgeolocalisation = new String(ch, start, length);
                currentReclamation.setidGeolocalisation(Integer.parseInt(idgeolocalisation));
            }  else if (descriptionTag.equals("open")) {
                String description = new String(ch, start, length);
                currentReclamation.setDescription(description);
            }   else if (villeTag.equals("open")) {
                String ville = new String(ch, start, length);
                Lieu lieu = new Lieu(ville);
                currentReclamation.setLieu(lieu);
            } 
        }
    }
     
    public void run() {
        try {
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/tw_mobile/reclamations.php?type=select");
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
