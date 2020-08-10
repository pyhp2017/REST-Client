package com.HttpRequest;

import java.io.Serializable;

/**
 * This is a Request
 */
public class Request implements Serializable
{
    //Fields
    private String method;
    private String name;
    private String url;
    private String query;
    private String json;
    private String headers;
    private String dataForm;
    private String autoRedirect; //should be remove



    /**
     * Create a new Request
     * @param method is Method Name
     * @param name is Request Name
     */
    public Request(String method,String name)
    {
        setMethod(method);
        setName(name);
        setUrl("http://");
        setQuery("");
        setJson("");
        setHeaders("");
        setDataForm("");
        setAutoRedirect("");
    }

    /**
     * @return method
     */
    public String getMethod() {
        return method;
    }

    /**
     * @param method is a String
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * @return Request Name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name is a String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * set a url
     * @param url is a string
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * get url
     * @return url
     */
    public String getUrl() {
        return url;
    }


    /**
     * get data
     * @return dataForm
     */
    public String getDataForm() {
        return dataForm;
    }

    /**
     * get headers
     * @return headers
     */
    public String getHeaders() {
        return headers;
    }

    /**
     * get json
     * @return json
     */
    public String getJson() {
        return json;
    }

    /**
     * get query
     * @return query
     */
    public String getQuery() {
        return query;
    }

    /**
     * set auto redirect
     * @param autoRedirect is a string
     * @deprecated
     */
    public void setAutoRedirect(String autoRedirect) {
        this.autoRedirect = autoRedirect;
    }

    /**
     * set data
     * @param dataForm is a String
     */
    public void setDataForm(String dataForm) {
        this.dataForm = dataForm;
    }

    /**
     * set Header
     * @param headers is a String
     */
    public void setHeaders(String headers) {
        this.headers = headers;
    }

    /**
     * set json
     * @param json is a String
     */
    public void setJson(String json) {
        this.json = json;
    }

    /**
     * set query
     * @param query is a String
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * Get Request
     * @return String
     */
    @Override
    public String toString() {
        return "Request{" +
                "method='" + method + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", query='" + query + '\'' +
                ", json='" + json + '\'' +
                ", headers='" + headers + '\'' +
                ", dataForm='" + dataForm + '\'' +
                ", autoRedirect='" + autoRedirect + '\'' +
                '}';
    }
}
