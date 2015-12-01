package helper;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

/**
 * Created by manue on 30.11.2015.
 */
public class ROIFrame extends Frame {
    private Rectangle rec;
    private Panel panel;
    private int x = 0;
    private int y = 55;
    private int height = 80;
    private int width = 420;
    BufferedImage bi;

    public ROIFrame(BufferedImage img) {
        bi = img;
        this.setName("ROI");
        this.setSize(img.getWidth(), img.getHeight());
        repaint();
        drag();
        this.setVisible(true);

        close();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(bi != null){
            g.drawImage(bi, 0, 0, this);
        }else {
            setBackground(Color.white);
            g.drawString("no Picture", 5, 20);
        }
    }

    private void close(){
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);

            }
        });
    }

    private void drag() {
        panel = new Panel();
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                 width = e.getY();
                height = e.getX();
            }
        });
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }
        });
        rec = new Rectangle(x, y, width,height);
        this.add(panel);
    }

    public Rectangle getRec() {
        return rec;
    }

//    public void setRec(Rectangle rec) {
//        this.rec = rec;
//    }
}
