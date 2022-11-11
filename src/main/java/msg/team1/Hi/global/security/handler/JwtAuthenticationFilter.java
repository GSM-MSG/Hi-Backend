package msg.team1.Hi.global.security.handler;

import io.jsonwebtoken.JwtException;
import msg.team1.Hi.global.security.auth.MemberDetailsService;
import msg.team1.Hi.global.security.jwt.properties.JwtProvider;
import msg.team1.Hi.global.security.jwt.Subject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final MemberDetailsService accountDetailsService;

    public JwtAuthenticationFilter(JwtProvider jwtProvider, MemberDetailsService accountDetailsService) {
        this.jwtProvider = jwtProvider;
        this.accountDetailsService = accountDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (!Objects.isNull(authorization)) {
            String atk = authorization.substring(7);
            try {
                Subject subject = jwtProvider.getSubject(atk);
                String requestURI = request.getRequestURI();
                if (subject.getTokenType().equals("RTK") && !requestURI.equals("/member/reissue")) {
                    throw new JwtException("토큰을 확인하세요.");
                }
                UserDetails userDetails = accountDetailsService.loadUserByUsername(subject.getEmail());
                Authentication token = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(token);
            } catch (JwtException e) {
                request.setAttribute("exception", e.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }}
