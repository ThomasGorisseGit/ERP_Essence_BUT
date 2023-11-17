package fr.gorisse.erp.backend.controller;

import fr.gorisse.erp.backend.entity.Incident;
import fr.gorisse.erp.backend.services.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/incident")
@CrossOrigin(origins = "http://localhost:4200")

public class IncidentController implements DefaultController<Incident> {

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
    public void deleteById(@PathVariable("id")int incident_id){
        this.incidentService.deleteById(incident_id);
    }
    @DeleteMapping("/delete")
    @Transactional
    public void delete(@RequestBody Incident incident){
        this.incidentService.delete(incident);
    }
    @Override
    @GetMapping("/getIncident/{id}")
    public Incident getById(@PathVariable("id") int incident_id){
        return this.incidentService.getEntityById(incident_id);
    }

    @GetMapping("/findByDate")
    public List<Incident> findByDate(@RequestParam String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
        Date localDate = formatter.parse(date);
        return  this.incidentService.findByDate(localDate);
    }

}
