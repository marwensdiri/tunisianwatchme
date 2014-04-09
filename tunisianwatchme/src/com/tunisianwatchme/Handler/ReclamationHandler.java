/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunisianwatchme.Handler;

import com.tunisianwatchme.Entity.Commentaire;
import com.tunisianwatchme.Entity.Domaine;
import com.tunisianwatchme.Entity.Geolocalisation;
import com.tunisianwatchme.Entity.Lieu;
import com.tunisianwatchme.Entity.Reclamation;
import com.tunisianwatchme.Entity.Utilisateur;
import de.enough.polish.util.StringTokenizer;

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
    String lonTag = "close";
    String latTag = "close";

    String idcommentaireTag = "close";
    String texteComTag = "close";
    String userComTag = "close";
    String dateComTag = "close";

    String descriptionTag = "close";
    String villeTag = "close";
    
    String iddocumentTag = "close";

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
    private Commentaire currentComentaire;

    
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
        } else if (qName.equals("lon")) {
            lonTag = "open";
        } else if (qName.equals("lat")) {
            latTag = "open";
        } else if (qName.equals("idcommentaire")) {
            idcommentaireTag = "open";
        } else if (qName.equals("texte-commentaire")) {
            texteComTag = "open";
        } else if (qName.equals("user-commentaire")) {
            userComTag = "open";
        } else if (qName.equals("date-commentaire")) {
            dateComTag = "open";
        } else if (qName.equals("description")) {
            descriptionTag = "open";
        } else if (qName.equals("ville")) {
            villeTag = "open";
        } else if (qName.equals("id-document")) {
            iddocumentTag = "open";
        }//iddocumentTag
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
        } else if (qName.equals("lon")) {
            lonTag = "close";
        } else if (qName.equals("lat")) {
            latTag = "close";
        } else if (qName.equals("idcommentaire")) {
            idcommentaireTag = "close";
        } else if (qName.equals("texte-commentaire")) {
            texteComTag = "close";
        } else if (qName.equals("user-commentaire")) {
            userComTag = "close";
        } else if (qName.equals("date-commentaire")) {
            dateComTag = "close";
        } else if (qName.equals("description")) {
            descriptionTag = "close";
        } else if (qName.equals("ville")) {
            villeTag = "close";
        } else if (qName.equals("id-document")) {
            iddocumentTag = "close";
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        // System.out.println("ab");
        if (currentReclamation != null) {
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
            } else if (heureTag.equals("open")) {
                String heure = new String(ch, start, length);
                currentReclamation.setHeure(heure);
            } else if (citoyenTag.equals("open")) {
                String citoyen = new String(ch, start, length);
                Utilisateur user = new Utilisateur();
                StringTokenizer st = new StringTokenizer(citoyen, ' ');

                user.setNom(st.nextToken());
                user.setPrenom(st.nextToken());
                currentReclamation.setCitoyen(user);
            } else if (domaineTag.equals("open")) {
                String domaine = new String(ch, start, length);
                currentReclamation.setDomaine(new Domaine(domaine));
            } else if (etatTag.equals("open")) {
                String etat = new String(ch, start, length);
                currentReclamation.setEtat(Integer.parseInt(etat));
            } else if (idgeolocalisationTag.equals("open")) {
                System.out.println("abc");
                String id = new String(ch, start, length);
                Geolocalisation geo = new Geolocalisation();
                geo.setId(Integer.parseInt(id));
                currentReclamation.setGeolocalisation(geo);
            } else if (lonTag.equals("open")) {
                String lon = new String(ch, start, length);
                Geolocalisation geo = currentReclamation.getGeolocalisation();
                //geo.setLon(Double.parseDouble(lon));
                currentReclamation.setGeolocalisation(geo);

            } else if (latTag.equals("open")) {
                String lat = new String(ch, start, length);
                Geolocalisation geo = currentReclamation.getGeolocalisation();
                geo.setLat(Double.parseDouble(lat));
                currentReclamation.setGeolocalisation(geo);

            } else if (descriptionTag.equals("open")) {
                String description = new String(ch, start, length);
                currentReclamation.setDescription(description);
            } else if (villeTag.equals("open")) {
                String ville = new String(ch, start, length);
                Lieu lieu = new Lieu(ville);
                currentReclamation.setLieu(lieu);
            } else if (idcommentaireTag.equals("open")) {
                String id = new String(ch, start, length);
                currentComentaire = new Commentaire();
                currentComentaire.setId(Integer.parseInt(id));
                //currentReclamation.addCommentaires(currentComentaire);
            } else if (texteComTag.equals("open")) {
                String text = new String(ch, start, length);
                currentComentaire.setTexte(text);
                //currentReclamation.addCommentaires(currentComentaire);
            } else if (userComTag.equals("open")) {
                String nom = new String(ch, start, length);
                Utilisateur user = new Utilisateur();
                StringTokenizer st = new StringTokenizer(nom, ' ');

                user.setNom(st.nextToken());
                user.setPrenom(st.nextToken());
                currentComentaire.setUser(user);
            } else if (dateComTag.equals("open")) {
                String text = new String(ch, start, length);
                currentComentaire.setDate(text);
                currentReclamation.addCommentaires(currentComentaire);
            } else if(iddocumentTag.equals("open")){
                 String id = new String(ch, start, length);
                currentReclamation.addDoc(Integer.parseInt(id));
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
