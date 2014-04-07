/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunisianwatchme.Handler;

import com.tunisianwatchme.Entity.Lieu;
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
public class LieuHandler extends DefaultHandler implements Runnable {

    private Vector LieuVector;
    String idTag = "close";
    String nomTag = "close";

    public LieuHandler() {
        try {
            LieuVector = new Vector();
            Thread thr = new Thread(this);
            thr.start();
            thr.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public Vector getLieuVector() {
        return LieuVector;
    }
    
    
    
     private Lieu currentLieu;
    
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("lieu")) {

            if (currentLieu != null) {
                throw new IllegalStateException("already processing a LieuVector");
            }
            currentLieu = new Lieu();
        } else if (qName.equals("id")) {
            idTag = "open";
        } else if (qName.equals("ville")) {
            nomTag = "open";
        }
    }
    
     public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("lieu")) {
            // we are no longer processing a <reg.../> tag
            LieuVector.addElement(currentLieu);
            currentLieu = null;
        } else if (qName.equals("id")) {
            idTag = "close";
        } else if (qName.equals("ville")) {
            nomTag = "close";
        }
    }

     public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentLieu != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (idTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                currentLieu.setId(Integer.parseInt(id));
            } else
                if (nomTag.equals("open")) {
                String nom = new String(ch, start, length);
                currentLieu.setNom(nom);
            } 
        }
    }
     
    public void run() {
        try {
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/tw_mobile/lieux.php");
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
