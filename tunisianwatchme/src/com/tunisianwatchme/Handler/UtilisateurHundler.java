/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunisianwatchme.Handler;

import com.sun.j2me.global.DateFormatSymbols;
import com.tunisianwatchme.Entity.Utilisateur;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/**
 *
 * @author Farouk
 */
public class UtilisateurHundler extends Thread {

    public Vector utilisateurVector;

    public Vector getUtilisateurVector() {
        return utilisateurVector;
    }

    public UtilisateurHundler() {
        this.utilisateurVector = new Vector();
    }

    
    
    
    
    public void run() {
        try {
            //Open http connection
            HttpConnection httpConnection = (HttpConnection) Connector.open("http://localhost/tw_mobile/utilisateurs.php?type=select");

            //Initilialize XML parser
            KXmlParser parser = new KXmlParser();

            parser.setInput(new InputStreamReader(httpConnection.openInputStream()));

            parser.nextTag();

            parser.require(XmlPullParser.START_TAG, null, "utilisateurs");

            //Iterate through our XML file
            while (parser.nextTag() != XmlPullParser.END_TAG) {
                readXMLData(parser);
            }

            parser.require(XmlPullParser.END_TAG, null, "utilisateurs");
            parser.next();

            parser.require(XmlPullParser.END_DOCUMENT, null, null);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void readXMLData(KXmlParser parser)
            throws IOException, XmlPullParserException {

        //Parse our XML file
        parser.require(XmlPullParser.START_TAG, null, "utilisateur");
        
        

        while (parser.nextTag() != XmlPullParser.END_TAG) {
            Utilisateur utilisateur = new Utilisateur();
            
            parser.require(XmlPullParser.START_TAG, null, null);
            
            String name = parser.getName();

            String text = parser.nextText();

            System.out.println("<" + name + ">" + text);

            if (name.equals("name")) {
            } else if (name.equals("id")) {
                utilisateur.setId(Integer.parseInt(text));
            } else if (name.equals("nom")) {
                utilisateur.setNom(text);
            } else if (name.equals("prenom")) {
                utilisateur.setPrenom(text);
            } else if (name.equals("sexe")) {
                utilisateur.setSexe(text.charAt(0));
            } else if (name.equals("adress")) {
                utilisateur.setAdress(text);
            } else if (name.equals("login")) {
                utilisateur.setLogin(text);
            } else if (name.equals("mdp")) {
                utilisateur.setMdp(text);
            } else if (name.equals("mail")) {
                utilisateur.setMail(text);
            } else if (name.equals("type")) {
                utilisateur.setType(text.charAt(0));
            } else if (name.equals("datenaissance")) {
                utilisateur.setDateNaissance(text);
            } 

            parser.require(XmlPullParser.END_TAG, null, name);
        }

        //bookVector.addElement(book);

        parser.require(XmlPullParser.END_TAG, null, "title");

    }

}
