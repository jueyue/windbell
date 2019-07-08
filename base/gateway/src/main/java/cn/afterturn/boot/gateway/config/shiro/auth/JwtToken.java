package cn.afterturn.boot.gateway.config.shiro.auth;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author by jueyue on 19-1-29.
 */
@Data
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public String toString() {
        return token;
    }
}
