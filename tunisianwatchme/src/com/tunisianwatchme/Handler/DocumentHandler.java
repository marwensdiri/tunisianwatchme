/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunisianwatchme.Handler;

import com.tunisianwatchme.Entity.Document;
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
public class DocumentHandler extends DefaultHandler implements Runnable {

    private Vector DocumentVector;
    private int id = 0;
    String nomTag = "close";
    String contentTag = "close";

    public DocumentHandler(int id) {
        try {
            this.id = id;
            DocumentVector = new Vector();
            Thread thr = new Thread(this);
            thr.start();
            thr.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public Vector getDocumentVector() {
        return DocumentVector;
    }
    private Document currentDocument;

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("geolocalisation")) {

            if (currentDocument != null) {
                throw new IllegalStateException("already processing a DomaineVector");
            }
            currentDocument = new Document();
        } else if (qName.equals("nom")) {
            nomTag = "open";
        } else if (qName.equals("content")) {
            contentTag = "open";
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("geolocalisation")) {
            // we are no longer processing a <reg.../> tag
            DocumentVector.addElement(currentDocument);
            currentDocument = null;
        } else if (qName.equals("nom")) {
            nomTag = "close";
        } else if (qName.equals("content")) {
            contentTag = "close";
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentDocument != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (nomTag.equals("open")) {
                String nom = new String(ch, start, length).trim();
                currentDocument.setNom(nom);
            } else if (contentTag.equals("open")) {
                String content = new String(ch, start, length);
                currentDocument.setImage(null);
            }
        }
    }

    public void run() {
        try {
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/tw_mobile/documents.php?id=" + id);
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
