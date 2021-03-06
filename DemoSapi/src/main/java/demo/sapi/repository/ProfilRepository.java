package demo.sapi.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import fr.socgen.sis.aga.domain.model.Profil;

/**
 * 
 * @author Lam NGUYEN
 * 
 */
public interface ProfilRepository extends CrudRepository<Profil, Integer> {

    Page<Profil> findAll(Pageable pageable);
}
