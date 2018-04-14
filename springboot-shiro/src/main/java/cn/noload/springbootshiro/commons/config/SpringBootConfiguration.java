package cn.noload.springbootshiro.commons.config;

import cn.noload.springbootshiro.commons.security.realm.SystemAuthorizingRealm;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.HashMap;

/**
 * @author caohao
 * @date 2018-04-14
 */
@Configuration
public class SpringBootConfiguration {

    @Autowired
    private SystemAuthorizingRealm systemAuthorizingRealm;

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        registration.addUrlPatterns("/*");
        registration.addInitParameter("targetFilterLifecycle", "true");
        registration.setOrder(1);
        return registration;
    }

    @Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(new DefaultWebSecurityManager(){{
            setAuthenticator(new ModularRealmAuthenticator()); // TODO
            setRealm(systemAuthorizingRealm);
            setSessionManager(new DefaultWebSessionManager());
        }});
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/login/success");
        shiroFilterFactoryBean.setFilters(new HashMap<String, Filter>(){{
            put("authc", new FormAuthenticationFilter());
        }});

        shiroFilterFactoryBean.setFilterChainDefinitions("/** = authc");
        return shiroFilterFactoryBean;
    }

    @Bean
    public FormAuthenticationFilter formAuthenticationFilter() {
        return new FormAuthenticationFilter();
    }
}
