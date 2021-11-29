package com.example.COVID19.utils;
/*本类为JsonUtils
 *1.解析获取html数据
 *2.解析获取jsonArray
 * */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsonUtils {
    //url为网址 id为传给选择器的值


    public static JSONArray getJsonArrayFromHtml(String url, String id) {

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        JSONArray jsonArray = null;
        try {
            response = client.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                //Document 是Jsoup解析文件或者html获得的
                String content = EntityUtils.toString(response.getEntity(), "utf8");
                Document doc = Jsoup.parse(content);
                Elements elements = doc.select(id);
                StringBuilder stringBuilder = new StringBuilder(elements.toString());
                //截取json的数据
                String json = stringBuilder.substring(stringBuilder.indexOf("["), stringBuilder.lastIndexOf("]") + 1);
                jsonArray = JSON.parseArray(json);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (client != null) {

                try {
                    client.close();
                } catch (IOException e) {
                    System.out.println("client close fault");
                }
            }
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonArray;
    }

}
