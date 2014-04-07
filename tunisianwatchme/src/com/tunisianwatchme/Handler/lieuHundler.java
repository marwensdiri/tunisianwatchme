/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tunisianwatchme.Handler;
import com.tunisianwatchme.Entity.Lieu;
import org.kxml2.io.*;
import org.xmlpull.v1.*;

import java.io.*;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

/**
 *
 * @author Khadija
 */
public class lieuHundler extends Thread{
    Vector lieuVector;
    int id;

    public lieuHundler(int i) {
        this.lieuVector = new Vector();
        this.id=i;
    }

    public Vector getLieuVector() {
        return lieuVector;
    }

        public void run() {
        try {
            HttpConnection httpConnection = (HttpConnection) Connector.open("http://localhost/tw_mobile/lieux.php?id="+this.id);
            KXmlParser parser = new KXmlParser();
            parser.setInput(new InputStreamReader(httpConnection.openInputStream()));
            parser.nextTag();
            parser.require(XmlPullParser.START_TAG, null, "lieux");
            while (parser.nextTag() != XmlPullParser.END_TAG) {
                readXMLData(parser);
            }
            parser.require(XmlPullParser.END_TAG, null, "lieux");
            parser.next();
            parser.require(XmlPullParser.END_DOCUMENT, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readXMLData(KXmlParser parser)
            throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, null, "lieu");
        Lieu lieu = new Lieu();
        while (parser.nextTag() != XmlPullParser.END_TAG) {
            parser.require(XmlPullParser.START_TAG, null, null);
            String name = parser.getName();
            String text = parser.nextText();
            if (name.equals("id")) {
                lieu.setId(Integer.parseInt(text));
            } else if (name.equals("ville")) {
                lieu.setNom(text);
            }
            parser.require(XmlPullParser.END_TAG, null, name);
        }
        lieuVector.addElement(lieu);
        parser.require(XmlPullParser.END_TAG, null, "lieu");

    }
    
}
