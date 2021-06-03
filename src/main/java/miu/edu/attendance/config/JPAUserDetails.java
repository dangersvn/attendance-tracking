package miu.edu.attendance.config;

import lombok.Getter;
import miu.edu.attendance.domain.Person;
import miu.edu.attendance.domain.PersonRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class JPAUserDetails implements UserDetails {
    String firstName;
    String lastName;
    String username;
    String password;
    Set<PersonRole> roles;

    public JPAUserDetails(Person person) {
        this.username = person.getUsername();
        this.password = person.getPassword();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.roles = person.getRoles();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(personRole -> {
            return new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return personRole.getName();
                }
            };
        }).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
