package demo.sapi.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import fr.socgen.sis.aga.domain.model.Uploadmobile;

/**
 * 
 * @author Lam NGUYEN
 * 
 */
public interface UploadMobiletRepository extends
        CrudRepository<Uploadmobile, Integer> {

    Page<Uploadmobile> findAll(Pageable pageable);

}
