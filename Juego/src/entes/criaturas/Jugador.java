package entes.criaturas;

import control.Teclado;
import graficos.Pantalla;
import graficos.Sprite;
import mapa.Mapa;

public class Jugador extends Criatura {
	private Teclado teclado;

	private int animacion;

	public Jugador(Mapa mapa, Teclado teclado, Sprite sprite) {
		this.mapa = mapa;
		this.teclado = teclado;
		this.sprite = sprite;
	}

	public Jugador(Mapa mapa, Teclado teclado, Sprite sprite, int posicionX, int posicionY) {
		this.mapa = mapa;
		this.teclado = teclado;
		this.sprite = sprite;
		this.x = posicionX;
		this.y = posicionY;
	}

	public void actualizar() {
		int desplazamientoX = 0;
		int desplazamientoY = 0;

		int velocidadMovimiento = 1;

		animacion = (animacion < 32767) ? animacion + 1 : 0;

		if (teclado.correr)
			velocidadMovimiento = 2;

		if (teclado.arriba) {
			desplazamientoY -= velocidadMovimiento;
		}
		if (teclado.abajo) {
			desplazamientoY += velocidadMovimiento;
		}
		if (teclado.izquierda) {
			desplazamientoX -= velocidadMovimiento;
		}
		if (teclado.derecha) {
			desplazamientoX += velocidadMovimiento;
		}

		if (desplazamientoX != 0 || desplazamientoY != 0) {
			mover(desplazamientoX, desplazamientoY);
			enMovimiento = true;
		} else {
			enMovimiento = false;
		}
		int resto = animacion % 40;
		if (direccion == 'n') {
			sprite = Sprite.ARRIBA0;
			if (enMovimiento) {
				if (resto > 10 && resto <= 20) {
					sprite = Sprite.ARRIBA1;
				} else if (resto > 20 && resto <= 30) {
					sprite = Sprite.ARRIBA0;
				} else if (resto < 30) {
					sprite = Sprite.ARRIBA2;
				} else {
					sprite = Sprite.ARRIBA0;
				}
			}
		}
		if (direccion == 's') {
			sprite = Sprite.ABAJO0;
			if (enMovimiento) {
				if (resto > 10 && resto <= 20) {
					sprite = Sprite.ABAJO1;
				} else if (resto > 20 && resto <= 30) {
					sprite = Sprite.ABAJO0;
				} else if (resto < 30) {
					sprite = Sprite.ABAJO2;
				} else {
					sprite = Sprite.ABAJO0;
				}
			}
		}
		if (direccion == 'o') {
			sprite = Sprite.IZQUIERDA0;
			if (enMovimiento) {
				if (resto > 10 && resto <= 20) {
					sprite = Sprite.IZQUIERDA1;
				} else if (resto > 20 && resto <= 30) {
					sprite = Sprite.IZQUIERDA0;
				} else if (resto < 30) {
					sprite = Sprite.IZQUIERDA2;
				} else {
					sprite = Sprite.IZQUIERDA0;
				}
			}
		}
		if (direccion == 'e') {
			sprite = Sprite.DERECHA0;
			if (enMovimiento) {
				if (resto > 10 && resto <= 20) {
					sprite = Sprite.DERECHA1;
				} else if (resto > 20 && resto <= 30) {
					sprite = Sprite.DERECHA0;
				} else if (resto < 30) {
					sprite = Sprite.DERECHA2;
				} else {
					sprite = Sprite.DERECHA0;
				}
			}
		}
	}

	public void mostrar(Pantalla pantalla) {
		pantalla.mostrarJugador(x, y, this);
	}

}
