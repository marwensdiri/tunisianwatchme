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
public class DomaineHundler extends Thread {

    Vector domaineVector;
    private String URL;

    public DomaineHundler(String URL) {
        this.domaineVector = new Vector();
        this.URL = URL;
    }

    public Vector getDomaineVector() {
        return domaineVector;
    }

    
    
    public void run() {
        try {
            HttpConnection httpConnection = (HttpConnection) Connector.open(URL);
            KXmlParser parser = new KXmlParser();
            parser.setInput(new InputStreamReader(httpConnection.openInputStream()));
            parser.nextTag();
            parser.require(XmlPullParser.START_TAG, null, "catalog");
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