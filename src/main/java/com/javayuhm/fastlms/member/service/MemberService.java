package com.javayuhm.fastlms.member.service;

import com.javayuhm.fastlms.admin.dto.MemberDto;
import com.javayuhm.fastlms.admin.model.MemberParam;
import com.javayuhm.fastlms.course.model.ServiceResult;
import com.javayuhm.fastlms.member.entity.Member;
import com.javayuhm.fastlms.member.model.MemberInput;
import com.javayuhm.fastlms.member.model.ResetPasswordInput;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface MemberService extends UserDetailsService {
    boolean register(MemberInput parameter);

    /**
     * @uuid에 해당하는 계정을 활성화함.
     */
    boolean emailAuth(String uuid);

    /**
     * 입력한 이메일로 초기화 정보를 전송
     *
     * @param parameter
     */
    boolean sendResetPassword(ResetPasswordInput parameter);

    /**
     * 입력한 패스워드로 패스워드 초기화
     */

    boolean resetPassword(String uuid, String password);

    /**
     * 입력 받은 uuid 값이 유효한 지 확인
     *
     * @param uuid
     * @return
     */
    boolean checkResetPassword(String uuid);

    /**
     * 회원 목록 가져오기
     * @return
     */
    List<MemberDto> list(MemberParam parameter);

    /**
     * 회원 상세정보
     * @param userId
     * @return
     */
    MemberDto detail(String userId);

    /**
     * 회원 상태 변경
     */
    boolean updateStatus(String userId, String userStatus);

    /**
     * 회원 비밀번호 초기화
     * @param userId
     * @param password
     * @return
     */
    boolean updatePassword(String userId, String password);

    /**
     * 회원 비밀번호 변경
     * @param parameter
     * @return
     */
    ServiceResult updateMemberPassword(MemberInput parameter);

    /**
     * 회원정보 수정
     */
    ServiceResult updateMemberInfo(MemberInput parameter);
}
