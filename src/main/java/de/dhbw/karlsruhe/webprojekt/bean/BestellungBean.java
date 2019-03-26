package de.dhbw.karlsruhe.webprojekt.bean;

import de.dhbw.karlsruhe.webprojekt.model.Bestellung;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BestellungBean {
    
    @PersistenceContext
    EntityManager em;
    
    public void saveBestellung(Bestellung bestellung){
        em.persist(bestellung);
    }
    
    
}
