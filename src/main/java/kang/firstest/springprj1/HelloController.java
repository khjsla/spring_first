// springprj1/src/main/java/kang/firstest/springprj1 에 위치
package kang.firstest.springprj1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index(){
        return "SPRING TESTTTTTTTTTTTTTTTTTTTTT";
    }
}

