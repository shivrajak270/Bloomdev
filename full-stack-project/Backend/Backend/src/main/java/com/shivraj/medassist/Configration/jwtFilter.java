package com.shivraj.medassist.Configration;

import com.shivraj.medassist.Models.UserPrincipal;
import com.shivraj.medassist.Service.UsersService;
import com.shivraj.medassist.ServiceImpl.JwtServiceImpl;
import com.shivraj.medassist.ServiceImpl.UserdetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;




@Configuration
public class jwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtServiceImpl jwtServiceImpl;

@Autowired
    ApplicationContext applicationContext;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username=null;
        String token = "";
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
           token = authorizationHeader.substring(7);
           username=jwtServiceImpl.extractUserName(token);
        }
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
        {
            UserDetails userDetails=applicationContext.getBean(UserdetailService.class).loadUserByUsername(username);

            if(jwtServiceImpl.validateToken(token, userDetails)){
                UsernamePasswordAuthenticationToken authtoken =new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authtoken);
            }
        }

        filterChain.doFilter(request,response);


    }
}
