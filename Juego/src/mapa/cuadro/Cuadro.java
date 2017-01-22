package mapa.cuadro;

import graficos.Pantalla;
import graficos.Sprite;

public class Cuadro {
	public int x;
	public int y;

	public Sprite sprite;

	public static final int LADO = 32;

	// colecci�on
	public static final Cuadro VACIO = new Cuadro(Sprite.VACIO);
	public static final Cuadro ASFALTO = new Cuadro(Sprite.ASFALTO);
	public static final Cuadro ARENA = new Cuadro(Sprite.ARENA);
	public static final Cuadro BORDE_CARRETERA = new Cuadro(Sprite.BORDE_CARRETERA);
	public static final Cuadro CENTRO_CARRETERA = new Cuadro(Sprite.CENTRO_CARRETERA);
	public static final Cuadro ESQUINA_CARRETERA = new Cuadro(Sprite.ESQUINA_CARRETERA);
	public static final Cuadro PARED_PIEDA = new Cuadro(Sprite.PARED_PIEDA);
	public static final Cuadro PARED_PIEDA_INFERIOR = new Cuadro(Sprite.PARED_PIEDA_INFERIOR);
	public static final Cuadro PARED_PIEDA_CARRETERA = new Cuadro(Sprite.PARED_PIEDA_CARRETERA);
	public static final Cuadro PUERTA_SUPERIOR_IZQUIERDA = new Cuadro(Sprite.PUERTA_SUPERIOR_IZQUIERDA);
	public static final Cuadro PUERTA_INTERMEDIA_IZQUIERDA = new Cuadro(Sprite.PUERTA_INTERMEDIA_IZQUIERDA);
	public static final Cuadro PUERTA_INFERIOR = new Cuadro(Sprite.PUERTA_INFERIOR);
	public static final Cuadro OXIDO = new Cuadro(Sprite.OXIDO);
	public static final Cuadro PUERTA_SUPERIOR_CENTRAL = new Cuadro(Sprite.PUERTA_SUPERIOR_CENTRAL);
	// fin colecci�n

	public Cuadro(Sprite sprite) {
		this.sprite = sprite;
	}

	public void mostrar(int x, int y, Pantalla pantalla) {
		pantalla.mostraCuadro(x << 5, y << 5, this);
	}

	public boolean solido() {
		return false;
	}

}
