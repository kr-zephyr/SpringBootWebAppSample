package com.sz21c.common.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ApacheHttpClient {

    static final Logger LOG = LoggerFactory.getLogger(ApacheHttpClient.class);

    public String reqestGET(
            String reqUrl,
            String reqParam,
            Map<String, Object> extendedHeader,
            String contentType,
            String charset) {
        LOG.debug("## reqestGET 진입");
        String serverResponse = new String();
        DefaultHttpClient httpClient = new DefaultHttpClient();
        try {

            if(null != reqParam && !"".equals(reqParam))
                reqParam = "?"+reqParam;
            HttpGet getRequest = new HttpGet(reqUrl + reqParam);

            getRequest.setHeader("Content-Type", contentType);
            getRequest.setHeader("Content-Encoding", "UTF-8");

            /** 추가 Header 필요한 경우 설정 **/
            if(extendedHeader != null){
                Set<String> keySet = (Set<String>)extendedHeader.keySet();
                Iterator<String> headerKeys = keySet.iterator();
                while(headerKeys.hasNext()){
                    String headerKey = headerKeys.next();
                    getRequest.addHeader(headerKey, (String)extendedHeader.get(headerKey));
                }

            }

            HttpResponse response = httpClient.execute(getRequest);
            serverResponse = catchResponse(response, charset);

        } catch (ClientProtocolException e) {
            LOG.error("########## ERROR IN REQUEST GET - ClientProtocolException : "+ this.getClass());
            LOG.error(e.toString());
            e.printStackTrace();
            serverResponse = null;
        }catch(IOException e) {;
            LOG.error("########## ERROR IN REQUEST GET - IOException : "+ this.getClass());
            LOG.error(e.toString());
            e.printStackTrace();
            serverResponse = null;
        }catch(Exception e) {;
            LOG.error("########## ERROR IN REQUEST GET - Exception : "+ this.getClass());
            LOG.error(e.toString());
            e.printStackTrace();
            serverResponse = null;
        }finally{
            httpClient.getConnectionManager().shutdown();
        }
        LOG.debug("########## FINAL RESPONSE VALUE FROM SERVER : "+serverResponse);
        return serverResponse;
    }

    public String requestPOST(
            String reqUrl,
            String reqParam,
            Map<String, Object> extendedHeader,
            String contentType,
            String charset) {
        String token = "";

        String serverResponse = new String();

        DefaultHttpClient httpClient = new DefaultHttpClient();

        try {

            HttpPost postRequest = new HttpPost(reqUrl);

            /** 추가 Header 필요한 경우 설정 **/
            if(extendedHeader != null){
                Set<String> keySet = (Set<String>)extendedHeader.keySet();
                Iterator<String> headerKeys = keySet.iterator();
                while(headerKeys.hasNext()){
                    String headerKey = headerKeys.next();
                    postRequest.addHeader(headerKey, (String)extendedHeader.get(headerKey));
                }

            }
            postRequest.addHeader("Authorization", token);

            /** requParam은 json 형태의 String value **/
            StringEntity input = new StringEntity(reqParam, charset);
            input.setContentEncoding(charset);
            input.setContentType(contentType);

            postRequest.setEntity(input);

            LOG.debug("PARAM TEST : "+input);

            HttpResponse response = httpClient.execute(postRequest);
            serverResponse = catchResponse(response, charset);

        } catch (MalformedURLException e) {
            LOG.error("########## ERROR IN REQUEST POST - MalformedURLException : "+ this.getClass());
            LOG.error(e.toString());
            e.printStackTrace();
            serverResponse = null;
        }catch(IOException e) {;
            LOG.error("########## ERROR IN REQUEST POST - IOException: "+ this.getClass());
            LOG.error(e.toString());
            e.printStackTrace();
            serverResponse = null;
        }catch(Exception e) {;
            LOG.error("########## ERROR IN REQUEST POST - Exception : "+ this.getClass());
            LOG.error(e.toString());
            e.printStackTrace();
            serverResponse = null;
        }finally{
            LOG.debug("End http connect");
            httpClient.getConnectionManager().shutdown();
        }

        return serverResponse;
    }

    public String requestPATCH(
            String reqUrl,
            String reqParam,
            Map<String, Object> extendedHeader,
            String contentType,
            String charset) {

        String serverResponse = new String();

        DefaultHttpClient httpClient = new DefaultHttpClient();

        try {

            HttpPatch patchRequest = new HttpPatch(reqUrl);

            /** 추가 Header 필요한 경우 설정 **/
            if(extendedHeader != null){
                Set<String> keySet = (Set<String>)extendedHeader.keySet();
                Iterator<String> headerKeys = keySet.iterator();
                while(headerKeys.hasNext()){
                    String headerKey = headerKeys.next();
                    patchRequest.addHeader(headerKey, (String)extendedHeader.get(headerKey));
                }

            }
            /** requParam은 json 형태의 String value **/
            StringEntity input = new StringEntity(reqParam, charset);
            input.setContentType(contentType);
            patchRequest.setEntity(input);

            HttpResponse response = httpClient.execute(patchRequest);
            serverResponse = catchResponse(response, charset);

        } catch (MalformedURLException e) {
            LOG.error("########## ERROR IN REQUEST PATCH - MalformedURLException : "+ this.getClass());
            LOG.error(e.toString());
            e.printStackTrace();

        }catch(IOException e) {;
            LOG.error("########## ERROR IN REQUEST PATCH - IOException: "+ this.getClass());
            LOG.error(e.toString());
            e.printStackTrace();

        }catch(Exception e) {;
            LOG.error("########## ERROR IN REQUEST PATCH - Excepion : "+ this.getClass());
            LOG.error(e.toString());
            e.printStackTrace();

        }finally{
            httpClient.getConnectionManager().shutdown();
        }
        return serverResponse;
    }

    private String catchResponse(HttpResponse response ,  String charset) throws IOException{
        if (response.getStatusLine().getStatusCode() != 200) {
            LOG.error("\n########## Failed ##########\n HTTP error code : "
                    + response.getStatusLine().getStatusCode()
                    + "\n HTTP error reason :" + response.getStatusLine().getReasonPhrase());

            String errorJson = "{\"resultCode\":"+response.getStatusLine().getStatusCode() +
                    ", \"resultMessage\":\""+response.getStatusLine().getReasonPhrase() +"\"" +
                    ", \"resultBody\":{}}";
            LOG.error(errorJson);
            return new String(errorJson.getBytes(), charset);
        }
        LOG.debug("Output from Server .... \n");

        String serverResponse = new String();

        serverResponse = EntityUtils.toString(response.getEntity(), charset);
        LOG.debug("########## serverResponse ########## \n"+ serverResponse );

        return serverResponse;
    }

}
