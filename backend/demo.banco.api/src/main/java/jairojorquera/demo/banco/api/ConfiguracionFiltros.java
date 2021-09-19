package jairojorquera.demo.banco.api;

import jairojorquera.demo.banco.api.filter.JWTAutorizacionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author jjorquerar
 */
@Configuration
public class ConfiguracionFiltros {

    @Autowired
    private JWTAutorizacionFilter filtro;

    @Bean
    public FilterRegistrationBean<JWTAutorizacionFilter> loggingFilter() {
        FilterRegistrationBean<JWTAutorizacionFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(filtro);        
        registrationBean.addUrlPatterns("/transacciones/*");        
        return registrationBean;
    }
}
