package ru.sberit.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sberit.web.entity.Role;
import ru.sberit.web.entity.User;
import ru.sberit.web.repository.RoleRepository;
import ru.sberit.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Logger logger = LoggerFactory.getLogger(UserService.class);
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        logger.info("User successfully found");
        return user;
    }

    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(User user) {
        Logger logger = LoggerFactory.getLogger(UserService.class);
        logger.debug("saveUser/findByUsername. start: '{}'", user.getUsername());
        User userFromDB = userRepository.findByUsername(user.getUsername());
        logger.debug("saveUser/findByUsername. ok");

        logger.debug("saveUser/findByUsername. check");
        if (userFromDB != null) {
            logger.debug("saveUser/findByUsername. check - ko : '{}', '{}'",
                    userFromDB.getUsername(), userFromDB.getId());
            return false;
        }
        logger.debug("saveUser/findByUsername. check - ok");


        logger.debug("saveUser/setRoles. start");
        user.setRoles(Collections.singleton(roleRepository.findRoleByNameEquals("ROLE_USER")));
        logger.debug("saveUser/setRoles. ok");
        logger.debug("saveUser/setPassword. start");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        logger.debug("saveUser/setPassword. ok");
        logger.debug("saveUser/save. start");
        userRepository.save(user);
        logger.debug("saveUser/save. ok");
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<User> usergtList(Long idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }

//    public String getName() {
//
//    }
}
