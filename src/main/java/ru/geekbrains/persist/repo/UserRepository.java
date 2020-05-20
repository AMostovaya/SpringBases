package ru.geekbrains.persist.repo;

import org.springframework.stereotype.Repository;
import ru.geekbrains.persist.enity.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {

   private final Map<Long,User> users;
   private final AtomicLong identityGen;

    public UserRepository() {

        this.identityGen = new AtomicLong(0);
        this.users = new ConcurrentHashMap<>();
    }

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public void saveUser(User user) {
        long id = identityGen.incrementAndGet();
        user.setId(id);
        users.put(id, user);
    }

    public User findById(long id) {
        return users.get(id);
    }
}

