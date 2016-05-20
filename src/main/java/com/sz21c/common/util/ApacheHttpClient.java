package com.sz21c.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Slf4j
public class ApacheHttpClient {

    public String reqestGET(
            String reqUrl,
            String reqParam,
            Map<String, Object> extendedHeader,
            String contentType,
            String charset) {
        log.debug("## reqestGET 진입");
        String serverResponse = new String();
        DefaultHttpClient httpClient = new DefaultHttpClient();
        try {

            if(null != reqParam && !"".equals(reqParam))
                reqParam = "?"+reqParam;
            HttpGet getRequest = new HttpGet(reqUrl + reqParam);

            getRequest.setHeader("Content-Type", contentType);
            getRequest.setHeader("Content-Encoding", "UTF-8");

            addExtenalHeader(extendedHeader, getRequest);

            HttpResponse response = httpClient.execute(getRequest);
            serverResponse = catchResponse(response, charset);

        } catch (ClientProtocolException e) {
            log.error("########## ERROR IN REQUEST GET - ClientProtocolException : "+ this.getClass());
            log.error(e.toString());
            e.printStackTrace();
            serverResponse = null;
        }catch(IOException e) {;
            log.error("########## ERROR IN REQUEST GET - IOException : "+ this.getClass());
            log.error(e.toString());
            e.printStackTrace();
            serverResponse = null;
        }catch(Exception e) {;
            log.error("########## ERROR IN REQUEST GET - Exception : "+ this.getClass());
            log.error(e.toString());
            e.printStackTrace();
            serverResponse = null;
        }finally{
            httpClient.getConnectionManager().shutdown();
        }
        log.debug("########## FINAL RESPONSE VALUE FROM SERVER : "+serverResponse);

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

            addExtenalHeader(extendedHeader, postRequest);

            postRequest.addHeader("Authorization", token);

            /** requParam은 json 형태의 String value **/
            StringEntity input = new StringEntity(reqParam, charset);
            input.setContentEncoding(charset);
            input.setContentType(contentType);

            postRequest.setEntity(input);

            log.debug("PARAM TEST : "+input);

            HttpResponse response = httpClient.execute(postRequest);
            serverResponse = catchResponse(response, charset);

        } catch (MalformedURLException e) {
            log.error("########## ERROR IN REQUEST POST - MalformedURLException : "+ this.getClass());
            log.error(e.toString());
            e.printStackTrace();
            serverResponse = null;
        }catch(IOException e) {;
            log.error("########## ERROR IN REQUEST POST - IOException: "+ this.getClass());
            log.error(e.toString());
            e.printStackTrace();
            serverResponse = null;
        }catch(Exception e) {;
            log.error("########## ERROR IN REQUEST POST - Exception : "+ this.getClass());
            log.error(e.toString());
            e.printStackTrace();
            serverResponse = null;
        }finally{
            log.debug("End http connect");
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

            addExtenalHeader(extendedHeader, patchRequest);

            /** requParam은 json 형태의 String value **/
            StringEntity input = new StringEntity(reqParam, charset);
            input.setContentType(contentType);
            patchRequest.setEntity(input);

            HttpResponse response = httpClient.execute(patchRequest);
            serverResponse = catchResponse(response, charset);

        } catch (MalformedURLException e) {
            log.error("########## ERROR IN REQUEST PATCH - MalformedURLException : "+ this.getClass());
            log.error(e.toString());
            e.printStackTrace();
        }catch(IOException e) {
            log.error("########## ERROR IN REQUEST PATCH - IOException: "+ this.getClass());
            log.error(e.toString());
            e.printStackTrace();
        }catch(Exception e) {
            log.error("########## ERROR IN REQUEST PATCH - Excepion : "+ this.getClass());
            log.error(e.toString());
            e.printStackTrace();
        }finally{
            httpClient.getConnectionManager().shutdown();
        }

        return serverResponse;
    }

    private void addExtenalHeader(Map<String, Object> extendedHeader, HttpRequest request) throws Exception {

        /** 추가 Header 필요한 경우 설정 **/
        if(extendedHeader != null){
            Set<String> keySet = (Set<String>)extendedHeader.keySet();
            Iterator<String> headerKeys = keySet.iterator();
            while(headerKeys.hasNext()){
                String headerKey = headerKeys.next();
                request.addHeader(headerKey, (String)extendedHeader.get(headerKey));
            }
        }

    }

    private String catchResponse(HttpResponse response ,  String charset) throws IOException{
        if (response.getStatusLine().getStatusCode() != 200) {
            log.error("\n########## Failed ##########\n HTTP error code : "
                    + response.getStatusLine().getStatusCode()
                    + "\n HTTP error reason :" + response.getStatusLine().getReasonPhrase());

            String errorJson = "{\"resultCode\":"+response.getStatusLine().getStatusCode() +
                    ", \"resultMessage\":\""+response.getStatusLine().getReasonPhrase() +"\"" +
                    ", \"resultBody\":{}}";
            log.error(errorJson);
            return new String(errorJson.getBytes(), charset);
        }
        log.debug("Output from Server .... \n");

        String serverResponse = new String();

        serverResponse = EntityUtils.toString(response.getEntity(), charset);
        log.debug("########## serverResponse ########## \n"+ serverResponse );

        return serverResponse;
    }

}
