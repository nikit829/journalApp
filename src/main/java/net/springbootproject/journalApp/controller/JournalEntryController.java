//package net.engineeringdigest.journalApp.controller;
//
//import net.engineeringdigest.journalApp.entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/journal")
//public class JournalEntryController {
//    public Map<Long, JournalEntry> journalentries = new HashMap<>();
//
//    @GetMapping
//    public List<JournalEntry> getAll(){
//        return new ArrayList<>(journalentries.values());
//    }
//
//    @GetMapping("id/{myid}")
//    public JournalEntry getById(@PathVariable Long myid){
//        return journalentries.get(myid);
//    }
//
//    @PostMapping
//    public boolean createEntry(@RequestBody JournalEntry entry){
//    journalentries.put(entry.getId(), entry);
//    return true;
//    }
//
//    @PutMapping("id/{myid}")
//    public boolean updateEntry(@RequestBody JournalEntry entry, @PathVariable Long myid){
//        journalentries.put(myid, entry);
//        return true;
//    }
//
//    @DeleteMapping("id/{myid}")
//    public boolean deleteEntry(@PathVariable Long myid){
//        journalentries.remove(myid);
//        return true;
//    }
//}
