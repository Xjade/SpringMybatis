package com.Utils;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.xml.rpc.ServiceException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class HttpsClientUtil {

    private static  final Logger LOGGER = LoggerFactory.getLogger(HttpsClientUtil.class);

    public static String doPost(String url, String jsonstr) throws ServiceException {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        HttpResponse response=null;
        String result = null;
        try{
            RequestConfig config = RequestConfig.custom()
                    .setConnectionRequestTimeout(10000).setConnectTimeout(10000)
                    .setSocketTimeout(10000).build();
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
         //   httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httpPost.addHeader("Content-Type", "application/json");
            StringEntity se = new StringEntity(jsonstr);
            se.setContentType("text/json");
            se.setContentEncoding("UTF-8");
            se.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
            httpPost.setEntity(se);
            response = httpClient.execute(httpPost);
            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,"utf-8");
                }else{
                    LOGGER.debug("请求失败");
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }catch(ConnectTimeoutException e){
            // 捕获超时异常 并反馈给调用者
            e.printStackTrace();
            return "请求超时";
        }catch(Exception ex){
            ex.printStackTrace();
            LOGGER.error("请求接口异常",ex);
            throw new ServiceException("");

        }finally {
            if (httpClient!=null){
            // 关闭HttpClient
            httpClient.getConnectionManager().shutdown();}
        }
        return result;
    }
    public static void main(String[] args){
        String url = "https://ddptcs.ele-cloud.com/recipt/checkFp/getFPInfoByNSRSBH";
        String jsonStr = "{\"authorize\":{\"appSecId\":\"d55c622a926252c22a220c7de1eaa9f1\",\"appSec\":\"JWLyTLZVLLxr/LtDBq9NI5B/ulM=\",\"globalInfo\":{\"appId\":\"greensoft\",\"version\": \"v1.0\", \"interfaceCode\":\"CHECK.SINGLE\",\"enterpriseCode\":\"BJPTXX\",\"dataExchangeId\":\"\"},\"data\":\"W3sidGF4Tm8iOm51bGwsImludm9pY2VUeXBlIjpudWxsLCJpbnZvaWNlTm8iOm51bGwsImludm9pY2VDb2RlIjpudWxsLCJpbnZvaWNlRGF0ZSI6bnVsbCwiaW52b2ljZUFtb3VudCI6bnVsbCwiY2hlY2tDb2RlIjpudWxsfV0=\"}";
        
        //String httpOrgCreateTestRtn = HttpClientUtil.doPost(url, jsonStr);
        //System.out.println(httpOrgCreateTestRtn);
    }
}