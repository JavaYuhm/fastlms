package com.javayuhm.fastlms.member.service.impl;

import com.javayuhm.fastlms.admin.dto.MemberDto;
import com.javayuhm.fastlms.admin.mapper.MemberMapper;
import com.javayuhm.fastlms.admin.model.MemberParam;
import com.javayuhm.fastlms.components.MailComponents;
import com.javayuhm.fastlms.course.model.ServiceResult;
import com.javayuhm.fastlms.member.entity.Member;
import com.javayuhm.fastlms.member.exception.MemberNotEamilAuthException;
import com.javayuhm.fastlms.member.exception.MemberStopUserException;
import com.javayuhm.fastlms.member.model.MemberInput;
import com.javayuhm.fastlms.member.model.ResetPasswordInput;
import com.javayuhm.fastlms.member.repository.MemberRepository;
import com.javayuhm.fastlms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    // args 자동으로 생성자 생성
    private final MemberRepository memberRepository;
    private final MailComponents mailComponents;
    private final MemberMapper memberMapper;
    @Override
    public boolean register(MemberInput parameter) {

        Optional<Member> optionalMember = memberRepository.findById(parameter.getUserId());

        if (optionalMember.isPresent()) {
            // ID가 이미 존재함.
            return false;
        }
        String uuid = UUID.randomUUID().toString();
        String encPassword = BCrypt.hashpw(parameter.getPassword(), BCrypt.gensalt());
        // 빌더
        Member member = Member.builder()
                .userId(parameter.getUserId())
                .userName(parameter.getUserName())
                .phone(parameter.getPhone())
                .password(encPassword)
                .regDt(LocalDateTime.now())
                .emailAuthKey(uuid)
                .emailAuthYn(false)
                .userStatus(Member.MEMBER_STATUS_ING)
                .build();
        memberRepository.save(member);

        String email = parameter.getUserId();
        String subject = "이메일 인증 테스트";
        String text = "<p>사이트 가입을 축하드립니다.</p> <p>아래 링크를 클릭하셔서 가입을 완료 하세요.</p>"
                + "<div><a target='_blank' href='http://localhost:8080/member/email-auth?id=" + uuid + "'> 가입 완료 </a></div>";

        mailComponents.sendMail(email, subject, text);
        return true;
    }

    @Override
    public boolean emailAuth(String uuid) {

        Optional<Member> optionalMember = memberRepository.findByEmailAuthKey(uuid);

        if (!optionalMember.isPresent()) {
            return false;
        }

        Member member = optionalMember.get();

        if (member.isEmailAuthYn()) {
            return false;
        }


        member.setEmailAuthYn(true);
        member.setEmailAuthDt(LocalDateTime.now());
        member.setUserStatus(Member.MEMBER_STATUS_ING);

        memberRepository.save(member);
        return true;
    }

    @Override
    public boolean sendResetPassword(ResetPasswordInput parameter) {

        Optional<Member> optionalMember = memberRepository.findByUserIdAndUserName(parameter.getUserId(), parameter.getUserName());

        if (!optionalMember.isPresent()) {
            throw new UsernameNotFoundException("일치하는 회원정보가 없습니다.");
        }

        Member member = optionalMember.get();

        String uuid = UUID.randomUUID().toString();

        member.setResetPasswordKey(uuid);
        member.setResetPasswordLimitDt(LocalDateTime.now().plusDays(1));
        memberRepository.save(member);

        String email = parameter.getUserId();
        String subject = "비밀번호 초기화 메일입니다.";
        String text = "<p>비밀번호 초기화 메일입니다.</p> <p>아래 링크를 클릭해서 비밀번호 초기화를 진행해주세요.</p>"
                + "<div><a target='_blank' href='http://localhost:8080/member/reset/password?id=" + uuid + "'> 비밀번호 초기화 링크 </a></div>";

        mailComponents.sendMail(email, subject, text);

        return true;
    }

    @Override
    public boolean resetPassword(String uuid, String password) {
        Optional<Member> optionalMember = memberRepository.findByResetPasswordKey(uuid);
        if (!optionalMember.isPresent()) {
            throw new UsernameNotFoundException("회원정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();

        if (member.getResetPasswordLimitDt() == null) {
            throw new RuntimeException("유효한 날짜가 아닙니다");
        }

        if (member.getResetPasswordLimitDt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("날짜가 이전입니다.");
        }

        String encPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        member.setPassword(encPassword);
        member.setResetPasswordKey("");
        member.setResetPasswordLimitDt(null);

        memberRepository.save(member);

        return true;
    }

    @Override
    public boolean checkResetPassword(String uuid) {
        Optional<Member> optionalMember = memberRepository.findByResetPasswordKey(uuid);
        if (!optionalMember.isPresent()) {
            return false;
        }

        Member member = optionalMember.get();

        if (member.getResetPasswordLimitDt() == null) {
            throw new RuntimeException("유효한 날짜가 아닙니다");
        }

        if (member.getResetPasswordLimitDt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("유효한 날짜가 아닙니다");
        }

        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> optionalMember = memberRepository.findById(username);
        if (!optionalMember.isPresent()) {
            System.out.println("회원정보없음.");
            throw new UsernameNotFoundException("회원정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();

        if(member.getUserStatus().equals(Member.MEMBER_STATUS_REQ)) {
            throw new MemberNotEamilAuthException("이메일 활성화 이후 이용 바랍니다.");
        }
        if(member.getUserStatus().equals(Member.MEMBER_STATUS_STOP)) {
            throw new MemberStopUserException("정지된 회원입니다");
        }

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_USER"));

        if (member.isAdminYn()) {
            grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }


        return new User(member.getUserId(), member.getPassword(), grantedAuthorityList);
    }


    @Override
    public List<MemberDto> list(MemberParam memberParam){

        long totalCount = memberMapper.selectListCount(memberParam);

        List<MemberDto> list = memberMapper.selectList(memberParam);

        if(!CollectionUtils.isEmpty(list)){
            int i = 0;
            for(MemberDto x : list){

                x.setTotalCount(totalCount);
                long seq = totalCount - memberParam.getPageStart() -i;
                x.setSeq(seq);
                i++;
            }
        }

        return  list;
    }

    @Override
    public MemberDto detail(String userId) {
        // 쿼리문이 복잡한 경우, mybatis (간단한 경우 jpa)
        Optional<Member> optionalMember = memberRepository.findById(userId);
        if(!optionalMember.isPresent()){
            return null;
        }
        Member member = optionalMember.get();

        return MemberDto.of(member);
    }

    @Override
    public boolean updateStatus(String userId, String userStatus) {
        Optional<Member> optionalMember = memberRepository.findById(userId);
        if (!optionalMember.isPresent()) {
            System.out.println("회원정보없음.");
            throw new UsernameNotFoundException("회원정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();

        member.setUserStatus(userStatus);
        memberRepository.save(member);
        return true;
    }

    @Override
    public boolean updatePassword(String userId, String password) {
        Optional<Member> optionalMember = memberRepository.findById(userId);
        if (!optionalMember.isPresent()) {
            System.out.println("회원정보없음.");
            throw new UsernameNotFoundException("회원정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();

        String encPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        member.setPassword(encPassword);
        memberRepository.save(member);
        return true;
    }

    @Override
    public ServiceResult updateMemberPassword(MemberInput parameter) {

        String userId = parameter.getUserId();

        Optional<Member> optionalMember = memberRepository.findById(userId);
        if(!optionalMember.isPresent()){
            return new ServiceResult(false, "회원 정보를 찾을 수 없습니다.");
        }
        Member member = optionalMember.get();

        if(!BCrypt.checkpw(parameter.getPassword(), member.getPassword())){
            return new ServiceResult(false, "현재 비밀번호가 일치하지 않습니다.");
        }

        String encPassword = BCrypt.hashpw(parameter.getNewPassword(), BCrypt.gensalt());
        member.setPassword(encPassword);
        memberRepository.save(member);
        return new ServiceResult(true, "비밀번호 변경 성공하였습니다");
    }

    @Override
    public ServiceResult updateMemberInfo(MemberInput parameter) {

        Optional<Member> optionalMember = memberRepository.findById(parameter.getUserId());

        if(!optionalMember.isPresent())
        {
            return new ServiceResult(false, "에러로 찾을 수 없습니다.");
        }
        Member member = optionalMember.get();
        
        member.setPhone(parameter.getPhone());
        member.setZipcode(parameter.getZipcode());
        member.setAddr(parameter.getAddr());
        member.setAddrDetail(parameter.getAddrDetail());
        member.setUptDt(LocalDateTime.now());
        memberRepository.save(member);


        return new ServiceResult(true, "성공");
    }
}
