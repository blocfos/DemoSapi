package demo.sapi.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.socgen.sis.aga.domain.model.Perimeter;
import fr.socgen.sis.aga.domain.model.Users;

/**
 * 
 * @author Lam NGUYEN
 * 
 */
public interface PerimeterRepository extends CrudRepository<Perimeter, Integer> {
    List<Perimeter> findByUsers(Users user);
}
