package gr.hua.dit.ds.springbootdemo.service;

import gr.hua.dit.ds.springbootdemo.dao.AppointmentDAO;
import gr.hua.dit.ds.springbootdemo.entity.Appointment;
import gr.hua.dit.ds.springbootdemo.entity.Request;
import gr.hua.dit.ds.springbootdemo.entity.Role;
import gr.hua.dit.ds.springbootdemo.entity.User;
import gr.hua.dit.ds.springbootdemo.repository.RequestRepository;
import gr.hua.dit.ds.springbootdemo.repository.RoleRepository;
import gr.hua.dit.ds.springbootdemo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RequestService implements UserDetailsService {

    @Autowired
    private static RequestRepository requestRepository;

    @Autowired
    private AppointmentDAO appointmentDAO;

    @Transactional
    public List<Request> getRequests(){
        return requestRepository.findAll();
    }

    @Transactional
    public  void saveRequest(Request request){
        requestRepository.save(request);
    }

    @Transactional
    public void deleteRequest(Integer requestId) {
        requestRepository.deleteById(requestId);
    }

    @Transactional
    public Request getRequest(Integer requestId) {
        return requestRepository.findById(requestId).get();
    }

    public List<Request> getAppointmentRequests(Integer appointmentId){
        Appointment appointment = appointmentDAO.getAppointment(appointmentId);
        return appointment.getRequests();
    }
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public Integer saveUser(User user) {
        String passwd= user.getPassword();
        String encodedPasswod = passwordEncoder.encode(passwd);
        user.setPassword(encodedPasswod);

        Role role = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        user = userRepository.save(user);
        return user.getId();
    }

    @Transactional
    public Integer updateUser(User user) {
        user = userRepository.save(user);
        return user.getId();
    }
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> opt = userRepository.findByUsername(username);

        if(opt.isEmpty())
            throw new UsernameNotFoundException("User with email: " +username +" not found !");
        else {
            User user = opt.get();
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    user.getRoles()
                            .stream()
                            .map(role-> new SimpleGrantedAuthority(role.toString()))
                            .collect(Collectors.toSet())
            );
        }
    }

    @Transactional
    public Object getUsers() {
        return userRepository.findAll();
    }

    public Object getUser(Long userId) {
        return userRepository.findById(userId).get();
    }
}