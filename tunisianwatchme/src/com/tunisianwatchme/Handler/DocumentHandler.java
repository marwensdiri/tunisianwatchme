/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunisianwatchme.Handler;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;

/**
 *
 * @author Farouk
 */
public class DocumentHandler implements Runnable {

    String image;

    public DocumentHandler(String image) {
        try {
            this.image = image;
            Thread thr = new Thread(this);
            thr.start();
            thr.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    HttpConnection hc;
    DataInputStream dc;
    Image img;

    public Image getImg() {
        return img;
    }
    
    public void run() {
        try {
            String url = "http://localhost/tw_mobile/images/"+image;
            hc = (HttpConnection) Connector.open(url);
            dc = new DataInputStream(hc.openInputStream());
            int lengt = (int) hc.getLength();
            byte[] data;
            if (lengt != -1) {
                data = new byte[lengt];
                dc.readFully(data);
                img = Image.createImage(data, 0, lengt);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            hc.close();
            dc.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
