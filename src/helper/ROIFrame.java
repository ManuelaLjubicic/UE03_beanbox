package helper;

import Catalano.Imaging.FastBitmap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * Created by manue on 30.11.2015.
 */
public class ROIFrame extends JDialog {

    private Rectangle rec;
    private Container panel;
    //height, width, x und y werden benötigt um die Werte an das rectangle weiterzugeben
    private int x;
    private int y;
    private int height;
    private int width;
    //in bi wird das orginal bild geladen und angezeigt
    private transient BufferedImage bi;

    //JDialog wird verwedent, da sobald dieses Fenster geöffnet wurde im Hintergrund alles "einfriert"
    public ROIFrame(Frame owner, String title, boolean modal, FastBitmap img) {
        super(owner, title, modal);

        bi = img.toBufferedImage();

        this.setSize(img.getWidth(), img.getHeight());
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);

        this.getContentPane().add(new JLabel(new ImageIcon(bi)));
        panel = getContentPane();
        initializeDrag();

        this.setVisible(true);
    }

    //in dieser Methode werden die Werte des Rechtecks genommen
    private void initializeDrag() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(rec.getWidth() > 0 && rec.getHeight() > 0) {
                    doDispose();
                }
            }
        });
        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int minX = Math.min(x, e.getX());
                int minY = Math.min(y, e.getY());
                height = Math.max(y,e.getY()) - minY;
                width = Math.max(x, e.getX()) - minX;

                rec = new Rectangle(x, y, width,height);
                repaint();
            }
        });
    }

    //wird benötigt um beim ausscheidnen ein rotes Reckteck anzuzeigen
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(width > 0 || height > 0){
            g.setColor(Color.red);
            g.drawRect(x, y, width, height);
        }
    }

    public Rectangle getRec() {
        return rec;
    }

    public void doDispose() {
        this.dispose();
    }

}
