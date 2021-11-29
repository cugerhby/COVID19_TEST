package com.example.COVID19.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class WebCrawlerTest {
    public static void main(String[] args) throws IOException, URISyntaxException {

        //httpclient用来解析 jsoup用来抓取数据

        //创建连接
        //Get方法的设置参数被封装到了URIBuilder里面去

        URIBuilder uriBuilder=new URIBuilder("https://so.csdn.net/so/search?q=123&t=&u=");

/*

        CloseableHttpClient httpClient= HttpClients.createDefault();

        //输入网址
        HttpGet httpGet=new HttpGet(uriBuilder.build());



        //发动请求
        CloseableHttpResponse response=httpClient.execute(httpGet);

*/

        //post方法
        HttpGet httpPost=new HttpGet(uriBuilder.build());

        CloseableHttpClient client=HttpClients.createDefault();

        RequestConfig requestConfig=RequestConfig.custom().setConnectionRequestTimeout(100).build();//自定义设置

     /*   //搞一个参数列表
        List<NameValuePair> list=new ArrayList<NameValuePair>();
        UrlEncodedFormEntity formEntity=new UrlEncodedFormEntity(list,"utf8");
        httpPost.setEntity(formEntity);
*/
        CloseableHttpResponse response=client.execute(httpPost);
        System.out.println(response);


        //判断状态码是否正常
        if(response.getStatusLine().getStatusCode()==200){
            HttpEntity httpEntity=response.getEntity();
            String web= EntityUtils.toString(httpEntity,"utf8");
            System.out.println(web);
        }else if(response.getStatusLine().getStatusCode()==302){
            //跳转
            String locationUrl=response.getLastHeader("Location").getValue();
            httpPost.setURI(new URI(locationUrl));
            response=client.execute(httpPost);
            HttpEntity httpEntity=response.getEntity();
            String web= EntityUtils.toString(httpEntity,"utf8");//指定获取返回页面的解析方式，不然容易出错
            System.out.println(web);
        }
    }


}
