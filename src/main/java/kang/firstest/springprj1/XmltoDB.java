/* THIS IS FOR JAVA 8 VERSION !! <- IMPORTANT */
// Public API data [XML] to insert [DB]

package kang.firstest.springprj1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.CommandMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.xmlbeans.impl.common.XPath;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import kang.firstest.springprj1.config.config;

@RestController
@RequestMapping(value = "/testuri", produces = "application/json")
public class XmltoDB {
    private String pharmacy_xml; // 다운로드 받은 문자열 저장할 변수
    List<String> data = new ArrayList<String>(); // parsing 결과를 저장할 리스트 ( 몇개인지 모르므로 LIST )

    @GetMapping("/pharmacyDataIn")
    public String pharmacyDataIn(HttpServletRequest request, HttpServletResponse response, CommandMap commandMap
    /* , Authentication authentication */) throws ParseException, IOException {
        // API 통해 가져온 XML 파일데이터를 AP_PHARM_STORE 에!
        System.out.println("/pharmacyDataIn START!");

        // for use DB
        ModelAndView modelAndView = new ModelAndView("jsonView");
        // Map<String, Object> authMap = (Map<String, Object>)
        // authentication.getPrincipal();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // paramMap.put("PHARM_CD", authMap.get("USER_NO"));

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        Document doc = null;

        try {
            // API CALL
            String api_addr = "http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyFullDown?pageNo=1&numOfRows=30000&ServiceKey" + config.returnPubilcK();
            URL url = new URL(api_addr);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);

            con.setRequestMethod("GET");
            con.setRequestProperty("ContentType", "text/xml;charset=UTF-8"); // 타입 설정

            // openStream() : URL페이지 정보를 읽어온다.
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();

            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line);
            }

            pharmacy_xml = sb.toString();
            reader.close();
            con.disconnect();

            // pharmacy_xml == xml_data
            // xml parsing

            InputSource is = new InputSource(new StringReader(pharmacy_xml));
            builder = factory.newDocumentBuilder();
            doc = builder.parse(is);

            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = (XPath) xpathFactory.newXPath();

            XPathExpression expr = xpath.compile("//items/item");
            NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); i++) { // nodeList.getLength() = 약국 전체 개수
                NodeList child = nodeList.item(i).getChildNodes();

                for (int j = 0; j < child.getLength(); j++) { // child.getLength() = 약국이 가진 데이터 전체 개수
                    Node node = child.item(j);

                    switch (node.getNodeName()) {
                        case "dutyAddr":
                            paramMap.put("add", node.getTextContent());
                            break;
                        case "dutyName":
                            paramMap.put("name", node.getTextContent());
                            break;
                        case "dutyTel1":
                            paramMap.put("tel", node.getTextContent());
                            break;
                        case "dutyTime1c":
                            paramMap.put("1_end", node.getTextContent());
                            break;
                        case "dutyTime2c":
                            paramMap.put("2_end", node.getTextContent());
                            break;
                        case "dutyTime3c":
                            paramMap.put("3_end", node.getTextContent());
                            break;
                        case "dutyTime4c":
                            paramMap.put("4_end", node.getTextContent());
                            break;
                        case "dutyTime5c":
                            paramMap.put("5_end", node.getTextContent());
                            break;
                        case "dutyTime6c":
                            paramMap.put("6_end", node.getTextContent());
                            break;
                        case "dutyTime7c":
                            paramMap.put("7_end", node.getTextContent());
                            break;
                        case "dutyTime8c":
                            paramMap.put("8_end", node.getTextContent());
                            break;
                        case "dutyTime1s":
                            paramMap.put("1_start", node.getTextContent());
                            break;
                        case "dutyTime2s":
                            paramMap.put("2_start", node.getTextContent());
                            break;
                        case "dutyTime3s":
                            paramMap.put("3_start", node.getTextContent());
                            break;
                        case "dutyTime4s":
                            paramMap.put("4_start", node.getTextContent());
                            break;
                        case "dutyTime5s":
                            paramMap.put("5_start", node.getTextContent());
                            break;
                        case "dutyTime6s":
                            paramMap.put("6_start", node.getTextContent());
                            break;
                        case "dutyTime7s":
                            paramMap.put("7_start", node.getTextContent());
                            break;
                        case "dutyTime8s":
                            paramMap.put("8_start", node.getTextContent());
                            break;
                        case "hpid":
                            paramMap.put("pid", node.getTextContent());
                            break;
                        case "postCdn1":
                            paramMap.put("pos1", node.getTextContent());
                            break;
                        case "postCdn2":
                            paramMap.put("pos2", node.getTextContent());
                            break;
                        case "wgs84Lon":
                            paramMap.put("long", node.getTextContent());
                            break;
                        case "wgs84Lat":
                            paramMap.put("lat", node.getTextContent());
                            break;
                        default:
                            break;
                    }

                    // log.info("현재 값 이름" + node.getNodeName()); // 이걸로 알맞는 attribute 값 찾아
                    // log.info("현재 값 데이터" + node.getTextContent()); // 데이터 넣어주면 됨
                }
                try {
                    // blahApiService.id_name(paramMap); // insert
                } catch (Exception e) {
                    throw new Exception("약국 리스트 DB에 저장 중 오류가 발생." + e.getMessage());
                }
            }

        } catch (Exception e) {
            System.out.println("약국 리스트 DB에 저장 중 오류가 발생." + e);
        }

        return "DB ALL INSERT DONE :)";
    }
}
