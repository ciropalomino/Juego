package mapa;

import java.util.Random;

import graficos.Sprite;

public class MapaGenerado extends Mapa
{
	private final Random aleatorio = new Random();

	public MapaGenerado(int ancho, int alto) {
		super(ancho, alto);
	}
	
	protected void generarMapa(){
		for (int y = 0; y < alto; y++) {
			for (int x = 0; x < ancho; x++) {
				cuadros[x + y * ancho] = aleatorio.nextInt(3);
			}
		}
	}

}
