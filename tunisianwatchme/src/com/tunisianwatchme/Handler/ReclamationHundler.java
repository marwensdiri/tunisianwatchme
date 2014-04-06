/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tunisianwatchme.Handler;

import com.tunisianwatchme.Entity.Reclamation;
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
 * @author MarwenSdiri <marwen.sdiri@esprit.tn>
 */

public class ReclamationHundler extends Thread {

    Vector reclamationVector;
    

    public ReclamationHundler(String URL) {
        this.reclamationVector = new Vector();
    }

    public Vector getDomaineVector() {
        return reclamationVector;
    }

    
    
    public void run() {
        try {
            HttpConnection httpConnection = (HttpConnection) Connector.open("http://localhost/tw_mobile/reclamations.php");
            KXmlParser parser = new KXmlParser();
            parser.setInput(new InputStreamReader(httpConnection.openInputStream()));
            parser.nextTag();
            parser.require(XmlPullParser.START_TAG, null, "");
            while (parser.nextTag() != XmlPullParser.END_TAG) {
                readXMLData(parser);
            }
            parser.require(XmlPullParser.END_TAG, null, "reclamations");
            parser.next();
            parser.require(XmlPullParser.END_DOCUMENT, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readXMLData(KXmlParser parser)
            throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, null, "reclamation");
        Reclamation reclamation = new Reclamation();
        while (parser.nextTag() != XmlPullParser.END_TAG) {
            parser.require(XmlPullParser.START_TAG, null, null);
            String name = parser.getName();
            String text = parser.nextText();
            if (name.equals("id")) {
                reclamation.setId(Integer.parseInt(text));
            } else if (name.equals("titre")) {
                reclamation.setTitre(text);
            }
            parser.require(XmlPullParser.END_TAG, null, name);
        }
        reclamationVector.addElement(reclamation);
        parser.require(XmlPullParser.END_TAG, null, "reclamation");

    }
}