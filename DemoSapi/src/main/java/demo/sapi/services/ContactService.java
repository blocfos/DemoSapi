package fr.socgen.sis.aga.service;

import java.util.List;

import fr.socgen.sis.aga.domain.model.Contact;

/**
 * 
 * @author xiaoyu.xu
 * 
 */
public interface ContactService {
    /**
     * 
     * @return
     */
    public List<Contact> getLatestResult();

    public Contact addContact(int idcontact, String nom, String commentaire);
}
