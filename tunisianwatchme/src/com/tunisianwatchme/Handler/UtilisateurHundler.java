/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunisianwatchme.Handler;

import java.io.IOException;
import java.io.InputStreamReader;
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

    public void run() {
        try {
            //Open http connection
            HttpConnection httpConnection = (HttpConnection) Connector.open("http://localhost/tw_mobile/utilisateurs.php?type=select");

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

        

        while (parser.nextTag() != XmlPullParser.END_TAG) {
            
            parser.require(XmlPullParser.START_TAG, null, null);
            
            String name = parser.getName();

            String text = parser.nextText();

            System.out.println("<" + name + ">" + text);

            /*if (name.equals("name")) {
                book.setName(text);
            } else if (name.equals("description")) {
                book.setDescription(text);
            } else if (name.equals("author")) {
                book.setAuthor(text);
            } else if (name.equals("rating")) {
                book.setRating(text);
            } else if (name.equals("available")) {
                book.setAvailable(text);
            }*/

            parser.require(XmlPullParser.END_TAG, null, name);
        }

        //bookVector.addElement(book);

        parser.require(XmlPullParser.END_TAG, null, "title");

    }

}
