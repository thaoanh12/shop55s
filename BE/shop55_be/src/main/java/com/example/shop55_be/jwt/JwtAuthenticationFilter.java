//package com.example.shop55_be.jwt;
//
//import com.example.shop55_be.security.CustomUser;
//import com.example.shop55_be.security.CustomUserService;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
////    @Autowired
////    private JwtTokenProvider jwtTokenProvider;
////    @Autowired
////    private CustomUserService customUserService;
////
////    private String getJwtFromRequest(HttpServletRequest servletRequest){
////        String bearerToken = servletRequest.getHeader("Authorization");
////        if(StringUtils.hasText(bearerToken)&&bearerToken.startsWith("Bearer ")){
////            return bearerToken.substring(7);
////        }
////        return null;
////    }
////
////    @Override
////    protected void doFilterInternal(HttpServletRequest request,
////                                    HttpServletResponse response,
////                                    FilterChain filterChain) throws ServletException, IOException {
////        try{
////            String token = getJwtFromRequest(request);
////            if(StringUtils.hasText(token)&&jwtTokenProvider.validateToken(token)){
////                String email = jwtTokenProvider.getMailFromJwt(token);
////                UserDetails userDetails = customUserService.loadUserByUsername(email);
////                if(userDetails!=null){
////                    UsernamePasswordAuthenticationToken authenticationToken =
////                            new UsernamePasswordAuthenticationToken(userDetails,
////                                    null,userDetails.getAuthorities());
////                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
////                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
////                }
////            }
////        }catch (Exception e){
////            e.printStackTrace();
////        }
////        filterChain.doFilter(request,response);
////    }
//}
