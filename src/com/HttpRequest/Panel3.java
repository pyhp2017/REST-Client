package com.HttpRequest;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * THis is our right Panel (third panel)
 * @author Ahmad Forouhgi
 * @version 2.0
 */
public class Panel3 extends JPanel
{
    //Fields
    private JPanel statusPanel;
    private JTabbedPane jTabbedPane;
    private JButton btnStatus;
    private JButton btnTimeStatus;
    private JButton btnData;
    private JTextArea areaRaw;
    private JEditorPane previewHTML;
    private JPanel insidePanelHeader;
    private JButton clipBtn;


    /**
     * Create a new Panel3
     */
    public Panel3()
    {
        //set layout and border
        setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(new Color(255,255,255)));
        this.setSize(425,575);
        //=========================================================================================
        statusPanel = new JPanel(new BorderLayout());
        jTabbedPane = new JTabbedPane();
        //=========================================================================================
        btnStatus = new JButton("Status Code");
        btnStatus.setEnabled(false);
        btnTimeStatus = new JButton("Time");
        btnTimeStatus.setEnabled(false);
        btnData = new JButton("Size");
        btnData.setEnabled(false);
        //=========================================================================================
        statusPanel.add(btnStatus,BorderLayout.WEST);
        statusPanel.add(btnTimeStatus,BorderLayout.CENTER);
        statusPanel.add(btnData,BorderLayout.EAST);
        //=========================================================================================
        //Message Body Tab
        areaRaw = new JTextArea();
        areaRaw.setEditable(false);
        JScrollPane scrollRaw = new JScrollPane(areaRaw);
        //=========================================================================================
        previewHTML = new JEditorPane();
        previewHTML.setContentType("text/html");
        previewHTML.setEditable(false);
        JScrollPane scrollHtml = new JScrollPane(previewHTML);
        //=========================================================================================
        ImagePanel panelImage = new ImagePanel();
        JScrollPane scrollImage = new JScrollPane(panelImage);
        //=========================================================================================
        JTabbedPane messageBodyTab = new JTabbedPane();
        messageBodyTab.addTab("RAW",scrollRaw);
        messageBodyTab.addTab("Preview HTML",scrollHtml);
        messageBodyTab.addTab("View Image" , scrollImage);
        //=========================================================================================
        //Header
        JPanel panelHeader = new JPanel(new BorderLayout());
        insidePanelHeader = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.weighty = 1;
        insidePanelHeader.add(new JPanel(), gbc);
        panelHeader.add(new JScrollPane(insidePanelHeader));
        clipBtn = new JButton("Copy to Clipboard");
        panelHeader.add(clipBtn, BorderLayout.SOUTH);
        //=========================================================================================
        jTabbedPane.addTab("Message Body",messageBodyTab);
        jTabbedPane.addTab("Header",panelHeader);
        //=========================================================================================
        add(statusPanel,BorderLayout.NORTH);
        add(jTabbedPane,BorderLayout.CENTER);
    }

    /**
     * @return statusPanel
     */
    public JPanel getStatusPanel() {
        return statusPanel;
    }

    /**
     * @return jTabbedPane
     */
    public JTabbedPane getjTabbedPane() {
        return jTabbedPane;
    }

    /**
     * @param jTabbedPane is a TabbedPane
     */
    public void setjTabbedPane(JTabbedPane jTabbedPane) {
        this.jTabbedPane = jTabbedPane;
    }

    /**
     * @param statusPanel is a Panel
     */
    public void setStatusPanel(JPanel statusPanel) {
        this.statusPanel = statusPanel;
    }

    /**
     * @return btnData
     */
    public JButton getBtnData() {
        return btnData;
    }

    /**
     * @return btnStatus
     */
    public JButton getBtnStatus() {
        return btnStatus;
    }

    /**
     * @return btnTimeStatus
     */
    public JButton getBtnTimeStatus() {
        return btnTimeStatus;
    }

    /**
     * @return previewHTML
     */
    public JEditorPane getPreviewHTML() {
        return previewHTML;
    }

    /**
     * @return areaRaw
     */
    public JTextArea getAreaRaw() {
        return areaRaw;
    }

    /**
     * add a new HeaderPanel
     * @param key is a key string (Header key)
     * @param value is a value string (Header value)
     */
    public void addHeader(String key, String value)
    {
                JPanel panel = new JPanel();
                HeaderPanel headerPanel = new HeaderPanel(key,value,true);
                headerPanel.getCheckBoxItem().setVisible(false);
                headerPanel.getLblRemoveIcon().setVisible(false);
                headerPanel.getTextKey().setEnabled(false);
                headerPanel.getTextValue().setEnabled(false);
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
     * get ClipBoard Button
     * @return clipBtn
     */
    public JButton getClipBtn() {
        return clipBtn;
    }

    /**
     * remove headers list
     */
    public void removeAllHeaders()
    {
        insidePanelHeader.removeAll();
        repaint();
        revalidate();
    }

}
