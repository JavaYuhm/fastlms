package com.javayuhm.fastlms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// 매핑하기 위한 Main Page
// 주소와(논리적인 주소, 인터넷 주소) 물리적인 파일(프로그래밍을 해야하니깐) 매핑
// 하나의 주소에 대해서(http://localhost:8080/)
// 어디서 ? 누가 매핑 ? 스프링의 어노테이션
// 후보군 - 클래스(계속 클래스 추가는 비효율) 속성과 메소드(가장 적절)

@Controller
public class MainController {

    @RequestMapping("/")
    public String index()
    {
        String email = "kora1492@naver.com";
        String subject = "안녕하세요. 제로베이스입니다.";
        String text = "<p>안녕하세요.</p> <p>반갑습니다.</p>";

        return "index";

    }

    @RequestMapping("/hello")
    public void hello(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();

        String msg = "<html>"+
                "<head>"+
                "<meta charset =\"UTF-8\">"+
                "</head>"+
                "<body>"+
                "<p>hello</p>  <p>fastlms website!!</p>"+
                "<p>안녕하세요!!!</p>"+
                "</body>"+
                "</html>";


        printWriter.write(msg);
        printWriter.close();

    }
}
