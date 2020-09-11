/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controlers.fileHandle;
import java.io.File;
import Models.WinRegistry;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kienh_000
 */
public class options extends javax.swing.JFrame {
    
    private javax.swing.JPanel GeneralPanel;
    private javax.swing.JCheckBox ckbAutoCheckEmail;
    private javax.swing.JCheckBox ckbAutoNotify;
    private javax.swing.JCheckBox ckbOpenMainFormAtStartUp;
    private javax.swing.JCheckBox ckbStartWithWindows;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lblAutoCheckAfter;
    private javax.swing.JLabel lblSecondToCheck;
    private javax.swing.JSlider sldSecondToCheck;
    
    private String before;
    private String after;
    
    public options() {
        initComponents();
        fileHandle fh = new fileHandle(new File("setting.theseven"));
        before = fh.Decrypt();
        setData();
    }
    
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        GeneralPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        ckbStartWithWindows = new javax.swing.JCheckBox();
        ckbAutoNotify = new javax.swing.JCheckBox();
        sldSecondToCheck = new javax.swing.JSlider();
        lblAutoCheckAfter = new javax.swing.JLabel();
        lblSecondToCheck = new javax.swing.JLabel();
        ckbOpenMainFormAtStartUp = new javax.swing.JCheckBox();
        ckbAutoCheckEmail = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        GeneralPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(204, 204, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Chung");
        jLabel1.setOpaque(true);

        ckbStartWithWindows.setBackground(new java.awt.Color(255, 255, 255));
        ckbStartWithWindows.setText("Khởi động cùng windows");

        ckbAutoNotify.setBackground(new java.awt.Color(255, 255, 255));
        ckbAutoNotify.setText("Tự động thông báo khi có email mới");

        sldSecondToCheck.setMaximum(3600);
        sldSecondToCheck.setMinimum(1);
        sldSecondToCheck.setOpaque(false);
        sldSecondToCheck.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sldSecondToCheckStateChanged(evt);
            }
        });

        lblAutoCheckAfter.setText("Tự động kiểm tra email sau:");

        ckbOpenMainFormAtStartUp.setBackground(new java.awt.Color(255, 255, 255));
        ckbOpenMainFormAtStartUp.setText("Bật giao diện chính khi khởi động");

        ckbAutoCheckEmail.setBackground(new java.awt.Color(255, 255, 255));
        ckbAutoCheckEmail.setText("Tự động kiểm tra email");
        ckbAutoCheckEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbAutoCheckEmailActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(204, 204, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Kiểm tra mail");
        jLabel2.setOpaque(true);

        javax.swing.GroupLayout GeneralPanelLayout = new javax.swing.GroupLayout(GeneralPanel);
        GeneralPanel.setLayout(GeneralPanelLayout);
        GeneralPanelLayout.setHorizontalGroup(
            GeneralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GeneralPanelLayout.createSequentialGroup()
                .addGroup(GeneralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(GeneralPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(GeneralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(GeneralPanelLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(GeneralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(GeneralPanelLayout.createSequentialGroup()
                                        .addGroup(GeneralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(ckbAutoNotify)
                                            .addComponent(ckbAutoCheckEmail))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(GeneralPanelLayout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(lblAutoCheckAfter)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblSecondToCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(sldSecondToCheck, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE))))))
                    .addGroup(GeneralPanelLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(GeneralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ckbOpenMainFormAtStartUp)
                            .addComponent(ckbStartWithWindows))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(GeneralPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2)))
                .addContainerGap())
        );
        GeneralPanelLayout.setVerticalGroup(
            GeneralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GeneralPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckbStartWithWindows)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ckbOpenMainFormAtStartUp)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(GeneralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(GeneralPanelLayout.createSequentialGroup()
                        .addComponent(ckbAutoNotify)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ckbAutoCheckEmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(GeneralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblAutoCheckAfter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblSecondToCheck, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(sldSecondToCheck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(151, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Chung", GeneralPanel);

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }
    
    private void ckbAutoCheckEmailActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        // TODO add your handling code here:
        if(!ckbAutoCheckEmail.isSelected()) {
            lblAutoCheckAfter.setEnabled(false);
            lblSecondToCheck.setEnabled(false);
            sldSecondToCheck.setEnabled(false);
        }
        else {
            lblAutoCheckAfter.setEnabled(true);
            lblSecondToCheck.setEnabled(true);
            sldSecondToCheck.setEnabled(true);
        }
    }                                                 

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        dispose();
    }                                        

    private void sldSecondToCheckStateChanged(javax.swing.event.ChangeEvent evt) {                                              
        // TODO add your handling code here:
        lblSecondToCheck.setText(sldSecondToCheck.getValue()+" giây");
    }                                             

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here
        StartWithWindows(ckbStartWithWindows.isSelected());
        Controlers.options options = new Controlers.options();
        options.setStartWithWindows(ckbStartWithWindows.isSelected());
        options.setOpenMainFormAtStartUp(ckbOpenMainFormAtStartUp.isSelected());
        options.setAutoNotify(ckbAutoNotify.isSelected());
        options.setAutoCheck(ckbAutoCheckEmail.isSelected());
        if(ckbAutoCheckEmail.isSelected()) {
            options.setSecondToCheck(sldSecondToCheck.getValue());
        }
        else {
            options.setSecondToCheck(0);
        }
        options.setData();
        dispose();
    }   
    
    public void setData() {
        Controlers.options options = new Controlers.options();
        options.getData();
        ckbStartWithWindows.setSelected(options.getStartWithWindows());
        ckbOpenMainFormAtStartUp.setSelected(options.getOpenMainFormAtStartUp());
        ckbAutoNotify.setSelected(options.getAutoNotify());
        ckbAutoCheckEmail.setSelected(options.getAutoCheck());
        lblSecondToCheck.setText(options.getSecondToCheck()+" giây");
        sldSecondToCheck.setValue(options.getSecondToCheck());
    }
    
    private void StartWithWindows(boolean a) {
        if(a) {
            try {
                if(WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER, "SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Run", "GmailNotify") == null) {
                    WinRegistry.writeStringValue(WinRegistry.HKEY_CURRENT_USER, "SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Run", "GmailNotify", "\""+System.getProperty("user.dir")+"\\GmailNotify.exe\"");
                }
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(options.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(options.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(options.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            try {
                if(WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER, "SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Run", "GmailNotify") != null) {
                    WinRegistry.deleteValue(WinRegistry.HKEY_CURRENT_USER, "SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Run", "GmailNotify");
                }
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(options.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(options.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(options.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
