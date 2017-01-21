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
		int o = compensacionX >> 5;
		int e = (compensacionX + pantalla.obtenAncho()) >> 5;
		int n = compensacionY >> 5;
		int s = (compensacionY + pantalla.obtenAlto()) >> 5;
		pantalla.estableceDiferencia(compensacionX, compensacionY);

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
		case 2:
		case 3:
		default:
			return Cuadro.VACIO;
		}
	}

	public Cuadro obtenCuadro(final int x, final int y) {
		if (cuadros[x + y * ancho] == 0) {
			return Cuadro.ASFALTO;
		}
		return null;

	}

}
