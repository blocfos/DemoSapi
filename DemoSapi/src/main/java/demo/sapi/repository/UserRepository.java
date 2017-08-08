package demo.sapi.repository;

import org.springframework.data.repository.CrudRepository;

import fr.socgen.sis.aga.domain.model.Users;

/**
 * 
 * @author Lam NGUYEN
 * 
 */
public interface UserRepository extends CrudRepository<Users, Integer> {

    Users findById(int idUser);

    Users findByUsername(String username);

}
