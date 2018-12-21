package com.example.demo.web;


import com.example.demo.comm.DemoProperties;
import com.example.demo.comm.DemoUtil;
import com.example.demo.model.BasicOpenOcr;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.*;
import java.util.Base64.Encoder;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;



public class DoPostGetJsonController {

    private String service=DemoProperties.DEMO_SERVICE;

    private String pid=DemoProperties.DEMO_PID;

    private String lang=DemoProperties.DEMO_LANG;

    private String key=DemoProperties.DEMO_KEY;

    private  String base64Image;
    private String sign;
    private String salt;
    private String submitImage;
    private String md5Sign;


    public String getBasicOpenOcr()  {
        //此处将要发送的数据转换为json格式字符串
        BasicOpenOcr basicOpenOcr = new BasicOpenOcr();
        basicOpenOcr.setService(service);
        basicOpenOcr.setPid(pid);
        salt = String.valueOf(System.currentTimeMillis());
        basicOpenOcr.setSalt(salt);
        basicOpenOcr.setLang(lang);
        base64Image = getImageStr("/Users/jianliu/Documents/base65.jpg");
        basicOpenOcr.setImage(base64Image);
        System.out.println("图片字符串长度="+base64Image.length());
        submitImage = base64Image.substring(0,1024);
        System.out.println("Image :"+submitImage);
        StringBuffer strBuf = new StringBuffer();
        strBuf.append(pid.trim()).append(service.trim()).append(salt.trim()).append(submitImage.trim()).append(key.trim());
        sign =strBuf.toString();
        md5Sign = DemoUtil.md5(sign);
        basicOpenOcr.setSign(md5Sign.trim());
        Map<String,String > map = new HashMap<String,String>();
        map.put("service",service);
        map.put("pid",pid);
        map.put("salt",salt);
        map.put("lang",lang);
        map.put("image",base64Image);
        map.put("sign",md5Sign);
        String sr = doPost(map);
        System.out.println("返回参数：" + sr);
        return sr;
    }

    public static String doPost(Map<String,String> date) {
        // 要调用的接口方法
        String url = "http://deepi.sogou.com/api/sogouService";
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        //配置连接超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(5000)
                .setRedirectsEnabled(true)
                .build();
        HttpPost httpPost = new HttpPost(url);
        //设置超时时间
        httpPost.setConfig(requestConfig);

        //装配post请求参数
        List<BasicNameValuePair> list = new ArrayList<>();
        for (String key : date.keySet()) {
            list.add(new BasicNameValuePair(key, String.valueOf(date.get(key))));
        }
        String strRequest = "";
        try {
            //将参数进行编码为合适的格式,如将键值对编码为param1=value1&param2=value2
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list,"UTF-8");
            httpPost.setEntity(urlEncodedFormEntity);

            //执行 post请求
            CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);

            if (null != closeableHttpResponse && !"".equals(closeableHttpResponse)) {

                if (closeableHttpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity httpEntity = closeableHttpResponse.getEntity();
                    strRequest = EntityUtils.toString(httpEntity);
                } else {
                    strRequest = "Error Response" + closeableHttpResponse.getStatusLine().getStatusCode();
                }
            }
            return strRequest;

        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return "协议异常";
        } catch (ParseException e) {
            e.printStackTrace();
            return "解析异常";
        } catch (IOException e) {
            e.printStackTrace();
            return "传输异常";
        } finally {
            try {
                closeableHttpClient.close();
                if (httpPost != null) {
                    httpPost.releaseConnection();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    private String getImageStr(String imgFile)
    {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        String strImage;
        //读取图片字节数组
        try
        {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        Encoder encoder = Base64.getEncoder();
        strImage = encoder.encodeToString(data);
//        System.out.println("Base64="+strImage);
        return strImage;//返回Base64编码过的字节数组字符串
    }





}
