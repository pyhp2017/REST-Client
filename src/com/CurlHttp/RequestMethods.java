package com.CurlHttp;

/**
 * Http Request Methods (Based on HomeWork)
 * @author Ahmad Foroughi
 * @version 1.0
 * NOTE : This is an Enum
 */
public enum RequestMethods
{

    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    PATCH("PATCH"),
    DELETE("DELETE");

    String method;

    RequestMethods(String method)
    {
        this.method = method;
    }

    @Override
    public String toString() {
        return this.method;
    }

}
