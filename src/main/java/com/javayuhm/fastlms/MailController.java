package com.javayuhm.fastlms;

import com.javayuhm.fastlms.components.MailComponents;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
public class MailController {

    private final MailComponents mailComponents;

/*    @RequestMapping("/")
    public String index(){
        mailComponents.sendMailTest();

        return "index";
    }*/

}
