package com.tunisianwatchme.Gui;


import com.tunisianwatchme.Handler.LoginHandler;
import de.enough.polish.ui.ChoiceGroup;
import de.enough.polish.ui.DateField;
import de.enough.polish.ui.TextField;
import java.util.TimeZone;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.StringItem;

import javax.microedition.lcdui.CustomItem;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.ItemCommandListener;

/**
 * <p>
 * Demonstrates DateField input.</p>
 *
 * <p>
 * Copyright Enough Software 2009</p>
 *
 * @author Robert Virkus, j2mepolish@enough.de
 */
public class Midlet extends MIDlet implements ItemCommandListener,CommandListener,Runnable {

    Display d;
    Form profil;
    Form menuScreen;
    Form ajout;
    Command cmdQuit = new Command("Quit", Command.EXIT, 10);
    Command log = new Command("Login", Command.SCREEN, 10);
    Command logout = new Command("Logout", Command.SCREEN, 10);

    StringItem loginItem = new StringItem("","",Item.BUTTON);
    StringItem img = new StringItem("", "", Item.PLAIN);
    StringItem space = new StringItem("", "", Item.PLAIN);
    TextField Loginfield = new TextField("", "UserName", 30, TextField.ANY);
    TextField Passfield = new TextField("", "****", 30, TextField.ANY);
    StringItem img2 = new StringItem("", "                                                                            ", Item.LAYOUT_TOP);
    StringItem fluxReclamationItem = new StringItem("", "                                                                            ", Item.BUTTON);
    StringItem ajoutReclamationItem = new StringItem("", "                                                                            ", Item.BUTTON);
    StringItem GeolocalisationItem = new StringItem("", "                                                                            ", Item.BUTTON);
    StringItem StatItem = new StringItem("", "                                                                            ", Item.BUTTON);
    Command test = new Command("test", Command.SCREEN, 10);

    StringItem bar = new StringItem("", "", Item.PLAIN);
    TextField titre = new TextField("Titre :", null, 30, TextField.ANY);
    DateField date = new DateField("Date : ", DateField.DATE, TimeZone.getDefault());
    ChoiceGroup domaine = new ChoiceGroup("Domaine", ChoiceGroup.POPUP);
    ChoiceGroup lieu = new ChoiceGroup("Lieu", ChoiceGroup.POPUP);
    DateField time = new DateField("Heure : ", DateField.TIME);
    TextField desc = new TextField("Description :", null, 30, TextField.ANY);
    Command cancel = new Command("cancel", Command.SCREEN, 10);
    
    
    
    public Midlet() {
        super();
        d = Display.getDisplay(this);
        
        loginItem.addCommand(log);
        loginItem.setItemCommandListener(this);
        
        this.menuScreen = new Form("");
        //#style mainScreen
        this.menuScreen.append(img);

        //#style textField
        this.menuScreen.append(Loginfield);

        //#style textField
        this.menuScreen.append(Passfield);

        //#style br
        this.menuScreen.append(space);

        //#style loginPng
        this.menuScreen.append(loginItem);
        this.menuScreen.setCommandListener(this);

        this.menuScreen.addCommand(this.cmdQuit);
        this.menuScreen.addCommand(this.log);

///Interface Profile 
        //#style profilBar
        profil = new Form("");

        //#style br
        profil.append(space);
        //#style br
        profil.append(space);
        //#style br
        profil.append(space);
        //#style br
        profil.append(space);
        //#style br
        profil.append(space);

        //#style flux
        profil.append(fluxReclamationItem);
       

        //#style flux1
        profil.append(ajoutReclamationItem);

        //#style flux2
        profil.append(GeolocalisationItem);
        //#style flux3
        profil.append(StatItem);
       
        profil.setCommandListener(this);
        profil.addCommand(this.logout);
        profil.addCommand(this.test);
        profil.addCommand(this.cmdQuit);

//Interface Ajout Réclamation 
        //#style acceuilBar
        ajout = new Form("");

        //#style br
        ajout.append(space);
        //#style br
        ajout.append(space);
        //#style br
        ajout.append(space);
        //#style br
        ajout.append(space);

        //#style dateField
        ajout.append(titre);

        //#style dateField
        ajout.append(date);

        //#style dateField
        ajout.append(domaine);

        //#style dateField
        ajout.append(lieu);

        //#style dateField
        ajout.append(time);

        //#style dateField
        ajout.append(desc);

        ajout.setCommandListener(this);
        ajout.addCommand(cancel);
    }

    protected void startApp() throws MIDletStateChangeException {
        d.setCurrent(this.menuScreen);
    }

    protected void pauseApp() {

    }

    protected void destroyApp(boolean unconditional) throws MIDletStateChangeException {

    }

    public void commandAction(Command cmd, Item item ) {
        if (item == this.loginItem && cmd == this.log) {
            try {
                Thread thr = new Thread(this);
                thr.start();
                thr.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        /*if (screen == this.menuScreen && cmd == this.cmdQuit) {
            notifyDestroyed();
        }
        if (screen == this.profil && cmd == this.cmdQuit) {
            notifyDestroyed();
        }

        if (screen == this.profil && cmd == this.logout) {

            d.setCurrent(menuScreen);
        }
        if (screen == this.profil && cmd == this.test) {

            d.setCurrent(ajout);
        }
        if (screen == this.ajout && cmd == this.cancel) {

            d.setCurrent(profil);
        }*/
    }

    
            public void run() {
                System.out.println("aa");
            if (d.getCurrent() == menuScreen ) {
                System.out.println("bb");
                LoginHandler loginHandler = new LoginHandler(Loginfield.getText(), Passfield.getText());
                if (loginHandler.getCurrentUtilisateur() != null) {
                    System.out.println("cc");
                    d.setCurrent(profil);
                }
            }
           else if( true) { 
                    System.out.println("test");
                }
        
        }
    
    private void quit() throws MIDletStateChangeException {

    }

    public void commandAction(Command c, Displayable d) {
    }

    

    class MyCustomItem extends CustomItem{

        int count = 0;

        public MyCustomItem(String label) {
            super(label);
        }

        protected void paint(Graphics g, int w, int h) {
            g.drawString("" + count, 10, 10, Graphics.TOP | Graphics.LEFT);
            //Paint ur own stuff here
        }

        protected void keyPressed(int keyCode) {
            //write ur code here to do stuff

            if (keyCode == -5) {
                System.out.println("dd");
            }
        }

        protected int getMinContentWidth() {
            return 0;

        }

        protected int getMinContentHeight() {
            return 0;
        }

        protected int getPrefContentWidth(int height) {
            return 0;
        }

        protected int getPrefContentHeight(int width) {
            return 0;
        }
        
        



    }

}
