//package app.security;
//
//import app.repo.UserRepo;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final UserRepo repo;
//
//    public SecurityConfig(UserRepo repo) {
//        this.repo = repo;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/index", "/signup", "/signin", "/**").permitAll()
//                .antMatchers("/dashboard", "/message", "/search", "/mypost").authenticated()
//                .antMatchers("/forgotpassword" ,"/info","/update", "/wishlist").hasRole("ADMIN")
//                .antMatchers("/user").hasAnyRole("ADMIN", "USER")
//                .anyRequest().permitAll();
//        http
//                .formLogin()
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .deleteCookies("JSESSIONID");
//    }
//
//}
