package se.lexicon.jpa_workshop.dao;


import org.springframework.stereotype.Repository;
import se.lexicon.jpa_workshop.entity.AppUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class AppUserDaoImpl implements AppUserDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public AppUser create(AppUser appUser) {
        entityManager.persist(appUser);
        return appUser;
    }

    @Override
    public AppUser findById(int id) {
        return entityManager.find(AppUser.class, id);
    }

    @Override
    public List<AppUser> findAll() {
        Query query = entityManager.createQuery("select * from AppUser *");
        List<AppUser> users = query.getResultList();
        return users;
    }

    @Override
    public void remove(int id) {
        AppUser userRemoved = findById(id);
        if(userRemoved != null)
            entityManager.remove(userRemoved);
    }

    @Override
    public AppUser merge(AppUser appUser) {
        return entityManager.merge(appUser);
    }

    @Override
    public List<AppUser> saveAllAppUsers(List<AppUser> appUsers) {
       for (AppUser appUser : appUsers)
           create(appUser);
        return appUsers;
    }

    @Override
    public List<AppUser> findByEmail(String email) {
        Query query = entityManager.createQuery("select e from AppUser e where e.email = :em");
        query.setParameter("em",AppuserEmail);
        List<AppUser> userEmail = query.getResultList();
        return Optional<AppUser> userEmail;
    }
}
