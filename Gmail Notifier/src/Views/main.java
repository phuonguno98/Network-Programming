/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;
import Models.gmailClient;
import specialComponent.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import Controlers.date;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class main extends javax.swing.JFrame {
    
    //<editor-fold desc="Component variables">
    JPanel aboutPanel;
    JPanel informationPanel;
    panelGradient menu_left;
    JPanel menu_right;
    JLabel lblinformation;
    JLabel lblabout;
    JLabel lblspace;
    JButton btnclose;
    JButton btnexit;
    //aboutPanel
    JLabel aboutTitle;
    JLabel aboutImage;
    JLabel aboutProgram;
    JLabel aboutVersion;
    JLabel aboutMyEmail;
    JLabel aboutMyFace;
    JLabel aboutCopyright;
    //information panel
    JLabel infoTitle;
    JLabel infoContent;
    //mainMenu panel
    panelGradient mainPanel, actionPanel;
    JButton btnnewEmail, btncheckNow, btnoptions, btnaccount;
    JPanel mainMenuPanel, mainBody;
    JLabel lblaccount;
    JScrollPane mainScrollPane;
    JTable emailTable;
    JLabel loading;
    //</editor-fold>
    
    Models.gmailClient gmailClient;
    private String Username;
    private String Password;
    Models.email mail;
    ArrayList mails;
    boolean haveAccount = false;
    Controlers.options options;

    public main() {
        gmailClient = new Models.gmailClient();
        options = new Controlers.options();
        options.getData();
        RunInSystemTray();
        initComponents();
        menu_design();
        main_design();
        initialize();
        windows_theme();
        if(options.getOpenMainFormAtStartUp()) {
            setVisible(true);
        }
        first_time_run();
        //ResolveRegistryProblem();
        Timer time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
               getMail();
               notifier();
            }
        }, options.getSecondToCheck()*1000, options.getSecondToCheck()*1000);
        
    }
    
    private void initialize() {
        setIconImage((new ImageIcon(getClass().getResource("/images/appicon.png"))).getImage());
        setTitle("Gmail Notifier - by The Seven");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        lblinformation.setOpaque(true);
        lblinformation.setBackground(new Color(23,109,231).brighter().brighter());
        aboutPanel.setVisible(false);
        informationPanel.setVisible(true);
        jTabbedPane1.setSelectedIndex(1);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth(); //get screen width
        double height = screenSize.getHeight(); //get screen height
        int x = (int)(width-this.getWidth())/2;
        int y = (int)(height-this.getHeight())/2;
        setLocation(x, y); //set form in center of screen
    }
    
    private void menu_design() {
        //menuPanel.setLayout(new GridLayout(1,2));
//        mainPanel.setLayout(new GridLayout(1,1));
//        actionPanel.setLayout(new GridLayout(1,1));
        menu_left = new panelGradient( new Color(225,236,247),new Color(187,208,234));
        
        lblinformation = new JLabel("        Information");
        lblinformation.setFont(new Font("Tahoma",Font.PLAIN,14));
        lblinformation.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                lblinformation.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 255)));
            }
            
            @Override
            public void mouseExited(MouseEvent evt) {
                lblinformation.setBorder(null);
                //lblinformation.setBackground(null);
            }
            
            @Override
            public void mouseClicked(MouseEvent evt) {
                lblabout.setOpaque(false);
                lblabout.setBackground(null);
                lblinformation.setOpaque(true);
                lblinformation.setBackground(new Color(23,109,231).brighter().brighter());
                aboutPanel.setVisible(false);
                informationPanel.setVisible(true);
            }
        });
        lblabout = new JLabel("        About");
        lblabout.setFont(new Font("Tahoma",Font.PLAIN,14));
        lblabout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                lblabout.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 255)));
            }
            
            @Override
            public void mouseExited(MouseEvent evt) {
                lblabout.setBorder(null);
                //lblinformation.setBackground(null);
            }
            
            @Override
            public void mouseClicked(MouseEvent evt) {
                lblinformation.setOpaque(false);
                lblinformation.setBackground(null);
                lblabout.setOpaque(true);
                lblabout.setBackground(new Color(23,109,231).brighter().brighter());
                informationPanel.setVisible(false);
                aboutPanel.setVisible(true);
            }
        });
        lblspace = new JLabel();
        btnclose = new JButton("Close");
        btnclose.setIcon(new ImageIcon(getClass().getResource("/images/close.jpg")));
        btnclose.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });
        btnexit = new JButton("   Exit");
        btnexit.setIcon(new ImageIcon(getClass().getResource("/images/exit.png")));
        btnexit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        //aboutPanel
        aboutPanel = new JPanel();
        aboutPanel.setBackground(Color.white);
        aboutTitle = new JLabel();
        aboutImage = new JLabel();
        aboutVersion = new JLabel();
        aboutCopyright = new JLabel();
        aboutMyEmail = new JLabel();
        aboutMyFace = new JLabel();
        aboutTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        aboutTitle.setText("<html> About Gmail Notifier<br> ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------</html>");

        aboutImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gmail.png"))); // NOI18N

        aboutVersion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        aboutVersion.setText("Version 1.0.0");

        aboutCopyright.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        aboutCopyright.setText("Copyright @ The Seven 2016 - Alright Reserved");

        aboutMyEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        aboutMyEmail.setText("kienhacker113@gmail.com");

        aboutMyFace.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        aboutMyFace.setText("http://facebook.com/kien.hacker.113");

        javax.swing.GroupLayout aboutPanelLayout = new javax.swing.GroupLayout(aboutPanel);
        aboutPanel.setLayout(aboutPanelLayout);
        aboutPanelLayout.setHorizontalGroup(
            aboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aboutPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(aboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(aboutTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(aboutPanelLayout.createSequentialGroup()
                        .addComponent(aboutImage)
                        .addGap(29, 29, 29)
                        .addGroup(aboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(aboutVersion)
                            .addComponent(aboutCopyright)
                            .addComponent(aboutMyEmail)
                            .addComponent(aboutMyFace))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        aboutPanelLayout.setVerticalGroup(
            aboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aboutPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(aboutTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(aboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(aboutImage)
                    .addGroup(aboutPanelLayout.createSequentialGroup()
                        .addComponent(aboutVersion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(aboutCopyright)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(aboutMyEmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(aboutMyFace)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        //information panel
        informationPanel = new JPanel();
        informationPanel.setBackground(Color.white);
        infoTitle = new JLabel();
        infoContent = new JLabel();
        infoTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        infoTitle.setText("<html> Information<br> ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------</html>");

        infoContent.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        infoContent.setText("<html>\nGmail Notifier is a Java Application<br>\nTo display notification when an email was sent to your gmail.<br>\nAnd this also is a tool for sending email via gmail.<br>\nMore information, please contact me in About.<br>\nHave fun!");

        javax.swing.GroupLayout informationPanelLayout = new javax.swing.GroupLayout(informationPanel);
        informationPanel.setLayout(informationPanelLayout);
        informationPanelLayout.setHorizontalGroup(
            informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(informationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(infoTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(infoContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        informationPanelLayout.setVerticalGroup(
            informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(informationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(infoTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(infoContent, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
//        menu_right = new JPanel();
//        menu_right.setBackground(Color.white);
//        menu_right.setLayout(null);
//        menu_right.setSize(900,900);
//        menu_right.add(aboutPanel);
        //menu_right.add(informationPanel);
        
        GroupLayout menuPanelLayout = new GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addComponent(menu_left, 142, 142, 142)
                .addComponent(aboutPanel,300,920,Short.MAX_VALUE)
                .addComponent(informationPanel,300,920,Short.MAX_VALUE)
            )
                //.addGap(0, 641, Short.MAX_VALUE))
        );
        menuPanelLayout.setVerticalGroup(
                menuPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(menu_left, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(aboutPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(informationPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        GroupLayout menu_leftLayout = new GroupLayout(menu_left);
        menu_left.setLayout(menu_leftLayout);
        menu_leftLayout.setHorizontalGroup(
            menu_leftLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblinformation, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                .addComponent(lblabout, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                .addComponent(lblspace, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                .addComponent(btnclose, 120, 120, 120)
                .addComponent(btnexit, 120, 120, 120)
        );
        menu_leftLayout.setVerticalGroup(
            menu_leftLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(menu_leftLayout.createSequentialGroup()
                    .addComponent(lblinformation, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblabout, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblspace, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnclose, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnexit, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                )
        );
        
    }
    
    private void main_design() {
        mainPanel = new panelGradient(new Color(225,236,247),new Color(187,208,234));
        btnnewEmail = new JButton("Gửi Email");
        btnnewEmail.setIcon(new ImageIcon(getClass().getResource("/images/email_edit.png")));
        btnnewEmail.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                //btnnewEmailActionPerformed();
            }
        });
        btncheckNow = new JButton("Kiểm tra Email");
        btncheckNow.setIcon(new ImageIcon(getClass().getResource("/images/arrow_refresh.png")));
        btncheckNow.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                btncheckNowActionPerformed();
            }
        });
        btnoptions = new JButton("Tuỳ chọn");
        btnoptions.setIcon(new ImageIcon(getClass().getResource("/images/check_box_list.png")));
        btnoptions.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                btnoptionsActionPerformed();
            }
        });
        lblaccount = new JLabel();
        btnaccount = new JButton("Tài khoản của bạn");
        lblaccount.setHorizontalAlignment(SwingConstants.CENTER);
        btnaccount.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                btnaccountActionPerformed();
            }
        });
        loading = new JLabel();
        ImageIcon loading_gif = new ImageIcon(getClass().getResource("/images/loading.gif"));
        loading.setIcon(loading_gif);
        loading.setVisible(false);
        
        mainMenuPanel = new JPanel();
        mainMenuPanel.setBackground(Color.white);
        javax.swing.GroupLayout mainMenuPanelLayout = new javax.swing.GroupLayout(mainMenuPanel);
        mainMenuPanel.setLayout(mainMenuPanelLayout);
        mainMenuPanelLayout.setHorizontalGroup(
            mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainMenuPanelLayout.createSequentialGroup()
                .addComponent(btnnewEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncheckNow, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnoptions, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnaccount)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 613, Short.MAX_VALUE)
                .addComponent(lblaccount, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
            )
        );
        mainMenuPanelLayout.setVerticalGroup(
            mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnnewEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btncheckNow, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addComponent(btnoptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblaccount)
                .addComponent(btnaccount)
            )
        );
        mainBody = new JPanel();
        mainBody.setOpaque(false);
        mainScrollPane = new JScrollPane();
        emailTable = new JTable();
        emailTable.setFont(new java.awt.Font("Tahoma", 0, 14));
        emailTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                
            },
            new String [] {
                "Người gửi", "Tiêu đề", "Thời gian nhận"
            }
        ));
        emailTable.setRowHeight(24);
        emailTable.getTableHeader().setReorderingAllowed(false);
        emailTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table =(JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    emailTableDoubleClick();
                }
            }
        });
        mainScrollPane.setViewportView(emailTable);

        javax.swing.GroupLayout mainBodyLayout = new javax.swing.GroupLayout(mainBody);
        mainBody.setLayout(mainBodyLayout);
        mainBodyLayout.setHorizontalGroup(
            mainBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 991, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainBodyLayout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(loading, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(293, 293, 293))
        );
        mainBodyLayout.setVerticalGroup(
            mainBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
            .addGroup(mainBodyLayout.createSequentialGroup()
            .addContainerGap(166, Short.MAX_VALUE)
            .addComponent(loading, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(234, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainMenuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mainBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(mainMenuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(mainBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("        Main        ", mainPanel);
        actionPanel = new panelGradient(new Color(225,236,247),new Color(187,208,234));
        javax.swing.GroupLayout actionPanelLayout = new javax.swing.GroupLayout(actionPanel);
        actionPanel.setLayout(actionPanelLayout);
        actionPanelLayout.setHorizontalGroup(
            actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1069, Short.MAX_VALUE)
        );
        actionPanelLayout.setVerticalGroup(
            actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 546, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("        Actions        ", actionPanel);
    }
    
    public void btnaccountActionPerformed() {
        account acc = new account();
        acc.setData(Username, Password);
        acc.setVisible(true);
    }
    
    public void btncheckNowActionPerformed() {
        getMail();
        notifier();
    }
    
    public void btnoptionsActionPerformed() {
        options options = new options();
        options.setVisible(true);
    }
    
    private void windows_theme() {
        String laf = "";
        laf = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        try {
            UIManager.setLookAndFeel(laf);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException excep) {
        }
    }
    
    //System tray icon running
    private void RunInSystemTray() {
        TrayIcon trayIcon = null;
        if (SystemTray.isSupported()) {
            // get the SystemTray instance
            SystemTray tray = SystemTray.getSystemTray();
            // load an image
            Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/trayicon.png"));
            // create a action listener to listen for default action executed on the tray icon
            ActionListener mnushowlt = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setVisible(true);
                }
            };
            ActionListener mnuexitlt = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            };
            // create a popup menu
            PopupMenu popup = new PopupMenu();
            // create menu item for the default action
            MenuItem mnushow = new MenuItem("Show Gmail Notifier");
            mnushow.addActionListener(mnushowlt);
            popup.add(mnushow);
            MenuItem mnuexit = new MenuItem("Exit");
            mnuexit.addActionListener(mnuexitlt);
            popup.add(mnuexit);
            // construct a TrayIcon
            trayIcon = new TrayIcon(image, "Gmail Notifier", popup);
            // set the TrayIcon properties
            trayIcon.addActionListener(mnushowlt);
            trayIcon.addActionListener(mnuexitlt);
            // ...
            // add the tray image
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println(e);
            }
            // ...
        } else {
            // disable tray option in your application or
            // perform other actions
            //...
        }
        // ...
        // some time later
        // the application state has changed - update the image
//        if (trayIcon != null) {
//            trayIcon.setImage(updatedImage);
//        }
    }
    
    private void first_time_run() {
        mainScrollPane.setVisible(false);
        loading.setVisible(true);
        getMail();
        notifier();
        loading.setVisible(false);
        mainScrollPane.setVisible(true);
    }
    
    private void getMail() {
        Controlers.account account = new Controlers.account();
        String username, password;
        if((username = account.getUsername())!= null && (password = account.getPassword()) != null) {
            gmailClient.setAccountDetails(username, password);
            haveAccount = true;
            this.Username = username;
            this.Password = password;
            lblaccount.setText(username);
            mails = gmailClient.readGmail();
            DefaultTableModel emailtable = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int col) {
                    //chỉ cho cột select được phép sửa (được phép chọn)
                    return col == -1;
                }
            };
            emailtable.addColumn("Người gửi");
            emailtable.addColumn("Tiêu đề");
            emailtable.addColumn("Thời gian nhận");
            for (int i = 0; i<mails.size(); i++) {
                mail = (Models.email)mails.get(i);
                date date = new date(mail.receiveDate);
                Object[] row = {mail.from,mail.subject,date.getdate()};
                emailtable.addRow(row);
            }
            emailTable.setModel(emailtable);
        }
        else {
            JOptionPane.showMessageDialog(this, "Tài khoản của bạn chưa được cài đặt!", "Warning!", 2);
        }
    }
    
    private void emailTableDoubleClick() {
        int selected_row = emailTable.getSelectedRow();
        email amail = new email();
        Models.email getmail = (Models.email)mails.get(selected_row);
        amail.setData(getmail.from, getmail.receiveDate, getmail.subject, getmail.attachments, getmail.message);
        amail.setVisible(true);
    }
    
    private void notifier() {
        date LastCheckDate = new date(options.getLastCheckDate());
        ArrayList emails = new ArrayList();
        for(int i=0;i<mails.size();i++) {
            Models.email email = (Models.email)mails.get(i);
            date emailDate = new date(email.receiveDate);
            if(LastCheckDate.equal(emailDate) == -1) {
                emails.add(email);
                if(i==0) {
                    options.setLastCheckDate(email.receiveDate);
                    options.setData();
                }
            }
        }
        if(emails.size() > 0) {
            notify notify = new notify(this.Username, emails);
            notify.setVisible(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlinformation1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        menuPanel = new javax.swing.JPanel();

        javax.swing.GroupLayout pnlinformation1Layout = new javax.swing.GroupLayout(pnlinformation1);
        pnlinformation1.setLayout(pnlinformation1Layout);
        pnlinformation1Layout.setHorizontalGroup(
            pnlinformation1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 679, Short.MAX_VALUE)
        );
        pnlinformation1Layout.setVerticalGroup(
            pnlinformation1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        menuPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1069, Short.MAX_VALUE)
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 546, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("          Menu          ", menuPanel);

        getContentPane().add(jTabbedPane1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }
    
    private void ResolveRegistryProblem() {
        try {
            if(Models.WinRegistry.readStringSubKeys(Models.WinRegistry.HKEY_LOCAL_MACHINE, "Software\\JavaSoft\\Prefs") == null)
            Models.WinRegistry.createKey(Models.WinRegistry.HKEY_LOCAL_MACHINE, "Software\\JavaSoft\\Prefs");
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JPanel pnlinformation1;
    // End of variables declaration//GEN-END:variables
}
