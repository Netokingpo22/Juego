package Control;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GAME_OVER extends JPanel implements Runnable {

    private Graphics g, g2;
    private Image bufImage;
    private boolean ejecutar;
    private int cont = 0;
    private boolean Continuar = false;
    private URL url;
    private final Image img1, img2, img3;
    private final Image[] Arma;

    public GAME_OVER() {
        url = getClass().getResource("/Game Over/Fin.png");
        img1 = new ImageIcon(url).getImage();
        url = getClass().getResource("/Game Over/Cargando.png");
        img2 = new ImageIcon(url).getImage();
        url = getClass().getResource("/Game Over/Continuar.png");
        img3 = new ImageIcon(url).getImage();
        Arma = new Image[10];
        for (int i = 0; i < 10; i++) {
            url = getClass().getResource("/Game Over/Fin.png");
            Arma[i] = new ImageIcon(url).getImage();
        }
        setSize(1360, 768);
        ejecutar = true;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img1, 0, 0, 1360, 768, this);
        if (cont > 100) {
            g.drawImage(img3, 0, 0, 1360, 768, this);
            Continuar = true;
        } else {
            g.drawImage(img2, 0, 0, 1360, 768, this);
        }
        cont++;
    }

    @Override
    public void run() {
        while (ejecutar) {
            try {
                bufImage = createImage(getWidth(), getHeight());
                g2 = bufImage.getGraphics();
                paint(g2);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                g = getGraphics();
                g.drawImage(bufImage, 0, 0, this);
            } catch (Exception e) {
            }
        }
    }

    public JPanel getP() {
        return this;
    }

    public synchronized void setEjecutar(boolean ejecutar) {
        this.ejecutar = ejecutar;
    }

    public boolean isEjecutar() {
        return ejecutar;
    }

    public boolean isContinuar() {
        return Continuar;
    }

}
