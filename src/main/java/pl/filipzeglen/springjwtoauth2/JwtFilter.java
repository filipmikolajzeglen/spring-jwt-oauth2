package pl.filipzeglen.springjwtoauth2;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String header = httpServletRequest.getHeader("authorization");

        if (httpServletRequest == null || !header.startsWith("Bearer ")) {
            throw new ServletException("Wrong or empty header");
        } else {
            try {
                String token = header.substring(7);
                Claims claims = Jwts.parser().setSigningKey("123456").parseClaimsJws(token).getBody();
                servletRequest.setAttribute("claims", claims);
            } catch (Exception e) {
                throw new ServletException("Wrong key");
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
