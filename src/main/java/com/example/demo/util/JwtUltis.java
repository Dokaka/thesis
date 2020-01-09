package com.example.demo.util;


import com.example.demo.entity.UserEntity;
import com.example.demo.security.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JwtUltis {
    public static Claims verifyToken(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.HEADER);
        if (token == null || !token.startsWith(SecurityConstants.PREFIX)) return null;
        // Decode
        return Jwts.parser()
                .setSigningKey(SecurityConstants.SECRET)
                .parseClaimsJws(token.replace(SecurityConstants.PREFIX, ""))
                .getBody();
    }

    public static String generateToken(UserEntity userEntity) {
        Claims claims = Jwts.claims().setSubject(userEntity.getPhone());
        List<String> roles = new ArrayList<>();
        //roles.add(userEntity.getRole());
        // Thông tin lưu trữ trong JWT dạng json key value
        // Muốn lưu thêm thông tin khác thì cứ put vào
        //claims.put("roles", roles);
        //claims.put("userid", user.getId());
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + SecurityConstants.EXPIRATION);
        // Encode
        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                .compact();
        return SecurityConstants.PREFIX + token;
    }
}

