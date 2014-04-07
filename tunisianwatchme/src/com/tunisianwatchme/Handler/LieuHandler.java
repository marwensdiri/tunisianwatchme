/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tunisianwatchme.Handler;
import java.util.Vector;

/**
 *
 * @author Khadija
 */
public class LieuHandler extends Thread{
    Vector lieuVector;
    int id;

    public LieuHandler(int i) {
        this.lieuVector = new Vector();
        this.id=i;
    }

    public Vector getLieuVector() {
        return lieuVector;
    }

        
    
}
