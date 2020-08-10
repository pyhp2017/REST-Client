package com.CurlHttp;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

import com.HttpRequest.FrmMain;
import org.json.JSONObject;

/**
 * This class is for Running app
 * @author Ahmad Foroughi
 * @version 1.0
 */
public class Run
{
    //Fields
    AsyncClient client = new AsyncClient();
    Query params = new Query();
    String dataToSend = null;
    String fileName = null;
    boolean jsonActive = false;

    /**
     * Set Options and Send Request
     * @param url
     * @param showHeaders
     * @param methodName
     * @param header
     * @param query
     * @param json
     * @param formData
     * @param autoRedirect
     * @param fileOutputName
     * @param saveRequest
     * @param help
     * @param showList
     * @param toLoad
     */
    public void run(String url,boolean showHeaders,String methodName,String header,String query,String json,String formData,boolean autoRedirect,String fileOutputName,boolean saveRequest,boolean help,boolean showList,List<Integer> toLoad)
    {
        if (showList) {
            try {
                FileReader reader = new FileReader("SAVE.txt");
                int character;
                while ((character = reader.read()) != -1) {
                    System.out.print((char) character);
                }
                reader.close();
            } catch (Exception e) {
                System.out.println("ERROR");
            }
        }


        if (header != null) {
            Run.setHeader(header, client);
        }
        if (query != null) {
            params.getStringToSet(query);
        }
        if (autoRedirect) {
            client.setFollowRedirects(true);
        }
        if (url == null) {
            System.exit(-1);
        }
        if (json != null) {
            dataToSend = json;
            jsonActive = true;
        } else if (formData != null) {
            dataToSend = formData;
        }
        if (fileOutputName != null) {
            fileName = fileOutputName;
        }


        ResponseHandler responseHandler = new ResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, byte[] content) {
                System.out.println("Status Code: " + statusCode);
                if (showHeaders) {
                    for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                        System.out.print("Key: " + entry.getKey());
                        for (String iterate : entry.getValue()) {
                            System.out.print(" ------ Value: " + iterate);
                        }
                        System.out.print("\n");
                    }
                }
                if (fileOutputName != null) {
                    if (fileOutputName.equals("none")) {
                        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");
                        Date date = new Date();
                        fileName = "output_(" + formatter.format(date) + ")";
                    }
                    Run.writeIntoFile(content, fileName);
                }
            }

            @Override
            public void onFailure(int statusCode, Map<String, List<String>> headers, byte[] content) {
                System.out.println("Status Code: " + statusCode);
                if (showHeaders) {
                    for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                        System.out.print("Key: " + entry.getKey());
                        for (String iterate : entry.getValue()) {
                            System.out.print(" ------ Value: " + iterate);
                        }
                        System.out.print("\n");
                    }
                }
                if (fileOutputName != null) {
                    if (fileOutputName.equals("none")) {
                        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");
                        Date date = new Date();
                        fileName = "output_(" + formatter.format(date) + ")";
                    }
                    Run.writeIntoFile(content, fileName);
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("THERE is something wrong with your url or your internet connection: " + throwable.getMessage());
            }
        };

        if (methodName == null) {
            methodName = "get";
        }
        switch (methodName) {
            case "post":
                //Do
                client.post(url, params, responseHandler, jsonActive, dataToSend);
                break;

            case "put":
                //Do
                client.put(url, params, responseHandler, jsonActive, dataToSend);
                break;

            case "delete":
                //Do
                client.delete(url, params, responseHandler, jsonActive, dataToSend);
                break;

            case "get":
                client.get(url, params, responseHandler, jsonActive, dataToSend);
                break;
        }

        if (saveRequest) {
            String followRedirect = "false";
            if (autoRedirect) {
                followRedirect = "true";
            }
            Run.saveRequestSettings(url, methodName, header, query, formData, json, followRedirect);
        }

    }

    /**
     * Print Json
     * @param content
     */
    public static void printBeauty(byte[] content)
    {
        String s = new String(content, StandardCharsets.UTF_8);
        JSONObject obj = new JSONObject(s);
        System.out.println(obj.toString(4));
    }


    /**
     * Write into File
     * @param content
     * @param address
     */
    public static void writeIntoFile(byte[] content, String address)
    {
        try
        {
            Path path = Paths.get(address);
            Files.write(path,content);
            System.out.println("File Successfully Saved");
        }
        catch (Exception ex)
        {
            System.out.println("There is something wrong while you're trying to save content");
        }
    }


    /**
     * Convert String to KeyValue ArrayList
     * @param str
     * @return arrayList
     */
    public static ArrayList<String> convertStringToKeyValue(String str)
    {
        char[] arrayOfString = str.toCharArray();
        StringBuffer buffer = new StringBuffer();
        ArrayList<String> keyValues = new ArrayList<>();

        int length = arrayOfString.length;
        int count = 0;
        for (char move : arrayOfString)
        {
            count++;
            if (move==':'||move==';'|| count==length)
            {
                if (count==length)
                {
                    buffer.append(move);
                }
                keyValues.add(buffer.toString());
                buffer.delete(0,buffer.length());
                continue;
            }
            buffer.append(move);
        }

        return keyValues;
    }


    public static void bufferOutFormData(HashMap<String, String> body, String boundary, BufferedOutputStream bufferedOutputStream) throws IOException {
        for (String key : body.keySet())
        {
            bufferedOutputStream.write(("--" + boundary + "\r\n").getBytes());
            bufferedOutputStream.write(("Content-Disposition: form-data; name=\"" + key + "\"\r\n\r\n").getBytes());
            bufferedOutputStream.write((body.get(key) + "\r\n").getBytes());
        }
        bufferedOutputStream.write(("--" + boundary + "--\r\n").getBytes());
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }



    /**
     *
     * @param hashMap
     * @param formData
     */
    public static void addFormDataStringToHashMap(HashMap<String,String> hashMap,String formData)
    {
        char[] arrayOfString = formData.toCharArray();
        StringBuffer buffer = new StringBuffer();
        int length = arrayOfString.length;
        int count = 0;
        ArrayList<String> keyValues = new ArrayList<>();
        for (char move : arrayOfString)
        {
            count++;
            if (move=='='||move=='&'|| count==length)
            {
                if (count==length)
                {
                    buffer.append(move);
                }
                keyValues.add(buffer.toString());
                buffer.delete(0,buffer.length());
                continue;
            }
            buffer.append(move);
        }

        try
        {
            for (int i=0 ; i<keyValues.size();i+=2)
            {
                hashMap.put(keyValues.get(i),keyValues.get(i+1));
            }
        }
        catch (IndexOutOfBoundsException ex)
        {
            //Do Nothing
        }

    }


    /**
     * Set Heaer for Client
     * @param s
     * @param client
     */
    public static void setHeader(String s, Client client)
    {
        String header = s;
        ArrayList<String> keyValues = convertStringToKeyValue(header);
        if (header!=null)
        {
            for (int i =0 ; i<keyValues.size();i+=2)
            {
                client.addHeader(keyValues.get(i),keyValues.get(i+1));
            }
        }
    }

    /**
     * Save Request Settings
     * @param url
     * @param methodName
     * @param headers
     * @param query
     * @param data
     * @param json
     * @param follow
     */
    public static void saveRequestSettings(String url,String methodName,String headers, String query,String data,String json,String follow)
    {
        try {
            String filename = "SAVE.txt";
            int numberOfElements = getNumberOfLines(filename);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename, true));
            FileWriter fw = new FileWriter(filename, true); //the true will append the new data
            if (headers!=null)
            {
                headers = headers.replaceAll("\\s+","");
            }
            if (query!=null)
            {
                query = query.replaceAll("\\s+","");
            }
            if (data!=null)
            {
                data = data.replaceAll("\\s+","");
            }
            if (json!=null)
            {
                json = json.replaceAll("\\s+","");
            }
            fw.write((numberOfElements+1)+"."+"URL:"+url+" | "+ "Method Name: " + methodName + " | " + " Query: " + query + "|" + " headers: " + headers + " | " + "JSON: " + json + " | " + " DataForm: "+ data +" | " + "autoRedirect: " + follow + " | " +"\n");//appends the string to the file
            fw.close();
        }
        catch (Exception exception)
        {
            //DO NOTHING
            exception.printStackTrace();
            System.out.println("SAVE ERROR: " + exception.getMessage());
        }
    }

    /**
     * Load all Text
     * @return String
     */
    public static String loadFromFile()
    {
        String result = null;
        StringBuffer st = new StringBuffer();
        try {
            FileReader reader = new FileReader("SAVE.txt");
            int character;
            while ((character = reader.read()) != -1) {
                st.append((char)character);
            }
            result = st.toString();
            reader.close();
        } catch (Exception e) {
            System.out.println("ERROR");
        }

        return result;
    }

    /**
     * Get number of lines in file using regex
     * @param fileName is file name
     * @return number of lines (\n)
     * @throws Exception is any IO exception
     */
    public static int getNumberOfLines(String fileName) throws Exception
    {
        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        fis.read(bytes);
        String data = new String(bytes);
        String[] strings = data.split("\n");
        return strings.length;
    }

    /**
     * get settings array
     * @return String[]
     * @throws Exception is any IO exception
     */
    public static String[] getSettingsArray() throws Exception
    {
        File file = new File("SAVE.txt");
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        fis.read(bytes);
        String data = new String(bytes);
        String[] strings = data.split("\n");
        return strings;
    }

}
