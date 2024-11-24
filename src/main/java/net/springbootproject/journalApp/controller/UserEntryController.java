package net.springbootproject.journalApp.controller;

import net.springbootproject.journalApp.entity.User;
import net.springbootproject.journalApp.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserEntryController {

    @Autowired
    private UserEntryService userEntryService;

    //below commented code will list all users which is unnecessary for users to see
//    @GetMapping
//    public ResponseEntity<?> getAllUsers() {
//        List<User> records = userEntryService.getAll();
//        if(records != null && !records.isEmpty()) {
//            return new ResponseEntity<>(records, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(records, HttpStatus.NOT_FOUND);
//    }
//
//    @GetMapping("/{myid}")
//    public ResponseEntity<User> getUserById(@PathVariable("myid") ObjectId myid) {
//        Optional<User> user=userEntryService.findById(myid);
//        if(user.isPresent()) {
//            return new ResponseEntity<>(user.get(), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User old = userEntryService.findByUsername(username);
            old.setUsername(user.getUsername());
            old.setPassword(user.getPassword());
            userEntryService.saveNewUser(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<User> deleteUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            userEntryService.deleteByUsername(username);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
