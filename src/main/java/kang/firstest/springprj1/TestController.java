package kang.firstest.springprj1;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kang.firstest.springprj1.springmvc.CommandMap;

import org.apache.jasper.tagplugins.jstl.core.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.data.web.JsonPath;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

/**
 * mediMapApiController
 * 
 * @author 강현지
 * @since 2021.09.09
 * @version 1.0
 * @see
 *
 *      <pre>
 *  Modification Information
 *  수정일      수정자   수정내용
 *  ---------- -------- ---------------------------
 *  2021.09.09 	강현지   최초 생성
 *  2021.09.10  강현지   선별진료소 RAW JSON 데이터
 *      </pre>
 *
 */

@Slf4j
@RestController
@RequestMapping(value = "/mydata/pharmacy", produces = "application/json")
public class TestController {

    /**
     * 메소드명 : internTest 설 명 : 약국 API 사용하여 약국 리스트 조회
     * 
     * @param
     * @return
     * @throws Exception
     */

    @GetMapping("/internTest")
    public @ResponseBody String test() {
        // header config
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization",
                "Infuser rZjxgEACpJRnNk9bbnGYXYCzjOa1JvN3KKob05PnzI2N6k7/vMw1GWKCmIw0wMDYm8gmfwELqv8Xhat4+x2mpg==");
        // token must be hide ! set config

        HttpEntity<String> entity = new HttpEntity<String>("", headers);

        String uri = "https://api.odcloud.kr/api/15066537/v1/uddi:3fcefe25-765d-41f2-8540-8c6df41d54bd?page=1&perPage=128";
        // i can get pharmacy data from above

        // HttpEntity entity = new HttpEntity<>(httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        // responseEntity.getBody() = all pharmacy list
        // log.info("what is res: ", responseEntity.getBody());

        return responseEntity.getBody();
    }

    /**
     * 메소드명 : clinicTest 설 명 : 전국 선별 진료소 data list
     * 
     * @param
     * @return
     * @throws ParseException
     * @throws IOException
     * @throws Exception
     */

    @GetMapping("/clinicTest")
    public JSONObject clinic_list() throws ParseException, IOException {
        JSONParser jsonParser = new JSONParser();

        // to get path
        String path = TestController.class.getResource("").getPath()
                + "../../../../../resources/covid.json";

        Reader reader = new FileReader(path);
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

        return jsonObject;
    }

    /**
     * 메소드명 : pharmacyTest 설 명 : 전국 선별 진료소 data list
     * 
     * @param
     * @return
     * @throws ParseException
     * @throws IOException
     * @throws Exception
     */

    @GetMapping("/pharmacyTest")
    public JSONObject pahrmacy_list() throws ParseException, IOException {
        JSONParser jsonParser = new JSONParser();

        String path = TestController.class.getResource("").getPath()
                + "../../../../../src/main/resources/pharmacy.json";
        Reader reader = new FileReader(path);
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

        return jsonObject;
    }

    @PostMapping("/pharmacyOne")
    public ArrayList searchSido(HttpServletRequest request, HttpServletResponse response, CommandMap commandMap
    /*Authentication authentication*/) throws ParseException, IOException {

        ModelAndView mav = new ModelAndView("jsonView");
        // Map<String, Object> authMap = (Map<String, Object>) authentication.getPrincipal();

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.putAll(commandMap.getMap());
        // paramMap.put("USER_NO", authMap.get("USER_NO"));

        log.info("pharmacyOne -------------------");

        System.out.println(paramMap);
        System.out.println(paramMap.get("sido"));
        log.info("pharmacyOne -------------------");

        JSONParser jsonParser = new JSONParser();
        ArrayList x = null;
        // to get path
        String path = TestController.class.getResource("").getPath()
                + "../../../../../src/main/resources/pharmacy.json";

        Reader reader = new FileReader(path);
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

        x = com.jayway.jsonpath.JsonPath.read(jsonObject, "$.Sheet1[?(@.시도코드==" + paramMap.get("sido") + ")]");

        return x;
    }
    
    /**
     * 메소드명 : pharmacyTest 설 명 : 전국 선별 진료소 data list
     * 
     * @param
     * @return
     * @throws ParseException
     * @throws IOException
     * @throws Exception
     */

    @PostMapping("/khjTrashCode")
    public /* JSONObject */ ArrayList pahrmacy_list(@RequestBody JSONObject pBody) throws ParseException, IOException {
        JSONParser jsonParser = new JSONParser();
        ArrayList x = null;

        // to get path
        String path = TestController.class.getResource("").getPath()
                + "../../../../../src/main/resources/pharmacy.json";

        Reader reader = new FileReader(path);
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

        x = com.jayway.jsonpath.JsonPath.read(jsonObject, "$.Sheet1[?(@.시도코드==" + pBody.get("code") + ")]");

        return x;
    }


    @PostMapping("/pharmacyRange")
    public ArrayList searchRange(HttpServletRequest request, HttpServletResponse response, CommandMap commandMap
    /*Authentication authentication*/) throws ParseException, IOException {
        // sx <= x좌표 <= ex && sy <= y좌표 <= ey 값 가져오기
        
        ModelAndView mav = new ModelAndView("jsonView");

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.putAll(commandMap.getMap());

        log.info("pharmacyOne -------------------");

        System.out.println(paramMap.get("sx"));
        System.out.println(paramMap.get("sy"));
        System.out.println(paramMap.get("ex"));
        System.out.println(paramMap.get("ey"));
        log.info("pharmacyOne -------------------");

        JSONParser jsonParser = new JSONParser();
        ArrayList x = null;
        // to get path
        String path = TestController.class.getResource("").getPath()
                + "../../../../../src/main/resources/pharmacy.json";

        Reader reader = new FileReader(path);
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

        x = com.jayway.jsonpath.JsonPath.read(jsonObject, "$.Sheet1[?(@.x좌표>=" + paramMap.get("sx") + "&& @.x좌표<="+ paramMap.get("ex") + "&& @.y좌표>="+ paramMap.get("sy") + "&& @.y좌표<="+ paramMap.get("ey") +")]");
        
        return x;
    }
}
