package com.tunisianwatchme.Gui;

import java.util.Vector;
import com.tunisianwatchme.Entity.Domaine;
import com.tunisianwatchme.Entity.Lieu;
import com.tunisianwatchme.Entity.Reclamation;
import com.tunisianwatchme.Entity.Stat;
import com.tunisianwatchme.Entity.Utilisateur;
import com.tunisianwatchme.Handler.DomaineHandler;
import com.tunisianwatchme.Handler.LieuHandler;
import com.tunisianwatchme.Handler.LoginHandler;
import com.tunisianwatchme.Handler.StatHandler;
import com.tunisianwatchme.Post.ReclamationPost;
import de.enough.polish.ui.ChartItem;
import de.enough.polish.ui.DateField;
import de.enough.polish.ui.Style;
import de.enough.polish.ui.TextField;
import de.enough.polish.ui.UiAccess;
import java.util.TimeZone;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.StringItem;

/**
 * <p>
 * Demonstrates DateField input.</p>
 *
 * <p>
 * Copyright Enough Software 2009</p>
 *
 * @author Robert Virkus, j2mepolish@enough.de
 */
public class Midlet extends MIDlet implements CommandListener, ItemCommandListener, Runnable {

    Utilisateur me;
    Display d;
    Form profil;
    Form menuScreen;
    Form ajout;
    //Command cmdQuit = new Command("Quit", Command.EXIT, 10);
    Command log = new Command("Login", Command.SCREEN, 10);

    Command select = new Command("Select", Command.SCREEN, 10);

    Command submit = new Command("Submit", Command.SCREEN, 10);

    Command logout = new Command("Logout", Command.EXIT, 10);

    StringItem loginItem = new StringItem("", "", Item.BUTTON);
    StringItem img = new StringItem("", "", Item.PLAIN);
    StringItem space = new StringItem("", "", Item.PLAIN);
    TextField Loginfield = new TextField("", "UserName", 30, TextField.ANY);
    TextField Passfield = new TextField("", "****", 30, TextField.ANY);
    StringItem img2 = new StringItem("", "                                                                            ", Item.LAYOUT_TOP);
    StringItem fluxReclamationItem = new StringItem("", "                                                                            ", Item.BUTTON);
    StringItem ajoutReclamationItem = new StringItem("", "                                                                            ", Item.BUTTON);
    StringItem GeolocalisationItem = new StringItem("", "                                                                            ", Item.BUTTON);
    StringItem StatItem = new StringItem("", "                                                                            ", Item.BUTTON);

    StringItem bar = new StringItem("", "", Item.PLAIN);
    TextField titre = new TextField("Titre :", null, 30, TextField.ANY);
    DateField date = new DateField("Date : ", DateField.DATE, TimeZone.getDefault());
    ChoiceGroup domaine = new ChoiceGroup("Domaine", ChoiceGroup.POPUP);
    ChoiceGroup lieu = new ChoiceGroup("Lieu", ChoiceGroup.POPUP);
    DateField time = new DateField("Heure : ", DateField.TIME);
    TextField desc = new TextField("Description :", null, 30, TextField.ANY);
    Command cancel = new Command("cancel", Command.EXIT, 10);

    String type;

    private Form StatForm;
    private Command exitCommand = new Command("Exit", Command.EXIT, 3);
    private Command styleLines = new Command("Lines", Command.SCREEN, 1);
    private Command styleVerticalBar = new Command("Vertical Bars", Command.SCREEN, 1);
    private Command stylePieChart = new Command("Pie Chart", Command.SCREEN, 1);
    Vector v1;
    Vector v2;
    Vector v3;
    DomaineHandler dh;
    LieuHandler lh;

    public Midlet() {
        super();
        date.setDateFormatPattern("yyyy-MM-dd");
        //time.setDateFormatPattern("hh:mm");
        // domaine.add(titre);
        d = Display.getDisplay(this);

        loginItem.addCommand(log);
        loginItem.setItemCommandListener(this);
        fluxReclamationItem.addCommand(select);
        fluxReclamationItem.setItemCommandListener(this);
        ajoutReclamationItem.addCommand(select);
        ajoutReclamationItem.setItemCommandListener(this);
        GeolocalisationItem.addCommand(select);
        GeolocalisationItem.setItemCommandListener(this);
        StatItem.addCommand(select);
        StatItem.setItemCommandListener(this);

        this.menuScreen = new Form("");
        //#style mainScreen
        this.menuScreen.append(img);

        //#style textField
        this.menuScreen.append(Loginfield);

        //#style textField
        this.menuScreen.append(Passfield);

        

        //#style loginPng
        this.menuScreen.append(loginItem);

        //this.menuScreen.addCommand(this.cmdQuit);
        this.menuScreen.addCommand(this.log);
        this.menuScreen.setCommandListener(this);

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

        
        //#style flux3
        profil.append(StatItem);
        //#style flux2
        profil.append(GeolocalisationItem);

        profil.setCommandListener(this);
        profil.addCommand(this.logout);
        // profil.addCommand(this.cmdQuit);

//Interface Ajout Réclamation 
        //#style acceuilBar
        ajout = new Form("");

        ajout.addCommand(submit);
        ajout.setCommandListener(this);

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

        //Interface Statistiques
        //#style test
        this.StatForm = new Form("Statistiques");
        this.StatForm.addCommand(this.exitCommand);
        Command parent = new Command("selectionnez type", Command.SCREEN, 2);
        this.StatForm.addCommand(parent);
        UiAccess.addSubCommand(this.styleLines, parent, this.StatForm);
        UiAccess.addSubCommand(this.styleVerticalBar, parent, this.StatForm);
        UiAccess.addSubCommand(this.stylePieChart, parent, this.StatForm);
        this.StatForm.setCommandListener(this);

    }

    protected void startApp() throws MIDletStateChangeException {
        d.setCurrent(this.menuScreen);
    }

    protected void pauseApp() {

    }

    protected void destroyApp(boolean unconditional) throws MIDletStateChangeException {

    }

    public void commandAction(Command cmd, Item item) {
        if (item == this.loginItem && cmd == this.log) {
            Thread thr = new Thread(this);
            thr.start();
        } else if (item == this.fluxReclamationItem && cmd == this.select) {

        } else if (item == this.ajoutReclamationItem && cmd == this.select) {
            type = "ajout";
            Thread thr = new Thread(this);
            thr.start();
        } else if (cmd == this.exitCommand) {
            notifyDestroyed();
        } else if (item == this.StatItem && cmd == this.select) {
            System.out.println("success");
            type = "chargement-stat";
            Thread thr = new Thread(this);
            thr.start();
         updateChart();
           
            d.setCurrent(StatForm);
        }
        /*............................................................if (d.getCurrent() == this.menuScreen && cmd == this.cmdQuit) {
         notifyDestroyed();
         }
         if (d.getCurrent() == this.profil && cmd == this.cmdQuit) {
         d.setCurrent(menuScreen);
         }

         if (d.getCurrent() == this.profil && cmd == this.logout) {
         d.setCurrent(menuScreen);
         }
         if (d.getCurrent() == this.profil && cmd == this.test) {

         d.setCurrent(ajout);
         }
         if (d.getCurrent() == this.ajout && cmd == this.cancel) {

         d.setCurrent(profil);
         }*/
    }

    public void run() {
        if (d.getCurrent() == menuScreen) {
            LoginHandler loginHandler = new LoginHandler(Loginfield.getText(), Passfield.getText());
            me = loginHandler.getCurrentUtilisateur();
            if (me != null) {
                d.setCurrent(profil);
            }
        } else if (d.getCurrent() == profil && type.equals("ajout")) {
            dh = new DomaineHandler();
            for (int i = 0; i < dh.getDomaineVector().size(); i++) {
                domaine.append(((Domaine) dh.getDomaineVector().elementAt(i)).toString(), null);
            }
            lh = new LieuHandler();
            for (int i = 0; i < lh.getLieuVector().size(); i++) {
                lieu.append(((Lieu) lh.getLieuVector().elementAt(i)).toString(), null);
            }
            d.setCurrent(ajout);
        } else if (d.getCurrent() == ajout && type.equals("submit")) {
            Reclamation reclamation = new Reclamation();
            reclamation.setCitoyen(me);
            reclamation.setDate(date.getText());
            reclamation.setHeure(time.getText() + ":00");
            reclamation.setDescription(desc.getText());
            reclamation.setEtat(0);
            reclamation.setTitre(titre.getText());
            for (int i = 0; i < dh.getDomaineVector().size(); i++) {
                Domaine dom = (Domaine) dh.getDomaineVector().elementAt(i);
                if (dom.getNom() == domaine.getString(domaine.getSelectedIndex())) {
                    reclamation.setDomaine(dom);
                    break;
                }
            }

            for (int i = 0; i < lh.getLieuVector().size(); i++) {
                Lieu li = (Lieu) lh.getLieuVector().elementAt(i);
                if (li.getNom() == lieu.getString(lieu.getSelectedIndex())) {
                    reclamation.setLieu(li);
                    break;
                }
            }
            System.out.println(reclamation.getDate());
            ReclamationPost rp = new ReclamationPost(reclamation, 1);
            rp.start();
            // Alert alertSuccess = new Alert

        } else if (d.getCurrent() == profil && type.equals("chargement-stat")) {
            StatHandler sh1 = new StatHandler('l');
            StatHandler sh2 = new StatHandler('e');
            StatHandler sh3 = new StatHandler('d');
            v1 = sh1.getstatVector();
            v2 = sh2.getstatVector();
            v3 = sh3.getstatVector();
             
        }

    }

    private void quit() throws MIDletStateChangeException {

    }

    public void commandAction(Command c, Displayable disp) {
        if (disp == profil && c == logout) {
            d.setCurrent(menuScreen);
        } else if (disp == ajout && c == submit) {
            type = "submit";
            Thread thr = new Thread(this);
            thr.start();
        } else if (disp == ajout && c == cancel) {
            d.setCurrent(profil);
        } else if (disp == menuScreen && c == log) {
            Thread thr = new Thread(this);
            thr.start();
        } else if (c == this.styleLines) {
            
            Thread  thr= new Thread(new Runnable() {
                public void run() {
                    //#style lineChart
                    updateChart();
                }
            });
            thr.start();
        } else if (c == this.styleVerticalBar) {
           
            Thread  thr= new Thread(new Runnable() {
                public void run() {
                    //#style verticalBarChart
                    updateChart();
                }
            });
            thr.start();
        } else if (c == this.stylePieChart) {
            
           Thread  thr= new Thread(new Runnable() {
                public void run() {
                    //#style pieChart
                    updateChart();
                }
            });
            thr.start();
        }
    }
    /*
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
     */

    private void updateChart() {
        //#style lineChart
        updateChart();
    }

    private void updateChart(Style style) {
        this.StatForm.deleteAll();

        int[] tab1 = new int[v1.size()];
        for (int i = 0; i < v1.size(); i++) {
            tab1[i] = ((Stat) v1.elementAt(i)).getValue();
            System.out.println(tab1[i]);
        }

        int[] tab2 = new int[v2.size()];
        for (int i = 0; i < v2.size(); i++) {
            tab2[i] = ((Stat) v2.elementAt(i)).getValue();
            System.out.println(tab2[i]);
        }

        int[] tab3 = new int[v3.size()];
        for (int i = 0; i < v3.size(); i++) {
            tab3[i] = ((Stat) v3.elementAt(i)).getValue();
            System.out.println(tab3[i]);
        }

        int[] colors = new int[]{0xFF0000, 0x00FF00, 0x0000FF, 0x660033, 0x009999, 0xFFCC33, 0x00CC99, 0xFFFF33, 0x996600};
        int[][] dataSequences = new int[][]{
            tab1
        };
        ChartItem chart = new ChartItem("lieux:", dataSequences, colors, style);
        chart.setLabelX("lieux");
        chart.setLabelY("reclamations");
        this.StatForm.append(chart);
        /*
         * TIGER conseil
         * 
         */
        //this.form.append(new StringItem("hh", "xx"));

        int[][] dataSequences2 = new int[][]{
            tab2
        };
        ChartItem chart2 = new ChartItem("etablissements :", dataSequences2, colors, style);
        chart2.setLabelX("etablissement");
        chart2.setLabelY("reclamations");
        this.StatForm.append(chart2);

        int[][] dataSequences3 = new int[][]{
            tab3
        };
        ChartItem chart3 = new ChartItem("domaines :", dataSequences3, colors, style);
        chart3.setLabelX("domaines");
        chart3.setLabelY("reclamations");
        this.StatForm.append(chart3);
       
    }

}
