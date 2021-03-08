package se.lexicon.jpa_workshop.dao;

import se.lexicon.jpa_workshop.entity.AppUser;

import java.util.List;

public interface AppUserDao {

    AppUser create(AppUser appUser);

    AppUser findById(int id);

    List<AppUser> findAll();

    void remove(int id);

    AppUser merge(AppUser appUser);

    List<AppUser> saveAllAppUsers(List<AppUser> appUsers);

    List<AppUser> findByEmail(String email);

}
