package POLYGLOT.MS.TEST.Gateway.filters;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.security.Key;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.spec.SecretKeySpec;

@Component
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {
	public JwtAuthenticationFilter() {
		super(Config.class);
	}

	@Value("${jwt.secret}")
	private String secret;

	@Override
	public GatewayFilter apply(Config config) {
		// Custom Pre Filter. Suppose we can extract JWT and perform Authentication
		return (exchange, chain) -> {
			System.out.println("Start pre filter jwt" + exchange.getRequest());

			if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
				throw new RuntimeException("Missing authorization information");
			}

			String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
			System.out.println("Show Header ==>> " + authHeader);
			String[] parts;
			try {
				parts = authHeader.split(" ");
				System.out.println("Token ==>> " + parts[1]);
				if (parts.length != 2 || !"Bearer".equals(parts[0])) {
					System.out.println("Incorrect authorization structure");
					exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
					return exchange.getResponse().setComplete();
				}
			} catch (Exception e) {
				System.out.println("INVALID_TOKEN EMPTY");
				exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
				return exchange.getResponse().setComplete();
			}

			try {
				Key hmacKey = new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS256.getJcaName());
				Jws<Claims> jwt = Jwts.parserBuilder().setSigningKey(hmacKey).build().parseClaimsJws(parts[1]);
			} catch (Exception e) {
				System.out.println("INVALID_TOKEN");
				exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
				return exchange.getResponse().setComplete();
			}
			System.out.println("End pre filter jwt");
			return chain.filter(exchange);
		};
	}

	public static class Config {
		// Put the configuration properties
	}
}
