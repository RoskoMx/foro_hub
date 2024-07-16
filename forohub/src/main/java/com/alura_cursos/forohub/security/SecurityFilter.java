package com.alura_cursos.forohub.security;

import com.alura_cursos.forohub.usuarios.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.ReactiveIsNewAwareAuditingHandler;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Este es el inicio del Filter");
        var varauthHeader = request.getHeader( "Authorization");//.replace("Bearer", "");
//        if (token ==""|| token ==null){
//            throw new RuntimeException("El token no es válido.");
//        }
        //System.out.println(token);

        if (varauthHeader != null){
            var token = varauthHeader.replace("Bearer ", "");
//            System.out.println("El filtro está siendo llamado: " );
//            System.out.println("Token: " + token);
//            System.out.println(tokenService.getSubject(token));
            var subject = tokenService.getSubject(token);
            if (subject != null){
                var usuario = usuarioRepository.findByEmail(subject);
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null,
                        usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

}
