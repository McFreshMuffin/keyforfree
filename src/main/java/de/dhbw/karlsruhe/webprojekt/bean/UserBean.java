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

    public User getUserDetails(int userId){
          try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.userId = :userId", User.class);
            query.setParameter("userId", userId);
            return query.getSingleResult();
        } catch (NoResultException nre) {
            User user = null;
            return user;
        }
    }
    
    public User loginUser(String email, String password) {
        User user = null;
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class);
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
