package cn.noload.springbootshiro.commons.security.realm;

import cn.noload.springbootshiro.commons.config.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

/**
 * @author caohao
 * @date 2018-04-14
 */
@Component
public class SystemAuthorizingRealm extends AuthorizingRealm {


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return new SimpleAuthorizationInfo();
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authToken;
        User user = new User("admin", "123");
        if(token.getUsername() != null && token.getPassword() != null && token.getUsername().equals(user.getUsername()) && new String(token.getPassword()).equals(user.getPassword())){

        } else {
            throw new IncorrectCredentialsException();
        }

        return new SimpleAuthenticationInfo(token.getUsername(), token.getPassword(), getName());
    }
}
