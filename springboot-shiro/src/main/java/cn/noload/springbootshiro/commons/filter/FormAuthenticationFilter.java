package cn.noload.springbootshiro.commons.filter;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author caohao
 * @date 2018-04-14
 */
/*@Service*/
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        return new UsernamePasswordToken(){{
            setUsername(request.getParameter("username"));
            setPassword(request.getParameter("password").toCharArray());
        }};
    }
}
