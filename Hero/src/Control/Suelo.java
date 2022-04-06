package Control;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Suelo {

    private final Image suelo;
    private final URL url;
    private Rectangle colision1;
    private Rectangle colision2;
    private Rectangle colision3;
    private final Personaje per;
    private int y = 692, y2 = 1600, yim = 0;
    private int x1 = -10, x2 = -6700, x3 = -10;
    private boolean Col1 = true, Col2 = false,BAND = false;

    public Suelo(Personaje per) {
        url = getClass().getResource("/ImagenesPrimer_Nivel/Fondo2.png");
        suelo = new ImageIcon(url).getImage();
        this.per = per;
        colision1 = new Rectangle();
        colision2 = new Rectangle();
        colision3 = new Rectangle();
    }

    public void paint(Graphics g1, JPanel j) {
        Graphics2D g = (Graphics2D) g1;
        if (x1 < -5100 && x1 > -8000 && !Col2) {
            Col1 = false;
            colision1 = null;
            Col2 = true;
            per.setBandSalto(false, 10);
            per.setBandASalto(true);
        } else if (x1 < -10590) {
            Col2 = false;
            colision2 = null;
            per.setBandSalto(false, 10);
            per.setBandASalto(true);
        }
        if (per.isBandDer()) {
            if (Col1) {
                g.drawImage(suelo, (x1 -= (per.getxDvel()) + 6), yim, j);
                colision1.setBounds(-10, y + yim, 5120, 20);
                g.setColor(Color.yellow);
                g.drawRect(-10, y + yim, 7680, 20);
            } else if (Col2) {
                g.drawImage(suelo, (x1 -= (per.getxDvel()) + 6), yim, j);
                colision2.setBounds(x1, y2 + yim, 14000, 20);
                g.setColor(Color.red);
                g.drawRect(x1, y2 + yim, 14000, 20);
            } else {
                g.drawImage(suelo, (x1 -= (per.getxDvel()) + 6), yim, j);
            }
        } else if (per.isBandIzq()) {
            if (Col1) {
                if (x1 >= -10) {
                    x1 = -10;
                    g.drawImage(suelo, (x1 += (per.getxIvel()) + 6), yim, j);
                    colision1.setBounds(-10, y + yim, 7680, 20);
                    g.setColor(Color.yellow);
                    g.drawRect(-10, y, 7680, 20);
                } else if (x1 <= -11) {
                    g.drawImage(suelo, (x1 += (per.getxIvel()) + 6), yim, j);
                    colision1.setBounds(-10, y + yim, 7680, 20);
                    g.setColor(Color.yellow);
                    g.drawRect(-10, y, 7680, 20);
                }
            } else if (Col2) {
                if (x1 >= -10) {
                    x1 = -10;
                    g.drawImage(suelo, x1, yim, j);
                    colision2.setBounds(x1, y2 + yim, 14000, 20);
                    g.setColor(Color.red);
                    g.drawRect(x1, y2 + yim, 14000, 20);
                } else if (x1 <= -11) {
                    g.drawImage(suelo, (x1 += (per.getxIvel()) + 4), yim, j);
                    colision2.setBounds(x1, y2 + yim, 14000, 20);
                    g.setColor(Color.red);
                    g.drawRect(x1, y2 + yim, 14000, 20);
                }
            } else {
                g.drawImage(suelo, (x1 -= (per.getxDvel()) + 6), yim, j);
            }
        } else {
            if (Col1) {
                g.drawImage(suelo, x1, yim, j);
                colision1.setBounds(-10, y + yim, 7680, 20);
                g.setColor(Color.yellow);
                g.drawRect(-10, y + yim, 7680, 20);
            } else if (Col2) {
                g.drawImage(suelo, x1, yim, j);
                colision2.setBounds(x1, y2 + yim, 14000, 20);
                g.setColor(Color.red);
                g.drawRect(x1, y2 + yim, 14000, 20);
            } else {
                g.drawImage(suelo, (x1 -= (per.getxDvel()) + 6), yim, j);
            }
        }
    }

    public void Colision1() {
        if (per.getColision().intersects(this.colision1)) {
            per.setBandSalto(true, 0);
            per.setBandASalto(false);
            per.setPosS(0);
            per.setPosDD(0);
            per.setPosDI(0);
            per.setCaida(0);
            per.setPC(0);
        } else {
            per.setBandSalto(false, 10);
        }
    }

    public void Colision2() {
        if (per.getColision().intersects(this.colision2)) {
            per.setBandSalto(true, 0);
            per.setBandASalto(false);
            per.setPosS(0);
            per.setPosDD(0);
            per.setPosDI(0);
            per.setCaida(0);
            per.setPC(0);
        } else {
            per.setBandSalto(false, 10);
        }
    }

    public Rectangle getCOL1() {
        return colision1;
    }

    public Rectangle getCOL2() {
        return colision2;
    }

    public int getX() {
        return x1;
    }

    public int getY() {
        return y;
    }

    public int getY2() {
        return y2;
    }

    public void setY(int y) {
        this.y += y;
    }

    public synchronized void setYim(int yim) {
        this.yim -= yim;
    }

    public synchronized int getYim() {
        return yim;
    }

    public boolean isCol1() {
        return Col1;
    }

    public void setCol1(boolean Col1) {
        this.Col1 = Col1;
    }

    public boolean isCol2() {
        return Col2;
    }

    public void setCol2(boolean Col2) {
        this.Col2 = Col2;
    }

    public boolean isBAND() {
        return BAND;
    }

    public void setBAND(boolean BAND) {
        this.BAND = BAND;
    }
    
}
