package com.HttpRequest;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import static com.CurlHttp.Run.convertStringToKeyValue;

/**
 * This is our left Panel
 * @author Ahmad Foroughi
 * @version 2.0
 */
public class Panel1  extends JPanel
{
    //List of requests panels
    private JPanel mainList;
    //list of requests details
    private ArrayList<Request> requestsList;
    //button to add new request
    private  JButton add;
    //selected request
    private RequestPanel selectedRequestPanel;

    /**
     * Create A new Panel
     */
    public Panel1()
    {
        //set panel layout
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(new Color(255,255,255)));
        //set layout for main list
        mainList = new JPanel(new GridBagLayout());
        //create list
        requestsList = new ArrayList<>();
        //load list from file
        try
        {
            FileInputStream fileInputStream = new FileInputStream("savedRequests.bin");
            ObjectInputStream ois = new ObjectInputStream(fileInputStream);
            requestsList = (ArrayList<Request>) ois.readObject();
            if (requestsList!=null)
            {
                for (Request request : requestsList)
                {
                    addNewRequest(request);
                }
            }
        }
        catch (Exception ex)
        {
            System.out.println("There is a Problem while trying to read objects: " + ex.getMessage());
        }

        //Create mainList
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.weighty = 1;
        mainList.add(new JPanel(), gbc);
        add(new JScrollPane(mainList));
        add = new JButton("Add");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                String name =  JOptionPane.showInputDialog(null,"Enter Request Name: ","Enter a Name",JOptionPane.QUESTION_MESSAGE);
                if (name==null)
                {
                    name = "untitled";
                }
                Request request = new Request("GET",name);
                requestsList.add(request);
                addNewRequest(request);
            }
        });
        add(add, BorderLayout.SOUTH);
    }

    /**
     * get requests list
     * @return requestsList
     */
    public ArrayList<Request> getRequestsList() {
        return requestsList;
    }

    /**
     * @return mainList
     */
    public JPanel getMainList() {
        return mainList;
    }

    /**
     * add new request item to list
     * @param request is a Request object
     */
    public void addNewRequest(Request request)
    {
        JPanel panel = new JPanel();
        RequestPanel requestPanel = new RequestPanel(request);
        requestPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                selectedRequestPanel = requestPanel;
                //Load request fields to panel2
                System.out.println(request.toString());
                if (request.getUrl()!=null)
                {
                    FrmMain.panel2.getUrlTextField().setText(request.getUrl());
                }
                else
                {
                    FrmMain.panel2.getUrlTextField().setText("http://");
                }
                if (request.getMethod()!=null)
                {
                    FrmMain.panel2.getComboBoxRequests().setSelectedItem(request.getMethod());
                }
                else
                {
                    FrmMain.panel2.getComboBoxRequests().setSelectedItem("GET");
                }
                if (request.getDataForm()!=null)
                {
                    FrmMain.panel2.getBodyText().setText(request.getDataForm());
                }
                else
                {
                    FrmMain.panel2.getBodyText().setText("");
                }
                if (request.getJson()!=null)
                {
                    FrmMain.panel2.getJsonText().setText(request.getJson());
                }
                else
                {
                    FrmMain.panel2.getJsonText().setText("");
                }
                if (request.getHeaders()!=null)
                {
                    //put Headers into list
                    //First Remove All previous ones
                    FrmMain.panel2.removeAllHeaders();
                    String header = request.getHeaders();
                    ArrayList<String>keyValues = convertStringToKeyValue(header);
                    try
                    {
                        for (int i =0 ; i<keyValues.size();i+=2)
                        {
                            FrmMain.panel2.addHeader(keyValues.get(i),keyValues.get(i+1),true);
                        }
                    }
                    catch (IndexOutOfBoundsException exception)
                    {
                        //Dont do anything
                    }
                }
                if (request.getQuery()!=null)
                {
                    //put Headers into list
                    FrmMain.panel2.removeAllQueries();
                    String header = request.getQuery();
                    ArrayList<String>keyValues = convertStringToKeyValue(header);
                    try
                    {
                        for (int i =0 ; i<keyValues.size();i+=2)
                        {
                            FrmMain.panel2.addQuery(keyValues.get(i),keyValues.get(i+1),true);
                        }
                    }
                    catch (IndexOutOfBoundsException exception)
                    {
                        //Dont do anything
                    }
                }
            }
        });
        panel.add(requestPanel);
        panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
        //REMOVE REQUEST FROM LIST
        requestPanel.getLabelRemoveICON().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int result = JOptionPane.showConfirmDialog(null,
                        "Are you sure?",
                        "Confirm Remove", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                {
                    mainList.remove(panel);
                    requestsList.remove(request);
                    repaint();
                    revalidate();
                }
            }
        });
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainList.add(panel, gbc, 0);
        validate();
        repaint();
    }

    /**
     * @return selectedRequestPanel
     */
    public RequestPanel getSelectedRequestPanel() {
        return selectedRequestPanel;
    }

    /**
     * @param selectedRequestPanel set selected panel to
     */
    public void setSelectedRequestPanel(RequestPanel selectedRequestPanel) {
        this.selectedRequestPanel = selectedRequestPanel;
    }
}