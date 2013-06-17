/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.spb.petrk.webissues.validators;

import ru.spb.petrk.webissues.utils.MessageCollector;
import ru.spb.petrk.webissues.utils.Severity;
import ru.spb.petrk.webissues.model.User;
import ru.spb.petrk.webissues.repositories.UsersRepository;

/**
 *
 * @author PetrK
 */
public class UserValidator extends AbstractValidator<User> {
    
    private final UsersRepository usersRepository;
    

    public UserValidator(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public boolean validate(MessageCollector collector, User target) {
        boolean result = true;        
        
        if (!isEmpty(target.getLogin())) {
            if (usersRepository.findByLogin(target.getLogin()) != null) {
                collector.addMessage(Severity.error, "Такой пользователь уже существует");
                result = false;
            }
        } else {
            collector.addMessage(Severity.error, "Поле 'логин' не может быть пустым");
            result = false;
        }
        
        if (isEmpty(target.getFirstName())) {
            collector.addMessage(Severity.error, "Поле 'имя' не может быть пустым");
            result = false;            
        }
        
        if (isEmpty(target.getLastName())) {
            collector.addMessage(Severity.error, "Поле 'фамилия' не может быть пустым");
            result = false;            
        }
        
        if (isEmpty(target.getEmail())) {
            collector.addMessage(Severity.error, "Поле 'почта' не может быть пустым");
            result = false;            
        }        
        
        if (isEmpty(target.getPassword())) {
            collector.addMessage(Severity.error, "Поле 'пароль' не может быть пустым");
            result = false;
        }            
        
        return result;
    } 
}
