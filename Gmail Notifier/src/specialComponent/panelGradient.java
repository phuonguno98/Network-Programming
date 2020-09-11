/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package specialComponent;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;
/**
 *
 * @author kienh_000
 */
public class panelGradient extends JPanel {
    Color color1, color2;
    public panelGradient(Color color1, Color color2) {
        this.color1 = color1;
        this.color2 = color2;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        GradientPaint gp = new GradientPaint(0, 0, this.color1, 0, h, this.color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
}
