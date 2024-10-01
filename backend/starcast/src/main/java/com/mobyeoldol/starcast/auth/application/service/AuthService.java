package com.mobyeoldol.starcast.auth.application.service;


public interface AuthService {

//    Long authenticateMember(String accessToken); //token to kakaoId
//    String kakaoUidToProfileUid(Long kakaoUid); //kakaoId to profileUid
    String  authenticateMember(String accessToken); //token to kakaoId
}
