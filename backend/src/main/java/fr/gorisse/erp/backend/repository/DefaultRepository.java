package fr.gorisse.erp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface DefaultRepository<T> extends JpaRepository<T,Integer> {
    T deleteById(int id);

}
