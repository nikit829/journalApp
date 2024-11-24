package net.springbootproject.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.springbootproject.journalApp.entity.User;
import net.springbootproject.journalApp.repository.UserEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserEntryService {

    @Autowired
    private UserEntryRepository userEntryRepository;

    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public void saveEntry(User user) {
        userEntryRepository.save(user);
    }
    public void saveNewUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userEntryRepository.save(user);
        }
        catch (Exception e) {
            log.error("Error occurred for {}:",user.getUsername(), e);
        }

    }
    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER", "ADMIN"));
        userEntryRepository.save(user);
    }
    public List<User> getAll() {
        return userEntryRepository.findAll();
    }
    public Optional<User> findById(ObjectId id) {
        return userEntryRepository.findById(id);
    }
    public User findByUsername(String username) {
        return userEntryRepository.findByUsername(username);
    }
    public void deleteById(ObjectId id) {
        userEntryRepository.deleteById(id);
    }
    public User deleteByUsername(String username) {
        return userEntryRepository.deleteByUsername(username);
    }
}
