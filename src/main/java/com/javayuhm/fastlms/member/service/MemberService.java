package com.javayuhm.fastlms.member.service;

import com.javayuhm.fastlms.member.model.MemberInput;

public interface MemberService {
    boolean register(MemberInput parameter);

    /**
     * @uuid에 해당하는 계정을 활성화함.
     */
    boolean emailAuth(String uuid);
}
