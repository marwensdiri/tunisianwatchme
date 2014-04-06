/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunisianwatchme.Handler;

/**
 *
 * @author Farouk
 */
import org.xml.sax.helpers.DefaultHandler;

public class DomaineHundler extends DefaultHandler {
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
}