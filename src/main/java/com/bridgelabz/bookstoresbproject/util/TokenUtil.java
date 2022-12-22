package com.bridgelabz.bookstoresbproject.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.stereotype.Component;

@Component
public class TokenUtil {
    //This method create a token with id parameter for encoding
    private static final String TOKEN_SECRET = "Key";
    //We create a token using the HMAC256 Algorithm and store the id as claim

    public String createToken(long id) {
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        String token = JWT.create().withClaim("id_key", id).sign(algorithm);
        return token;
    }

    //decode
    //This method  decodes the passed token and returns the id claim.  If the verified
    public Integer decodeToken(String token) throws SignatureVerificationException {

        int userId;
        //we specify the algorithm for the verifier here and then build ver
        Verification verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET));
        JWTVerifier jwtVerifier = verification.build();
        //we verify  and decode the token using the verifier build method
        DecodedJWT decodedJWT = jwtVerifier.verify(token);

        //  we extract the claim from the decode token and convert into long
        Claim idClaim = decodedJWT.getClaim("id_key");
        userId = idClaim.asInt();
        return userId;
    }
}
