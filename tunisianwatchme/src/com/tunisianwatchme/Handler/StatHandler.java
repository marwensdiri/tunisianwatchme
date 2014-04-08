/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tunisianwatchme.Handler;

import com.tunisianwatchme.Entity.Stat;
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
public class StatHandler extends DefaultHandler implements Runnable{
     private Vector statVector;
    String critereTag = "close";
    String valueTag = "close";
    char type;
    
    public StatHandler(char type) {
        try {
            this.type = type;
            statVector = new Vector();
            Thread thr = new Thread(this);
            thr.start();
            thr.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public Vector getstatVector() {
        return statVector;
    }
    
    
    
     private Stat currentStat;
    
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("criteres")) {

            if (currentStat != null) {
                throw new IllegalStateException("already processing a statVector");
            }
            currentStat = new Stat();
        } else if (qName.equals("critere")) {
            critereTag = "open";
        } else if (qName.equals("value")) {
            valueTag = "open";
        }
    }
    
     public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("criteres")) {
            // we are no longer processing a <reg.../> tag
            statVector.addElement(currentStat);
            currentStat = null;
        } else if (qName.equals("critere")) {
            critereTag = "close";
        } else if (qName.equals("value")) {
            valueTag = "close";
        }
    }

     public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentStat != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (valueTag.equals("open")) {
                String value = new String(ch, start, length).trim();
                currentStat.setValue(Integer.parseInt(value));
            } else
                if (critereTag.equals("open")) {
                String nom = new String(ch, start, length);
                currentStat.setCritere(nom);
            } 
        }
    }
     
    public void run() {
        try {
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/tw_mobile/statistiques.php?type="+type);
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
