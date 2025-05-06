package com.hsys.dv1.Security;

import java.security.Key;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    //Clave secreta para firmar el token
    //En produccion se debe guardar comovariable de entorno  
    private final String SECRET_KEY = "mysecretkey1234567890mysecretkey1234567890";

    //Convierte la cadena SECRET_KEY en un objeto HMAC
    //con SHA-256 necesario para firmar/verificar el token
    private Key getSigningKey (){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    //Genera el token firmando con el signing key definido previamente
    //Se define la fecha de creación y expración del token
    public String generateToken ( String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact(); //10 minutos en milisegundos 
    }

    //Este metodo extrae el nombre de usuario de un token valido
    //si el token fue modificado o la firma no es valida lanza una excepcion
    public String extractUsername (String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /*Compara si el username extraido del token coincide con el esperado
    y que el token no este vencido*/
    public boolean validateToken (String token, String username){
        String extractedUsername = extractUsername (token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    /*
     * Hace lo mismo que valideteToken pero con otros parametros, este
     * metodo es el mas utilizado en la actualidad.
     */
    public boolean isTokenValid (String token, UserDetails userDetails){
        String username =extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token)); 
    }

    //Revisa si la fecha de expiracion ya paso
    private boolean isTokenExpired (String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJwt(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}
