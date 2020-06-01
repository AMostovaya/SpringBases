package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.persist.entity.User;
import ru.geekbrains.persist.repo.UserRepository;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(long id) {

        return userRepository.findById(id);
    }

    public Page<User> findByAgeName(Integer minAge, Integer maxAge, String nameSearch, Pageable pageable) {

        if (minAge == null && maxAge == null && nameSearch == null) {
            return userRepository.findAll(pageable);
        }
        if (minAge != null && maxAge == null && nameSearch == null) {
            return userRepository.findByAgeGreaterThanEqual(minAge, pageable);
        }
        if (minAge == null && nameSearch == null) {
            return userRepository.findByAgeLessThanEqual(maxAge, pageable);
        }

        if (nameSearch != null  ) {
            return userRepository.queryByNamePattern(nameSearch, pageable);
        }

        return userRepository.findByAgeBetween(minAge, maxAge, nameSearch, pageable);
   }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }


}
