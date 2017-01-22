package mapa;

import graficos.Pantalla;
import mapa.cuadro.Cuadro;

public abstract class Mapa {
	protected int ancho;
	protected int alto;

	protected int[] cuadros;

	public Mapa(int ancho, int alto) {
		this.ancho = ancho;
		this.alto = alto;
		cuadros = new int[ancho * alto];
		generarMapa();
	}

	public Mapa(String ruta) {
		cargaMapa(ruta);
	}

	protected void generarMapa() {

	}

	private void cargaMapa(String ruta) {

	}

	public void actualizar() {

	}

	public void mostrar(final int compensacionX, final int compensacionY, final Pantalla pantalla) {
		pantalla.estableceDiferencia(compensacionX, compensacionY);
		int o = compensacionX >> 5;
		int e = (compensacionX + pantalla.obtenAncho() + Cuadro.LADO) >> 5;
		int n = compensacionY >> 5;
		int s = (compensacionY + pantalla.obtenAlto() + Cuadro.LADO) >> 5;

		for (int y = n; y < s; y++) {
			for (int x = o; x < e; x++) {
				obtencuadro(x, y).mostrar(x, y, pantalla);
			}
		}
	}

	public Cuadro obtencuadro(final int x, final int y) {
		if (x < 0 || y < 0 || x >= ancho || y >= alto) {
			return Cuadro.VACIO;
		}
		switch (cuadros[x + y * ancho]) {
		case 0:
			return Cuadro.ASFALTO;
		case 1:
			return Cuadro.ARENA;
		case 2:
			return Cuadro.BORDE_CARRETERA;
		case 3:
			return Cuadro.CENTRO_CARRETERA;
		case 4:
			return Cuadro.ESQUINA_CARRETERA;
		case 5:
			return Cuadro.PARED_PIEDA;
		case 6:
			return Cuadro.PARED_PIEDA_INFERIOR;
		case 7:
			return Cuadro.PARED_PIEDA_CARRETERA;
		case 8:
			return Cuadro.PUERTA_SUPERIOR_IZQUIERDA;
		case 9:
			return Cuadro.PUERTA_INTERMEDIA_IZQUIERDA;
		case 10:
			return Cuadro.PUERTA_INFERIOR;
		case 11:
			return Cuadro.OXIDO;
		case 12:
			return Cuadro.PUERTA_SUPERIOR_CENTRAL;
		default:
			return Cuadro.VACIO;
		}
	}

}
