package Control;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Personaje {

    private URL url;
    private Image img1, img2, img3, img4;
    private Image PersonajeQuietoD[] = new Image[21];
    private Image PersonajeQuietoI[] = new Image[21];
    private Image PersonajeCorrerD[] = new Image[21];
    private Image PersonajeCorrerI[] = new Image[21];
    private Image PersonajeDashD[] = new Image[6];
    private Image PersonajeDashI[] = new Image[6];
    private Image PersonajeSaltoD[] = new Image[21];
    private Image PersonajeSaltoI[] = new Image[21];
    private Image PersonajeCaidaD[] = new Image[21];
    private Image PersonajeCaidaI[] = new Image[21];
    private Image PersonajeAtaque1Der[] = new Image[13];
    private Image PersonajeAtaque1Izq[] = new Image[13];
    private Image PersonajeAtaque2Der[] = new Image[13];
    private Image PersonajeAtaque2Izq[] = new Image[13];
    private Image PersonajeAtaque3Der[] = new Image[15];
    private Image PersonajeAtaque3Izq[] = new Image[15];
    private Image PersonajeAtaqueDDer[] = new Image[29];
    private Image PersonajeAtaqueDIzq[] = new Image[29];
    private int x = 0, y = 0, xAD = 0;
    private int PosQD = 0, PosQI = 0, PosD = 0, PosI = 0, PosS = 0,
            PosDD = 0, PosDI = 0, PosCD = 0, PosA1 = 0, PosA2 = 0, PosA3 = 0, PosAD = 0;
    private int xb = 270;
    private int contA = 0;
    private int saltoAltura = 0;
    private boolean bandSalto = false, bandASalto = false, bandAtaque = false,
            bandAtaqueD = false, bandAtaqueDM = false, bandEnergia = true, bandMagia = true;
    private boolean bandIzq = false, bandDer = false;
    private boolean bandQIzq = false, bandQDer = true;
    private boolean bandDaño = false;
    private boolean Recuperar = false;
    private int daño = 0, Qaux = 0;
    private int xIdash = 0, xDdash = 0;
    private int xIvel = 0, xDvel = 0;
    private int Energia = 250;
    private int Magia = 226;
    private int Vida = 300;
    private int caida = 0;
    private int ID = 0;
    private Rectangle colision;
    private Rectangle colisionAtaque;
    private Rectangle colisionAtaqueD;

    public Personaje() {
//        for (int i = 0; i < 20; i++) {
//            url = getClass().getResource("/QuietoDer/quieto(" + (i + 1) + ").png");
//            PersonajeQuietoD[i] = new ImageIcon(url).getImage();
//        }
//        for (int i = 0; i < 20; i++) {
//            url = getClass().getResource("/QuietoIzq/quieto(" + (i + 1) + ").png");
//            PersonajeQuietoI[i] = new ImageIcon(url).getImage();
//        }
//        for (int i = 0; i < 5; i++) {
//            url = getClass().getResource("/DashDer/Dash(" + (i + 1) + ").png");
//            PersonajeDashD[i] = new ImageIcon(url).getImage();
//        }
//        for (int i = 0; i < 5; i++) {
//            url = getClass().getResource("/DashIzq/Dash(" + (i + 1) + ").png");
//            PersonajeDashI[i] = new ImageIcon(url).getImage();
//        }
//        for (int i = 0; i < 21; i++) {
//            url = getClass().getResource("/SaltoDer/salto(" + (i + 1) + ").png");
//            PersonajeSaltoD[i] = new ImageIcon(url).getImage();
//        }
//        for (int i = 0; i < 21; i++) {
//            url = getClass().getResource("/SaltoIzq/salto(" + (i + 1) + ").png");
//            PersonajeSaltoI[i] = new ImageIcon(url).getImage();
//        }
//        for (int i = 0; i < 21; i++) {
//            url = getClass().getResource("/CorrerDerP/correr(" + (i + 1) + ").png");
//            PersonajeCorrerD[i] = new ImageIcon(url).getImage();
//        }
//        for (int i = 0; i < 20; i++) {
//            url = getClass().getResource("/CorrerIzqP/correr(" + (i + 1) + ").png");
//            PersonajeCorrerI[i] = new ImageIcon(url).getImage();
//        }
//        for (int i = 0; i < 20; i++) {
//            url = getClass().getResource("/CaidaDer/Caida (" + (i + 1) + ").png");
//            PersonajeCaidaD[i] = new ImageIcon(url).getImage();
//        }
//        for (int i = 0; i < 20; i++) {
//            url = getClass().getResource("/CaidaIzq/Caida (" + (i + 1) + ").png");
//            PersonajeCaidaI[i] = new ImageIcon(url).getImage();
//        }
//        for (int i = 0; i < 12; i++) {
//            url = getClass().getResource("/Ataque1Der/Ataque (" + (i + 1) + ").png");
//            PersonajeAtaque1Der[i] = new ImageIcon(url).getImage();
//        }
//        for (int i = 0; i < 12; i++) {
//            url = getClass().getResource("/Ataque1Izq/Ataque (" + (i + 1) + ").png");
//            PersonajeAtaque1Izq[i] = new ImageIcon(url).getImage();
//        }
//        for (int i = 0; i < 12; i++) {
//            url = getClass().getResource("/Ataque2Der/Ataque2 (" + (i + 1) + ").png");
//            PersonajeAtaque2Der[i] = new ImageIcon(url).getImage();
//        }
//        for (int i = 0; i < 12; i++) {
//            url = getClass().getResource("/Ataque2Izq/Ataque2 (" + (i + 1) + ").png");
//            PersonajeAtaque2Izq[i] = new ImageIcon(url).getImage();
//        }
//        for (int i = 0; i < 14; i++) {
//            url = getClass().getResource("/Ataque3Der/Ataque3 (" + (i + 1) + ").png");
//            PersonajeAtaque3Der[i] = new ImageIcon(url).getImage();
//        }
//        for (int i = 0; i < 14; i++) {
//            url = getClass().getResource("/Ataque3Izq/Ataque3 (" + (i + 1) + ").png");
//            PersonajeAtaque3Izq[i] = new ImageIcon(url).getImage();
//        }
//        for (int i = 0; i < 28; i++) {
//            url = getClass().getResource("/AtaqueDistanciaD/AtaqueD (" + (i + 1) + ").png");
//            PersonajeAtaqueDDer[i] = new ImageIcon(url).getImage();
//        }
//        for (int i = 0; i < 28; i++) {
//            url = getClass().getResource("/AtaqueDistanciaI/AtaqueD (" + (i + 1) + ").png");
//            PersonajeAtaqueDIzq[i] = new ImageIcon(url).getImage();
//        }
        colision = new Rectangle(x, y, 50, 50);
        colisionAtaque = new Rectangle();
        colisionAtaqueD = new Rectangle();
    }

    public void paint(Graphics g1, JPanel p) {
        Graphics2D g = (Graphics2D) g1;
        g.drawImage(img4, 0, 0, 600, 200, p);
        g.drawImage(img3, 0, 0, 600, 200, p);
        g.drawImage(img2, 0, 0, 600, 200, p);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
        g.setColor(new Color(208, 81, 94));
        g.fillRect(xb, 60, Vida, 15);
        g.drawString(Vida + "", 520, 41);
        if (bandEnergia) {
            g.setFont(new Font("TimesRoman", Font.PLAIN, 26));
            g.setColor(new Color(92, 188, 61));
            g.fillRect(xb, 80, Energia, 10);
            if (Energia >= 100) {
                g.drawString(Energia + "", 550, 115);
            } else {
                g.drawString(Energia + "", 558, 115);
            }
        } else {
            g.setFont(new Font("TimesRoman", Font.PLAIN, 26));
            g.setColor(new Color(0, 149, 79));
            g.fillRect(xb, 80, Energia, 10);
            if (Energia >= 100) {
                g.drawString(Energia + "", 550, 115);
            } else {
                g.drawString(Energia + "", 558, 115);
            }
            bandAtaque = false;
        }
        g.setFont(new Font("TimesRoman", Font.PLAIN, 22));
        g.setColor(new Color(168, 184, 208));
        g.fillRect(xb, 95, Magia, 10);
        g.drawString(Magia + "", 455, 137);
        if (bandAtaqueD && bandMagia && bandDer == false && bandIzq == false && bandQDer && !bandAtaqueDM) {
            g.drawImage(PersonajeAtaqueDDer[PosAD++], x - 185, y - 185, p);
            Magia -= 2;
            if (PosAD == 28) {
                bandAtaqueD = false;
                bandAtaqueDM = true;
                Qaux = 1;
                PosAD = 0;
            }
        } else if (bandAtaqueD && bandMagia && bandDer == false && bandIzq == false && bandQIzq && !bandAtaqueDM) {
            g.drawImage(PersonajeAtaqueDIzq[PosAD++], x - 185, y - 185, p);
            Magia -= 2;
            if (PosAD == 28) {
                bandAtaqueD = false;
                bandAtaqueDM = true;
                Qaux = 2;
                PosAD = 0;
            }
        } else if (bandAtaque && bandEnergia && bandDer == false && bandIzq == false && bandQDer) {
            bandAtaqueD = false;
            switch (contA) {
                case 1:
                    g.drawImage(PersonajeAtaque1Der[PosA1++], x - 185, y - 185, p);
                    bandDer = false;
                    Energia -= 5;
                    if (PosA1 >= 12) {
                        PosA1 = 0;
                        bandAtaque = false;
                        contA++;
                    }
                    break;
                case 2:
                    g.drawImage(PersonajeAtaque2Der[PosA2++], x - 185, y - 185, p);
                    bandDer = false;
                    Energia -= 5;
                    if (PosA2 >= 12) {
                        PosA2 = 0;
                        bandAtaque = false;
                        contA++;
                    }
                    break;
                case 3:
                    g.drawImage(PersonajeAtaque3Der[PosA3++], x - 185, y - 185, p);
                    bandDer = false;
                    Energia -= 5;
                    if (PosA3 >= 14) {
                        PosA3 = 0;
                        bandAtaque = false;
                        contA = 0;
                    }
                    break;
            }
        } else if (bandAtaque && bandEnergia && bandDer == false && bandIzq == false && bandQIzq) {
            switch (contA) {
                case 1:
                    g.drawImage(PersonajeAtaque1Izq[PosA1++], x - 185, y - 185, p);
                    bandIzq = false;
                    Energia -= 5;
                    if (PosA1 >= 12) {
                        PosA1 = 0;
                        bandAtaque = false;
                        contA++;
                    }
                    break;
                case 2:
                    g.drawImage(PersonajeAtaque2Izq[PosA2++], x - 185, y - 185, p);
                    bandIzq = false;
                    Energia -= 5;
                    if (PosA2 >= 12) {
                        PosA2 = 0;
                        bandAtaque = false;
                        contA++;
                    }
                    break;
                case 3:
                    g.drawImage(PersonajeAtaque3Izq[PosA3++], x - 185, y - 185, p);
                    bandIzq = false;
                    Energia -= 5;
                    if (PosA3 >= 12) {
                        PosA3 = 0;
                        bandAtaque = false;
                        contA = 0;
                    }
                    break;
            }
        } else if (bandDer == false && bandIzq == false && bandQDer) {
            if (bandASalto) {
                if (PosS >= 20) {
                    PosS = 20;
                }
                if (PosCD >= 19) {
                    PosCD = 19;
                }
                caida++;
                if (caida > 50) {
                    g.drawImage(PersonajeCaidaD[PosCD++], x, y, p);
                } else {
                    g.drawImage(PersonajeSaltoD[PosS++], x, y, p);
                }
            } else {
                if (PosQD == 10) {
                    PosQD = 0;
                }
                g.drawImage(PersonajeQuietoD[PosQD++], x, y, p);
                xDvel = 0;
                xIvel = 0;
            }
        } else if (bandDer == false && bandIzq == false && bandQIzq) {
            if (bandASalto) {
                if (PosS >= 20) {
                    PosS = 20;
                }
                if (PosCD >= 19) {
                    PosCD = 19;
                }
                caida++;
                if (caida > 50) {
                    g.drawImage(PersonajeCaidaI[PosCD++], x, y, p);
                } else {
                    g.drawImage(PersonajeSaltoI[PosS++], x, y, p);
                }
            } else {
                if (PosQI == 20) {
                    PosQI = 0;
                }
                g.drawImage(PersonajeQuietoI[PosQI++], x, y, p);
                xDvel = 0;
                xIvel = 0;
            }
        } else if (bandDer == true && !bandASalto) {
            if (PosD == 20) {
                PosD = 0;
            }
            g.drawImage(PersonajeCorrerD[PosD++], x, y, p);
        } else if (bandIzq == true && !bandASalto) {
            if (PosI == 20) {
                PosI = 0;
            }
            g.drawImage(PersonajeCorrerI[PosI++], x, y, p);
        } else if (bandASalto && bandQDer) {
            if (xDdash != 0) {
                if (PosDD >= 5) {
                    PosDD = 4;
                }
                g.drawImage(PersonajeDashD[PosDD++], x, y, p);
            } else {
                if (PosS >= 20) {
                    PosS = 20;
                }
                if (PosCD >= 19) {
                    PosCD = 19;
                }
                caida++;
                if (caida > 50) {
                    g.drawImage(PersonajeCaidaD[PosCD++], x, y, p);
                } else {
                    g.drawImage(PersonajeSaltoD[PosS++], x, y, p);
                }
            }
        } else if (bandASalto && bandQIzq) {
            if (xIdash != 0) {
                if (PosDI >= 5) {
                    PosDI = 4;
                }
                g.drawImage(PersonajeDashI[PosDI++], x, y, p);
            } else {
                if (PosS >= 20) {
                    PosS = 20;
                }
                if (PosCD >= 19) {
                    PosCD = 19;
                }
                caida++;
                if (caida > 50) {
                    g.drawImage(PersonajeCaidaI[PosCD++], x, y, p);
                } else {
                    g.drawImage(PersonajeSaltoI[PosS++], x, y, p);
                }
            }
        }
        if (Energia < 0) {
            Energia = -1;
        }
        if (bandAtaque && bandQDer) {
            colisionAtaque.setBounds(x + 60, y, 220, 120);
            g.setColor(Color.blue);
            g.drawRect(x + 60, y, 220, 120);
        } else if (bandAtaque && bandQIzq) {
            colisionAtaque.setBounds(x - 180, y, 220, 120);
            g.setColor(Color.blue);
            g.drawRect(x - 180, y, 220, 120);
        } else {
            colisionAtaque.setBounds(x - 180, 1000, 220, 120);
        }
        if (bandAtaqueDM) {
            switch (Qaux) {
                case 1:
                    if (xAD == 5000) {
                        xAD = x;
                        xAD += 200;
                    }
                    g.setColor(Color.pink);
                    g.fillRect(xAD += 5, 600, 10, 200);
                    colisionAtaqueD.setBounds(xAD += 5, 600, 10, 200);
                    break;
                case 2:
                    if (xAD == 5000) {
                        xAD = x;
                        xAD -= 200;
                    }
                    g.setColor(Color.pink);
                    g.fillRect(xAD -= 5, 600, 10, 200);
                    colisionAtaqueD.setBounds(xAD -= 5, 600, 10, 200);
                    break;
            }

        } else {
            colisionAtaqueD.setBounds(2000, -100, 20, 100);
            xAD = 5000;
        }
        g.setColor(Color.red);
        g.drawRect(x, y, 80, 120);
        colision.setBounds(x, y, 80, 120);
        bandDer = false;
        bandIzq = false;
        g.drawImage(img1, 0, 0, 600, 200, p);
    }

    public void UpdateEstado() {
        if (bandSalto == false) {
            saltoAltura -= 2;
            y -= saltoAltura;
        }
        if (xDdash != 0) {
            xDdash = 0;
        }
        if (xIdash != 0) {
            xIdash = 0;
        }
        if (Energia <= 249) {
            Energia += 1;
        }
        if (Energia >= 249) {
            bandEnergia = true;
        }
        if (Energia <= 0 && !bandAtaque) {
            bandEnergia = false;
            bandAtaque = false;
        }
        if (Magia > 226) {
            Magia = 226;
        }
        if (Magia <= 55 && !bandAtaqueD) {
            bandMagia = false;
        } else if (Magia + 56 >= 56) {
            bandMagia = true;
        }
        if (bandDaño) {
            Vida -= daño;
            Magia += daño / 4;
            daño = 0;
        }
        if (xAD > 1300 || xAD < -100) {
            bandAtaqueDM = false;
        }
        if (Recuperar && (Vida < 270)) {
            Vida+=30;
            Recuperar=false;
        }
    }

    public void Izq() {
        if (!bandAtaque && !bandAtaqueD) {
            bandIzq = true;
            bandQIzq = true;
            bandQDer = false;
            if (xIvel != 2) {
                xIvel++;
            }
            if (xDvel > 0) {
                xDvel--;
            }
            if (x <= 10) {
                x = 11;
            }
            x -= (xIvel - xDvel + xIdash);
        }
    }

    public void Der() {
        if (!bandAtaque && !bandAtaqueD) {
            bandDer = true;
            bandQDer = true;
            bandQIzq = false;
            if (xDvel != 2) {
                xDvel++;
            }
            if (xIvel > 0) {
                xIvel--;
            }
            if (x >= 1250) {
                x = 1250;
            }
            x += (xDvel - xIvel + xDdash);
        }
    }

    public void Salto() {
        if (bandSalto && !bandAtaque) {
            saltoAltura = 35;
            bandSalto = false;
            bandASalto = true;
            y -= (saltoAltura);
        }
    }

    public boolean isBandSalto() {
        return bandSalto;
    }

    public void DashIzq(int x) {
        if (Energia >= 3 && bandEnergia) {
            xIdash = x;
            Energia -= 3;
        }
        if (Energia <= 2 && bandEnergia) {
            bandEnergia = false;
            Energia = 0;
            xIdash = x;
        }
    }

    public void DashDer(int x) {
        if (Energia >= 3 && bandEnergia) {
            xDdash = x;
            Energia -= 3;
        }
        if (Energia <= 2 && bandEnergia) {
            bandEnergia = false;
            Energia = 0;
            xDdash = x;
        }
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getColision() {
        return colision;
    }

    public Rectangle getColisionAtaque() {
        return colisionAtaque;
    }

    public void setBandSalto(boolean bandSalto, int x) {
        this.bandSalto = bandSalto;
    }

    public boolean isBandIzq() {
        return bandIzq;
    }

    public void setBandIzq(boolean bandIzq) {
        this.bandIzq = bandIzq;
    }

    public boolean isBandDer() {
        return bandDer;
    }

    public void setBandAtaque(boolean bandAtaque) {
        if (!bandDer || !bandIzq && bandEnergia && !bandAtaqueDM) {
            this.bandAtaque = bandAtaque;
            if (contA == 0) {
                this.contA++;
            }
        }
    }

    public void setBandAtaqueD(boolean bandAtaqueD) {
        if (!bandDer || !bandIzq && bandMagia && !bandAtaqueDM) {
            this.bandAtaqueD = bandAtaqueD;
        }
    }

    public void setBandDer(boolean bandDer) {
        this.bandDer = bandDer;
    }

    public int getxIvel() {
        return xIvel - xDvel + xIdash;
    }

    public int getxDvel() {
        return xDvel - xIvel + xDdash;
    }

    public void setBandASalto(boolean bandASalto) {
        this.bandASalto = bandASalto;
    }

    public void setPosS(int PosS) {
        this.PosS = PosS;
    }

    public void setPosDD(int PosDD) {
        this.PosDD = PosDD;
    }

    public void setPosDI(int PosDI) {
        this.PosDI = PosDI;
    }

    public int getSaltoAltura() {
        return saltoAltura;
    }

    public void setCaida(int caida) {
        this.caida = caida;
    }

    public void setPC(int PC) {
        this.PosCD = PC;
    }

    public boolean isBandEnergia() {
        return bandEnergia;
    }

    public void setBandDaño(boolean bandDaño, int daño) {
        this.bandDaño = bandDaño;
        this.daño = daño;
    }

    public Rectangle getColisionAtaqueD() {
        return colisionAtaqueD;
    }

    public boolean isBandAtaqueDM() {
        return bandAtaqueDM;
    }

    public void setBandAtaqueDM(boolean bandAtaqueDM) {
        this.bandAtaqueDM = bandAtaqueDM;
    }

    public boolean isBandMagia() {
        return bandMagia;
    }

    public void setMagia(int Magia) {
        this.Magia += Magia;
    }

    public int getxIdash() {
        return xIdash;
    }

    public int getxDdash() {
        return xDdash;
    }

    public int getVida() {
        return Vida;
    }

    public int getID() {
        return ID;
    }

    public void setRecuperar(boolean Recuperar) {
        this.Recuperar = Recuperar;
    }
     public int getPosCD() {
        return PosCD;
    }
}
