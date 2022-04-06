package Control;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
public class Seleccion extends JPanel implements Runnable {

    private int x = 0;
    private URL url;
    private final Image img3,img2,img1;
    private Image buffer;
    private Graphics dbf;
    private int seleccion = 2;
    private boolean ejecutar = true;

    public Seleccion() {
        url = getClass().getResource("/Personajes/Serbal.png");
        img1 = new ImageIcon(url).getImage();
        url = getClass().getResource("/Personajes/Brianda.png");
        img2 = new ImageIcon(url).getImage();
        url = getClass().getResource("/Personajes/Ehogan.png");
        img3 = new ImageIcon(url).getImage();
        setBackground(Color.red);
        setSize(1360, 768);
    }

    @Override
    public void run() {
        while (ejecutar) {
            try {
                paint(getGraphics()); 
                Thread.sleep(100);
            } catch (InterruptedException ex) {
//                Logger.getLogger(Seleccion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        buffer = createImage(getWidth(), getHeight());
        dbf = buffer.getGraphics();
        updatePaint(dbf);
        g.drawImage(buffer, 0, 0, 1360, 768, this);
    }

    public void updatePaint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, 1360, 768);
        switch (seleccion) {
            case 1:
            g.drawImage(img1, 0, 0, 1360, 768, this);
                break;
            case 2:
            g.drawImage(img2, 0, 0, 1360, 768, this);
                break;
            case 3:
            g.drawImage(img3, 0, 0, 1360, 768, this);
                break;
        }
    }

    public JPanel getP() {
        return this;

    }

    public int getSeleccion() {
        return seleccion;
    }

    public synchronized void setSeleccion(int seleccion) {
        if (seleccion == 1) {
            this.seleccion--;
            if (this.seleccion <= 0) {
                this.seleccion = 3;
            }
        } else if (seleccion == 2) {
            this.seleccion++;
            if (this.seleccion > 3) {
                this.seleccion = 1;
            }
        }
    }

    public void setEjecutar(boolean ejecutar) {
        this.ejecutar = ejecutar;
    }

    public int getX() {
        return seleccion;
    }
    
}
