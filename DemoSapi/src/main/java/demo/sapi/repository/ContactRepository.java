package demo.sapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import fr.socgen.sis.aga.domain.model.Contact;

/**
 * 
 * @author Lam NGUYEN
 * 
 */
public interface ContactRepository extends CrudRepository<Contact, Integer> {

    Page<Contact> findAll(Pageable pageable);
}
