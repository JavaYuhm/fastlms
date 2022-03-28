package com.javayuhm.fastlms.member.service;

import com.javayuhm.fastlms.member.entity.Member;
import com.javayuhm.fastlms.member.model.MemberInput;
import com.javayuhm.fastlms.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public boolean register(MemberInput parameter) {


        Member member = new Member();
        member.setUserId(parameter.getUserId());
        member.setPassword(parameter.getPassword());
        member.setUserName(parameter.getUserName());
        member.setPhone(parameter.getPhone());
        member.setRegDt(LocalDateTime.now());

        memberRepository.save(member);


        return true;
    }


}
