/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testUI;
import java.awt.Graphics;
import javax.swing.*;


public class YourFrame extends JFrame {

  private ImageIcon icon;
  private JLabel label;


  public YourFrame(){

    icon = new ImageIcon(getClass().getResource("/images/gmail.png"));

    label= new JLabel() {
      public void paintComponent(Graphics g) {
        g.drawImage(icon.getImage(), 0, 0, null);
        super.paintComponent(g);
      }
    };

    label.setOpaque(false);
    getContentPane().add( label );

    label.setText("Text1");

  }

  public static void main(String[] args) {
    YourFrame frame = new YourFrame();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
} 
