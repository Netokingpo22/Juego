package Control;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Inicio extends JPanel implements Runnable, ActionListener {

    private int x = 0, y = 0;
    private URL url;
    private final Image img1, img2, img3, img4, img5;
    private Image buffer;
    private Graphics dbf;
    private Timer cron;
    private boolean ejecutar = true;
    private boolean parpadeo = true;

    public Inicio() {
        url = getClass().getResource("/ImagenesInicio/Reality busters.png");
        img1 = new ImageIcon(url).getImage();
        url = getClass().getResource("/ImagenesInicio/YaCo.png");
        img2 = new ImageIcon(url).getImage();
        url = getClass().getResource("/ImagenesInicio/Inicio2.png");
        img3 = new ImageIcon(url).getImage();
        url = getClass().getResource("/ImagenesInicio/InicioA.png");
        img4 = new ImageIcon(url).getImage();
        url = getClass().getResource("/ImagenesInicio/InicioB.png");
        img5 = new ImageIcon(url).getImage();
        cron = new Timer(1000, this);
        cron.start();
        setSize(1360, 768);
    }

    @Override
    public void paint(Graphics g) {
        buffer = createImage(getWidth(), getHeight());
        dbf = buffer.getGraphics();
        updatePaint(dbf);
        g.drawImage(buffer, 0, 0, 1360, 768, this);
    }

    public void updatePaint(Graphics g) {
        if (x <= 8) {
            g.drawImage(img1, 0, 0, 1360, 768, this);
        } else if (x <= 14 && x > 8) {
            g.setColor(Color.white);
            g.fillRect(0, 0, 1360, 768);
            g.drawImage(img2, 0, 0, 1360, 768, this);
        } else if (x <= 17 && x > 14) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 1360, 768);
            g.drawImage(img3, 0, 0, 1360, 768, this);
        } else if (x <= 20 && x > 17) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 1360, 768);
        } else if(x >= 21){
            if (parpadeo) {
                g.setColor(new Color(((int) (Math.random() * 255)), ((int) (Math.random() * 255)), ((int) (Math.random() * 255))));
                parpadeo = false;
            } else {
                g.setColor(Color.WHITE);
                parpadeo = true;
            }
            g.fillRect(0, 0, 1360, 768);
            g.drawImage(img5, 0, 0, 1360, 768, this);
            g.drawImage(img4, 0, 0, 1360, 768, this);
            y = 0;

        }
    }

    @Override
    public void run() {
        while (ejecutar) {
            paint(getGraphics());
        }
    }

    public JPanel getP() {
        return this;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x++;
    }

    public void setEjecutar(boolean ejecutar) {
        cron.stop();
        this.ejecutar = ejecutar;
    }

}
