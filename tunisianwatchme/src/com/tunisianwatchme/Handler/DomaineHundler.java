/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunisianwatchme.Handler;

import com.tunisianwatchme.Entity.Domaine;
import org.kxml2.io.*;
import org.xmlpull.v1.*;

import java.io.*;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

/**
 *
 * @author Farouk
 */
//import org.xml.sax.helpers.DefaultHandler;
public class DomaineHundler/* extends DefaultHandler j*/ {

    Vector domaineVector;
    private String URL;

    public DomaineHundler(String URL) {
        this.domaineVector = new Vector();
        this.URL = URL;
    }

    public Vector getDomaineVector() {
        return domaineVector;
    }
    
//    public static void main(String[] args) {
//         DomaineHundler dh = new DomaineHundler("http://localhost/tw_mobile/domaines.php");
//         System.out.println(dh.getDomaineVector().size());
//    }

//    private Vector domaines;
//    String idTag = "close";
//    String nomTag = "close";
//    String prenTag = "close";
//
//    public DomaineHundler() {
//        domaines = new Vector();
//    }
//
//    public Domaine[] getPersonne() {
//        Domaine[] doms = new Domaine[domaines.size()];
//        domaines.copyInto(doms);
//        return doms;
//    }
//    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
//    private Domaine currentDomaine;
//
//    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
//    // startElement is the opening part of the tag "<tagname...>"
//    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//        if (qName.equals("domaine")) {
//
//            if (currentDomaine != null) {
//                throw new IllegalStateException("already processing a personnes");
//            }
//            currentDomaine = new Domaine();
//        } else if (qName.equals("id")) {
//            idTag = "open";
//        } else if (qName.equals("nom")) {
//            nomTag = "open";
//        } 
//    }
//
//    public void endElement(String uri, String localName, String qName) throws SAXException {
//
//        if (qName.equals("domaine")) {
//            // we are no longer processing a <reg.../> tag
//            domaines.addElement(currentDomaine);
//            currentDomaine = null;
//        } else if (qName.equals("id")) {
//            idTag = "close";
//        } else if (qName.equals("nom")) {
//            nomTag = "close";
//        } 
//    }
//    // "characters" are the text inbetween tags
//
//    public void characters(char[] ch, int start, int length) throws SAXException {
//        // we're only interested in this inside a <phone.../> tag
//        if (currentDomaine != null) {
//            // don't forget to trim excess spaces from the ends of the string
//            if (idTag.equals("open")) {
//                String id = new String(ch, start, length).trim();
//                currentDomaine.setId(Integer.parseInt(id));
//            } else
//                if (nomTag.equals("open")) {
//                String nom = new String(ch, start, length).trim();
//                currentDomaine.setNom(nom);
//            } 
//        }
//    }
    public void parseXML() {
        try {
            //Open http connection
            HttpConnection httpConnection = (HttpConnection) Connector.open(URL);

            //Initilialize XML parser
            KXmlParser parser = new KXmlParser();

            parser.setInput(new InputStreamReader(httpConnection.openInputStream()));

            parser.nextTag();

            parser.require(XmlPullParser.START_TAG, null, "catalog");

            //Iterate through our XML file
            while (parser.nextTag() != XmlPullParser.END_TAG) {
                readXMLData(parser);
            }

            parser.require(XmlPullParser.END_TAG, null, "catalog");
            parser.next();

            parser.require(XmlPullParser.END_DOCUMENT, null, null);


        } catch (Exception e) {
            e.printStackTrace();


        }
    }

    private void readXMLData(KXmlParser parser)
            throws IOException, XmlPullParserException {


        //Parse our XML file
        parser.require(XmlPullParser.START_TAG, null, "title");

        Domaine domaine = new Domaine();

        while (parser.nextTag() != XmlPullParser.END_TAG) {


            parser.require(XmlPullParser.START_TAG, null, null);
            String name = parser.getName();

            String text = parser.nextText();

            System.out.println("<" + name + ">" + text);

            if (name.equals("id")) {
                domaine.setId(Integer.parseInt(text));
            } else if (name.equals("nom")) {
                domaine.setNom(text);
            }




            parser.require(XmlPullParser.END_TAG, null, name);
        }

        domaineVector.addElement(domaine);

        parser.require(XmlPullParser.END_TAG, null, "title");



    }
}