/* THIS IS FOR JAVA 11 VERSION !! <- IMPORTANT */
// Public API data [XML] to [JSON] and finally insert [DB]

package kang.firstest.springprj1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import kang.firstest.springprj1.config.config;
import net.sf.json.JSONException;

public class XmltoJsontoDB {
    
    public void pharmacyDataUpOrIn(Map<String, String> map) throws Exception {
        // API 통해 가져온 XML 파일데이터를 AP_PHARM_STORE 에!
        System.out.println("/pharmacyDataIn start");

        Map<String, Object> paramMap = new HashMap<String, Object>();
        String PHARM_API_DATA = null;

        // batchMapper.deletePharmacyListAll(paramMap); //trucate table test API
    
        try {
            PHARM_API_DATA = receiveXmlToJson(receiveToString()); // success

            net.sf.json.JSONObject test1 = net.sf.json.JSONObject.fromObject(PHARM_API_DATA);
            String response_data_str = (String) test1.get("response").toString();

            net.sf.json.JSONObject response_data_json = net.sf.json.JSONObject.fromObject(response_data_str);
            String body_data_str = (String) response_data_json.get("body").toString();

            net.sf.json.JSONObject body_data_json = net.sf.json.JSONObject.fromObject(body_data_str);
            String items_data_str = (String) body_data_json.get("items").toString();

            net.sf.json.JSONObject items_data_json = net.sf.json.JSONObject.fromObject(items_data_str);
            Object item_datas = items_data_json.get("item");

            JSONArray jsonArray = new JSONArray(item_datas.toString());

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                paramMap.put("HPID", jsonObject.get("hpid").toString());
                paramMap.put("NM", jsonObject.get("dutyName").toString());

                try {
                    paramMap.put("TEL1", jsonObject.get("dutyTel1").toString());
                } catch (Exception e) {
                    // paramMap.put("TEL", null);
                }

                try {
                    paramMap.put("POS_1", jsonObject.get("postCdn1").toString());
                } catch (Exception e) {
                    // paramMap.put("POS_1", null);
                }

                try {
                    paramMap.put("POS_2", jsonObject.get("postCdn2").toString());
                } catch (Exception e) {
                    // paramMap.put("POS_2", null);
                }

                try {
                    paramMap.put("ADDRE", jsonObject.get("dutyAddr").toString());
                } catch (Exception e) {
                    // paramMap.put("ADDR", null);
                }

                try {
                    paramMap.put("1_START", jsonObject.get("dutyTime1s").toString());
                } catch (Exception e) {
                    // paramMap.put("1_START", null);
                }

                try {
                    paramMap.put("2_START", jsonObject.get("dutyTime2s").toString());
                } catch (Exception e) {
                    // paramMap.put("2_START", null);
                }

                try {
                    paramMap.put("3_START", jsonObject.get("dutyTime3s").toString());
                } catch (Exception e) {
                    // paramMap.put("3_START", null);
                }

                try {
                    paramMap.put("4_START", jsonObject.get("dutyTime4s").toString());
                } catch (Exception e) {
                    // paramMap.put("4_START", null);
                }

                try {
                    paramMap.put("5_START", jsonObject.get("dutyTime5s").toString());
                } catch (Exception e) {
                    // paramMap.put("5_START", null);
                }

                try {
                    paramMap.put("6_START", jsonObject.get("dutyTime6s").toString());
                } catch (Exception e) {
                    // paramMap.put("6_START", null);
                }

                try {
                    paramMap.put("7_START", jsonObject.get("dutyTime7s").toString());
                } catch (Exception e) {
                    // paramMap.put("7_START", null);
                }

                try {
                    paramMap.put("8_START", jsonObject.get("dutyTime8s").toString());
                } catch (Exception e) {
                    // paramMap.put("8_START", null);
                }

                try {
                    paramMap.put("1_END", jsonObject.get("dutyTime1c").toString());
                } catch (Exception e) {
                    // paramMap.put("1_END", null);
                }

                try {
                    paramMap.put("2_END", jsonObject.get("dutyTime2c").toString());
                } catch (Exception e) {
                    // paramMap.put("2_END", null);
                }

                try {
                    paramMap.put("3_END", jsonObject.get("dutyTime3c").toString());
                } catch (Exception e) {
                    // paramMap.put("3_END", null);
                }

                try {
                    paramMap.put("4_END", jsonObject.get("dutyTime4c").toString());
                } catch (Exception e) {
                    // paramMap.put("4_END", null);
                }

                try {
                    paramMap.put("5_END", jsonObject.get("dutyTime5c").toString());
                } catch (Exception e) {
                    // paramMap.put("5_END", null);
                }

                try {
                    paramMap.put("6_END", jsonObject.get("dutyTime6c").toString());
                } catch (Exception e) {
                    // paramMap.put("6_END", null);
                }

                try {
                    paramMap.put("7_END", jsonObject.get("dutyTime7c").toString());
                } catch (Exception e) {
                    // paramMap.put("7_END", null);
                }

                try {
                    paramMap.put("8_END", jsonObject.get("dutyTime8c").toString());
                } catch (Exception e) {
                    // paramMap.put("8_END", null);
                }

                try {
                    paramMap.put("84LAT", jsonObject.get("wgs84Lat").toString());
                } catch (Exception e) {
                    // paramMap.put("LAT", null);
                }

                try {
                    paramMap.put("84LON", jsonObject.get("wgs84Lon").toString());
                } catch (Exception e) {
                    // paramMap.put("LON", null);
                }

                // batchMapper.updatePharmacyList(paramMap);
                // insert data in DB 
                // paramMap == { "데이터이름1": 값1, "데이터이름2": 값2, "데이터이름3": 값3 } 이런것 .
                
                System.out.println(paramMap);
                // 이걸로 XML 에서 JSON 으로 변환된 API 값 확인 가능 
            }

        } catch (Exception e) {
            System.out.println("약국 리스트 DB에 저장 중 오류가 발생." + e);
        }
        
    }

    public String receiveToString() throws Exception {
        String result = null;
        HttpURLConnection conn = null;

        try {
            String api_addr = "http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyFullDown?pageNo=1&numOfRows=30000&ServiceKey=" + config.returnPubilcK();

            // String api_addr = "http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyFullDown?pageNo=1&numOfRows=30000&ServiceKey=" + config.returnPubilcK();
            // TEST 용 PUBLIC API

            conn = (HttpURLConnection) new URL(api_addr).openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            StringBuilder sb = new StringBuilder();
            String s;

            while ((s = br.readLine()) != null) {
                sb.append(s);
            }

            result = sb.toString();

        } catch (Exception e) {
            System.out.println("약국 리스트 DB에 저장 중 오류가 발생." + e);
        } finally {
            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception e) {
                    System.out.println("약국 리스트 DB에 저장 중 오류가 발생.2" + e);
                }
            }
        }

        // log.info(result);
        return result;
    }

    // 공공_API_XML TO String_JSON
    public String receiveXmlToJson(String spec) throws Exception {
        String result = null;

        try {
            String s = receiveToString();
            JSONObject job = XML.toJSONObject(s);
            result = job.toString();
        } catch (Exception e) {
            throw e;
        }

        return result;
    }

    public static JSONArray objectToJSONArray(Object object) {
        Object json = null;
        JSONArray jsonArray = null;

        try {
            json = new net.sf.json.util.JSONTokener(object.toString()).nextValue();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (json instanceof JSONArray) {
            jsonArray = (JSONArray) json;
        }

        return jsonArray;
    }

}
