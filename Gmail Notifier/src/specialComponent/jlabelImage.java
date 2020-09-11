/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package specialComponent;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 *
 * @author kienh_000
 */
public class jlabelImage extends JLabel{
    ImageIcon icon;
    public jlabelImage(ImageIcon icon) {
        this.icon = icon;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(icon.getImage(), 0, 0, null);
        super.paintComponent(g);
    }
}
