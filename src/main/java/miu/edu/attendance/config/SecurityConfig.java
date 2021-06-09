package miu.edu.attendance.config;


import miu.edu.attendance.security.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity( securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("JPAUserDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("student")
//                .password("student")
//                .roles("STUDENT")
//                .and()
//                .withUser("faculty")
//                .password("faculty")
//                .roles("FACULTY");
//        auth.jdbcAuthentication()
//                .usersByUsernameQuery("SELECT username, password, is_active WHERE username = ?")
//                .authoritiesByUsernameQuery("SELECT ")
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/authentication/login").permitAll()
//                .antMatchers(HttpMethod.POST,"/barcoderecord").permitAll()
//                .antMatchers("/authentication/register").hasAnyAuthority(Admin.class.getSimpleName().toUpperCase())
//                .antMatchers("/personnel/attendance").hasAnyAuthority(Personnel.class.getSimpleName().toUpperCase())
//                .anyRequest().authenticated()
//                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                .anyRequest().permitAll();
        http.addFilterAfter(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable();

        // select s.registration.courseOffering from Student s where s.studentId
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
