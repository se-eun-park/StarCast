package com.mobyeoldol.starcast.auth.application.service;

public interface AuthService {

    String getAccessCode();
    String getAccessToken(String code);
}
