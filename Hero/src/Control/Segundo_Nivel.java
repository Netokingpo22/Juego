package Control;

import Brianda.Personaje.Brianda;
import Ehogan.Personaje.Ehogan;
import Enemigos.Ayudante;
import Enemigos.Caido_Basico;
import Pueblo.Pueblo;
import Serbal.Personaje.Serbal;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Segundo_Nivel extends JPanel implements Runnable, ActionListener {

    private Image buffer;
    private Graphics dbf;
    private boolean ejecutar = true;
    private boolean caida = true;
    private boolean bandera = true;
    public Personaje per;
    public Suelo pueblo;
    public Caido_Basico enem[] = new Caido_Basico[30];
    private int x;
    private Timer cron;

    public Segundo_Nivel(int x) {
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
        for (int i = 0; i < 30; i++) {
            enem[i] = new Caido_Basico(per, Random(), Random2(), Random2());
            enem[i].start();
        }
        pueblo = new Suelo(per);
        cron = new Timer(10, this);
        setSize(1360, 768);
    }

    @Override
    public void run() {
        while (ejecutar) {
            paint(getGraphics());
            if (pueblo.getCOL1() != null) {
                pueblo.Colision1();
            } else if (pueblo.getCOL2() != null) {
                if (per.getColision().intersects(pueblo.getCOL2())) {
                    pueblo.Colision2();
                    pueblo.setBAND(true);
                } else {
                    if (!pueblo.isBAND()) {
                        pueblo.setYim(10);
                    }
                }
            } else {
                pueblo.setYim(10);
            }
            if (pueblo.getYim() < -900) {
                if (pueblo.getX() >= -5030) {
                    bandera = false;
                } else {
                    bandera = true;
                }
            }
            per.UpdateEstado();
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(Primer_Nivel.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (per.getY() + 120 > pueblo.getY()) {
                per.setY(pueblo.getY() - 119);
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
        pueblo.paint(g, this);
        per.paint(g, this);
        if (pueblo.getYim() <= -50 && pueblo.getYim() >= -900) {

        } else if (pueblo.getYim() <= -920) {
            if (!caida) {
                for (int i = 0; i < 30; i++) {
                    enem[i].setVida(0);
                    caida=true;
                }
            }
        } else {
            if (per.getPosCD() > 0 && caida) {
                for (int i = 0; i < 30; i++) {
                    enem[i] = new Caido_Basico(per, Random(), Random2(), Random2());
                    enem[i].start();
                }
                caida = false;
            }
            for (int i = 0; i < 30; i++) {
                if (enem[i].isVivo()) {
                    enem[i].paint(g, this);
                } else {
                    enem[i] = new Caido_Basico(per, Random(), Random2(), Random2());
                    enem[i].start();
                }
            }
        }
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
        return (-2500 - (int) (Math.random() * 500));
    }

    private int Random2() {
        return ((int) (Math.random() * 4));
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public boolean isBandera() {
        return bandera;
    }

}
