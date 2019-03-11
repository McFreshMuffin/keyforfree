package de.dhbw.karlsruhe.webprojekt.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import de.dhbw.karlsruhe.webprojekt.model.Benutzer;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@Stateless
public class UserBean {

    @PersistenceContext
    private EntityManager em;

    public Benutzer getUserDetails(int userId){
          try {
            TypedQuery<Benutzer> query = em.createQuery("SELECT b FROM Benutzer b WHERE b.userId = :userId", Benutzer.class);
            query.setParameter("userId", userId);
            return query.getSingleResult();
        } catch (NoResultException nre) {
            Benutzer user = null;
            return user;
        }
    }
    
    public Benutzer loginUser(String email, String password) {
        Benutzer user = null;
        try {
            TypedQuery<Benutzer> query = em.createQuery("SELECT b FROM Benutzer b WHERE b.email = :email AND b.password = :password", Benutzer.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            user = query.getSingleResult();
        } catch (NoResultException nre) {
            nre.printStackTrace();
        }
        return user;
    }

    public boolean registerUser(String email, String password, String vorname, String nachname, String addresse) {
        try {
            TypedQuery<Benutzer> query = em.createQuery("SELECT b FROM Benutzer b WHERE b.email = :email", Benutzer.class);
            query.setParameter("email", email).getSingleResult();
            return false;
        } catch (NoResultException nre) {
            Benutzer user = new Benutzer(email, password, vorname, nachname, addresse);
            em.persist(user);
            return true;
        }
    }

    public boolean login(String username, String password) {

        Object object = em.createQuery("SELECT b FROM Benutzer b WHERE username='"
                + username
                + "' AND password='"
                + password
                + "'").getSingleResult();

        if (object != null) {
            return true;
        }
        return false;
    }
}
