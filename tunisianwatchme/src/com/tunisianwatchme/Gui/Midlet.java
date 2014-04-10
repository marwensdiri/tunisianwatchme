package com.tunisianwatchme.Gui;

import java.util.Vector;
import com.tunisianwatchme.Entity.Domaine;
import com.tunisianwatchme.Entity.Lieu;
import com.tunisianwatchme.Entity.Reclamation;
import com.tunisianwatchme.Entity.Stat;
import com.tunisianwatchme.Entity.Utilisateur;
import com.tunisianwatchme.Handler.DocumentHandler;
import com.tunisianwatchme.Handler.DomaineHandler;
import com.tunisianwatchme.Handler.LieuHandler;
import com.tunisianwatchme.Handler.LoginHandler;
import com.tunisianwatchme.Handler.ReclamationHandler;
import com.tunisianwatchme.Handler.StatHandler;
import com.tunisianwatchme.Handler.UtilisateurHandler;
import com.tunisianwatchme.Post.EmailPost;
import com.tunisianwatchme.Post.ReclamationPost;
import com.tunisianwatchme.Post.UtilisateurPost;
import de.enough.polish.ui.ChartItem;
import de.enough.polish.ui.DateField;
import de.enough.polish.ui.Style;
import de.enough.polish.ui.TextField;
import de.enough.polish.ui.UiAccess;
import java.util.TimeZone;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.List;
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
    Form mProfil;
    List listReclamations;
    Form infoReclamation;

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
    StringItem mailItem = new StringItem("", "                                                                            ", Item.BUTTON);
    StringItem profilItem = new StringItem("", "                                                                            ", Item.BUTTON);
    StringItem StatItem = new StringItem("", "                                                                            ", Item.BUTTON);

    StringItem bar = new StringItem("", "", Item.PLAIN);
    TextField titre = new TextField("Titre :", null, 30, TextField.ANY);
    DateField date = new DateField("Date : ", DateField.DATE, TimeZone.getDefault());
    ChoiceGroup domaine = new ChoiceGroup("Domaine :", ChoiceGroup.POPUP);
    ChoiceGroup lieu = new ChoiceGroup("Lieu", ChoiceGroup.POPUP);
    DateField time = new DateField("Heure : ", DateField.TIME);
    TextField desc = new TextField("Description :", null, 30, TextField.ANY);
    Command cancel = new Command("cancel", Command.EXIT, 10);

    String type;

    StringItem titreReclamation = new StringItem("titre", "");
    StringItem descriptionReclamation = new StringItem("description", "");
    StringItem dateReclamation = new StringItem("date", "");
    StringItem heureReclamation = new StringItem("heure", "");
    StringItem citoyenReclamation = new StringItem("citoyen", "");
    StringItem domaineReclamation = new StringItem("domaine", "");
    StringItem lieuReclamation = new StringItem("lieu", "");
    Vector imagesReclamation = new Vector();

    private Form StatForm;
    private Command styleLines = new Command("Lines", Command.SCREEN, 1);
    private Command styleVerticalBar = new Command("Vertical Bars", Command.SCREEN, 1);
    private Command stylePieChart = new Command("Pie Chart", Command.SCREEN, 1);
    Vector v1;
    Vector v2;
    Vector v3;
    DomaineHandler dh;
    LieuHandler lh;
    ReclamationHandler rh;
    UtilisateurHandler uh;

    TextField nom = new TextField("Nom :", null, 30, TextField.ANY);
    TextField prenom = new TextField("Prenom :", null, 30, TextField.ANY);
    TextField login = new TextField("Login :", null, 30, TextField.ANY);
    ChoiceGroup sexe = new ChoiceGroup("Sexe :", ChoiceGroup.POPUP);
    TextField email = new TextField("Email :", null, 30, TextField.EMAILADDR);
    DateField dateP = new DateField("Date : ", DateField.DATE, TimeZone.getDefault());
    TextField adresse = new TextField("Adresse :", null, 30, TextField.ANY);

    Form mailForm;
    ChoiceGroup lisrResp = new ChoiceGroup("Utilisateur", ChoiceGroup.POPUP);
    TextField sujetMail = new TextField("Sujet", "", 50, TextField.ANY);
    TextField msgMail = new TextField("Méssage", "", 50, TextField.ANY);

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
        profilItem.addCommand(select);
        profilItem.setItemCommandListener(this);

        StatItem.addCommand(select);
        StatItem.setItemCommandListener(this);

        mailItem.addCommand(select);
        mailItem.setItemCommandListener(this);

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

        //#style flux2
        profil.append(StatItem);
        //#style flux3
        profil.append(profilItem);

        //#style flux4
        profil.append(mailItem);

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
        this.StatForm.addCommand(cancel);
        Command parent = new Command("selectionnez type", Command.SCREEN, 2);
        this.StatForm.addCommand(parent);
        UiAccess.addSubCommand(this.styleLines, parent, this.StatForm);
        UiAccess.addSubCommand(this.styleVerticalBar, parent, this.StatForm);
        UiAccess.addSubCommand(this.stylePieChart, parent, this.StatForm);
        this.StatForm.setCommandListener(this);

        //Interface Modifier Profile 
        //#style modifierBar
        mProfil = new Form("");

        //#style br
        mProfil.append(space);
        //#style br
        mProfil.append(space);
        //#style br
        mProfil.append(space);
        //#style br
        mProfil.append(space);

        //#style dateField
        mProfil.append(nom);
        //#style dateField
        mProfil.append(prenom);
        //#style dateField
        mProfil.append(login);
        //#style dateField
        mProfil.append(sexe);
        //#style dateField
        mProfil.append(email);
        //#style dateField
        mProfil.append(dateP);
        //#style dateField
        mProfil.append(adresse);
        mProfil.setCommandListener(this);
        mProfil.addCommand(cancel);
        mProfil.addCommand(submit);

        //interface de la liste des réclamations
        //#style list
        listReclamations = new List("", List.IMPLICIT);

        //listReclamations.addCommand(select);
        listReclamations.addCommand(cancel);
        listReclamations.setCommandListener(this);

        //interface d'information d'une réclamation
        //#style news
        infoReclamation = new Form("");
        //#style br
        infoReclamation.append(space);
        //#style br
        infoReclamation.append(space);
        //#style br
        infoReclamation.append(space);
        //#style br
        infoReclamation.append(space);
        //#style dateField
        infoReclamation.append(titreReclamation);
        //#style dateField
        infoReclamation.append(descriptionReclamation);
        //#style dateField
        infoReclamation.append(lieuReclamation);
        //#style dateField
        infoReclamation.append(citoyenReclamation);
        //#style dateField
        infoReclamation.append(dateReclamation);
        //#style dateField
        infoReclamation.append(heureReclamation);
        //#style dateField
        infoReclamation.append(domaineReclamation);
        //#style dateField
        infoReclamation.addCommand(cancel);

        infoReclamation.setCommandListener(this);

        //interface de mail
        //#style mail
        mailForm = new Form("");
        //#style br
        mailForm.append(space);
         //#style br
        mailForm.append(space);
        //#style br
        mailForm.append(space);
        //#style br
        mailForm.append(space);
        //#style br
        mailForm.append(lisrResp);
        mailForm.append(sujetMail);
        mailForm.append(msgMail);
        mailForm.addCommand(submit);
        mailForm.addCommand(cancel);
        mailForm.setCommandListener(this);
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
            Thread thr = new Thread(new Runnable() {

                public void run() {
                    rh = new ReclamationHandler();
                    rh.getReclamation();
                    for (int i = 0; i < rh.getReclamation().size(); i++) {
                        Reclamation reclamation = (Reclamation) rh.getReclamation().elementAt(i);
                        listReclamations.append(reclamation.toString(), null);
                    }
                }
            });

            thr.start();
            d.setCurrent(listReclamations);
        } else if (item == this.ajoutReclamationItem && cmd == this.select) {
            type = "ajout";
            Thread thr = new Thread(this);
            thr.start();
        } else if (item == mailItem && cmd == this.select) {
            Thread thr = new Thread(new Runnable() {

                public void run() {
                    uh = new UtilisateurHandler(0);
                    for (int i = 0; i < uh.getUtilisateurVector().size(); i++) {
                        Utilisateur resp = (Utilisateur) uh.getUtilisateurVector().elementAt(i);
                        lisrResp.append(resp.getNom(), null);
                    }
                    d.setCurrent(mailForm);
                }
            });

            thr.start();
        } else if (item == this.StatItem && cmd == this.select) {
            Thread thr = new Thread(new Runnable() {

                public void run() {
                    StatHandler sh1 = new StatHandler('l');
                    StatHandler sh2 = new StatHandler('e');
                    StatHandler sh3 = new StatHandler('d');
                    v1 = sh1.getstatVector();
                    v2 = sh2.getstatVector();
                    v3 = sh3.getstatVector();
                }
            });
            thr.start();
            updateChart();
            d.setCurrent(StatForm);
        } else if (item == this.profilItem && cmd == this.select) {
            System.out.println("success");
            type = "modifier-profil";
            Thread thr = new Thread(new Runnable() {

                public void run() {

                    nom.setText(me.getNom());
                    prenom.setText(me.getPrenom());
                    login.setText(me.getLogin());
                    sexe.append("Homme", null);
                    sexe.append("Femme", null);
                    if (me.getSexe() == 'H') {
                        sexe.setSelectedIndex(0, true);
                    } else {
                        sexe.setSelectedIndex(1, true);
                    }
                    email.setText(me.getMail());
                    adresse.setText(me.getAdress());

                }
            });
            thr.start();
            d.setCurrent(mProfil);
        }
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
        } else if (c == cancel) {
            d.setCurrent(profil);
        } else if (disp == menuScreen && c == log) {
            Thread thr = new Thread(this);
            thr.start();
        } else if (c == this.styleLines) {

            Thread thr = new Thread(new Runnable() {
                public void run() {
                    //#style lineChart
                    updateChart();
                }
            });
            thr.start();
        } else if (c == this.styleVerticalBar) {

            Thread thr = new Thread(new Runnable() {
                public void run() {
                    //#style verticalBarChart
                    updateChart();
                }
            });
            thr.start();
        } else if (c == this.stylePieChart) {

            Thread thr = new Thread(new Runnable() {
                public void run() {
                    //#style pieChart
                    updateChart();
                }
            });
            thr.start();
        } else if (disp == mProfil && c == submit) {
            Thread thr = new Thread(new Runnable() {

                public void run() {
                    try {
                        me.setAdress(adresse.getText());
                        me.setLogin(login.getText());
                        me.setPrenom(prenom.getText());
                        me.setPrenom(nom.getText());
                        me.setSexe(sexe.getString(sexe.getSelectedIndex()).charAt(0));
                        UtilisateurPost uPost = new UtilisateurPost(me, 'U');
                        uPost.start();
                        uPost.join();
                        Alert alert = new Alert("succee de modification", "modification effectuee avec succes", null, AlertType.INFO);
                        d.setCurrent(alert);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            thr.start();
        } else if (disp == listReclamations && c == List.SELECT_COMMAND) {
            Image img = null;
            for (int i = 0; i < rh.getReclamation().size(); i++) {
                Reclamation reclamation = (Reclamation) rh.getReclamation().elementAt(i);
                if (listReclamations.getString(listReclamations.getSelectedIndex()).equals(reclamation.toString())) {
                    if (reclamation.getCitoyen() != null) {
                        citoyenReclamation.setText(reclamation.getCitoyen().toString());
                    }
                    titreReclamation.setText(reclamation.getTitre());

                    descriptionReclamation.setText(reclamation.getDescription());
                    domaineReclamation.setText(reclamation.getDomaine().toString());
                    dateReclamation.setText(reclamation.getDate());
                    heureReclamation.setText(reclamation.getHeure());
                    lieuReclamation.setText(reclamation.getLieu().toString());
                    // String url =(String) reclamation.getListDocument().elementAt(0);
                    //img = new DocumentHandler(url).getImg();
                    break;
                }
            }
            if (img != null) {
                infoReclamation.append(img);
            }
            d.setCurrent(infoReclamation);
        } else if (disp == mailForm && c == submit) {
            System.out.println("aaa");
            //if (!sujetMail.getText().equals("") && !msgMail.getText().equals("")) {
            for (int i = 0; i < uh.getUtilisateurVector().size(); i++) {
                user = (Utilisateur) uh.getUtilisateurVector().elementAt(i);
                if (user.getNom().equals(lisrResp.getString(lisrResp.getSelectedIndex()))) {
                    Thread thr = new Thread(new Runnable() {

                        public void run() {
                            try {
                                EmailPost emailPost = new EmailPost(sujetMail.getText(), msgMail.getText(), user.getMail());
                                emailPost.start();
                                emailPost.join();
                                Alert alert = new Alert("mail envoyee", "mail envoyee avec succees", null, AlertType.INFO);
                                d.setCurrent(alert);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    });

                    thr.start();
                    break;
                }
            }
            // }
        }
    }

    Utilisateur user;

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
