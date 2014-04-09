package com.tunisianwatchme.Gui;

import com.tunisianwatchme.Entity.Reclamation;
import com.tunisianwatchme.Handler.DocumentHandler;
import com.tunisianwatchme.Handler.DomaineHandler;
import com.tunisianwatchme.Handler.LoginHandler;
import com.tunisianwatchme.Handler.ReclamationHandler;
import de.enough.polish.ui.TextField;
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

/**
 * <p>
 * Demonstrates DateField input.</p>
 *
 * <p>
 * Copyright Enough Software 2009</p>
 *
 * @author Robert Virkus, j2mepolish@enough.de
 */
public class Midlet extends MIDlet implements CommandListener {

    Display d;
    Form profil;
    Form menuScreen;
    Form ajout;
    Command cmdQuit = new Command("Quit", Command.EXIT, 10);
    Command log = new Command("Login", Command.SCREEN, 10);
    Command logout = new Command("Logout", Command.SCREEN, 10);
    //  StringItem stringItem = new StringItem("", "", Item.BUTTON);
    MyCustomItem stringItem = new MyCustomItem("");

    TextField field = new TextField("", "UserName", 30, TextField.ANY);
    TextField field2 = new TextField("", "****", 30, TextField.ANY);

    public Midlet() {
        super();
        d = Display.getDisplay(this);
        this.menuScreen = new Form("");

        StringItem img = new StringItem("", "", Item.LAYOUT_TOP);
        StringItem space = new StringItem("", "", Item.PLAIN);

        //#style mainScreen
        this.menuScreen.append(img);

        //#style textField
        this.menuScreen.append(field);

        //#style textField
        this.menuScreen.append(field2);

        //#style br
        this.menuScreen.append(space);

        //#style loginPng
        this.menuScreen.append(stringItem);
        this.menuScreen.setCommandListener(this);

        this.menuScreen.addCommand(this.cmdQuit);
        this.menuScreen.addCommand(this.log);

        this.menuScreen.addCommand(this.cmdQuit);
        this.menuScreen.addCommand(this.log);

        this.menuScreen.addCommand(this.cmdQuit);
        this.menuScreen.addCommand(this.log);

//Interface Profile 
        //#style profilBar
        profil = new Form("");

        StringItem img2 = new StringItem("", "                                                             ", Item.LAYOUT_TOP);
        StringItem flux = new StringItem("", "                                                             ", Item.BUTTON);
        StringItem flux1 = new StringItem("", "                                                             ", Item.BUTTON);
        StringItem flux2 = new StringItem("", "                                                             ", Item.BUTTON);
        StringItem flux3 = new StringItem("", "                                                             ", Item.BUTTON);

        profil.append(img2);
        profil.append(img2);
        profil.append(img2);
        profil.append(img2);
        profil.append(img2);
        //#style flux
        profil.append(flux);
        //#style flux1
        profil.append(flux1);
        //#style flux2
        profil.append(flux2);
        //#style flux3
        profil.append(flux3);
        profil.setCommandListener(this);
        profil.addCommand(this.logout);
        profil.addCommand(this.cmdQuit);

        //Interface Ajout Réclamation 
        ajout = new Form("");
        
        //style acceuilBar
        this.ajout.append(img);
        ajout.setCommandListener(this);

    }

    protected void startApp() throws MIDletStateChangeException {

        d.setCurrent(this.menuScreen);

    }

    protected void pauseApp() {

    }

    protected void destroyApp(boolean unconditional) throws MIDletStateChangeException {

    }

    public void commandAction(Command cmd, Displayable screen) {
        if (screen == this.menuScreen && cmd == this.log) {

            d.setCurrent(this.profil);

        }
        if (screen == this.menuScreen && cmd == this.cmdQuit) {
            notifyDestroyed();
        }
        if (screen == this.profil && cmd == this.cmdQuit) {
            notifyDestroyed();
        }

        if (screen == this.profil && cmd == this.logout) {

            d.setCurrent(menuScreen);
        }
    }

    private void quit() throws MIDletStateChangeException {

    }

    class MyCustomItem extends CustomItem implements Runnable {

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
                Thread thr = new Thread(this);
                thr.start();
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

        public void run() {
            if (d.getCurrent() == menuScreen) {

                System.out.println(field.getText());
                LoginHandler loginHandler = new LoginHandler(field.getText(), field2.getText());
                if (loginHandler.getCurrentUtilisateur() != null) {
                    d.setCurrent(profil);
                } else {
                    //Alert alert = new Alert(null, null, null, AlertType.INFO)
                }
            }

        }
    }
}
