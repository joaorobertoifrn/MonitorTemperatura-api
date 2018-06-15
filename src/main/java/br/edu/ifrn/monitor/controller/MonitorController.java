package br.edu.ifrn.monitor.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrn.monitor.model.Monitor;
import br.edu.ifrn.monitor.repository.MonitorRepository;

@RestController
@RequestMapping("/api")
public class MonitorController {

    @Autowired
    MonitorRepository monitorRepository;

    @GetMapping("/temperatura")       
    public List<Monitor> getAllNotes() {
        return monitorRepository.findAll();
    }

    @PostMapping("/temperatura")
    public Monitor createTemp(@Valid @RequestBody Monitor monitor) {
        return monitorRepository.save(monitor);
    }

    @GetMapping("/temperatura/{id}")
    public Monitor getTempById(@PathVariable(value = "id") Long tempId) {
        return monitorRepository.findOne(tempId);
    }

    @PutMapping("/temperatura/{id}")
    public Monitor updateTemp(@PathVariable(value = "id") Long tempId, @Valid @RequestBody Monitor monitor) {

        Monitor temp = monitorRepository.findOne(tempId);

        temp.setTemperatura(monitor.getTemperatura());
        temp.setUmidade(monitor.getUmidade());

        Monitor updatedMonitor = monitorRepository.save(temp);
        return updatedMonitor;
    }

    @DeleteMapping("/temperatura/{id}")
    public ResponseEntity<?> deleteTemp(@PathVariable(value = "id") Long tempId) {
        Monitor temp = monitorRepository.findOne(tempId);

        monitorRepository.delete(temp);

        return ResponseEntity.ok().build();
    }
}
