package com.mb.demo.web.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    private String SECRET_KEY = "secret";

    
    // return username
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // return expiration time
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
//which takes a token then uses claimsresolver in order to figure out what the claims are we have bunch of data that we have passed
    
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
    	
    	
     //jwt api we included in pom.xml it uses builder pattern and its setting the claims that we have passed now an empty object
    	//this can change it sets a subject, subject is person who is been authenticated who has successfully authnticated
    	// in this case , we are using the username over here and geeting username over here and geeting username passing that 
    	//and settign as a subject 
    	// there are some parameters like issuedate, expiration time then sign in method to sign the token by using algorithm 
    	//signature  secret key = defined above

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    
    // check username is same or not and also check token has not expired
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}