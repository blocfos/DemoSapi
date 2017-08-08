package demo.sapi.services;

import java.util.List;

import fr.socgen.sis.aga.domain.model.Users;

/**
 * 
 * @author arnaud.jelmoni
 * 
 */
public interface UsersService {

    /**
     * @return la liste de tout les utilisateurs de agasi
     */
    public List<Users> getAllUsers();

    /**
     * Supprime un user de la base agasi
     * 
     * @param IdUser
     */
    public void deleteUser(int idUser);

    /**
     * Sauvegade un user dans la base agasi
     * 
     * @param user
     * @return id de l'utilisateur
     */
    public int saveUser(Users user);

    /**
     * Recherche un user de la base par son Id
     * 
     * @param idUser
     * @return
     */
    public Users getUserById(int idUser);

    /**
     * Recherche un user de la base par son username
     * 
     */
    public Users getUserByUsername(String username);
}
