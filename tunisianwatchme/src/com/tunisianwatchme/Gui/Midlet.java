package com.tunisianwatchme.Gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.tunisianwatchme.Entity.Commentaire;
import com.tunisianwatchme.Entity.Reclamation;
import com.tunisianwatchme.Entity.Utilisateur;
import com.tunisianwatchme.Handler.ReclamationHandler;
import com.tunisianwatchme.Post.ReclamationPost;
import com.tunisianwatchme.Post.UtilisateurPost;
import javax.microedition.midlet.*;

/**
 * @author MarwenSdiri <marwen.sdiri@esprit.tn>
 */
public class Midlet extends MIDlet {

    public void startApp() {

        /**** test habchi  ****/
//        ReclamationHandler dh = new ReclamationHandler();
//        System.out.println(dh.getReclamation());
//
//        for (int i = 0; i < dh.getReclamation().size(); i++) {
//            Reclamation rec = (Reclamation) dh.getReclamation().elementAt(i);
//            System.out.println(rec.getId() + " " + rec.getListCommentaire().size());
//            for(int j=0;j<rec.getListCommentaire().size();j++){
//                Commentaire comm = (Commentaire) rec.getListCommentaire().elementAt(j);
//                System.out.println(comm.getTexte());
//            }
//        }
//        System.out.println(dh.getReclamation().size());
        
        
        //        ReclamationHandler dh = new ReclamationHandler();
//        System.out.println(dh.getReclamation());
//        Reclamation rec = (Reclamation)dh.getReclamation().elementAt(0);
//        System.out.println(rec.getId());
        
        /**** test Farouk  ****/
  /*
        
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
 */       
        /**** test Utilisateur  ****/
        
        Utilisateur user = new Utilisateur();
        user.setId(43);
        user.setNom("Tiger");
        user.setPrenom("Aymen");
        user.setMail("mail");
        user.setLogin("login");
        user.setMdp("password");
        user.setSexe('M');
        
        
        UtilisateurPost userPost = new UtilisateurPost(user,'U');
        userPost.start();
        
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
}
