package com.HttpRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

/**
 * This is a Request Panel
 * @author Ahmad Foroughi
 * @version 1.0
 */
public class RequestPanel extends JPanel implements Serializable
{
    //Fields
    private Request request;
    private JLabel labelMethodName;
    private JLabel labelRequestName;
    private JLabel labelRemoveICON;

    /**
     * Create a new Request Panel
     * @param request is a Request
     */
    public RequestPanel(Request request)
    {
        initComponents(request);
    }


    /**
     * initialize components
     * @param request is a Request
     */
    private void initComponents(Request request) {

        this.request = request;
        labelMethodName = new javax.swing.JLabel();
        labelRequestName = new javax.swing.JLabel();
        labelRemoveICON = new javax.swing.JLabel();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));


        labelMethodName = new JLabel();
        labelRequestName = new JLabel();
        labelRemoveICON = new JLabel();
        labelMethodName.setText(request.getMethod());
        labelRequestName.setText(request.getName());
        labelRemoveICON.setIcon(new ImageIcon("res/Pictures/rubbish-can.png"));


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelMethodName)
                                .addGap(18, 18, 18)
                                .addComponent(labelRequestName, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labelRemoveICON)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelMethodName)
                                        .addComponent(labelRequestName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(labelRemoveICON)
                                                ))
                                .addContainerGap())
        );

    }

    /**
     * @return labelMethodName
     */
    public JLabel getLabelMethodName() {
        return labelMethodName;
    }

    /**
     * @return labelRemoveICON
     */
    public JLabel getLabelRemoveICON() {
        return labelRemoveICON;
    }

    /**
     * @return labelRequestName
     */
    public JLabel getLabelRequestName() {
        return labelRequestName;
    }

    /**
     * @param labelMethodName is a JLabel
     */
    public void setLabelMethodName(JLabel labelMethodName) {
        this.labelMethodName = labelMethodName;
    }

    /**
     * @param labelRemoveICON is a Remove ICon
     */
    public void setLabelRemoveICON(JLabel labelRemoveICON) {
        this.labelRemoveICON = labelRemoveICON;
    }

    /**
     * @param labelRequestName is a Request Name (JLabel)
     */
    public void setLabelRequestName(JLabel labelRequestName) {
        this.labelRequestName = labelRequestName;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Request getRequest() {
        return request;
    }
}
