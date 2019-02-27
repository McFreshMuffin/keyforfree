/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.karlsruhe.webprojekt.test;

import de.dhbw.karlsruhe.webprojekt.bean.GameBean;
import javax.ejb.EJB;

/**
 *
 * @author Uwe-Laptop
 */
public class GamesTest {
    
    @EJB
    private static GameBean gameBean;
    
    public static void main(String[] args){
        System.out.println(gameBean.findGame(10));
        
    }
    
}
