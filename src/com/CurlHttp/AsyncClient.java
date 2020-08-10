package com.CurlHttp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This is a class for creating  asynchronous HTTP requests
 * @author Ahmad Foroughi
 * @version 1.0
 */
public class AsyncClient extends Client
{
    private final ExecutorService threadPool;
    private long time;

    /**
     * Create a new asynchronous client
     */
    public AsyncClient()
    {
        //Calling father :)
        super();
        threadPool = Executors.newCachedThreadPool();
    }



    /**
     * Make a HTTP GET request with parameters.
     * @param url is URL
     * @param params is query (If you don't have one put NULL)
     * @param handler the response handler
     */
    @Override
    public void get(String url, Query params, ResponseHandler handler, Boolean jsonActive, Object body)
    {

        threadPool.execute(new Runnable() {
            @Override
            public void run()
            {
                //Start timer
                long startTime = System.currentTimeMillis();
                AsyncClient.super.request(url, RequestMethods.GET, params, handler,jsonActive,body);
                long endTime = System.currentTimeMillis();
                time = endTime - startTime;
            }
        });
    }

    /**
     * Make a HTTP Post request with parameters.
     * @param url is URL
     * @param params is query (If you don't have one put NULL)
     * @param handler the response handler
     */
    @Override
    public void post(String url, Query params, ResponseHandler handler, Boolean jsonActive, Object body)
    {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                AsyncClient.super.request(url, RequestMethods.POST, params, handler,jsonActive,body);
                long endTime = System.currentTimeMillis();
                time = endTime - startTime;
            }
        });

    }

    /**
     * Make a HTTP put request with parameters.
     * @param url is URL
     * @param params is query (If you don't have one put NULL)
     * @param handler the response handler
     */
    @Override
    public void put(String url, Query params, ResponseHandler handler, Boolean jsonActive, Object body)
    {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                AsyncClient.super.request(url, RequestMethods.PUT, params, handler,jsonActive,body);
                long endTime = System.currentTimeMillis();
                time = endTime - startTime;

            }
        });

    }

    /**
     * Make a HTTP patch request with parameters.
     * @param url is URL
     * @param params is query (If you don't have one put NULL)
     * @param handler the response handler
     */
    @Override
    public void patch(String url, Query params, ResponseHandler handler, Boolean jsonActive, Object body)
    {
        threadPool.execute(new Runnable() {
            @Override
            public void run()
            {

                AsyncClient.super.request(url, RequestMethods.PATCH, params, handler,jsonActive,body);

            }
        });
    }

    /**
     * Make a HTTP delete request with parameters.
     * @param url is URL
     * @param params is query (If you don't have one put NULL)
     * @param handler the response handler
     */
    @Override
    public void delete(String url, Query params, ResponseHandler handler, Boolean jsonActive, Object body)
    {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                AsyncClient.super.request(url, RequestMethods.DELETE, params, handler,jsonActive,body);
                long endTime = System.currentTimeMillis();
                time = endTime - startTime;
            }
        });

    }

    /**
     * get Time
     * @return time
     */
    public long getTime() {
        return time;
    }
}
