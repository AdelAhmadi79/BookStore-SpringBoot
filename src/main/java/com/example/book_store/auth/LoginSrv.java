package com.example.book_store.auth;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginSrv {
    String accessToken;
    String refreshToken;
}
