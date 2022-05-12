package org.example;

import org.apache.http.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VisaRequestPostGet {

    // one instance, reuse
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private String Token;
    CookieStore httpCookieStore = new BasicCookieStore();
    HttpGet request = new HttpGet("https://lift-api.vfsglobal.com/appointment/slots?countryCode=blr&missionCode=ltu&centerCode=BRS&loginUser=kirmaxik%40mail.ru&visaCategoryCode=SS&languageCode=ru-RU&applicantsCount=1&days=180&fromDate=21%2F03%2F2022&slotType=2&toDate=17%2F09%2F2022");
    HttpClient http = null;
    HttpClientBuilder builder = HttpClientBuilder.create().setDefaultCookieStore(httpCookieStore);
    //http = builder.build();
    HttpPost post = new HttpPost("https://lift-api.vfsglobal.com/user/login");
    HttpResponse httpResponse = null;

    public static void main(String[] args) throws Exception {

        VisaRequestPostGet obj = new VisaRequestPostGet();

        try {



            System.out.println("Testing 1 - Send Http POST request");
            obj.sendPost();

            System.out.println("Testing 2 - Send Http GET request");
            obj.sendGet();

        } finally {
            obj.close();
        }
    }

    private void close() throws IOException {
        httpClient.close();
    }

    private void sendGet() throws Exception {


           HttpGet request = new HttpGet("https://lift-api.vfsglobal.com/appointment/slots?");

        //HttpGet request = new HttpGet("https://lift-api.vfsglobal.com/appointment/slots?countryCode=blr&missionCode=ltu&centerCode=BRS&loginUser=kirmaxik%40mail.ru&visaCategoryCode=SS&languageCode=ru-RU&applicantsCount=1&days=180&fromDate=21%2F03%2F2022&slotType=2&toDate=17%2F09%2F2022");


        // add request headers

       request.addHeader("countryCode", "blr");
        request.addHeader("missionCode", "ltu");
        request.addHeader("centerCode", "BRS");
        request.addHeader("loginUser", "kirmaxik%40mail.ru");
        request.addHeader("visaCategoryCode", "SS");
        request.addHeader("languageCode", "ru-RU");
        request.addHeader("applicantsCount", "1");
        request.addHeader("days", "180");
        request.addHeader("fromDate", "21%2F03%2F2022");
        request.addHeader("slotType", "2");
        request.addHeader("toDate", "17%2F09%2F2022");

        request.addHeader(":authority", "lift-api.vfsglobal.com");
        request.addHeader(":method", "GET");
        request.addHeader(":path", "/appointment/slots?countryCode=blr&missionCode=ltu&centerCode=BRS&loginUser=kirmaxik%40mail.ru&visaCategoryCode=SS&languageCode=ru-RU&applicantsCount=1&days=180&fromDate=21%2F03%2F2022&slotType=2&toDate=17%2F09%2F2022");
        request.addHeader(":scheme", "https");
        request.addHeader("accept", "application/json, text/plain, */*");
        request.addHeader("accept-encoding", "gzip, deflate, br");
        request.addHeader("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");
 //       request.addHeader("authorization", Token);
        request.addHeader("content-type", "application/json;charset=utf-8");
        request.addHeader("origin", "https://visa.vfsglobal.com");
        request.addHeader("referer", "https://visa.vfsglobal.com/");
        request.addHeader("route", "blr/ru/ltu");
        request.addHeader("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"99\", \"Google Chrome\";v=\"99\"");
        request.addHeader("sec-ch-ua-mobile", "?0");
        request.addHeader("sec-ch-ua-platform", "\"Windows\"");
        request.addHeader("sec-fetch-dest", "empty");
        request.addHeader("sec-fetch-mode", "cors");
        request.addHeader("sec-fetch-site", "same-site");
        //request.addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.74 Safari/537.36");

        //   ((DefaultHttpClient) httpclient).setCookieStore(cookieStore);
        HttpContext localContext = new BasicHttpContext();
        localContext.setAttribute(HttpClientContext.COOKIE_STORE, httpCookieStore);
        request.addHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.74 Safari/537.36");

        try (CloseableHttpResponse response = httpClient.execute(request, localContext)) {

            // Get HttpResponse Status

            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                System.out.println(result);
            }


            //Выведем в консоль имена cookie с их значениями
            List<Cookie> cookieList = httpCookieStore.getCookies();
            if (!cookieList.isEmpty()) {
                for (Cookie cookie : cookieList) {
                    System.out.println("Кукис = " + cookie.getName() + " = " + cookie.getValue());
                }

            }

        }
    }
    private void sendPost () throws Exception {

        HttpGet request = new HttpGet("https://lift-api.vfsglobal.com/appointment/slots?countryCode=blr&missionCode=ltu&centerCode=BRS&loginUser=kirmaxik%40mail.ru&visaCategoryCode=SS&languageCode=ru-RU&applicantsCount=1&days=180&fromDate=21%2F03%2F2022&slotType=2&toDate=17%2F09%2F2022");
        //       HttpClient http = null;
        HttpClient http = HttpClientBuilder.create().build();
        HttpClientBuilder builder = HttpClientBuilder.create().setDefaultCookieStore(httpCookieStore);
        //       http = builder.build();
        HttpPost post = new HttpPost("https://lift-api.vfsglobal.com/user/login");
        HttpResponse httpResponse = null;
        CookieStore cookieStore;


        try {httpResponse = http.execute(request);} catch (Throwable error) {throw new RuntimeException(error);}



        // add request parameter, form parameters
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("username", "kirmaxik@mail.ru"));
        urlParameters.add(new BasicNameValuePair("password", "!GRUppa104"));
        urlParameters.add(new BasicNameValuePair("missioncode", "ltu"));
        urlParameters.add(new BasicNameValuePair("countrycode", "blr"));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        // Object httpclient;
        //        cookieStore = ((DefaultHttpClient) http).getCookieStore();

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
            String MyString = EntityUtils.toString(response.getEntity());
            System.out.println(MyString);

            String[] words = MyString.split("\"");
            for (String word : words) {
                System.out.println(word);

            }
            Token = words[3];
            System.out.println("Токен=" + words[3]);

            //Выведем в консоль имена cookie с их значениями
            List<Cookie> cookieList = httpCookieStore.getCookies();
            if (!cookieList.isEmpty()) {
                for (Cookie cookie : cookieList) {
                    System.out.println("Кукис = " + cookie.getName() + " = " + cookie.getValue());
                }

            }


        }

    }


}