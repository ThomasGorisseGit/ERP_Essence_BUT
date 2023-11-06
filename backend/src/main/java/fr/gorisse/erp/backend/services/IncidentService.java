package fr.gorisse.erp.backend.services;

import fr.gorisse.erp.backend.entity.Incident;
import fr.gorisse.erp.backend.repository.IncidentRepository;
import org.hibernate.dialect.identity.IdentityColumnSupportImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class IncidentService extends ServiceMethods<Incident> {
    @Autowired
    private IncidentRepository incidentRepository;
    @Autowired
    @Override
    protected void setRepository() {
        super.repository = incidentRepository;
    }
}
