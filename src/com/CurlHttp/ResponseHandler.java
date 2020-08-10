package com.CurlHttp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

/**
 * This is a Response Handler
 * This class is meant to be used as an anonymous inner class
 * @author Ahmad Foroughi
 * @version 1.0
 */
public abstract class ResponseHandler
{
    // Size of the buffer when reading data from output stream
    private static final int BUFFER_SIZE = 4096;

    /**
     * @param httpURLConnection is a HttpURLConnection
     */
    public void onStart(HttpURLConnection httpURLConnection)
    {
    }

    /**
     * @param httpURLConnection is a HttpURLConnection
     */
    public void onFinish(HttpURLConnection httpURLConnection)
    {
    }


    /**
     * Called when the request was successful and contains response information. This method is meant to be overridden in an anonymous inner class.
     * @param statusCode is an Integer
     * @param headers is a List of Headers
     * @param content is array of contents bytes
     */
    public abstract void onSuccess(int statusCode, Map<String, List<String>> headers, byte[] content);

    /**
     * Called when the request failed and the server issued an error code. This method is meant to be overridden in an anonymous inner class.
     * @param statusCode is an Integer
     * @param headers is a List of Headers
     * @param content is array of contents bytes
     */
    public abstract void onFailure(int statusCode, Map<String, List<String>> headers, byte[] content);

    /**
     * Any Problem in connection will be handled by this
     * Called when an irrecoverable exception occurred while processing the response.
     * @param throwable the {@link Throwable} that occurred
     */
    public abstract void onFailure(Throwable throwable);

    /**
     * Called every time the buffer fills while reading the response content. Best used when downloading large files or reading a lot of content.
     * @param bytesReceived is a long
     * @param totalBytes is a long
     */
    public void onProgressChanged(long bytesReceived, long totalBytes)
    {
        // Default, do nothing.
    }

    /**
     * @param inputStream is an InputStream
     * @param length is a long
     * @return a byte array representing the content body
     * @throws IOException if an exception occurs while reading the content body
     */
    private byte[] readFrom(InputStream inputStream, long length) throws IOException
    {
        if (inputStream == null)
        {
            return new byte[0];
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer, 0, buffer.length)) != -1)
        {
            os.write(buffer, 0, bytesRead);
            onProgressChanged(bytesRead, length);
        }
        os.flush();
        os.close();
        return os.toByteArray();
    }

    /**
     * Processes the response from the HTTP request and makes the appropriate callbacks.
     * @param connection is a HttpURLConnection
     */
    public void processResponse(HttpURLConnection connection)
    {
        try
        {
            // Response
            int responseCode = connection.getResponseCode();
            long contentLength = connection.getContentLength();
            Map<String, List<String>> responseHeaders = connection.getHeaderFields();

            // 'Successful' response codes will be in interval [200,300)
            if (responseCode >= 200 && responseCode < 300) {
                byte[] responseContent = readFrom(connection.getInputStream(), contentLength);
                onSuccess(responseCode, responseHeaders, responseContent);
            } else {
                byte[] responseContent = readFrom(connection.getErrorStream(), contentLength);
                onFailure(responseCode, responseHeaders, responseContent);
            }
        } catch (IOException e) {
            onFailure(e);
        }
    }


}
