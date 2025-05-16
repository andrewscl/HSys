package com.hsys.dv1.Api;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SetPassword {
        public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "Eneene2014";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println(encodedPassword);
    }
}
