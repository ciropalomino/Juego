package juego;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import control.Teclado;
import graficos.Pantalla;

public class Juego extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	private static final int ANCHO = 800;
	private static final int ALTO = 600;
	private static volatile boolean enFuncionamiento = Boolean.TRUE;
	private static final String NOMBRE = "Juego";

	private static int aps = 0;
	private static int fps = 0;

	private static int x = 0;
	private static int y = 0;

	private static JFrame ventana;
	private static Thread thread;
	private static Teclado teclado;
	private static Pantalla pantalla;

	private static BufferedImage imagen = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);
	private static int[] pixeles = ((DataBufferInt) imagen.getRaster().getDataBuffer()).getData();

	private static final ImageIcon icono = new ImageIcon(Juego.class.getResource("/icono/iconoVentana.png"));

	private Juego() {
		setPreferredSize(new Dimension(ANCHO, ALTO));

		pantalla = new Pantalla(ANCHO, ALTO);

		teclado = new Teclado();
		addKeyListener(teclado);
		ventana = new JFrame(NOMBRE);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setResizable(false);
		ventana.setIconImage(icono.getImage());
		ventana.setLayout(new BorderLayout());
		ventana.add(this, BorderLayout.CENTER);
		ventana.pack();
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}

	public static void main(String[] args) {
		Juego juego = new Juego();
		juego.iniciar();
	}

	private synchronized void iniciar() {
		enFuncionamiento = Boolean.TRUE;
		thread = new Thread(this, "Graficos");
		thread.start();
	}

	private synchronized void detener() {
		enFuncionamiento = Boolean.FALSE;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void actualizar() {
		teclado.actualizar();
		if (teclado.arriba) {
			y++;
		}
		if (teclado.abajo) {
			y--;
		}
		if (teclado.izquierda) {
			x++;
		}
		if (teclado.derecha) {
			x--;
		}
		aps++;
	}

	private void mostrar() {
		BufferStrategy estrategia = getBufferStrategy();

		if (estrategia == null) {
			createBufferStrategy(3);
			return;
		}

		pantalla.limpiar();
		pantalla.mostrar(x, y);

		System.arraycopy(pantalla.pixeles, 0, pixeles, 0, pixeles.length);
		/*
		 * for(int i = 0;i < pixeles.length;i++){ pixeles[i] =
		 * pantalla.pixeles[i]; }
		 */
		Graphics g = estrategia.getDrawGraphics();

		g.drawImage(imagen, 0, 0, getWidth(), getHeight(), null);
		g.dispose();

		estrategia.show();

		fps++;
	}

	@Override
	public void run() {
		final int NS_POR_SEGUNDO = 1000000000;
		final byte APS_OBJETIVO = 60;
		final double NS_POR_ACTULIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;

		long referenciaActulizacion = System.nanoTime();
		long referenciaContador = System.nanoTime();

		double tiempoTranscurrido;
		double delta = 0;

		requestFocus();

		while (enFuncionamiento) {
			final long inicioBucle = System.nanoTime();
			tiempoTranscurrido = inicioBucle - referenciaActulizacion;
			referenciaActulizacion = inicioBucle;
			delta += tiempoTranscurrido / NS_POR_ACTULIZACION;
			while (delta >= 1) {
				actualizar();
				delta--;
			}
			mostrar();
			if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {
				ventana.setTitle(
						NOMBRE + " ||actualizaciones por segundo: " + aps + "||Frameworks por segundo:" + fps + "||");
				aps = 0;
				fps = 0;
				referenciaContador = System.nanoTime();
			}
		}
	}
}
