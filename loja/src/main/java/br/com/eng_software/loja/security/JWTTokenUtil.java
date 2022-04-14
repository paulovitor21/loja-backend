package br.com.eng_software.loja.security;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import br.com.eng_software.loja.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JWTTokenUtil {
	
	private static final String SECRET_KEY = "*N@tur@55pW3bS3cur1tyT0k3n202101";
	private static final int EXPIRATION = 12*60*1000;
	private static final String TK_PREFIX = "Bearer";
	private static final String HEADER_AUTH = "Authorization";
	
	public static String generateToken(Usuario usuario) {
		Key secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
		String jwt = Jwts.builder()
			.setSubject(usuario.getUsername())
			.setIssuer("*Paulovitor*")
			.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
			.signWith(secretKey, SignatureAlgorithm.HS256)
			.compact();
		
		return TK_PREFIX + jwt;
	}
	
	public static boolean isIssuerValid(String issuer) {
		return issuer.equals("*Paulovitor*");
	}
	
	public static boolean isSubjectValid(String subject) {
		return subject != null && subject.length() > 0;
	}
	
	public static boolean isExpirationValid(Date expiration) {
		return expiration.after(new Date(System.currentTimeMillis()));
	}
	
	public static Authentication decodeToken(HttpServletRequest request) {
		String token = request.getHeader(HEADER_AUTH);
		token = token.replace(TK_PREFIX, ""); // retirado o bearer
		
		Jws<Claims> jswClaims = Jwts.parserBuilder()
									.setSigningKey(SECRET_KEY.getBytes())
									.build()
									.parseClaimsJws(token);
		
		String username = jswClaims.getBody().getSubject();
		String emissor 	= jswClaims.getBody().getIssuer();
		Date expira   	= jswClaims.getBody().getExpiration();
		
		if (isSubjectValid(username) && isIssuerValid(emissor) && isExpirationValid(expira)) {
			return new UsernamePasswordAuthenticationToken(username,null, Collections.emptyList());
		}
		return null;
	}
}
