package entes.criaturas;

import entes.Ente;
import graficos.Sprite;

public abstract class Criatura extends Ente {
	protected Sprite sprite;
	protected char direccion = 'n';
	protected boolean enMovimiento = false;

	public void actualizar() {

	}

	public void mostrar() {

	}

	public void mover(int desplazamientoX, int desplazamientoY) {
		if (desplazamientoX > 0) {
			direccion = 'e';
		}
		if (desplazamientoX < 0) {
			direccion = 'o';
		}
		if (desplazamientoY > 0) {
			direccion = 's';
		}
		if (desplazamientoY < 0) {
			direccion = 'n';
		}

		if (!estaEliminado()) {
			if (!enColicion(desplazamientoX, 0))
				modificarPosicionX(desplazamientoX);
			if (!enColicion(0, desplazamientoY))
				modificarPosicionY(desplazamientoY);

		}
	}

	private boolean enColicion(int desplazamientoX, int desplazamientoY) {
		boolean colision = false;

		int posicionX = x + desplazamientoX;
		int posicionY = y + desplazamientoY;

		int margenIzquierdo = -6;
		int margenDerecho = 18;
		int margenSuperior = -4;
		int margeInferior = 31;

		int bordeIzquierdo = (posicionX + margenDerecho) / sprite.obtenLado();
		int bordeDerecho = (posicionX + margenIzquierdo) / sprite.obtenLado();
		int bordeSuperior = (posicionY + margeInferior) / sprite.obtenLado();
		int bordeInferior = (posicionY + margenSuperior) / sprite.obtenLado();
		if (mapa.obtenerCuadroCatalogo(bordeIzquierdo + (bordeSuperior * mapa.obtenerAncho())).esSolido()) {
			colision = true;
		}
		if (mapa.obtenerCuadroCatalogo(bordeIzquierdo + (bordeInferior * mapa.obtenerAncho())).esSolido()) {
			colision = true;
		}
		if (mapa.obtenerCuadroCatalogo(bordeDerecho + (bordeSuperior * mapa.obtenerAncho())).esSolido()) {
			colision = true;
		}
		if (mapa.obtenerCuadroCatalogo(bordeDerecho + (bordeInferior * mapa.obtenerAncho())).esSolido()) {
			colision = true;
		}

		return colision;
	}

	public Sprite obtenSprite() {
		return sprite;
	}
}
