package de.dhbw.karlsruhe.webprojekt.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import de.dhbw.karlsruhe.webprojekt.model.User;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@Stateless
public class UserBean {

    @PersistenceContext
    private EntityManager em;

    public User loginUser(String email, String password) {
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            return query.getSingleResult();
        } catch (NoResultException nre) {
            User user = null;
            return user;
        }
    }

    public boolean registerUser(String email, String password, String vorname, String nachname, String addresse) {
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
            query.setParameter("email", email).getSingleResult();
            return false;
        } catch (NoResultException nre) {
            User user = new User(email, password, vorname, nachname, addresse);
            em.persist(user);
            return true;
        }
    }

    public boolean login(String username, String password) {

        Object object = em.createQuery("SELECT u FROM user u WHERE username='"
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
