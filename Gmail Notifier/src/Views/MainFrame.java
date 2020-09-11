/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;
//<editor-fold desc="Import section">
import java.awt.*;
import javax.swing.*;
//</editor-fold>
public class MainFrame extends JFrame{
    //<editor-fold desc="Component variables">
    JFrame mainFrame;
    JTabbedPane mainMenu;
    JPanel menuItem_menu;
    JPanel menuItem_main;
    JPanel menuItem_actions;
    //</editor-fold>
    //<editor-fold desc="View Design">
    /*********************************
     * Main Design */
    private void main_design() {
        mainFrame = new JFrame("Gmail Notifier by The Seven");
        mainFrame.setLayout(new GridLayout(1,1));
        mainMenu = new JTabbedPane();
        menuItem_menu = new JPanel();
    }
    /*
     *********************************/
    //</editor-fold>
}
