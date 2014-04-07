/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tunisianwatchme.Handler;

import com.tunisianwatchme.Entity.Geolocalisation;
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
 * @author Farouk
 */
public class GeolocalisationHandler extends DefaultHandler implements Runnable{
private Vector GeolocalisationVector;
private int id = 0;
    String idTag = "close";
    String latTag = "close";
    String lonTag = "close";
    public GeolocalisationHandler(int id) {
        try {
            this.id=id;
            GeolocalisationVector = new Vector();
            Thread thr = new Thread(this);
            thr.start();
            thr.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public Vector getGeolocalisationVector() {
        return GeolocalisationVector;
    }
    
    
    
     private Geolocalisation currentDomaine;
    
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("geolocalisation")) {

            if (currentDomaine != null) {
                throw new IllegalStateException("already processing a DomaineVector");
            }
            currentDomaine = new Geolocalisation();
        } else if (qName.equals("id")) {
            idTag = "open";
        } else if (qName.equals("lat")) {
            latTag = "open";
        } else if (qName.equals("lon")) {
            lonTag = "open";
        }
    }
    
     public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("geolocalisation")) {
            // we are no longer processing a <reg.../> tag
            GeolocalisationVector.addElement(currentDomaine);
            currentDomaine = null;
        } else if (qName.equals("id")) {
            idTag = "close";
        } else if (qName.equals("lat")) {
            latTag = "close";
        } else if (qName.equals("lon")) {
            lonTag = "close";
        }
    }

     public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentDomaine != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (idTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                currentDomaine.setId(Integer.parseInt(id));
            } else
                if (latTag.equals("open")) {
                String Lat = new String(ch, start, length);
                System.out.println("--------------------------"+Lat);
                currentDomaine.setLat(Double.parseDouble(Lat));
            } else
                if (lonTag.equals("open")) {
                String Lon = new String(ch, start, length);
                System.out.println("--------------------------"+Lon);
                currentDomaine.setLon(Double.parseDouble(Lon));
            } 
        }
    }
     
    public void run() {
        try {
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/tw_mobile/geolocalisations.php?type=select&id="+id);
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