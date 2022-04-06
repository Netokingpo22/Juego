package Control;

import Brianda.Personaje.Brianda;
import Ciudad.Ciudad;
import Ehogan.Personaje.Ehogan;
import Enemigos.Caido_Basico;
import Serbal.Personaje.Serbal;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Primer_Nivel extends JPanel implements Runnable, ActionListener {

    private Image buffer;
    private Graphics dbf;
    private boolean ejecutar = true;
    private boolean caida = false;
    private boolean bandera = false;
    public Personaje per;
    public Caido_Basico enem[] = new Caido_Basico[25];
    public final Ciudad ciudad;
    private Timer cron;

    public Primer_Nivel(int x) {
        switch (x) {
            case 1:
                per = new Serbal();
                break;
            case 2:
                per = new Brianda();
                break;
            case 3:
                per = new Ehogan();
                break;
        }
        ciudad = new Ciudad(per);
        cron = new Timer(10, this);
        for (int i = 0; i < 25; i++) {
            enem[i] = new Caido_Basico(per, Random(),Random2(),Random3());
            enem[i].start();
        }
        setSize(1360, 768);
    }

    @Override
    public void run() {
        while (ejecutar) {
            paint(getGraphics());
            per.UpdateEstado();
            ciudad.Colision1();
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(Primer_Nivel.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (per.getY() + 120 > ciudad.getY()) {
                per.setY(ciudad.getY() - 119);
            }
            if (per.getVida() <= 0) {
                ejecutar = false;
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
        ciudad.paint(g, this);
        per.paint(g, this);
        for (int i = 0; i < 25; i++) {
            if (enem[i].isVivo()) {
                enem[i].paint(g, this);
            } else {
                enem[i] = new Caido_Basico(per, Random(),Random2(),Random2());
                enem[i].start();
            }
        }

        ciudad.paint2(g, this);
    }

    public JPanel getP() {
        return this;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if (!enem[i].isVivo()) {
//
//        }
    }

    private int Random() {
        return ((int) (Math.random() * 5000)+3000);
    }
    private int Random2() {
        return ((int) (Math.random() * 7));
    }
    private int Random3() {
        return ((int) (Math.random() * 2));
    }

    public synchronized void setEjecutar(boolean ejecutar) {
        this.ejecutar = ejecutar;
    }

    public boolean isEjecutar() {
        return ejecutar;
    }
}
