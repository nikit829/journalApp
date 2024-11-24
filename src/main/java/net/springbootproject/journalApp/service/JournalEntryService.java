package net.springbootproject.journalApp.service;

import net.springbootproject.journalApp.entity.JournalEntry;
import net.springbootproject.journalApp.entity.User;
import net.springbootproject.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserEntryService userEntryService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username) {
        try {
            User user = userEntryService.findByUsername(username);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved= journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userEntryService.saveEntry(user);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }
    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }
    @Transactional
    public boolean deleteById(ObjectId id,String username) {
        boolean removed =false;
        try {
        User user = userEntryService.findByUsername(username);
        removed=user.getJournalEntries().removeIf(journalEntry -> journalEntry.getId().equals(id));
        if(removed) {
            userEntryService.saveEntry(user);
            journalEntryRepository.deleteById(id);
        }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return removed;
    }
}
