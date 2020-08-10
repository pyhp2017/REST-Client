package com.HttpRequest;

import com.CurlHttp.AsyncClient;
import com.CurlHttp.ResponseHandler;
import com.CurlHttp.Run;
import com.CurlHttp.RunArgs;
import org.json.JSONObject;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This is our 2nd panel
 * @author Ahmad Foroughi
 * @version 2.0
 */
public class Panel2 extends JPanel
{
    //All Fields some of them are bull shit
    private JPanel urlPanel;
    private JTabbedPane tabbedPane;
    private JButton save;
    private JComboBox<String> comboBoxRequests;
    private JTextField urlTextField;
    private JButton buttonSend;
    private JPanel panelHeader;
    private JPanel insidePanelHeader;
    private JPanel panelQuery;
    private JPanel insidePanelQuery;
    private JPanel panelBody;
    private JTabbedPane tabbedPaneEdit;
    private JTextArea bodyText;
    private JScrollPane sp;
    private JPanel panelJson;
    private JTextArea jsonText;
    private JScrollPane scrollJson;
    private ArrayList<HeaderPanel> headerPanels;
    private ArrayList<HeaderPanel> queryPanels;
    private AsyncClient asyncClient;
    private boolean jsonWrote;
    private boolean redirectActive;

    public Panel2()
    {
        //set Layout and border
        setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(new Color(255,255,255)));
        //==============================================================================================
        headerPanels = new ArrayList<>();
        queryPanels = new ArrayList<>();
        asyncClient = new AsyncClient();
        //==============================================================================================
        urlPanel = new JPanel();
        urlPanel.setLayout(new BoxLayout(urlPanel, BoxLayout.X_AXIS));
        String[] list = {"GET" , "POST" , "PUT" ,"DELETE"};
        comboBoxRequests = new JComboBox<>(list);
        urlTextField = new JTextField();
        buttonSend = new JButton("SEND");
        //==============================================================================================


        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //Remove HeaderPanel 3
                FrmMain.panel3.removeAllHeaders();
                //FollowRedirect
                asyncClient.setFollowRedirects(redirectActive);
                //Start timer
                long startTime = System.currentTimeMillis();
                //params
                com.CurlHttp.Query params = new com.CurlHttp.Query();
                jsonWrote = false;
                //Query
                if(queryPanels!=null)
                {
                    for (HeaderPanel move : queryPanels)
                    {
                        if (move.getCheckBoxItem().isSelected())
                        {
                            params.addKeyAndValue(move.getTextKey().getText(),move.getTextValue().getText());
                        }
                    }
                }
                //Header
                String header = null;
                if (headerPanels!=null)
                {
                    for (HeaderPanel move: headerPanels)
                    {
                        if (move.getCheckBoxItem().isSelected())
                        {
                            asyncClient.addHeader(move.getTextKey().getText(),move.getTextValue().getText());
                        }
                    }
                }
                //body
                String bodyToSend = null;
                if (!jsonText.getText().isEmpty())
                {
                    jsonWrote = true;
                    bodyText.setText("");
                    bodyToSend = jsonText.getText();
                    System.out.println("JSON ACTIVED");
                }
                else
                {
                    jsonWrote = false;
                    jsonText.setText("");
                    bodyToSend = bodyText.getText();
                    System.out.println("Form Data");
                }
                ResponseHandler responseHandler = new ResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Map<String, List<String>> headers, byte[] content) {
                        FrmMain.panel3.getBtnStatus().setText("Status: " + statusCode);
                        //Size
                        FrmMain.panel3.getBtnData().setText("Size:"+content.length/1000+" kb");
                        //Set Body raw
                        String s = new String(content, StandardCharsets.UTF_8);
                        try
                        {
                            JSONObject obj = new JSONObject(s);
                            FrmMain.panel3.getAreaRaw().setText(obj.toString(4));
                        }
                        catch (Exception ex)
                        {
                            FrmMain.panel3.getAreaRaw().setText(s);
                        }
                        //Set Preview
                        String contentType = "text/html";
                        try {
                            contentType = headers.get("Content-Type").get(0);
                        }
                        catch (Exception ex)
                        {
                            contentType = "text/html";
                        }

                        FrmMain.panel3.getPreviewHTML().setContentType("text/html");
                        //Set Preview
                        if (contentType.contains("image"))
                        {
                            Run.writeIntoFile(content,"temp.png");
                            FrmMain.panel3.getPreviewHTML().setText("<html><h1>Nothing to Show</h1></html>");
                        }
                        else
                        {
                            FrmMain.panel3.getPreviewHTML().setText(s);
                        }

                        //Create Headers in panel
                        StringBuffer bufferClipBoard = new StringBuffer();
                        for (Map.Entry<String, List<String>> entry : headers.entrySet())
                        {
                            for (String iterate : entry.getValue())
                            {
                                FrmMain.panel3.addHeader(entry.getKey(),iterate);
                                bufferClipBoard.append("key:"+entry.getKey()+"\t"+"value:"+iterate+"\n");
                            }
                        }

                        //ClipBoard
                        FrmMain.panel3.getClipBtn().addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent actionEvent) {
                                StringSelection selection = new StringSelection(bufferClipBoard.toString());
                                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                                clipboard.setContents(selection, selection);
                            }
                        });

                    }

                    @Override
                    public void onFailure(int statusCode, Map<String, List<String>> headers, byte[] content) {
                        FrmMain.panel3.getBtnStatus().setText("Status: " + statusCode);
                        //Size
                        FrmMain.panel3.getBtnData().setText("Size:"+content.length/1000+" kb");
                        //Set Body raw
                        String s = new String(content, StandardCharsets.UTF_8);
                        try
                        {
                            JSONObject obj = new JSONObject(s);
                            FrmMain.panel3.getAreaRaw().setText(obj.toString(4));
                        }
                        catch (Exception ex)
                        {
                            FrmMain.panel3.getAreaRaw().setText(s);
                        }

                        //Set Preview
                        FrmMain.panel3.getPreviewHTML().setContentType("text/html");
                        //Set Preview
                        FrmMain.panel3.getPreviewHTML().setText(s);

                        //Create Headers in panel
                        StringBuffer bufferClipBoard = new StringBuffer();
                        for (Map.Entry<String, List<String>> entry : headers.entrySet())
                        {
                            for (String iterate : entry.getValue())
                            {
                                FrmMain.panel3.addHeader(entry.getKey(),iterate);
                                bufferClipBoard.append("key:"+entry.getKey()+"\t"+"value:"+iterate+"\n");
                            }
                        }

                        //ClipBoard
                        FrmMain.panel3.getClipBtn().addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent actionEvent) {
                                StringSelection selection = new StringSelection(bufferClipBoard.toString());
                                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                                clipboard.setContents(selection, selection);
                            }
                        });

                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        FrmMain.panel3.getBtnStatus().setText("Error");
                    }
                };


                //Send data via CurlHttp
                if(comboBoxRequests.getSelectedItem().equals("GET"))
                {
                    if(!urlTextField.getText().isEmpty())
                    {
                        asyncClient.get(urlTextField.getText(),params,responseHandler,jsonWrote,bodyToSend);
                    }

                }
                if(comboBoxRequests.getSelectedItem().equals("POST"))
                {
                    if(!urlTextField.getText().isEmpty())
                    {
                        asyncClient.post(urlTextField.getText(),params,responseHandler,jsonWrote,bodyToSend);
                    }

                }
                if(comboBoxRequests.getSelectedItem().equals("PUT"))
                {
                    if(!urlTextField.getText().isEmpty())
                    {
                        asyncClient.put(urlTextField.getText(),params,responseHandler,jsonWrote,bodyToSend);
                    }

                }
                if(comboBoxRequests.getSelectedItem().equals("DELETE"))
                {
                    if(!urlTextField.getText().isEmpty())
                    {
                        asyncClient.delete(urlTextField.getText(),params,responseHandler,jsonWrote,bodyToSend);
                    }
                }
                long endTime = System.currentTimeMillis();
                FrmMain.panel3.getBtnTimeStatus().setText("Time:"+(asyncClient.getTime())+" ms");
                FrmMain.panel3.repaint();
                FrmMain.panel3.revalidate();
            }
        });


        //==============================================================================================
        //url panel
        urlPanel.add(comboBoxRequests);
        urlPanel.add(urlTextField);
        urlPanel.add(buttonSend);
        //==============================================================================================
        panelHeader = new JPanel(new BorderLayout());
        insidePanelHeader = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.weighty = 1;
        insidePanelHeader.add(new JPanel(), gbc);
        panelHeader.add(new JScrollPane(insidePanelHeader));
        JButton add = new JButton("Add Header");
        //==============================================================================================
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addHeader("key","value",true);
            }
        });
        panelHeader.add(add, BorderLayout.SOUTH);
        //==============================================================================================
        // panelQuery
        panelQuery = new JPanel(new BorderLayout());
        insidePanelQuery = new JPanel(new GridBagLayout());
        GridBagConstraints gbcQuery = new GridBagConstraints();
        gbcQuery.gridwidth = GridBagConstraints.REMAINDER;
        gbcQuery.weightx = 1;
        gbcQuery.weighty = 1;
        insidePanelQuery.add(new JPanel(),gbcQuery);
        panelQuery.add(new JScrollPane(insidePanelQuery));
        //==============================================================================================
        JButton addQuery = new JButton("Add Query param");
        addQuery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addQuery("key","value",true);
            }
        });
        panelQuery.add(addQuery, BorderLayout.SOUTH);
        //==============================================================================================
        tabbedPane = new JTabbedPane();
        tabbedPaneEdit = new JTabbedPane();
        //==============================================================================================
        panelBody = new JPanel(new BorderLayout());
        panelJson = new JPanel(new BorderLayout());
        bodyText = new JTextArea();
        jsonText = new JTextArea();
        sp = new JScrollPane(bodyText);
        scrollJson = new JScrollPane(jsonText);
        panelBody.add(sp);
        panelJson.add(scrollJson);
        //==============================================================================================
        tabbedPaneEdit.addTab("Form Data",panelBody);
        tabbedPaneEdit.addTab("JSON",panelJson);
        //==============================================================================================
        tabbedPane.addTab("Body",tabbedPaneEdit);
        tabbedPane.addTab("Header",panelHeader);
        tabbedPane.addTab("Query",panelQuery);
        //==============================================================================================
        save = new JButton("SAVE",new ImageIcon("res/Pictures/listIcon.png"));
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                saveButtonAction();
            }
        });
        //==============================================================================================
        add(urlPanel,BorderLayout.NORTH);
        add(tabbedPane,BorderLayout.CENTER);
        add(save,BorderLayout.SOUTH);
        //==============================================================================================
        this.setSize(427,575);
    }

    /**
     * Get comboBox (used in panel1)
     * @return comboBoxRequests
     */
    public JComboBox<String> getComboBoxRequests() {
        return comboBoxRequests;
    }

    /**
     * get Form Data text (used in panel1)
     * @return bodyText
     */
    public JTextArea getBodyText() {
        return bodyText;
    }

    /**
     * get url text field
     * @return urlTextField
     */
    public JTextField getUrlTextField() {
        return urlTextField;
    }

    /**
     * get json text (used in panel1)
     * @return
     */
    public JTextArea getJsonText() {
        return jsonText;
    }


    /**
     * add a new Header
     * @param key is a String
     * @param value is a String
     * @param active is a boolean (true or false)
     */
    public void addHeader(String key, String value, boolean active)
    {
                JPanel panel = new JPanel();
                HeaderPanel headerPanel = new HeaderPanel(key,value,active);
                headerPanels.add(headerPanel);
                headerPanel.getLblRemoveIcon().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        insidePanelHeader.remove(panel);
                        headerPanels.remove(headerPanel);
                        repaint();
                        revalidate();
                    }
                });
                panel.add(headerPanel);
                panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.weightx = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                insidePanelHeader.add(panel, gbc, 0);
                validate();
                repaint();
    }

    /**
     * add a new Header
     * @param key is a String
     * @param value is a String
     * @param active is a boolean (true or false)
     */
    public void addQuery(String key, String value, boolean active)
    {
        JPanel panel = new JPanel();
        HeaderPanel queryPanel = new HeaderPanel(key,value,active); //Same as Header so we don't need to do anything
        queryPanels.add(queryPanel);
        queryPanel.getLblRemoveIcon().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                insidePanelQuery.remove(panel);
                queryPanels.remove(queryPanel);
                repaint();
                revalidate();
            }
        });
        panel.add(queryPanel);
        panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        insidePanelQuery.add(panel, gbc, 0);
        validate();
        repaint();
    }

    /**
     * Save button Click action
     */
    public void saveButtonAction()
    {
        //Save Request fields into panel1
        FrmMain.panel1.getSelectedRequestPanel().getRequest().setUrl(urlTextField.getText());
        FrmMain.panel1.getSelectedRequestPanel().getRequest().setMethod((String)comboBoxRequests.getSelectedItem());
        FrmMain.panel1.getSelectedRequestPanel().getLabelMethodName().setText((String)comboBoxRequests.getSelectedItem());
        FrmMain.panel1.getSelectedRequestPanel().getRequest().setDataForm(bodyText.getText());
        FrmMain.panel1.getSelectedRequestPanel().getRequest().setJson(jsonText.getText());
        //Save Headers
        if (headerPanels!=null)
        {
            StringBuffer buffer = new StringBuffer();
            int i = 0;
            int length = headerPanels.size();
            for (HeaderPanel move: headerPanels)
            {
                if (move.getCheckBoxItem().isSelected())
                {
                    buffer.append(move.getTextKey().getText());
                    buffer.append(":");
                    buffer.append(move.getTextValue().getText());
                    if (i!=length-1)
                    {
                        buffer.append(";");
                    }
                    i++;
                }
            }
            FrmMain.panel1.getSelectedRequestPanel().getRequest().setHeaders(buffer.toString());
        }
        //Set Queries
        if (queryPanels!=null)
        {
            StringBuffer buffer = new StringBuffer();
            int i = 0;
            int length = queryPanels.size();
            for(HeaderPanel move: queryPanels)
            {
                if (move.getCheckBoxItem().isSelected())
                {
                    buffer.append(move.getTextKey().getText());
                    buffer.append(":");
                    buffer.append(move.getTextValue().getText());
                    if (i!=length-1)
                    {
                        buffer.append(";");
                    }
                    i++;
                }
            }
            FrmMain.panel1.getSelectedRequestPanel().getRequest().setQuery(buffer.toString());
        }
    }


    /**
     * Remove all Headers
     */
    public void removeAllHeaders()
    {
        insidePanelHeader.removeAll();
        headerPanels.clear();
        repaint();
        revalidate();
    }

    /**
     * Remove all queries
     */
    public void removeAllQueries()
    {
        insidePanelQuery.removeAll();
        queryPanels.clear();
        repaint();
        revalidate();
    }

    /**
     * check if given address is a url (starts with a http)
     * @param url is a String
     * @return true or false
     */
    private boolean isURL(String url) {
        return url.toLowerCase().startsWith("file") ||
                url.toLowerCase().startsWith("http");
    }

    /**
     * get Auto Redirect status
     * @return true or false
     */
    public boolean isRedirectActive() {
        return redirectActive;
    }

    /**
     * set auto redirect status
     * @param redirectActive is a boolean
     */
    public void setRedirectActive(boolean redirectActive) {
        this.redirectActive = redirectActive;
    }
}
