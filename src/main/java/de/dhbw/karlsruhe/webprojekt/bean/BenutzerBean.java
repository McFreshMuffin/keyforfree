/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package de.dhbw.karlsruhe.webprojekt.bean;

import de.dhbw.karlsruhe.webprojekt.model.Benutzer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Enterprise Java Bean zur Verwaltung der angelegten Textschnippsel.
 */
@Stateless
public class BenutzerBean {
    
    @PersistenceContext
    EntityManager em;
    
    /**
     * Anlegen eines neuen Textschnippsels.
     * @param name Bezeichnung
     * @param vorname Bezeichnung
     * @return Der angelegte Textschnippsel
     */
    public Benutzer createNewBenutzer(String name, String vorname){
        Benutzer benutzer = new Benutzer(name, vorname);
        em.persist(benutzer);
        return em.merge(benutzer);
    }

    
    /**
     * @return Liste mit allen Textschnippsel in umgedrehter Anlagereihenfolge
     */
    public List<Benutzer> findAllBenutzer() {
        return em.createQuery("SELECT b FROM Benutzer b").getResultList();
    }
    
    /**
     * Einzelnen Textschnippsel ermitteln
     * @param id ID des Textschnippsels
     * @return Gefundenes Objekt oder null
     */
    public Benutzer findBenutzer(long id) {
        return em.find(Benutzer.class, id);
    }
    
    /**
     * Löschen eines Textschnippsels. (Müll leeren :-))
     * 
     * @param id ID des zu löschenden Schnippsels
     * @return Der gelöschte Schnippsel oder null
     */
    public Benutzer deleteWaste(long id) {
        Benutzer benutzer = em.find(Benutzer.class, id);
        
        if (benutzer != null) {
            em.remove(benutzer);
        }
        
        return benutzer;
    }
    
    /**
     * Änderungen an einem Textschnippsel speichern.
     * @param benutzer Der zu ändernde Textschnippsel
     * @return Neue, gespeicherte Version des Textschnippsels
     */
    public Benutzer updateWaste(Benutzer benutzer) {
        return em.merge(benutzer);
    }
    
}
