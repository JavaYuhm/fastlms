package com.javayuhm.fastlms.member.repository;

import com.javayuhm.fastlms.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {


}
