package cn.afterturn.boot.bussiness.auth;

import lombok.Data;
import org.apache.shiro.authc.HostAuthenticationToken;

/**
 * @author by jueyue on 19-1-29.
 */
@Data
public class JwtToken implements HostAuthenticationToken {
    private String token;
    private String host;

    public JwtToken(String token) {
        this(token, null);
    }

    public JwtToken(String token, String host) {
        this.token = token;
        this.host = host;
    }

    @Override
    public String getHost() {
        return host;
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
        return token + ':' + host;
    }
}
