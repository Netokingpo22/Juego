package Control;

import Enemigos.Caido_Basico;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Jefe_Bulto extends Thread {

    private URL url;
    private Image CorrerDer[] = new Image[80];
    private Image CorrerIzq[] = new Image[80];
    private Image AtaqueDer[] = new Image[50];
    private Image AtaqueIzq[] = new Image[50];
    private Image TacleoDer[] = new Image[46];
    private Image TacleoIzq[] = new Image[46];
    private int x = 1200, y = 642;
    private int posCD = 0, posCI = 0;
    private int posAD = 0, posAI = 0;
    private int vida = 20000, Carga = 0;
    private int Dv = 0, Iv = 0;
    private boolean vivo;
    private boolean bandAtaque = false;
    private Personaje per;
    private Rectangle colision;
    private Rectangle Alcanze;
    private Rectangle Ataque;

    public Jefe_Bulto(Personaje p) {
        for (int i = 0; i < 51; i++) {
            url = getClass().getResource("/Bulto/CaminarDer/Caminar (" + (i + 1) + ").png");
            CorrerDer[i] = new ImageIcon(url).getImage();
        }
        for (int i = 0; i < 51; i++) {
            url = getClass().getResource("/Bulto/CaminarIzq/Caminar (" + (i + 1) + ").png");
            CorrerIzq[i] = new ImageIcon(url).getImage();
        }
        for (int i = 0; i < 35; i++) {
            url = getClass().getResource("/Bulto/DebilDer/Ataque (" + (i + 1) + ").png");
            AtaqueDer[i] = new ImageIcon(url).getImage();
        }
        for (int i = 0; i < 35; i++) {
            url = getClass().getResource("/Bulto/DebilIzq/Ataque (" + (i + 1) + ").png");
            AtaqueIzq[i] = new ImageIcon(url).getImage();
        }
        for (int i = 0; i < 30; i++) {
            url = getClass().getResource("/Bulto/TacleoDer/Placa (" + (i + 1) + ").png");
            TacleoDer[i] = new ImageIcon(url).getImage();
        }
        for (int i = 0; i < 30; i++) {
            url = getClass().getResource("/Bulto/TacleoIzq/Placa (" + (i + 1) + ").png");
            TacleoIzq[i] = new ImageIcon(url).getImage();
        }
        colision = new Rectangle();
        Ataque = new Rectangle();
        Alcanze = new Rectangle();
        vivo = true;
        per = p;
    }

    public void paint(Graphics g, JPanel p) {
        g.setColor(new Color(208, 81, 94));
        //g.fillRect(x + 100, y - 500, (int) (vida / 100), 3);

        if (per.getX() < x - 25) {
            colision.setBounds(x + 250, y - 100, 400, 200);
            g.setColor(Color.green);
            //g.drawRect(x + 250, y - 100, 400, 200);
        } else if (per.getX() > x) {
            colision.setBounds(x + 100, y - 100, 400, 200);
            g.setColor(Color.green);
            //g.drawRect(x + 100, y - 100, 400, 200);
        }
        Alcanze.setBounds(x - 100, y, 1000, 100);
        g.setColor(Color.ORANGE);
        //g.drawRect(x - 100, y, 1000, 100);
        if (!Alcanze.intersects(per.getColision()) && !bandAtaque) {
            if (per.getX() < x + 50) {
                g.drawImage(CorrerIzq[posCI++], (x -= 4) - 100, y - 650, p);
                Ataque.setBounds(x, 2000, 50, 50);
                if (posCI >= 51) {
                    posCI = 0;
                }
                posCD = 0;
            } else if (per.getX() > x - 25) {
                g.drawImage(CorrerDer[posCD++], (x += 4), y - 650, p);
                Ataque.setBounds(x, 2000, 50, 50);
                if (posCD >= 51) {
                    posCD = 0;
                }
                posCI = 0;
            } else {
                if (Carga > 75) {
                    if (posAD >= 21) {
                        Ataque.setBounds((x -= Dv) + 200, y - 50, 600, 100);
                        g.setColor(Color.cyan);
                        //g.drawRect(x + 200, y - 50, 600, 100);
                    }
                    g.drawImage(AtaqueDer[posAD++], (x -= Dv) - 25, y - 650, p);
                    if (posAD > 34) {
                        posAD = 0;
                        ReinicarAtaque();
                    }
                }
            }
        } else if (Alcanze.intersects(per.getColision()) || bandAtaque) {
            bandAtaque = true;
            if (per.getX() > x - 25) {
                if (Carga > 75) {
                    if (posAD >= 21) {
                        Ataque.setBounds((x -= Dv) + 100, y - 50, 800, 100);
                        g.setColor(Color.cyan);
                        //g.drawRect(x + 100, y - 50, 800, 100);
                    }
                    g.drawImage(AtaqueDer[posAD++], (x -= Dv), y - 650, p);
                    if (posAD > 34) {
                        posAD = 0;
                        ReinicarAtaque();
                    }
                } else {
                    if (posAD >= 21) {
                        posAD = 21;
                    }
                    g.drawImage(AtaqueDer[posAD++], (x -= Dv), y - 650, p);
                }
                CargarAtaque();
            } else if (per.getX() < x - 50) {
                if (Carga > 75) {
                    if (posAI >= 21) {
                        Ataque.setBounds((x += Iv) - 100, y - 25, 700, 100);
                        g.setColor(Color.cyan);
                        //g.drawRect(x - 100, y - 25, 700, 100);
                    }
                    g.drawImage(AtaqueIzq[posAI++], (x += Iv) - 130, y - 650, p);
                    if (posAI > 34) {
                        posAI = 0;
                        ReinicarAtaque();
                    }
                } else {
                    if (posAI >= 21) {
                        posAI = 21;
                    }
                    g.drawImage(AtaqueIzq[posAI++], (x += Iv) - 130, y - 650, p);
                }
                CargarAtaque();
            } else {
                if (Carga > 75) {
                    if (posAD >= 21) {
                        Ataque.setBounds((x -= Dv), y - 50, 1000, 100);
                        g.setColor(Color.cyan);
                        //g.drawRect(x, y - 50, 1000, 100);
                    }
                    g.drawImage(AtaqueDer[posAD++], (x -= Dv), y - 650, p);
                    if (posAD > 34) {
                        posAD = 0;
                        ReinicarAtaque();
                    }
                } else {
                    if (posAD >= 21) {
                        posAD = 21;
                    }
                    g.drawImage(AtaqueDer[posAD++], (x -= Dv), y - 650, p);
                }
                CargarAtaque();
            }
        }
    }

    public void actualizarEstado() {
        if (vida <= 0) {
            vivo = false;
        }
        if (per.getColisionAtaque().intersects(colision)) {
            switch (per.getID()) {
                case 1:
                    vida -= 15;
                    per.setMagia(1);
                    break;
                case 2:
                    vida -= 15;
                    per.setMagia(1);
                    break;
                case 3:vida -= 50;
                    per.setMagia(1);
                    
                    break;
            }
            per.setMagia(1);
        }
        if (per.getColision().intersects(Ataque)) {
            per.setBandDaño(true, 25);
        } else {
            per.setBandDaño(false, 0);
        }
        if (per.getColisionAtaqueD().intersects(colision)) {
            vida -= 300;
            per.setBandAtaqueDM(false);
            per.setRecuperar(true);
        }
    }

    public boolean isVivo() {
        return vivo;
    }

    private void CargarAtaque() {
        Carga++;
    }

    private void ReinicarAtaque() {
        Carga = 0;
        Ataque.setBounds(x, y + 2000, 50, 50);
        bandAtaque = false;
    }

    @Override
    public void run() {
        while (vivo) {
            actualizarEstado();
            try {
                sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Caido_Basico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
