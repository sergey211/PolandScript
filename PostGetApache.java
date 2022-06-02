package org.example;

// import com.sun.deploy.net.cookie.HttpCookie;
import org.apache.http.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;



public class PostGetApache {

    // one instance, reuse


    private static String Token;
    private static CookieStore cookieStore;
    private static CookieStore httpCookieStore = new BasicCookieStore();
    private static DefaultHttpClient httpclient = new DefaultHttpClient();
    private static CloseableHttpClient httpClient = HttpClients.createDefault();
    private static HttpPost httpost = new HttpPost("https://lift-api.vfsglobal.com/user/login");
    private static List<Cookie> cookieList;
    private static Cookie cookie;


   // HttpGet requestGet = new HttpGet("https://lift-api.vfsglobal.com/appointment/slots?countryCode=blr&missionCode=ltu&centerCode=BRS&loginUser=kirmaxik%40mail.ru&visaCategoryCode=SS&languageCode=ru-RU&applicantsCount=1&days=180&fromDate=21%2F03%2F2022&slotType=2&toDate=17%2F09%2F2022");
  //  HttpClient http = null;
  //  HttpClientBuilder builder = HttpClientBuilder.create().setDefaultCookieStore(httpCookieStore);
    //http = builder.build();
   // HttpPost requestPost = new HttpPost("https://lift-api.vfsglobal.com/user/login");
  //  HttpResponse httpResponse = null;

    public static void main(String[] args) throws Exception {

boolean post1 = false;
boolean post2con = false;
boolean post9apr = true;
boolean post3 = false;


        // final CloseableHttpClient httpclient = HttpClients.createDefault();

if (post1) {
    // sending POST 1
    System.out.println("sending POST 1");

    // Payload Form Data

    List<NameValuePair> nvps = new ArrayList<NameValuePair>();
    nvps.add(new BasicNameValuePair("username", ""));
    nvps.add(new BasicNameValuePair("password", ""));
    nvps.add(new BasicNameValuePair("missioncode", "ltu"));
    nvps.add(new BasicNameValuePair("countrycode", "blr"));
    //   httpost.setEntity(new UrlEncodedFormEntity(nvps, StandardCharsets.UTF_8));
    httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
    try {
        HttpResponse response = httpclient.execute(httpost);

        System.out.println(response.getStatusLine().toString());

        HttpEntity entity = response.getEntity();
        Header headers = entity.getContentType();
        System.out.println(headers);

        if (entity != null) {
            // return it as a String
            String result = EntityUtils.toString(entity);
            System.out.println(result);

            String[] words = result.split("\"");
            for (String word : words) {
                //             System.out.println(word);

            }
            Token = words[3];
            System.out.println("Токен=" + words[3]);

        }


    } catch (IOException e) {
        e.printStackTrace();
    }

    System.out.println("Проверяем кукис:");
    cookieStore = ((DefaultHttpClient) httpclient).getCookieStore();
    //Выведем в консоль имена cookie с их значениями
    List<Cookie> cookieList = cookieStore.getCookies();
    if (!cookieList.isEmpty()) {
        for (Cookie cookie : cookieList) {
            System.out.println(cookie.getName() + " = " + cookie.getValue());
        }
    }
    Thread.sleep(1000);
}
// post 1 end

        if (post2con) {
            System.out.println("sending POST 2 appl - connection");
            Thread.sleep(1000);

            URL url = new URL("https://lift-api.vfsglobal.com/appointment/application");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setRequestProperty("Host", "lift-api.vfsglobal.com");
            http.setRequestProperty("pragma", "no-cache");
            http.setRequestProperty("cache-control", "no-cache");
            http.setRequestProperty("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"98\", \"Yandex\";v=\"22\"");
            http.setRequestProperty("sec-ch-ua-mobile", "?0");
            http.setRequestProperty("authorize", Token);
            http.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.119 YaBrowser/22.3.0.2430 Yowser/2.5 Safari/537.36");
            http.setRequestProperty("content-type", "application/json");
            http.setRequestProperty("accept", "application/json, text/plain, * /*");
            http.setRequestProperty("route", "blr/ru/ltu");
            http.setRequestProperty("sec-ch-ua-platform", "\"Windows\"");
            http.setRequestProperty("origin", "https://visa.vfsglobal.com");
            http.setRequestProperty("sec-fetch-site", "same-site");
            http.setRequestProperty("sec-fetch-mode", "cors");
            http.setRequestProperty("sec-fetch-dest", "empty");
            http.setRequestProperty("referer", "https://visa.vfsglobal.com/");
            http.setRequestProperty("accept-language", "ru,en;q=0.9,pt;q=0.8,uk;q=0.7,be;q=0.6");

            String data = "{\"countryCode\":\"blr\",\"missionCode\":\"ltu\",\"loginUser\":\"kirmaxik@mail.ru\"}";

            byte[] out = data.getBytes(StandardCharsets.UTF_8);

            OutputStream stream = http.getOutputStream();
            stream.write(out);

            System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
            http.disconnect();
        }

        if (post9apr) {
            // post 2 - 09 april
            System.out.println("sending post2 9 apr");
            // Payload Form Data
            System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
            HttpPost httpost2 = new HttpPost("https://lift-api.vfsglobal.com/appointment/application");


            List<NameValuePair> nvps2 = new ArrayList<NameValuePair>();
            nvps2.add(new BasicNameValuePair("countryCode", "blr"));
            nvps2.add(new BasicNameValuePair("missionCode", "ltu"));
            nvps2.add(new BasicNameValuePair("loginUser", "kirmaxik@mail.ru"));


            httpost2.addHeader("Host", "lift-api.vfsglobal.com");
            httpost2.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:51.0) Gecko/20100101 Firefox/51.0");
            //       httpost2.addHeader("Accept","application/json, text/plain, * / *");
            httpost2.addHeader("Accept", "application/json, text/plain, * / * ");
            httpost2.addHeader("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3");
            httpost2.addHeader("Accept-Encoding", "gzip, deflate, br");
//        httpost2.addHeader("Authorize",Token);//"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoiSW5kaXZpZHVhbCIsInVzZXJJZCI6Ind5SFNXN3lRU0kvdGVjWENIcjRoSFE9PSIsImVtYWlsIjoiZkdOL3llbTBxSXV5UjJVYUMvZzJuQThOQnJZaUtTM001UmFWak0xTjNOWUlOWjhLdDl6azFjRWNpbFR4Q1ErNCIsIm5iZiI6MTY0OTQ5MzA1NSwiZXhwIjoxNjQ5NDk5MDU1LCJpYXQiOjE2NDk0OTMwNTV9.MoEmuEGGTCfyCuMs2LyG48prVeXGRsVHTAJKv8FVcrw");
            httpost2.addHeader("Authorize", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoiSW5kaXZpZHVhbCIsInVzZXJJZCI6IkdHanpjWlVZMnAwVUNuN2dseHFOVGc9PSIsImVtYWlsIjoiZkZoc3JXWVVZOGV6MTNIYUNoeENSS2pRRGlMT2dMclRTZlNxdVVmTjJoM1BFUmk1Yng5YW5hV3Y4emZBZmoraiIsIm5iZiI6MTY0OTUwMjQ4OCwiZXhwIjoxNjQ5NTA4NDg4LCJpYXQiOjE2NDk1MDI0ODh9.f2-LwXCi_9CVX5pmrUbgB9T9hElG4SLF71BTfB1Pvjw");

            httpost2.addHeader("Content-Type", "application/json");
            // content-type: application/json; charset=UTF-8
            httpost2.addHeader("route", "blr/ru/ltu");
            httpost2.addHeader("Referer", "https://visa.vfsglobal.com/blr/ru/ltu/dashboard");
//        httpost2.addHeader("Referer","https://visa.vfsglobal.com");
            //       httpost2.addHeader("Content-Length","76");
            httpost2.addHeader("Origin", "https://visa.vfsglobal.com");
            httpost2.addHeader("Connection", "keep-alive");
            httpost2.addHeader("Pragma", "no-cache");
            httpost2.addHeader("Cache-Control", "no-cache");

            //  httpost2.addHeader(HttpHeaders.AUTHORIZATION, Token);
/*
        httpost2.addHeader("authority", "lift-api.vfsglobal.com");
        httpost2.addHeader("pragma", "no-cache");
        httpost2.addHeader("cache-control", "no-cache");
        httpost2.addHeader("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"99\", \"Google Chrome\";v=\"99\"");
        httpost2.addHeader("sec-ch-ua-mobile", "?0");
        httpost2.addHeader("authorize", Token);
        httpost2.addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.74 Safari/537.36");
        httpost2.addHeader("content-type", "application/json; charset=utf-8");
        httpost2.addHeader("accept", "application/json, text/plain, * / *");
        httpost2.addHeader("route", "blr/ru/ltu");
        httpost2.addHeader("sec-ch-ua-platform", "\"Windows\"");
        httpost2.addHeader("origin", "https://visa.vfsglobal.com");
        httpost2.addHeader("sec-fetch-site", "same-site");
        httpost2.addHeader("sec-fetch-mode", "cors");
        httpost2.addHeader("sec-fetch-dest", "empty");
        httpost2.addHeader("referer", "https://visa.vfsglobal.com/");
        httpost2.addHeader("accept-language", "ru,en;q=0.9,pt;q=0.8,uk;q=0.7,be;q=0.6");
     //   httpost2.addHeader("accept-encoding", "gzip, deflate, br"); - этот хедер портит язык ответа

    /*       cookieList = cookieStore.getCookies();
          if (!cookieList.isEmpty()) {
            for (Cookie cookie : cookieList) {
                httpost2.addHeader("set-cookie",cookie.getName() + "=" + cookie.getValue());
            }
        }
     */


            // httpPost2.setEntity(new UrlEncodedFormEntity(params2));

            //    ((DefaultHttpClient) httpclient).setCookieStore(cookieStore);
            //    HttpContext localContext2 = new BasicHttpContext();
            //    localContext2.setAttribute(HttpClientContext.COOKIE_STORE, httpCookieStore);
            // requestPost.addHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.74 Safari/537.36");


            //   httpost.setEntity(new UrlEncodedFormEntity(nvps, StandardCharsets.UTF_8));
            //   httpost2.setEntity(new UrlEncodedFormEntity(nvps2, "UTF-8"));

            try {
                HttpResponse response = httpclient.execute(httpost2);

                //    try (CloseableHttpResponse response = httpClient.execute(httpPost2, localContext2)

                System.out.println(response.getStatusLine().toString());

                HttpEntity entity = response.getEntity();
                Header headers = entity.getContentType();
                System.out.println(headers);

                if (entity != null) {
                    // return it as a String
                    String result = EntityUtils.toString(entity);
                    System.out.println(result);

                }


            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Проверяем кукис 2:");
            cookieStore = ((DefaultHttpClient) httpclient).getCookieStore();
            //Выведем в консоль имена cookie с их значениями
            cookieList = cookieStore.getCookies();
            if (!cookieList.isEmpty()) {
                for (Cookie cookie : cookieList) {
                    System.out.println(cookie.getName() + " = " + cookie.getValue());
                }
            }

        }
        // end post2 9 apr

if (post3) {
    // sending POST 3
    Thread.sleep(3000);
    System.out.println("sending POST 2");
    Thread.sleep(1000);
    final HttpPost httpPost2 = new HttpPost("https://lift-api.vfsglobal.com/appointment/application");

    //    httpPost2.addHeader("Host", "https://lift-api.vfsglobal.com/appointment/application");
    httpPost2.addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.74 Safari/537.36");


    final List<NameValuePair> params2 = new ArrayList<>();
    params2.add(new BasicNameValuePair("countryCode", "blr"));
    params2.add(new BasicNameValuePair("missionCode", "ltu"));
    params2.add(new BasicNameValuePair("loginUser", "kirmaxik@mail.ru"));

    //       params2.add(new BasicNameValuePair("authorize", Token));
    //       params2.add(new BasicNameValuePair("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.74 Safari/537.36"));
    if (!cookieList.isEmpty()) {


        for (Cookie cookie : cookieList) {
            //  HttpCookie cookie;
            params2.add(new BasicNameValuePair("cookie", cookie.getName() + "=" + cookie.getValue()));
            System.out.println("Cookie in parameters" + cookie.getName() + "=" + cookie.getValue());

        }
    }
//
    httpPost2.setEntity(new UrlEncodedFormEntity(params2));

    ((DefaultHttpClient) httpclient).setCookieStore(cookieStore);
    HttpContext localContext2 = new BasicHttpContext();
    localContext2.setAttribute(HttpClientContext.COOKIE_STORE, httpCookieStore);
    // requestPost.addHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.74 Safari/537.36");

    try (CloseableHttpResponse response3 = httpClient.execute(httpPost2, localContext2))

    // try ( CloseableHttpResponse response3 = httpclient.execute(httpPost2) )

    {
        final HttpEntity entity3 = response3.getEntity();
        System.out.println(EntityUtils.toString(entity3));
    }
}
//
// end post3

        // sending GET

/*
        url = new URL("https://lift-api.vfsglobal.com/appointment/slots?countryCode=blr&missionCode=ltu&centerCode=BAR&loginUser=kirmaxik%40mail.ru&visaCategoryCode=LT&languageCode=ru-RU&applicantsCount=1&days=180&fromDate=26%2F03%2F2022&slotType=2&toDate=22%2F09%2F2022");
        final HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // Далее необходимо сдобрить соединение всеми параметрами:

        con.setRequestMethod("GET");
       // con.setRequestProperty("Content-Type", "application/json");

   //     con.setRequestProperty("authority", "lift-api.vfsglobal.com");
   //     con.setRequestProperty("method", "GET");
   //     con.setRequestProperty("path", "/appointment/slots?countryCode=blr&missionCode=ltu&centerCode=BRS&loginUser=kirmaxik%40mail.ru&visaCategoryCode=SS&languageCode=ru-RU&applicantsCount=1&days=180&fromDate=24%2F03%2F2022&slotType=2&toDate=20%2F09%2F2022");
   //     con.setRequestProperty("scheme", "https");

        con.setRequestProperty("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"99\", \"Google Chrome\";v=\"99\"");
        con.setRequestProperty("sec-ch-ua-mobile", "?0");
   //     con.setRequestProperty("authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoiSW5kaXZpZHVhbCIsInVzZXJJZCI6IkdHanpjWlVZMnAwVUNuN2dseHFOVGc9PSIsImVtYWlsIjoiZkZoc3JXWVVZOGV6MTNIYUNoeENSS2pRRGlMT2dMclRTZlNxdVVmTjJoM1BFUmk1Yng5YW5hV3Y4emZBZmoraiIsIm5iZiI6MTY0ODE1OTczNSwiZXhwIjoxNjQ4MTY1NzM1LCJpYXQiOjE2NDgxNTk3MzV9.8o44ZiGtF-sCvBQqu14s2VkIu0Fx7DJv6kvWJsteiEQ" ); //"// Token);
        con.setRequestProperty("authorization",  Token);

        con.setRequestProperty("content-type", "application/json;charset=utf-8");
        con.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.74 Safari/537.36");
        con.setRequestProperty("route", "blr/ru/ltu");
        con.setRequestProperty("sec-ch-ua-platform", "\"Windows\"");
        con.setRequestProperty("origin", "https://visa.vfsglobal.com");
        con.setRequestProperty("sec-fetch-site", "same-site");
        con.setRequestProperty("sec-fetch-mode", "cors");
        con.setRequestProperty("sec-fetch-dest", "empty");
        con.setRequestProperty("referer", "https://visa.vfsglobal.com/");
        con.setRequestProperty("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");



  //      con.setRequestProperty("accept", "application/json, text/plain, */
        //*");
  //      con.setRequestProperty("accept-encoding", "gzip, deflate, br");


      /*  if (!cookieList.isEmpty()) {


            for (Cookie cookie : cookieList) {
                //  HttpCookie cookie;
                con.setRequestProperty("Cookie", cookie.getName() + "=" + cookie.getValue());
                System.out.println("Cookie in parameters" + cookie.getName() + "=" + cookie.getValue());

            }
        }

        */
        /*
        con.setConnectTimeout(10000);
        con.setReadTimeout(10000);

        //  И получить InputStream, откуда уже прочитать все полученные данные.

        try (final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            final StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            //return content.toString();
        } catch (final Exception ex) {
            ex.printStackTrace();
            //  return "";
        }
    }
        /*
        Thread.sleep(3000);
        System.out.println("sending GET");
        Thread.sleep(1000);

       // HttpGet requestGet = new HttpGet("https://lift-api.vfsglobal.com/appointment/slots");

        HttpGet requestGet = new HttpGet("https://lift-api.vfsglobal.com/appointment/slots?countryCode=blr&missionCode=ltu&centerCode=BRS&loginUser=kirmaxik%40mail.ru&visaCategoryCode=SS&languageCode=ru-RU&applicantsCount=1&days=180&fromDate=24%2F03%2F2022&slotType=2&toDate=20%2F09%2F2022");


        // add requestGet headers


      //  requestGet.

  //  Request Headers:

 //       requestGet.addHeader(":authority", "lift-api.vfsglobal.com");
 //       requestGet.addHeader(":method", "GET");
 //       requestGet.addHeader(":path", "/appointment/slots?countryCode=blr&missionCode=ltu&centerCode=BRS&loginUser=kirmaxik%40mail.ru&visaCategoryCode=SS&languageCode=ru-RU&applicantsCount=1&days=180&fromDate=24%2F03%2F2022&slotType=2&toDate=20%2F09%2F2022");
  //      requestGet.addHeader(":scheme", "https");
    //    requestGet.addHeader("accept", "application/json, text/plain, */ //*");
  /*      requestGet.addHeader("accept-encoding", "gzip, deflate, br");
        requestGet.addHeader("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");
        requestGet.addHeader("authorization", Token);
        requestGet.addHeader("content-type", "application/json;charset=utf-8");
        requestGet.addHeader("origin", "https://visa.vfsglobal.com");
        requestGet.addHeader("referer", "https://visa.vfsglobal.com/");
        requestGet.addHeader("route", "blr/ru/ltu");
        requestGet.addHeader("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"99\", \"Google Chrome\";v=\"99\"");
        requestGet.addHeader("sec-ch-ua-mobile", "?0");
        requestGet.addHeader("sec-ch-ua-platform", "\"Windows\"");
        requestGet.addHeader("sec-fetch-dest", "empty");
        requestGet.addHeader("sec-fetch-mode", "cors");
        requestGet.addHeader("sec-fetch-site", "same-site");

        requestGet.addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.74 Safari/537.36");


       /*

       // Payload query string parameters:

        requestGet.addHeader("countryCode", "blr");
        requestGet.addHeader("missionCode", "ltu");
        requestGet.addHeader("centerCode", "BRS");
        requestGet.addHeader("loginUser", "kirmaxik@mail.ru");
        requestGet.addHeader("visaCategoryCode", "SS");
        requestGet.addHeader("languageCode", "ru-RU");
        requestGet.addHeader("applicantsCount", "1");
        requestGet.addHeader("days", "180");
        requestGet.addHeader("fromDate", "22/03/2022");
        requestGet.addHeader("slotType", "2");
        requestGet.addHeader("toDate", "18/09/2022");
*/


   /*     if (!cookieList.isEmpty()) {


            for (Cookie cookie : cookieList) {
                //  HttpCookie cookie;
                requestGet.addHeader("cookie", cookie.getName() + "=" + cookie.getValue());
                System.out.println("Cookie in parameters"+ cookie.getName() + "=" + cookie.getValue());

            }
        } else {
            System.out.println("cookies empty");
        }



         ((DefaultHttpClient) httpclient).setCookieStore(cookieStore);
        HttpContext localContext = new BasicHttpContext();
        localContext.setAttribute(HttpClientContext.COOKIE_STORE, httpCookieStore);
        requestGet.addHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.74 Safari/537.36");



        try (CloseableHttpResponse response = httpClient.execute(requestGet, localContext)) {

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




        }
        httpclient.close();


    }
}
*/

}}
