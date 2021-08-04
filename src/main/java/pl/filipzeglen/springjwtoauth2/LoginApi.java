package pl.filipzeglen.springjwtoauth2;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class LoginApi {

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        // First we need to check if user exist in db
        // and credentials are ok. Then we can return to him jwts object

        long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles", "user")
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + 120000)) // 120s
                .signWith(SignatureAlgorithm.HS512, user.getPassword())
                .compact();
    }

}
