package mapa.cuadro;

import graficos.Pantalla;
import graficos.Sprite;

public class Cuadro {
	public int x;
	public int y;

	public Sprite sprite;

	public static final int LADO = 32;

	// coleccióon
	public static final Cuadro VACIO = new Cuadro(Sprite.VACIO);
	public static final Cuadro ASFALTO = new Cuadro(Sprite.ASFALTO);
	// fin colección

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
