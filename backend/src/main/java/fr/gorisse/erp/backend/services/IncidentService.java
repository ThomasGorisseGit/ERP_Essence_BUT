package fr.gorisse.erp.backend.services;

import fr.gorisse.erp.backend.entity.Incident;
import fr.gorisse.erp.backend.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IncidentService extends ServiceMethods<Incident> {
    private IncidentRepository incidentRepository;
    @Autowired
    protected void setRepository(IncidentRepository incidentRepository) {
        super.repository = incidentRepository;
        this.incidentRepository = incidentRepository;
    }

    public List<Incident> findByDate(Date date){
        return this.incidentRepository.findByDate(date);
    }
}
