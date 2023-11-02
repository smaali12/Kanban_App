package com.spotify.userservice.filter;

import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        ServletOutputStream pw = httpServletResponse.getOutputStream();
        String authHearder = httpServletRequest.getHeader("Authorization");

        System.out.println("***************************authHeader  " + authHearder + "***************");
        if (authHearder == null || !authHearder.startsWith("Bearer")) {
            // token is missing
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            pw.println("Missing or invalid token");
            pw.close();
        } else {
            //token is valid
            String jwtToken = authHearder.substring(7);
//            System.out.println("******************************jwtToken "+jwtToken+" ***********************");
            String email = Jwts.parser().setSigningKey("securityKey").parseClaimsJws(jwtToken).getBody().getSubject();
//            System.out.println("***************************username  "+ username+"***************");
            httpServletRequest.setAttribute("firstname", email);
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

}

