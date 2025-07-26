package team404a.MVC.Employee.system.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team404a.MVC.Employee.system.entities.Role;
import team404a.MVC.Employee.system.entities.User;
import team404a.MVC.Employee.system.repository.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User FindUserById(long id) {
        Optional<User> optUser=userRepository.findById(id);
        if(optUser.isEmpty()) {
            throw new RuntimeException("User Not Found");
        }
        User user=optUser.get();

        return user;


    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        if(user==null){
            throw new RuntimeException("User Not Found");
        }
        userRepository.save(user);

    }

    @Override
    public User deleteUser(User user) {
        if(user==null){
            throw new RuntimeException("User Not Found");
        }
         userRepository.delete(user);
        return user;

    }


    @Override
    public List<Role> roles(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean usernameCheck(String username) {
        Optional<User> optUser=userRepository.findByUsernameIgnoreCase(username);
        if(optUser.isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public User findUserByUsername(String username) {
        Optional<User> optUser=userRepository.findUserByUsername(username);
        if(optUser.isEmpty()){
            throw new RuntimeException("User not found");
        }
        return optUser.get();
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> optUser=userRepository.findUserByUsername(username);
//        if(optUser.isEmpty()){
//            throw new RuntimeException("user not found");
//        }
//        User user=optUser.get();
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                convertRoles(user)
//        );
//
//
//    }
//    private Collection<? extends GrantedAuthority> convertRoles(User user) {
//        return user.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
//                .collect(Collectors.toList());
//    }
}
