package com.CurlHttp;

import java.util.List;
import java.util.*;
import com.beust.jcommander.*;
import com.sscanf.Sscanf;

//{PROGRAM NAME} [URL  **necessary**] [-i Show Response Optional] [-M Method Name - Default GET Optional]
//[-H headers Optional] [-f follow Redirect Active Optional (Default false)]
//[-O save response body + name || -O (Name: output_{CurrentDateAndTime}) ]
//[-S save Request with all data (NOTE: First save than do it)]
//[-D form data Optional] [-J json Optional] [-U upload file]

/**
 * Run Code via arguments
 * @author Ahmad Foroughi
 * @version 1.0
 */
public class RunArgs
{
    @Parameter(names = "-url", description = "URL for sending request.")
    public String url;

    @Parameter(names = "-i", description = "Show Headers")
    public boolean showHeaders;

    @Parameter(names = "-M", description = "set Method Name")
    public String methodName;

    @Parameter(names = "-H", description = "set Header")
    public String header;

    @Parameter(names = "-Q", description = "set Query")
    public String query;

    @Parameter(names = "-J", description = "set Json")
    public String json;

    @Parameter(names = "-D", description = "set FormData")
    public String formData;

    @Parameter(names = "-f", description = "auto Redirect")
    public boolean autoRedirect;

    @Parameter(names = "-O", description = "Save Response")
    public String fileOutputName;

    @Parameter(names = "-S", description = "Save Request")
    public boolean saveRequest;

    @Parameter(names = "--help", description = "Help")
    public boolean help;

    @Parameter(names = "--list", description = "list of Requests")
    public boolean showList;

    @Parameter(names = "--fire", description = "load Saved Request")
    public List<Integer> toLoad = new ArrayList<>();


    public static void main(String ... argv)
    {
        try
        {
            RunArgs main = new RunArgs();
            JCommander commander = new JCommander();
            commander.addObject(main);
            commander.parse(argv);
            Run run = new Run();
            if (main.help) {
                System.out.println("Help:\n" +
                        "-url [url] - default: http://google.com \n" +
                        " -i [show headers] \n" +
                        " -M [Method Name]" +
                        " -H [Headers list example : \"key1:value1;key2:value2\" \n" +
                        " -Q [Queries list example : \"key1:value1;key2:value2\" \n" +
                        " -J [JSON] -> MUST‌ be in json format example: {\"FirstName\":\"value\",\"LastName\":\"value\"} REMEMBER: escape (\\n) is important for JSON\n" +
                        " -D [FormURLEncoded] -> Must be in formData format example: firstName=ahmad&lastName=foroughi \n" +
                        " -f [auto redirect] \n" +
                        " -O [fileName or address] , -O none -> (Create a Random Name) \n" +
                        " -S [save requests options] \n" +
                        " --list [show requests] \n" +
                        " --fire [requests id] \n"+
                        "Coded by Ahmad Foroughi\n\n");
            }
            if (main.toLoad!=null && main.toLoad.size()>0)
            {
                //Load
                try
                {
                    for (Integer num: main.toLoad)
                    {
                        System.out.println(num);
                        int line = num - 1;
                        String[] array = Run.getSettingsArray();
                        String request = array[line];
                        String format = "%i.URL:%s | Method Name: %s |  Query: %s| headers: %s | JSON: %s |  DataForm: %s | autoRedirect: %s | ";
                        Object[] vars = Sscanf.scan(request, format, (int) 1, "2", "3", "4", "6", "7", "8", "9");

                        String givenUrl = (String) vars[1];
                        String givenMethodName = (String) vars[2];
                        String givenQuery = (String) vars[3];
                        String givenHeader = (String) vars[4];
                        String givenJson = (String) vars[5];
                        String givenFormData = (String) vars[6];
                        String givenAuto = (String) vars[7];


                        if (givenUrl.equals("null")) {
                            main.url = null;
                        } else {
                            main.url = givenUrl;
                        }
                        if (givenMethodName.equals("null")) {
                            main.methodName = "get";
                        } else {
                            main.methodName = givenMethodName;
                        }
                        if (givenQuery.equals("null")) {
                            main.query = null;
                        } else {
                            main.query = givenQuery;
                        }
                        if (givenHeader.equals("null")) {
                            main.header = null;
                        } else {
                             main.header = givenHeader;
                        }
                        if (givenJson.equals("null")) {
                            main.json = null;
                        } else {
                            main.json = givenJson;
                        }
                        if (givenFormData.equals("null")) {
                            main.formData = null;
                        } else {
                            main.formData = givenFormData;
                        }
                        if (givenAuto.equals("false")) {
                            main.autoRedirect = false;
                        } else {
                            main.autoRedirect = true;
                        }
                        run.run(main.url,main.showHeaders,main.methodName,main.header,main.query,main.json,main.formData,main.autoRedirect,main.fileOutputName,main.saveRequest,main.help,main.showList,main.toLoad);

                    }
                }
                catch (Exception ex)
                {
                    System.out.println("Error while trying to load requests: " + ex.getMessage());
                }
            }
            else
            {
                run.run(main.url,main.showHeaders,main.methodName,main.header,main.query,main.json,main.formData,main.autoRedirect,main.fileOutputName,main.saveRequest,main.help,main.showList,main.toLoad);
            }

        }
        catch (Exception ex)
        {
            System.out.println("THERE is Something wrong with your args: " + ex.getMessage());
        }

    }





}
