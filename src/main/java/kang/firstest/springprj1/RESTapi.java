package kang.firstest.springprj1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.*;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

@RestController
public class RESTapi {

    // private Logger logger = LoggerFac
    

    @GetMapping("/getTest")
    public String welcome() {
        return "server is running well XD";
    }

    @PostMapping("/postTest")
    public Object hello(@RequestBody String pBody) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(pBody, new TypeReference<Map<String, Object>>() {
        });
        Map<String, Object> userRequest = ((Map<String, Object>) map.get("userRequest"));
        return userRequest.get("utterance");
    }
    // pBody - input JSON
    // pBody 의 userRequest 의 utterance 를 get

    @GetMapping("/getApiTest")
    public String publicApi() {
        String uri = "https://api.odcloud.kr/api/15066537/v1/uddi:3fcefe25-765d-41f2-8540-8c6df41d54bd?page=1&perPage=128";
        uri += "&format=json";
        // i can get pharmacy data from above

        try {
            URL url = new URL(uri);
            URLConnection connection = url.openConnection();
            
            

        } catch (IOException e) {

        }

        return "server is running well XD";
    }
}
