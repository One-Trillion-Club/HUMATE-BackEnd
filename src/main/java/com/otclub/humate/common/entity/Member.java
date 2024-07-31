package com.otclub.humate.common.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    private String memberId;
    private int nationality;
    private String loginId;
    private String password;
    private boolean isAdmin;
    private String gender;
    private Date birthdate;
    private String nickname;
    private String introduction;
    private String profileImgUrl;
    private int point;
    private double manner;
    private String language;
    private int status;
    private Date createdAt;
    private Date deletedAt;
    private String refreshToken;
}
