package org.coursecollector.esi.service;
/*
*
*/

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.coursecollector.esi.model.StudentRepository;
import org.coursecollector.esi.model.Student;
import org.coursecollector.esi.model.UserRole;

/**
 *
 * @author
 */
@Component
public class UserService implements UserDetailsService {

    @Inject
    StudentRepository repo;

    public final PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Student> opt = repo.findById(username);
        if (opt == null) {
            throw new UsernameNotFoundException(username);
        }
        Student u = opt.get();
        return new org.springframework.security.core.userdetails.User(u.getUserName(), u.getDerivedPassword(),
                u.getRoles());
    }

    public void saveUserComputingDerivedPassword(Student u, String rawPassword) {
        setComputingDerivedPassword(u, rawPassword);
        repo.save(u);
    }

    public void setComputingDerivedPassword(Student u, String rawPassword) {
        String codedPassword = encoder.encode(rawPassword);
        u.setDerivedPassword(codedPassword);
    }

    public void makeUserAdmin(String username) {
        Student u = repo.findById(username).orElse(null);
        u.getRoles().add(UserRole.ADMIN);
        repo.save(u);
    }

    public List<Student> listAllUsers() {
        return repo.findAllByOrderByUserName();
    }

    public String encodePassword(String rawPassword) {
        return encoder.encode(rawPassword);
    }

}
