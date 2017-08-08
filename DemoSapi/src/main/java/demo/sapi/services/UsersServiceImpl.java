package demo.sapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.socgen.sis.aga.domain.model.Users;
import fr.socgen.sis.aga.domain.repository.UserRepository;

/**
 * 
 * @author arnaud.jelmoni
 * 
 */
@Component
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public List<Users> getAllUsers() {
        final Iterable<Users> iter = userRepo.findAll();
        // On mes l'iternation dans une liste
        final List<Users> result = new ArrayList<Users>();
        for (Users user : iter) {
            result.add(user);
        }

        return result;
    }

    @Override
    public void deleteUser(int idUser) {
        userRepo.delete(idUser);
    }

    @Override
    public int saveUser(Users user) {
        return userRepo.save(user).getId();
    }

    @Override
    public Users getUserById(int idUser) {
        return userRepo.findById(idUser);
    }

    @Override
    public Users getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
