package com.javayuhm.fastlms;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 매핑하기 위한 Main Page
// 주소와(논리적인 주소, 인터넷 주소) 물리적인 파일(프로그래밍을 해야하니깐) 매핑
// 하나의 주소에 대해서(http://localhost:8080/)
// 어디서 ? 누가 매핑 ? 스프링의 어노테이션
// 후보군 - 클래스(계속 클래스 추가는 비효율) 속성과 메소드(가장 적절)

@RestController
public class MainPage {

    @RequestMapping("/")
    public String index(){
        return "Index Page";
    }

    @RequestMapping("/hello")
    public String hello(){
        return  "Hello";
    }
}
