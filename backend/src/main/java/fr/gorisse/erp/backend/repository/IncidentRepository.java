package fr.gorisse.erp.backend.repository;

import fr.gorisse.erp.backend.entity.Incident;

import java.util.Date;
import java.util.List;

public interface IncidentRepository extends DefaultRepository<Incident>{
    List<Incident> findByDate(Date date);
}
