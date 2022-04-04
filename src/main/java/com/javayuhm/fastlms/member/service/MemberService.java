package com.javayuhm.fastlms.member.service;

import com.javayuhm.fastlms.member.model.MemberInput;
import com.javayuhm.fastlms.member.model.ResetPasswordInput;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {
    boolean register(MemberInput parameter);

    /**
     * @uuid에 해당하는 계정을 활성화함.
     */
    boolean emailAuth(String uuid);

    /**
     * 입력한 이메일로 초기화 정보를 전송
     * @param parameter
     *
     */
    boolean sendResetPassword(ResetPasswordInput parameter);

    /**
     * 입력한 패스워드로 패스워드 초기화
     */

    boolean resetPassword(String uuid, String password);

    /**
     * 입력 받은 uuid 값이 유효한 지 확인
     * @param uuid
     * @return
     */
    boolean checkResetPassword(String uuid);
}
