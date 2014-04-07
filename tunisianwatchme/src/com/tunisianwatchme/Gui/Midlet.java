package com.tunisianwatchme.Gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.tunisianwatchme.Entity.Reclamation;
import com.tunisianwatchme.Handler.ImageUtils;
import com.tunisianwatchme.Handler.LieuHandler;
import com.tunisianwatchme.Handler.ReclamationHandler;
import java.io.IOException;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.midlet.*;

/**
 * @author MarwenSdiri <marwen.sdiri@esprit.tn>
 */
public class Midlet extends MIDlet {
    Form f = new Form("");
    Display disp = Display.getDisplay(this);
    public void startApp() {

//        ReclamationHandler dh = new ReclamationHandler();
//        System.out.println(dh.getReclamation());
//        Reclamation rec = (Reclamation)dh.getReclamation().elementAt(0);
//        System.out.println(rec.getId());
        
        
//        Image img = Image.createImage(null);
        String s = "TmV3IHdvcmQgdG8gZW5jb2RlIHVzaW5nIEJhc2U2NCBhbHNvIHNwZWNpYWwgY2hhcnMgbGlrZSDRIGFuZCDz";
        try {
            String str = ImageUtils.decToString(s);
            System.out.println(str);
//            Image img = Image.createImage(str);
//            f.append(img);
//            disp.setCurrent(f);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
}
