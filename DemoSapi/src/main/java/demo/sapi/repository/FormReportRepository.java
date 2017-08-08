package demo.sapi.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import fr.socgen.sis.aga.domain.model.Formreport;

/**
 * 
 * @author Lam NGUYEN
 * 
 */
public interface FormReportRepository extends
        CrudRepository<Formreport, Integer> {

    Page<Formreport> findAll(Pageable pageable);
}
