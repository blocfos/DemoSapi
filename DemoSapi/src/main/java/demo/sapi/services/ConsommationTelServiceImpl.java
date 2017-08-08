package fr.socgen.sis.aga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.socgen.sis.aga.domain.repository.TelMobileRepository;

/**
 * 
 * @author NSIT
 * 
 */
@Component
public class ConsommationTelServiceImpl implements ConsommationTelService {

    @Autowired
    private TelMobileRepository telrepo;

    @Override
    public List<String> getOperateurList() {
        return telrepo.getOperateur();
    }

    @Override
    public List<String> getSatutLigneList() {
        return telrepo.getEtatLignetel();
    }

    @Override
    public List<String> getTypeMaterielList() {
        return telrepo.getTypeEffco();
    }

}
