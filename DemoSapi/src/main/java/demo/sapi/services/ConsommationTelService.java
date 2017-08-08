package demo.sapi.services;

import java.util.List;

/**
 * 
 * @author arnaud.jelmoni
 * 
 */
public interface ConsommationTelService {
    /**
     * Retourn la liste des opertateur contenu dans la base de donnée
     * 
     * @return
     */
    public List<String> getOperateurList();

    /**
     * Retourne la liste des Statut de Ligne contenu dans la base de donnée
     * 
     * @return
     */
    public List<String> getSatutLigneList();

    /**
     * Retourne la liste des Type de Materiel contenu dans la base de donnée
     * 
     * @return
     */
    public List<String> getTypeMaterielList();
}
