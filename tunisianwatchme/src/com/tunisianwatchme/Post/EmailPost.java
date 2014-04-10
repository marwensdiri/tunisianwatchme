package com.tunisianwatchme.Post;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import com.tunisianwatchme.util.EncodeURL;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

/**
 *
 * @author MarwenSdiri <marwen.sdiri@esprit.tn>
 */
public class EmailPost extends Thread {

    
    char aux;
    String texte,sujet,mail;
    String url = null;
    
    public EmailPost(String sujet,String msg ,String mail) {
        this.texte = texte;
        this.mail = mail;
        this.sujet = sujet;
    }

    public void run() {
        HttpConnection hc;
        DataInputStream dc;
        StringBuffer str = new StringBuffer("");
        System.out.println("1: "+mail+"   " + sujet + "   "+ texte);
        url = "http://localhost/tw_mobile/mailer/mailer.php?mail="+ mail +"&sujet="+ EncodeURL.encode(sujet) + "&texte="+ EncodeURL.encode(texte)  ;
 

        try {
            hc = (HttpConnection) Connector.open(url);
            dc = new DataInputStream(hc.openInputStream());
            int ch;
            while ((ch = dc.read()) != -1) {
                str.append((char) ch);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
