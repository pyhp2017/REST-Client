package com.CurlHttp;

import javax.swing.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * This is our Client to send Requests and get responses
 * @author Ahmad Foroughi
 * @version 1.0
 */
public class Client {
    //Default User Agent
    public static final String DEFAULT_USER_AGENT = "Ahmad-User-Agent";
    // HTTP request headers
    private final Map<String, String> headers;
    //Connection Timeout Limit (milliseconds)
    private int connectionTimeout = 30000;
    //Data Reading Timeout Limit (milliseconds)
    private int dataRetrievalTimeout = 30000;
    //Follow Redirects property
    private boolean followRedirects = false;

    //BufferSize field
//    private static final int BUFFER_SIZE = 2048;


    /**
     * Create a new Client
     */
    public Client() {
        headers = Collections.synchronizedMap(new LinkedHashMap<String, String>());
        setUserAgent(DEFAULT_USER_AGENT);
    }


    /**
     * Makes an HTTP request.
     *
     * @param url     is a String
     * @param method  is a Method
     * @param params  is a Query that contain a HashMap of key and values
     * @param handler the response handler (IMPORTANT for more details see ResponseHandler abstract class java docs)
     */
    protected void request(String url, RequestMethods method, Query params, ResponseHandler handler, Boolean jsonActive, Object body) {
        Charset charset = StandardCharsets.UTF_8;
        HttpURLConnection urlConnection = null;
        // Create empty params if one isn't specified
        if (params == null) {
            params = new Query();
        }

        if (params.size() > 0)
        {
            url = url + "?" + convertParamsOfQueries(params.getKeyValues());
        }


        try {
            URL resourceUrl = null;
            try
            {
                resourceUrl = new URL(url);
            }
            catch (Exception ex)
            {
                System.out.println("Wrong URL: " + ex.getMessage());
            }

            try
            {
                urlConnection = (HttpURLConnection) resourceUrl.openConnection();

            }
            catch (NullPointerException ex)
            {
                JOptionPane.showMessageDialog(null,"Please Enter Correct Address with http://","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Settings
            urlConnection.setConnectTimeout(connectionTimeout);
            urlConnection.setReadTimeout(dataRetrievalTimeout);
            urlConnection.setUseCaches(false);
            urlConnection.setInstanceFollowRedirects(followRedirects);
            urlConnection.setRequestMethod(method.toString());
            urlConnection.setDoInput(true);

            //Set Headers
            for (Map.Entry<String, String> header : headers.entrySet())
            {
                urlConnection.setRequestProperty(header.getKey(), header.getValue());
            }

            //Start
            handler.onStart(urlConnection);

            // Request Body
            // POST and PUT expect an output body. (Contains body)
            if (method == RequestMethods.POST || method == RequestMethods.PUT)
            {
                urlConnection.setDoOutput(true);
                byte[] content = null;

                //JSON --> WORKS
                if (jsonActive)
                {
                    if (body instanceof String)
                    {
                        String json = (String) body;
                        content = json.getBytes();
                        urlConnection.setRequestProperty("Content-Type", "application/json;charset=" + charset.name());
                    }
                }
                else
                {
                    //Form URL ENCODED (MultiPart) - Form Data
                        String formData = (String) body;
                        String boundary = System.currentTimeMillis() + "";
                        urlConnection.setRequestProperty("Content-Type", "multipart/form-data; charset=" + charset.name()+"; " + "boundary="+boundary);
                        HashMap<String, String> multiPart = new HashMap<>();
                        Run.addFormDataStringToHashMap(multiPart,formData);
                        BufferedOutputStream request = new BufferedOutputStream(urlConnection.getOutputStream());
                        Run.bufferOutFormData(multiPart,boundary,request);
                }

                if (content!=null)
                {
                    urlConnection.setRequestProperty("Content-Length", Long.toString(content.length));
                    // Stream the data so we don't run out of memory
                    urlConnection.setFixedLengthStreamingMode(content.length);
                    try (OutputStream os = urlConnection.getOutputStream())
                    {
                        os.write(content);
                    }
                }

            }
            // Process the response in the handler because it can be done in different ways
            handler.processResponse(urlConnection);
            // Request finished
            handler.onFinish(urlConnection);

        } catch (IOException e) {
            handler.onFailure(e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }



    // Methods Functions


    /**
     * Make a HTTP GET request with parameters.
     * @param url is URL
     * @param params is query (If you don't have one put NULL)
     * @param handler the response handler
     */
    public void get(String url, Query params, ResponseHandler handler, Boolean jsonActive, Object body)
    {
        request(url, RequestMethods.GET, params, handler,jsonActive,body);
    }

    /**
     * Make a HTTP Post request with parameters.
     * @param url is URL
     * @param params is query (If you don't have one put NULL)
     * @param handler the response handler
     */
    public void post(String url, Query params, ResponseHandler handler, Boolean jsonActive, Object body)
    {
        request(url, RequestMethods.POST, params, handler,jsonActive,body);
    }

    /**
     * Make a HTTP put request with parameters.
     * @param url is URL
     * @param params is query (If you don't have one put NULL)
     * @param handler the response handler
     */
    public void put(String url, Query params, ResponseHandler handler, Boolean jsonActive, Object body)
    {
        request(url, RequestMethods.POST, params, handler,jsonActive,body);
    }

    /**
     * Make a HTTP patch request with parameters.
     * @param url is URL
     * @param params is query (If you don't have one put NULL)
     * @param handler the response handler
     */
    public void patch(String url, Query params, ResponseHandler handler, Boolean jsonActive, Object body)
    {
        request(url, RequestMethods.PATCH, params, handler,jsonActive,body);
    }

    /**
     * Make a HTTP delete request with parameters.
     * @param url is URL
     * @param params is query (If you don't have one put NULL)
     * @param handler the response handler
     */
    public void delete(String url, Query params, ResponseHandler handler, Boolean jsonActive, Object body)
    {
        request(url, RequestMethods.DELETE, params, handler,jsonActive,body);
    }


    // End of Methods Functions





    /**
     * Encodes parameters into a query string based on the charset.
     * @return converted string
     */
    public String convertParamsOfQueries(HashMap<String , String> queries)
    {
        Charset charset = StandardCharsets.UTF_8;
        try
        {
            StringBuilder encoded = new StringBuilder();
            for (Map.Entry<String , String> param : queries.entrySet())
            {
                if (encoded.length() > 0)
                {
                    encoded.append("&");
                }
                encoded.append(URLEncoder.encode(param.getKey(), charset.name()));
                encoded.append("=");
                encoded.append(URLEncoder.encode(param.getValue(), charset.name()));
            }
            return encoded.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * @return headers
     */
    public Map<String, String> getHeaders()
    {
        return headers;
    }

    /**
     * @return connectionTimeout
     */
    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    /**
     * @return dataRetrievalTimeout
     */
    public int getDataRetrievalTimeout() {
        return dataRetrievalTimeout;
    }

    /**
     * @return followRedirects
     */
    public boolean isFollowRedirects() {
        return followRedirects;
    }

    /**
     * @param connectionTimeout is an Integer
     */
    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    /**
     * @param dataRetrievalTimeout is an Integer
     */
    public void setDataRetrievalTimeout(int dataRetrievalTimeout) {
        this.dataRetrievalTimeout = dataRetrievalTimeout;
    }

    /**
     * @param followRedirects is an Integer
     */
    public void setFollowRedirects(boolean followRedirects) {
        this.followRedirects = followRedirects;
    }


    /**
     * @param userAgent the User Agent to be set
     */
    public void setUserAgent(String userAgent)
    {
        headers.put("User-Agent", userAgent);
    }

    /**
     * add a key&value to header hashMap
     * @param key is a String
     * @param value is a String
     */
    public void addHeader(String key , String value)
    {
        headers.put(key,value);
    }

}
