package fr.gorisse.erp.backend.services;

import fr.gorisse.erp.backend.entity.Incident;
import fr.gorisse.erp.backend.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncidentService extends ServiceMethods<Incident> {
    @Autowired
    protected void setRepository(IncidentRepository incidentRepository) {
        super.repository = incidentRepository;
    }
}
