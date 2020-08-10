package com.HttpRequest;

import javax.swing.*;
import java.awt.*;

/**
 * This is a Header Panel
 * @author Ahmad Foroughi
 * @version 1.0
 */
public class HeaderPanel extends JPanel
{
    //Fields
    private JLabel lblIcon;
    private JTextField textKey;
    private JTextField textValue;
    private JCheckBox checkBoxItem;
    private JLabel lblRemoveIcon;

    /**
     * Create a new HeaderPanel
     */
    public HeaderPanel(String key, String value, boolean active)
    {
        initComponents(key, value, active);
    }


    /**
     * Initialize Components to JPanel
     */
    private void initComponents(String key, String value, boolean active) {

        lblIcon = new javax.swing.JLabel();
        textKey = new javax.swing.JTextField();
        textValue = new javax.swing.JTextField();
        checkBoxItem = new javax.swing.JCheckBox();
        lblRemoveIcon = new javax.swing.JLabel();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblIcon.setIcon(new ImageIcon("res/Pictures/listIcon.png"));
        lblRemoveIcon.setIcon(new ImageIcon("res/Pictures/rubbish-can.png"));

        textKey.setText(key);

        textValue.setText(value);

        checkBoxItem.setSelected(active);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblIcon)
                                .addGap(18, 18, 18)
                                .addComponent(textKey, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(textValue, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(checkBoxItem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblRemoveIcon)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblIcon)
                                        .addComponent(textKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(lblRemoveIcon)
                                                .addComponent(checkBoxItem)))
                                .addContainerGap())
        );
    }


    /**
     * get Check Box Item
     * @return checkBoxItem
     */
    public JCheckBox getCheckBoxItem() {
        return checkBoxItem;
    }

    /**
     * get Icon
     * @return lblIcon
     */
    public JLabel getLblIcon() {
        return lblIcon;
    }

    /**
     * get Remove Icon
     * @return lblRemoveIcon
     */
    public JLabel getLblRemoveIcon() {
        return lblRemoveIcon;
    }

    /**
     * Get Text Field Key
     * @return textKey
     */
    public JTextField getTextKey() {
        return textKey;
    }

    /**
     * Get Text Field Value
     * @return textValue
     */
    public JTextField getTextValue() {
        return textValue;
    }

    /**
     * set CheckBox
     * @param checkBoxItem is CheckBox
     */
    public void setCheckBoxItem(JCheckBox checkBoxItem) {
        this.checkBoxItem = checkBoxItem;
    }

    /**
     * set LblIcon
     * @param lblIcon is Icon
     */
    public void setLblIcon(JLabel lblIcon) {
        this.lblIcon = lblIcon;
    }

    /**
     * Set lbl Remove Icon
     * @param lblRemoveIcon is Remove Icon
     */
    public void setLblRemoveIcon(JLabel lblRemoveIcon) {
        this.lblRemoveIcon = lblRemoveIcon;
    }

    /**
     * set Text Key
     * @param textKey is given TextField
     */
    public void setTextKey(JTextField textKey) {
        this.textKey = textKey;
    }

    /**
     * set Text Value
     * @param textValue is given TextField
     */
    public void setTextValue(JTextField textValue) {
        this.textValue = textValue;
    }

    @Override
    public String toString() {
        return "HeaderPanel{" +
                ", textKey=" + textKey.getText() +
                ", textValue=" + textValue.getText() +
                ", checkBoxItem=" + checkBoxItem.isSelected() +
                '}';
    }
}
