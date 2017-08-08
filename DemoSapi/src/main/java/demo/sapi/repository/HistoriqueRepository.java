package demo.sapi.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import fr.socgen.sis.aga.domain.model.Historique;

/**
 * 
 * @author Lam NGUYEN
 * 
 */
public interface HistoriqueRepository extends
        CrudRepository<Historique, Integer> {

    Page<Historique> findAll(Pageable pageable);
}
