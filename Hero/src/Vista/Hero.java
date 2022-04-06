package Vista;

import Cinematicas.Cinematicas;
import Control.Final;
import Control.GAME_OVER;
import Control.Inicio;
import Control.Musica;
import Control.Nivel_Jefe;
import Control.Nivel_Jefe2;
import Control.Nivel_Jefe3;
import Control.Nivel_Jefe4;
import Control.Primer_Nivel;
import Control.Segundo_Nivel;
import Control.Seleccion;
import Control.Teclado;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Hero extends JFrame implements Runnable {

    private static final int ANCHO = 1360;
    private static final int ALTO = 768;

    private static volatile boolean enFuncionamiento = false;

    private static final String NOMBRE = "Juego";

    private static int aps = 0;
    private static int fps = 0;

    private static Thread thread;
    private static Teclado teclado;
    /*
    1.-Inicio 
    2.-Seleccion
    3.-Cinematica introductoria(Depende del personaje)
    4.-Primer nivel(Depende del personaje)    
    5.-Cinematica para el Jefe(Depende del personaje)
    6.-primer Jefe Bulto
    7.-Cinematica para el Segundo Nivel(Depende del personaje)
    8.-Segundo nivel(Depende del personaje)    
    9.-Cinematica para el 2do Jefe(Depende del personaje)
    10.-2do Jefe(Depende del personaje)
    11.-Cinematica para el 3do Jefe(Depende del personaje)
    12.-3do Jefe(Depende del personaje)
    13.-Cinematica para el Jefe Final 1ra Forma
    14.-Jefe Final 1ra Forma
    15.-Cinematica para el Jefe Final 2da Forma
    16.-Jefe Final 2da Forma
    17.-Cinematica para el Jefe Final Forma Final
    18.-Jefe Final Forma Final
    19.-Pelea Final
    20.-Cinematica final
    21.-Fin
    0.-Game Over
     */
    public static int estado;

    private int personaje;
    private Inicio inicio;
    private Cinematicas cinematicas;
    private Seleccion seleccion;
    private Primer_Nivel primer_Nivel;
    private Segundo_Nivel segundo_nivel;
    private Nivel_Jefe bulto;
    private Nivel_Jefe2 gigante;
    private Nivel_Jefe3 Personajes;
    private Nivel_Jefe4 Personajes2;
    private Final Aseem;
    private GAME_OVER gameover;
    private static Musica musica;
    private Thread actual;

    boolean bandera = true;

    private Hero() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(ANCHO, ALTO));
        teclado = new Teclado();
        addKeyListener(teclado);
        inicio = new Inicio();
        inicio.addKeyListener(teclado);
        musica = new Musica();
        add(inicio.getP());
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setLayout(new BorderLayout());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
        setCursor(blankCursor);
        estado = 1;
        musica.Pista(1);
        musica.start();
        actual = new Thread(inicio);
        actual.start();
        bandera = false;
    }

    public static void main(String[] args) {
        Hero Juego = new Hero();
        Juego.iniciar();
    }

    private synchronized void iniciar() {
        enFuncionamiento = true;

        Thread thread = new Thread(this, "Graficos");
        thread.start();
    }

    private synchronized void detener() {
        enFuncionamiento = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void actualizar() {
        teclado.actualizar();
        switch (estado) {
            case 0:
                if (teclado.salir) {
                    try {
                        System.exit(0);
                    } catch (Exception e) {
                    }
                }
                if (teclado.letra_A && gameover.isContinuar()) {
                    estado = 1;
                    bandera = true;
                    Dormir(200);
                }
                break;
            case 1:
                if (teclado.salir) {
                    System.exit(0);
                }
                if (teclado.letra_A) {
                    estado = 2;
                    bandera = true;
                    Dormir(200);
                }
                break;

            case 2:
                if (teclado.salir) {
                    System.exit(0);
                }
                if (teclado.Izq) {
                    seleccion.setSeleccion(1);
                    Dormir(200);
                }
                if (teclado.Der) {
                    seleccion.setSeleccion(2);
                    Dormir(200);
                }
                if (teclado.letra_A) {
                    estado = 3;
                    bandera = true;
                    Dormir(200);
                }
                break;
            case 3:
                if (teclado.salir) {
                    System.exit(0);
                }
                if (teclado.letra_A) {
                    estado = 4;
                    bandera = true;
                    Dormir(200);
                }
                break;
            case 4:
                if (teclado.salir) {
                    try {
                        System.exit(0);
                    } catch (Exception e) {
                    }
                }
                if (teclado.letra_A && primer_Nivel.per.isBandEnergia() && primer_Nivel.per.isBandSalto()) {
                    if (primer_Nivel.per.getColision().intersects(primer_Nivel.ciudad.getCOL1())) {
                        primer_Nivel.per.setBandAtaque(true);
                        Dormir(10);
                    }
                } else if (teclado.letra_D && primer_Nivel.per.isBandMagia() && primer_Nivel.per.isBandSalto() && !primer_Nivel.per.isBandAtaqueDM()) {
                    if (primer_Nivel.per.getColision().intersects(primer_Nivel.ciudad.getCOL1())) {
                        primer_Nivel.per.setBandAtaqueD(true);
                        Dormir(10);
                    }
                } else if (teclado.Izq) {
                    primer_Nivel.per.Izq();
                    if (teclado.Izq && teclado.letra_S) {
                        primer_Nivel.per.DashIzq(7);
                    }
                    Dormir(10);
                } else if (teclado.Der) {
                    primer_Nivel.per.Der();
                    if (teclado.Der && teclado.letra_S) {
                        primer_Nivel.per.DashDer(7);
                    }
                    Dormir(10);
                }
                if (teclado.Up) {
                    if (primer_Nivel.per.isBandSalto()) {
                        primer_Nivel.per.Salto();
                        primer_Nivel.per.setBandASalto(true);
                        Dormir(10);
                    }
                }

                break;
            case 5:
                if (teclado.salir) {
                    System.exit(0);
                }
                if (teclado.letra_A) {
                    estado = 6;
                    bandera = true;
                    Dormir(200);
                }
                break;
            case 6:
                if (teclado.salir) {
                    try {
                        System.exit(0);
                    } catch (Exception e) {
                    }
                }
                if (teclado.letra_A && bulto.per.isBandEnergia() && bulto.per.isBandSalto()) {
                    if (bulto.per.getColision().intersects(bulto.pueblo.getCOL1())) {
                        bulto.per.setBandAtaque(true);
                        Dormir(10);
                    }
                } else if (teclado.letra_D && bulto.per.isBandMagia() && bulto.per.isBandSalto() && !bulto.per.isBandAtaqueDM()) {
                    if (bulto.per.getColision().intersects(bulto.pueblo.getCOL1())) {
                        bulto.per.setBandAtaqueD(true);
                        Dormir(10);
                    }
                } else if (teclado.Izq) {
                    bulto.per.Izq();
                    if (teclado.Izq && teclado.letra_S) {
                        bulto.per.DashIzq(7);
                    }
                    Dormir(10);
                } else if (teclado.Der) {
                    bulto.per.Der();
                    if (teclado.Der && teclado.letra_S) {
                        bulto.per.DashDer(7);
                    }
                    Dormir(10);
                }
                if (teclado.Up) {
                    if (bulto.per.isBandSalto()) {
                        bulto.per.Salto();
                        bulto.per.setBandASalto(true);
                        Dormir(10);
                    }
                }
                break;
            case 7:
                if (teclado.salir) {
                    System.exit(0);
                }
                if (teclado.letra_A) {
                    estado = 8;
                    bandera = true;
                    Dormir(200);
                }
                break;
            case 8:
                if (teclado.salir) {
                    try {
                        System.exit(0);
                    } catch (Exception e) {
                    }
                }
                if (teclado.letra_A && segundo_nivel.per.isBandEnergia() && segundo_nivel.per.isBandSalto()) {
                    if (segundo_nivel.pueblo.getCOL1() != null) {
                        segundo_nivel.per.setBandAtaque(true);
                    } else {
                        segundo_nivel.per.setBandAtaque(true);
                    }
                    Dormir(10);
                } else if (teclado.letra_D && segundo_nivel.per.isBandMagia() && segundo_nivel.per.isBandSalto() && !segundo_nivel.per.isBandAtaqueDM()) {
                    if (segundo_nivel.pueblo.getCOL1() != null) {
                        segundo_nivel.per.setBandAtaqueD(true);
                    } else {
                        segundo_nivel.per.setBandAtaqueD(true);
                    }
                    Dormir(10);
                } else if (teclado.Izq && segundo_nivel.isBandera()) {
                    segundo_nivel.per.Izq();
                    if (teclado.Izq && teclado.letra_S) {
                        segundo_nivel.per.DashIzq(7);
                    }
                    Dormir(10);
                } else if (teclado.Der) {
                    segundo_nivel.per.Der();
                    if (teclado.Der && teclado.letra_S) {
                        segundo_nivel.per.DashDer(7);
                    }
                    Dormir(10);
                }
                if (teclado.Up) {
                    if (segundo_nivel.per.isBandSalto()) {
                        segundo_nivel.per.Salto();
                        segundo_nivel.per.setBandASalto(true);
                        Dormir(10);
                    }
                }
                break;
            case 9:
                if (teclado.salir) {
                    System.exit(0);
                }
                if (teclado.letra_A) {
                    estado = 10;
                    bandera = true;
                    Dormir(200);
                }
                break;
            case 10:
                if (teclado.salir) {
                    try {
                        System.exit(0);
                    } catch (Exception e) {
                    }
                }
                if (teclado.letra_A && gigante.per.isBandEnergia() && gigante.per.isBandSalto()) {
                    if (gigante.per.getColision().intersects(gigante.pueblo.getCOL1())) {
                        gigante.per.setBandAtaque(true);
                        Dormir(10);
                    }
                } else if (teclado.letra_D && gigante.per.isBandMagia() && gigante.per.isBandSalto() && !gigante.per.isBandAtaqueDM()) {
                    if (gigante.per.getColision().intersects(gigante.pueblo.getCOL1())) {
                        gigante.per.setBandAtaqueD(true);
                        Dormir(10);
                    }
                } else if (teclado.Izq) {
                    gigante.per.Izq();
                    if (teclado.Izq && teclado.letra_S) {
                        gigante.per.DashIzq(7);
                    }
                    Dormir(10);
                } else if (teclado.Der) {
                    gigante.per.Der();
                    if (teclado.Der && teclado.letra_S) {
                        gigante.per.DashDer(7);
                    }
                    Dormir(10);
                }
                if (teclado.Up) {
                    if (gigante.per.isBandSalto()) {
                        gigante.per.Salto();
                        gigante.per.setBandASalto(true);
                        Dormir(10);
                    }
                }
                break;
            case 11:
                if (teclado.salir) {
                    System.exit(0);
                }
                if (teclado.letra_A) {
                    estado = 12;
                    bandera = true;
                    Dormir(200);
                }
                break;
            case 12:
                if (teclado.salir) {
                    try {
                        System.exit(0);
                    } catch (Exception e) {
                    }
                }
                if (teclado.letra_A && Personajes.per.isBandEnergia() && Personajes.per.isBandSalto()) {
                    if (Personajes.per.getColision().intersects(Personajes.pueblo.getCOL1())) {
                        Personajes.per.setBandAtaque(true);
                        Dormir(10);
                    }
                } else if (teclado.letra_D && Personajes.per.isBandMagia() && Personajes.per.isBandSalto() && !Personajes.per.isBandAtaqueDM()) {
                    if (Personajes.per.getColision().intersects(Personajes.pueblo.getCOL1())) {
                        Personajes.per.setBandAtaqueD(true);
                        Dormir(10);
                    }
                } else if (teclado.Izq) {
                    Personajes.per.Izq();
                    if (teclado.Izq && teclado.letra_S) {
                        Personajes.per.DashIzq(7);
                    }
                    Dormir(10);
                } else if (teclado.Der) {
                    Personajes.per.Der();
                    if (teclado.Der && teclado.letra_S) {
                        Personajes.per.DashDer(7);
                    }
                    Dormir(10);
                }
                if (teclado.Up) {
                    if (Personajes.per.isBandSalto()) {
                        Personajes.per.Salto();
                        Personajes.per.setBandASalto(true);
                        Dormir(10);
                    }
                }
                break;
            case 13:
                if (teclado.salir) {
                    System.exit(0);
                }
                if (teclado.letra_A) {
                    switch (personaje) {
                        case 1:
                            estado = 14;
                            bandera = true;
                            break;
                        case 2:
                            estado = 15;
                            bandera = true;
                            break;
                        case 3:
                            cinematicas.setEjecutar(false);
                            actual.interrupt();
                            remove(cinematicas);
                            estado = 0;
                            bandera = true;
                            break;
                    }
                    Dormir(200);
                }
                break;
            case 14:
                if (teclado.salir) {
                    try {
                        System.exit(0);
                    } catch (Exception e) {
                    }
                }
                if (teclado.letra_A && Personajes2.per.isBandEnergia() && Personajes2.per.isBandSalto()) {
                    if (Personajes2.per.getColision().intersects(Personajes2.pueblo.getCOL1())) {
                        Personajes2.per.setBandAtaque(true);
                        Dormir(10);
                    }
                } else if (teclado.letra_D && Personajes2.per.isBandMagia() && Personajes2.per.isBandSalto() && !Personajes2.per.isBandAtaqueDM()) {
                    if (Personajes2.per.getColision().intersects(Personajes2.pueblo.getCOL1())) {
                        Personajes2.per.setBandAtaqueD(true);
                        Dormir(10);
                    }
                } else if (teclado.Izq) {
                    Personajes2.per.Izq();
                    if (teclado.Izq && teclado.letra_S) {
                        Personajes2.per.DashIzq(7);
                    }
                    Dormir(10);
                } else if (teclado.Der) {
                    Personajes2.per.Der();
                    if (teclado.Der && teclado.letra_S) {
                        Personajes2.per.DashDer(7);
                    }
                    Dormir(10);
                }
                if (teclado.Up) {
                    if (Personajes2.per.isBandSalto()) {
                        Personajes2.per.Salto();
                        Personajes2.per.setBandASalto(true);
                        Dormir(10);
                    }
                }
                break;
            case 15:
                if (teclado.salir) {
                    System.exit(0);
                }
                if (teclado.letra_A) {
                    switch (personaje) {
                        case 1:
                            estado = 0;
                            bandera = true;
                            break;
                        case 2:
                            estado = 16;
                            bandera = true;
                            break;
                    }
                    Dormir(200);
                }
                break;
            case 16:
                if (teclado.salir) {
                    try {
                        System.exit(0);
                    } catch (Exception e) {
                    }
                }
                if (teclado.letra_A && Aseem.per.isBandEnergia() && Aseem.per.isBandSalto()) {
                    if (Aseem.per.getColision().intersects(Aseem.pueblo.getCOL1())) {
                        Aseem.per.setBandAtaque(true);
                        Dormir(10);
                    }
                } else if (teclado.letra_D && Aseem.per.isBandMagia() && Aseem.per.isBandSalto() && !Aseem.per.isBandAtaqueDM()) {
                    if (Aseem.per.getColision().intersects(Aseem.pueblo.getCOL1())) {
                        Aseem.per.setBandAtaqueD(true);
                        Dormir(10);
                    }
                } else if (teclado.Izq) {
                    Aseem.per.Izq();
                    if (teclado.Izq && teclado.letra_S) {
                        Aseem.per.DashIzq(7);
                    }
                    Dormir(10);
                } else if (teclado.Der) {
                    Aseem.per.Der();
                    if (teclado.Der && teclado.letra_S) {
                        Aseem.per.DashDer(7);
                    }
                    Dormir(10);
                }
                if (teclado.Up) {
                    if (Aseem.per.isBandSalto()) {
                        Aseem.per.Salto();
                        Aseem.per.setBandASalto(true);
                        Dormir(10);
                    }
                }
                break;
            case 17:
                if (teclado.salir) {
                    System.exit(0);
                }
                if (teclado.letra_A) {
                    estado = 0;
                    bandera = true;
                    Dormir(200);
                }
                break;
            default:
                break;
        }
        aps++;
    }

    private void mostrar() {
        fps++;
    }

    @Override
    public void run() {
        final int NS_POR_SEGUNDO = 1000000000;
        final byte APS_OBJETICO = 60;
        final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETICO;

        long referenciaActualizacion = System.nanoTime();
        long referenciaContador = System.nanoTime();

        double tiempoTrancurrido;
        double delta = 0;

        requestFocus();
        while (enFuncionamiento) {
            final long inicioBlucle = System.nanoTime();
            tiempoTrancurrido = inicioBlucle - referenciaActualizacion;
            referenciaActualizacion = inicioBlucle;

            delta += tiempoTrancurrido / NS_POR_ACTUALIZACION;
            while (delta >= 1) {
                delta--;
            }
            mostrar();
            if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {
                setTitle(NOMBRE + " || APS: " + aps + " || FPS: " + fps);
                System.out.println(fps);
                aps = 0;
                fps = 0;
                referenciaContador = System.nanoTime();
            }
            actualizar();
            switch (estado) {
                case 0:
                    if (bandera) {
                        actual.interrupt();
                        gameover = new GAME_OVER();
                        gameover.addKeyListener(teclado);
                        add(gameover.getP());
                        musica.setX(3);
                        switch (personaje) {
                            case 1:
                                musica.Pista(12);
                                break;
                            case 2:
                                musica.Pista(11);
                                break;
                            case 3:
                                musica.Pista(13);
                                break;
                        }
                        musica.Pista(4);
                        musica.setX(1);
                        actual = new Thread(gameover);
                        actual.start();
                        repaint();
                        bandera = false;
                    } else {
                    }
                    break;
                case 1:
                    if (bandera) {
                        gameover.setEjecutar(false);
                        actual.interrupt();
                        inicio = new Inicio();
                        inicio.addKeyListener(teclado);
                        remove(gameover);
                        add(inicio.getP());
                        musica.setX(3);
                        musica.Pista(1);
                        musica.setX(1);
                        actual = new Thread(inicio);
                        actual.start();
                        bandera = false;
                    } else {
                    }
                    break;
                case 2:
                    if (bandera) {
                        inicio.setEjecutar(false);
                        actual.interrupt();
                        seleccion = new Seleccion();
                        seleccion.addKeyListener(teclado);
                        remove(inicio);
                        add(seleccion.getP());
                        musica.setX(3);
                        musica.Pista(2);
                        musica.setX(1);
                        actual = new Thread(seleccion);
                        actual.start();
                        repaint();
                        bandera = false;
                    } else {

                    }
                    break;
                case 3:
                    if (bandera) {
                        musica.setX(3);
                        personaje = seleccion.getX();
                        seleccion.setEjecutar(false);
                        actual.interrupt();
                        cinematicas = new Cinematicas(personaje, 1);
                        cinematicas.addKeyListener(teclado);
                        remove(seleccion);
                        add(cinematicas.getP());
                        actual = new Thread(cinematicas);
                        actual.start();
                        repaint();
                        bandera = false;
                    } else {

                    }
                    break;
                case 4:
                    if (bandera) {
                        personaje = seleccion.getX();
                        cinematicas.setEjecutar(false);
                        actual.interrupt();
                        primer_Nivel = new Primer_Nivel(personaje);
                        primer_Nivel.addKeyListener(teclado);
                        remove(cinematicas);
                        add(primer_Nivel.getP());
                        musica.setX(3);
                        musica.Pista(3);
                        musica.setX(1);
                        actual = new Thread(primer_Nivel);
                        actual.start();
                        repaint();
                        bandera = false;
                    } else {
                        if (!primer_Nivel.isEjecutar()) {
                            primer_Nivel.setEjecutar(false);
                            actual.interrupt();
                            remove(primer_Nivel);
                            estado = 0;
                            bandera = true;
                        } else if (primer_Nivel.ciudad.getX() <= -6500) {
                            primer_Nivel.setEjecutar(false);
                            estado = 5;
                            bandera = true;
                        }
                    }
                    break;
                case 5:
                    if (bandera) {
                        musica.setX(3);
                        cinematicas = new Cinematicas(personaje, 2);
                        cinematicas.addKeyListener(teclado);
                        add(cinematicas.getP());
                        actual = new Thread(cinematicas);
                        actual.start();
                        repaint();
                        bandera = false;
                        bulto = new Nivel_Jefe(personaje);
                    } else {

                    }
                    break;
                case 6:
                    if (bandera) {
                        cinematicas.setEjecutar(false);
                        actual.interrupt();
                        bulto.addKeyListener(teclado);
                        remove(cinematicas);
                        add(bulto.getP());
                        musica.setX(3);
                        musica.Pista(4);
                        musica.setX(1);
                        actual = new Thread(bulto);
                        actual.start();
                        repaint();
                        bandera = false;
                    } else {
                        if (!bulto.isEjecutar()) {
                            bulto.setEjecutar(false);
                            actual.interrupt();
                            remove(bulto);
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Hero.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if (bulto.per.getVida() >= 1) {
                                estado = 7;
                            } else {
                                estado = 0;
                            }
                            bandera = true;
                        }
                    }
                    break;
                case 7:
                    if (bandera) {
                        musica.setX(3);
                        cinematicas = new Cinematicas(personaje, 3);
                        cinematicas.addKeyListener(teclado);
                        add(cinematicas.getP());
                        actual = new Thread(cinematicas);
                        actual.start();
                        repaint();
                        bandera = false;
                    } else {

                    }
                    break;
                case 8:
                    if (bandera) {
                        segundo_nivel = new Segundo_Nivel(personaje);
                        cinematicas.setEjecutar(false);
                        actual.interrupt();
                        segundo_nivel.addKeyListener(teclado);
                        remove(cinematicas);
                        add(segundo_nivel.getP());
                        musica.setX(3);
                        musica.Pista(5);
                        musica.setX(1);
                        actual = new Thread(segundo_nivel);
                        actual.start();
                        repaint();
                        bandera = false;
                    } else {
                        if (!segundo_nivel.isEjecutar() || segundo_nivel.pueblo.getYim() == -4440) {
                            segundo_nivel.setEjecutar(false);
                            actual.interrupt();
                            remove(segundo_nivel);
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Hero.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if (segundo_nivel.pueblo.getYim() == -4440) {
                                estado = 9;
                                bandera = true;
                            }
                        }
                    }
                    break;
                case 9:
                    if (bandera) {
                        musica.setX(3);
                        cinematicas = new Cinematicas(personaje, 4);
                        cinematicas.addKeyListener(teclado);
                        add(cinematicas.getP());
                        actual = new Thread(cinematicas);
                        actual.start();
                        repaint();
                        bandera = false;
                    } else {

                    }
                    break;
                case 10:
                    if (bandera) {
                        gigante = new Nivel_Jefe2(personaje);
                        cinematicas.setEjecutar(false);
                        actual.interrupt();
                        gigante.addKeyListener(teclado);
                        remove(cinematicas);
                        add(gigante.getP());
                        musica.setX(3);
                        musica.Pista(6);
                        musica.setX(1);
                        actual = new Thread(gigante);
                        actual.start();
                        repaint();
                        bandera = false;
                    } else {
                        if (!gigante.isEjecutar()) {
                            gigante.setEjecutar(false);
                            actual.interrupt();
                            remove(gigante);
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Hero.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if (gigante.per.getVida() >= 1) {
                                estado = 11;
                            } else {
                                estado = 0;
                            }
                            bandera = true;
                        }
                    }
                    break;
                case 11:
                    if (bandera) {
                        musica.setX(3);
                        cinematicas = new Cinematicas(personaje, 5);
                        cinematicas.addKeyListener(teclado);
                        add(cinematicas.getP());
                        actual = new Thread(cinematicas);
                        actual.start();
                        repaint();
                        bandera = false;
                    } else {

                    }
                    break;
                case 12:
                    if (bandera) {
                        Personajes = new Nivel_Jefe3(personaje);
                        cinematicas.setEjecutar(false);
                        actual.interrupt();
                        Personajes.addKeyListener(teclado);
                        remove(cinematicas);
                        add(Personajes.getP());
                        musica.setX(3);
                        switch (personaje) {
                            case 1:
                                musica.Pista(8);
                                break;
                            case 2:
                                musica.Pista(9);
                                break;
                            case 3:
                                musica.Pista(7);
                                break;
                        }
                        musica.setX(1);
                        actual = new Thread(Personajes);
                        actual.start();
                        repaint();
                        bandera = false;
                    } else {
                        if (!Personajes.isEjecutar()) {
                            Personajes.setEjecutar(false);
                            actual.interrupt();
                            remove(Personajes);
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Hero.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if (Personajes.per.getVida() >= 1) {
                                estado = 13;
                            } else {
                                estado = 0;
                            }
                            bandera = true;
                        }
                    }
                    break;
                case 13:
                    if (bandera) {
                        musica.setX(3);
                        cinematicas = new Cinematicas(personaje, 5);
                        cinematicas.addKeyListener(teclado);
                        add(cinematicas.getP());
                        actual = new Thread(cinematicas);
                        actual.start();
                        repaint();
                        bandera = false;
                    } else {

                    }
                    break;
                case 14:
                    if (bandera) {
                        Personajes2 = new Nivel_Jefe4();
                        cinematicas.setEjecutar(false);
                        actual.interrupt();
                        Personajes2.addKeyListener(teclado);
                        remove(cinematicas);
                        add(Personajes2.getP());
                        musica.setX(3);
                        musica.Pista(8);
                        musica.setX(1);
                        actual = new Thread(Personajes2);
                        actual.start();
                        repaint();
                        bandera = false;
                    } else {
                        if (!Personajes2.isEjecutar()) {
                            Personajes2.setEjecutar(false);
                            actual.interrupt();
                            remove(Personajes2);
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Hero.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if (Personajes2.per.getVida() >= 1) {
                                estado = 15;
                            } else {
                                estado = 0;
                            }
                            bandera = true;
                        }
                    }
                    break;
                case 15:
                    if (bandera) {
                        musica.setX(3);
                        cinematicas = new Cinematicas(personaje, 5);
                        cinematicas.addKeyListener(teclado);
                        add(cinematicas.getP());
                        actual = new Thread(cinematicas);
                        actual.start();
                        repaint();
                        bandera = false;
                    } else {

                    }
                    break;
                case 16:
                    if (bandera) {
                        Aseem = new Final();
                        cinematicas.setEjecutar(false);
                        actual.interrupt();
                        Aseem.addKeyListener(teclado);
                        remove(cinematicas);
                        add(Aseem.getP());
                        musica.setX(3);
                        musica.Pista(10);
                        musica.setX(1);
                        actual = new Thread(Aseem);
                        actual.start();
                        repaint();
                        bandera = false;
                    } else {
                        if (!Aseem.isEjecutar()) {
                            Aseem.setEjecutar(false);
                            actual.interrupt();
                            remove(Aseem);
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Hero.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if (Aseem.per.getVida() >= 1) {
                                estado = 17;
                            } else {
                                estado = 0;
                            }
                            bandera = true;
                        }
                    }
                    break;
                case 17:
                    if (bandera) {
                        musica.setX(3);
                        cinematicas = new Cinematicas(personaje, 5);
                        cinematicas.addKeyListener(teclado);
                        add(cinematicas.getP());
                        actual = new Thread(cinematicas);
                        actual.start();
                        repaint();
                        bandera = false;
                    } else {

                    }
                    break;
            }
        }
    }

    public void Dormir(int x) {
        try {
            Thread.sleep(x);
        } catch (InterruptedException ex) {
//            Logger.getLogger(Hero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
;
}
