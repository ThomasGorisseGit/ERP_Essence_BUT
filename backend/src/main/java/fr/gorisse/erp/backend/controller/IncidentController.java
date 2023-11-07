package fr.gorisse.erp.backend.controller;

import fr.gorisse.erp.backend.entity.Incident;
import fr.gorisse.erp.backend.services.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/incident")
public class IncidentController {

    @Autowired
    private IncidentService incidentService;
    @PostMapping("/add")
    @Transactional
    public Incident create(@RequestBody Incident incident){
        return this.incidentService.create(incident);
    }

    @GetMapping("/getIncidents")
    public List<Incident> getAll(){
        return this.incidentService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public Incident deleteIncidentById(@PathVariable("id")int incident_id){
        return this.incidentService.deleteById(incident_id);
    }
    @DeleteMapping("/delete")
    @Transactional
    public void deleteIncident(@RequestBody Incident incident){
        this.incidentService.delete(incident);
    }
    @GetMapping("/getIncident/{id}")
    public Incident getIncidentById(@PathVariable("id") int incident_id){
        return this.incidentService.getEntityById(incident_id);
    }


}
