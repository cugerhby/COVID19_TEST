package com.example.COVID19.test;

import com.alibaba.fastjson.JSONException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;

public class jsoup {

    @Test
    public void sb() throws IOException {
        //正则表达式

        //reader和writer是处理字符的   inputstream和outputstream是处理字节的

        //jsoup解析URL
        Connection.Response res = Jsoup.connect("https://ncov.dxy.cn/ncovh5/view/pneumonia?from=timeline")
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
                .timeout(10000).ignoreContentType(true).execute();//.get();

    }

    //jsoup解析String
    @Test
    public void testJsoupChar() throws IOException {
        //content是指书的内容或什么的内容物
        //parse对什么作分析
        String content = FileUtils.readFileToString(new File("C:\\Users\\黄邦宇的电脑\\Desktop\\2.html"), "utf8");
        //select选择器，操纵使用jsoup获取到的doc文档
        System.out.println(content);


        //选择器的组合使用 元素加案例，元素加id，元素加

        //还可以通过属性和属性的值来获取内容  <a href=“adskjwdhkja”>  </a>
        //还可以获取标签的各种属性id className

        //根据属性的名称来获得属性的值
        //b.attr("span");//class a
        //attributes,获取一个标签所有属性的值
    }

    //jsoup解析文件

    //搜索约翰霍普斯金大学的信息
    @Test
    public void SearchJhon() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://yqapp.cug.edu.cn/xsfw/sys/swmwcsqglapp/*default/index.do#/");
        CloseableHttpResponse response = client.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity httpEntity = response.getEntity();
           /* Document doc = Jsoup.parse(EntityUtils.toString(httpEntity, "utf8"));*/
            String d=EntityUtils.toString(httpEntity);
            System.out.println(d);
/*            StringBuilder stringBuilder = new StringBuilder(elements.toString());
            String content = stringBuilder.substring(stringBuilder.indexOf("["), stringBuilder.lastIndexOf("]") + 1);
            JSONArray jsonObject = JSON.parseArray(content);
            JSONObject jsonObject1= (JSONObject) jsonObject.get(5);
            System.out.println(jsonObject1.getJSONArray("cities"));*/

  /*        StringBuilder entityStringBuilder=new StringBuilder();
            //inputStreamReader 是将字节流转换成字符流的桥梁，应为字符如果按字节读取容易出错
            BufferedReader bf=new BufferedReader(new InputStreamReader(httpEntity.getContent(),"utf8"),8*1024);
            String line="";
            while ((line=bf.readLine())!=null) {

                entityStringBuilder.append(line+"/n");

            }
            */
        }

    }

    @Test
    void url() {
        InputStreamReader reader = null;
        BufferedReader in = null;
        try {
            URL url = new URL("https://ncov.dxy.cn/ncovh5/view/pneumonia?from=timeline");
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(1000);
            reader = new InputStreamReader(connection.getInputStream(), "UTF-8");
            in = new BufferedReader(reader);
            String line = null; // 每行内容
            StringBuffer content = new StringBuffer();
            while ((line = in.readLine()) != null) {
                content.append(line);
            }
            if (StringUtils.isNotBlank(content)) {
                String jsonStr = content.toString().replaceAll("\\n", "");
                System.out.println(jsonStr);
            }
        } catch (SocketTimeoutException e) {
            System.out.println("连接超时!!!");
        } catch (JSONException e) {
            System.out.println("网站响应不是json格式，无法转化成JSONObject!!!");
        } catch (Exception e) {
            System.out.println("连接网址不对或读取流出现异常!!!");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    System.out.println("关闭流出现异常!!!");
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("关闭流出现异常!!!");
                }
            }
        }
    }


    //使用selector对元素进行选择，上面的是用dom对元素进行选择


}
