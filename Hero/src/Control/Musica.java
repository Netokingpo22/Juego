package Control;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.Timer;

public class Musica extends Thread {

    private int pista, x = 1;
    private Clip sonido;
    private File a;
    private int Duracion[] = {323000, 1148000};
    private Timer cron;

    public void Pista(int pista) {
        this.pista = pista;
    }

    @Override
    public void run() {
        while (true) {
            if (x == 1) {
                switch (pista) {
                    case 1:
                        try {
                            sonido = AudioSystem.getClip();
                            a = new File("D:/Hero/src/Musica/The_Angels_Among_Demons.wav");
                        } catch (LineUnavailableException ex) {
                            Logger.getLogger(Musica.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case 2:
                        try {
                            sonido = AudioSystem.getClip();
                            a = new File("D:/Hero/src/Musica/Flowers.wav");
                        } catch (LineUnavailableException ex) {
                            Logger.getLogger(Musica.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case 3:
                        try {
                            sonido = AudioSystem.getClip();
                            a = new File("D:/Hero/src/Musica/Camino al Crater.wav");
                        } catch (LineUnavailableException ex) {
                            Logger.getLogger(Musica.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case 4:
                        try {
                            sonido = AudioSystem.getClip();
                            a = new File("D:/Hero/src/Musica/Segundo Bulto Battle Theme.wav");
                        } catch (LineUnavailableException ex) {
                            Logger.getLogger(Musica.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case 5:
                        try {
                            sonido = AudioSystem.getClip();
                            a = new File("D:/Hero/src/Musica/Camino al Crater.wav");
                        } catch (LineUnavailableException ex) {
                            Logger.getLogger(Musica.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case 6:
                        try {
                            sonido = AudioSystem.getClip();
                            a = new File("D:/Hero/src/Musica/Furia del Guardian Caido.wav");
                        } catch (LineUnavailableException ex) {
                            Logger.getLogger(Musica.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case 7:
                        try {
                            sonido = AudioSystem.getClip();
                            a = new File("D:/Hero/src/Musica/Lich Battle.wav");
                        } catch (LineUnavailableException ex) {
                            Logger.getLogger(Musica.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case 8:
                        try {
                            sonido = AudioSystem.getClip();
                            a = new File("D:/Hero/src/Musica/Serbal Final Battle.wav");
                        } catch (LineUnavailableException ex) {
                            Logger.getLogger(Musica.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case 9:
                        try {
                            sonido = AudioSystem.getClip();
                            a = new File("D:/Hero/src/Musica/Desesperación de Brianda.wav");
                        } catch (LineUnavailableException ex) {
                            Logger.getLogger(Musica.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case 10:
                        try {
                            sonido = AudioSystem.getClip();
                            a = new File("D:/Hero/src/Musica/Aseem Forma Final.wav");
                        } catch (LineUnavailableException ex) {
                            Logger.getLogger(Musica.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case 11:
                        try {
                            sonido = AudioSystem.getClip();
                            a = new File("D:/Hero/src/Musica/The_Day_the_Wind_Blew.wav");
                        } catch (LineUnavailableException ex) {
                            Logger.getLogger(Musica.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case 12:
                        try {
                            sonido = AudioSystem.getClip();
                            a = new File("D:/Hero/src/Musica/Serbal Game Over.wav");
                        } catch (LineUnavailableException ex) {
                            Logger.getLogger(Musica.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case 13:
                        try {
                            sonido = AudioSystem.getClip();
                            a = new File("D:/Hero/src/Musica/Eoghan Game Over.wav");
                        } catch (LineUnavailableException ex) {
                            Logger.getLogger(Musica.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                }
                try {
                    sonido.open(AudioSystem.getAudioInputStream(a));
                } catch (Exception tipoError) {
                    System.out.println("" + tipoError);
                }
                sonido.start();
                x++;
            }
            if (x == 3) {
                sonido.close();
                x++;
            }
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Musica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void setX(int x) {
        if (x == 3) {
            sonido.close();
            
        }
        this.x = x;
    }

    public int getX() {
        return x;
    }

}
