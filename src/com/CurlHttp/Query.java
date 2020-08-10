package com.CurlHttp;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is for Query sets
 * @author Ahmad Foroughi
 * @version 1.0
 */
public class Query
{
    //Key and Values List
    private HashMap<String,String> keyValues;

    /**
     * Create an Empty Query
     */
    public Query()
    {
        keyValues = new HashMap<>();
    }

    /**
     * @return keyValues
     */
    public HashMap<String, String> getKeyValues() {
        return keyValues;
    }

    /**
     * @param keyValues is a HashMap
     */
    public void setKeyValues(HashMap<String, String> keyValues) {
        this.keyValues = keyValues;
    }

    /**
     * Add a new Key Value
     * @param key is a String
     * @param value is a String
     */
    public void addKeyAndValue(String key , String value)
    {
        keyValues.put(key,value);
    }

    /**
     * Get number of existing parameters.
     * @return the number of parameters
     */
    public int size()
    {
        return keyValues.size();
    }

    /**
     * get string and set queries
     * @param s is a string
     */
    public void getStringToSet(String s)
    {
            ArrayList<String> keyValues = Run.convertStringToKeyValue(s);
            int i = 0;
            while (i<keyValues.size())
            {
                addKeyAndValue(keyValues.get(i),keyValues.get(i+1));
                if (keyValues.size()>=2)
                {
                    i+=2;
                }
            }

    }


}
