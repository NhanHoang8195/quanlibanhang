package da.java.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {
    private final static Logger logger = LoggerFactory.getLogger(WebConfigSecurity.class);
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        logger.debug("IN - configureGlobal()");
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        logger.debug("OUT - configureGlobal()");
    }
    
   @Bean
   public MySimpleUrlAuthenticationSuccessHandler mySimpleUrlAuthenticationSuccessHandler() {
       return new MySimpleUrlAuthenticationSuccessHandler();
   }
   
   
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.debug("IN - configure(HttpSecurity http)");
        http
            .csrf().csrfTokenRepository(csrfTokenRepository()).and()
            .authorizeRequests()
                .antMatchers("/order/").authenticated()
                .antMatchers("/account").authenticated()
                .antMatchers("/checkout/").authenticated()
                .antMatchers("/admin/**").hasAnyRole("ADMIN", "SWITCHBOARD", "BRANCH")
                .anyRequest().permitAll()
             .and()
            .formLogin()
                .loginPage("/account/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .successHandler(mySimpleUrlAuthenticationSuccessHandler())
                .failureUrl("/account/login?error")
             .and()
             .logout()
                 .logoutSuccessUrl("/")
                 .logoutUrl("/account/logout")
                 .and()
            .rememberMe().tokenValiditySeconds(1000);
      //  http.csrf().disable();
        logger.debug("OUT - configure(HttpSecurity http)");
    }

    private CsrfTokenRepository csrfTokenRepository() { 
        logger.debug("IN - csrfTokenRepository()");
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository(); 
        repository.setSessionAttributeName("_csrf");
        logger.debug("OUT - csrfTokenRepository()");
        return repository; 
    }
}
