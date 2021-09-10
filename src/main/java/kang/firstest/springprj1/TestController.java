package com.nds.mediMap;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
 *      Copyright (C) 2021 by NDS., All right reserved.
 */

@Slf4j
@RestController
@RequestMapping(value = "/mydata/pharmacy", produces = "application/json")
public class mediMapApiController {

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
        String path = mediMapApiController.class.getResource("").getPath()
                + "../../../../../src/main/resources/static/covid.json";

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
    public /*JSONObject*/ String pahrmacy_list() throws ParseException, IOException {
        JSONParser jsonParser = new JSONParser();

        // to get path
        String path = mediMapApiController.class.getResource("").getPath()
                + "../../../../../src/main/resources/static/pharmacy.json";

        Reader reader = new FileReader(path);
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        
        Object pharmacy_list = jsonObject.get("Sheet1");
        // pharmacy_list => 우선 이걸로 sheet1 안으로는 들어가게 되었음

        try {
            // forEach 로 조건 걸 수 있을까?
            // log.info("WHAT IS .get", jsonObject.get("Sheet1"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // return jsonObject;
        return "test";
    }

}
