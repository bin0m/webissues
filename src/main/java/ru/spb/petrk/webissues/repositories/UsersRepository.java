/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.spb.petrk.webissues.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import ru.spb.petrk.webissues.model.User;

/**
 *
 * @author PetrK
 */
public interface UsersRepository extends CrudRepository<User, Long> {
    
    @Override
    List<User> findAll();

    User findByLogin(String login);
    
}
