package demo.sapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.socgen.sis.aga.domain.model.Contact;
import fr.socgen.sis.aga.domain.repository.ContactRepository;

/**
 * 
 * @author xiaoyu.xu
 * 
 */
@Component
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactrepo;

    @Override
    @Transactional(readOnly = false)
    public Contact addContact(int idcontact, String nom, String commentaire) {
        final Contact p = new Contact();
        p.setIdcontact(idcontact);
        p.setCommentaires(commentaire);
        p.setNom(nom);
        contactrepo.save(p);
        return p;
    }

    @Override
    public List<Contact> getLatestResult() {
        return contactrepo.findAll(
                new PageRequest(0, 20, Direction.DESC, "datedemande"))
                .getContent();
    }
}
