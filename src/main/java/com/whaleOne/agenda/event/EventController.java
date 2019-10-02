package com.whaleOne.agenda.event;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventRepository events;

    @GetMapping
    public ResponseEntity get() {
        return new ResponseEntity(events.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable Long id) {
        return new ResponseEntity(events.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> post(
            @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") @RequestParam("date") Date date,
            @RequestParam("description") String description) {
        Event event = new Event();
        event.setDate(date);
        event.setDescription(description);
        events.save(event);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(
            @PathVariable Long id,
            @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") @RequestParam("date") Date date,
            @RequestParam("description") String description) {
        Event event = events.findById(id).get();
        event.setId(id);
        event.setDate(date);
        event.setDescription(description);
        events.save(event);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        events.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
