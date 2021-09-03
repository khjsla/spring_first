package kang.firstest.springprj1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RESTapi {

    @GetMapping("/getTest")
    public String welcome(){
        return "server is running well XD";
    }

    @PostMapping("/postTest")
    public Object hello(@RequestBody String pBody) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(pBody, new TypeReference<Map<String, Object>>() {});
        Map<String, Object> userRequest = ((Map<String,Object>) map.get("userRequest"));
        return userRequest.get("utterance");
    }
    // pBody - input JSON
    // pBody 의 userRequest 의 utterance 를 get
}
