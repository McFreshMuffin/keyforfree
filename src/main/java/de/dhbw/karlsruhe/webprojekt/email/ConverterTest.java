/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dhbw.karlsruhe.webprojekt.email;

import de.dhbw.karlsruhe.webprojekt.model.Games;

/**
 *
 * @author Uwe-Laptop
 */
public class ConverterTest {
    
    public static void main(String[] args){
        ObjectConverter converter = new ObjectConverter();
        Games game = new Games();
        game.setName("Test");
        game.setAboutText("Über Test");
        converter.convertToXml(game);
    }
}
