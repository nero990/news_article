package article.news.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

/**
 * Encodes and Decodes jwt claims
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */
public class JwtService<T> {
    private static final String SECRET = "kP0i_RvTAmI8mgpIkDFhRX3XthSdP-eqqFKGcU92ZIQ";
    private String data;
    private String token;
    private Class<T> tClass;
    private SignatureAlgorithm algorithm;

    public JwtService(T data) {
        this(data, SignatureAlgorithm.HS256);
    }

    public JwtService(T data, SignatureAlgorithm algorithm) {
        this.setData(data);
        this.algorithm = algorithm;
    }

    public JwtService(Class<T> tClass, String token) {
        this(tClass, token, SignatureAlgorithm.HS256);
    }

    public JwtService(Class<T> tClass, String token, SignatureAlgorithm algorithm) {
        this.tClass = tClass;
        this.token = token;
        this.algorithm = algorithm;
    }

    public void setData(T data) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.data = mapper.writeValueAsString(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String encode() {
        return Jwts.builder()
                .setPayload(data)
                .signWith(algorithm, TextCodec.BASE64.encode(SECRET))
                .compact();
    }

    public T decode() {
        Claims claims = getClaims(token);
        return tClass.cast(claims);
    }

    public static Claims decode(String token) {
        return getClaims(token);
    }

    private static Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(TextCodec.BASE64.encode(SECRET))
                .parseClaimsJws(token).getBody();
    }

}
