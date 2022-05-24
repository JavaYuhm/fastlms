package com.javayuhm.fastlms.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

public interface MemberCode {

    // 가입요청 상태
    String MEMBER_STATUS_REQ = "REQ";
    // 이용중인 상태
    String MEMBER_STATUS_ING = "ING";
    
    // 정지된 상태
    String MEMBER_STATUS_STOP = "STOP";
  
}
