package miu.edu.attendance.config;

import miu.edu.attendance.domain.Person;
import miu.edu.attendance.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@Qualifier("JPAUserDetailsService")
public class JPAUserDetailsService implements UserDetailsService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException());
        return new JPAUserDetails(person);
    }
}
