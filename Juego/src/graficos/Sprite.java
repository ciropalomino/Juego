package graficos;

public final class Sprite {
	private final int lado;

	private int x;
	private int y;

	public int[] pixeles;
	private HojaSprites hoja;

	// Colecci�n de sprites del personaje
	public static final Sprite ABAJO0 = new Sprite(32, 0, 0, 0, HojaSprites.jugador);
	public static final Sprite ABAJO1 = new Sprite(32, 0, 1, 0, HojaSprites.jugador);
	public static final Sprite ABAJO2 = new Sprite(32, 0, 2, 0, HojaSprites.jugador);

	public static final Sprite ARRIBA0 = new Sprite(32, 1, 0, 0, HojaSprites.jugador);
	public static final Sprite ARRIBA1 = new Sprite(32, 1, 1, 0, HojaSprites.jugador);
	public static final Sprite ARRIBA2 = new Sprite(32, 1, 2, 0, HojaSprites.jugador);

	public static final Sprite DERECHA0 = new Sprite(32, 2, 0, 0, HojaSprites.jugador);
	public static final Sprite DERECHA1 = new Sprite(32, 2, 1, 0, HojaSprites.jugador);
	public static final Sprite DERECHA2 = new Sprite(32, 2, 2, 0, HojaSprites.jugador);

	public static final Sprite IZQUIERDA0 = new Sprite(32, 3, 0, 0, HojaSprites.jugador);
	public static final Sprite IZQUIERDA1 = new Sprite(32, 3, 1, 0, HojaSprites.jugador);
	public static final Sprite IZQUIERDA2 = new Sprite(32, 3, 2, 0, HojaSprites.jugador);
	// Fin de la colecci�n

	// Colecci�n de sprites del mapa des�rtico
	public static final Sprite VACIO = new Sprite(32, 0);
	public static final Sprite ASFALTO = new Sprite(32, 0, 0, 0, HojaSprites.desierto);
	public static final Sprite ARENA = new Sprite(32, 1, 0, 0, HojaSprites.desierto);
	public static final Sprite BORDE_CARRETERA_IZQUIERDO = new Sprite(32, 2, 0, 0, HojaSprites.desierto);
	public static final Sprite BORDE_CARRETERA_DERECHO = new Sprite(32, 2, 0, 1, HojaSprites.desierto);
	public static final Sprite BORDE_CARRETERA_INFERIOR = new Sprite(32, 2, 0, 4, HojaSprites.desierto);
	public static final Sprite BORDE_CARRETERA_SUPERIOR = new Sprite(32, 2, 0, 5, HojaSprites.desierto);
	public static final Sprite CENTRO_CARRETERA_VERTICAL = new Sprite(32, 3, 0, 0, HojaSprites.desierto);
	public static final Sprite CENTRO_CARRETERA_HORIZONTAL = new Sprite(32, 3, 0, 4, HojaSprites.desierto);
	public static final Sprite ESQUINA_CARRETERA_INFERIOR_IZQUIERDA = new Sprite(32, 4, 0, 0, HojaSprites.desierto);
	public static final Sprite ESQUINA_CARRETERA_INFERIOR_DERECHA = new Sprite(32, 4, 0, 1, HojaSprites.desierto);
	public static final Sprite ESQUINA_CARRETERA_SUPERIOR_IZQUIERDA = new Sprite(32, 4, 0, 5, HojaSprites.desierto);
	public static final Sprite ESQUINA_CARRETERA_SUPERIOR_DERECHA = new Sprite(32, 4, 0, 3, HojaSprites.desierto);
	public static final Sprite PARED_PIEDRA = new Sprite(32, 5, 0, 0, HojaSprites.desierto);
	public static final Sprite PARED_PIEDRA_INFERIOR = new Sprite(32, 6, 0, 0, HojaSprites.desierto);
	public static final Sprite PARED_PIEDRA_CARRETERA = new Sprite(32, 0, 3, 0, HojaSprites.desierto);
	public static final Sprite PARED_PIEDRA_CARRETERA_X_INVERTIDO = new Sprite(32, 0, 3, 1, HojaSprites.desierto);
	public static final Sprite PUERTA_SUPERIOR_IZQUIERDA = new Sprite(32, 7, 0, 0, HojaSprites.desierto);
	public static final Sprite PUERTA_SUPERIOR_DERECHA = new Sprite(32, 7, 0, 1, HojaSprites.desierto);
	public static final Sprite PUERTA_INTERMEDIA_IZQUIERDA = new Sprite(32, 7, 1, 0, HojaSprites.desierto);
	public static final Sprite PUERTA_INTERMEDIA_DERECHA = new Sprite(32, 7, 1, 1, HojaSprites.desierto);
	public static final Sprite PUERTA_INFERIOR = new Sprite(32, 8, 1, 0, HojaSprites.desierto);
	public static final Sprite OXIDO = new Sprite(32, 9, 0, 0, HojaSprites.desierto);
	public static final Sprite PUERTA_SUPERIOR_CENTRAL = new Sprite(32, 8, 0, 0, HojaSprites.desierto);
	// Fin de la colecci�n

	public int obtenLado() {
		return lado;
	}

	public Sprite(final int lado, final int columna, final int fila, final int version, final HojaSprites hoja) {
		this.lado = lado;

		pixeles = new int[lado * lado];

		this.x = columna * lado;
		this.y = fila * lado;
		this.hoja = hoja;

		cargarSprite(version);
	}

	public Sprite(final int lado, final int color) {
		this.lado = lado;
		pixeles = new int[lado * lado];

		for (int i = 0; i < pixeles.length; i++) {
			pixeles[i] = color;
		}
	}

	private void cargarSprite(int version) {
		if (version == 0) {
			cargaNormal();
		} else {
			cargaManipulada(version);
		}
	}

	private void cargaNormal() {
		for (int y = 0; y < lado; y++) {
			for (int x = 0; x < lado; x++) {
				pixeles[x + y * lado] = hoja.pixeles[(x + this.x) + ((y + this.y) * hoja.obtenAncho())];
			}
		}
	}

	private void cargaManipulada(int version) {
		int[] pixelesTemporales = iniciarPixelestemporales();
		switch (version) {
		case 1:
			invertirX(pixelesTemporales);
			break;
		case 2:
			invertirY(pixelesTemporales);
			break;
		case 3:
			invertirXY(pixelesTemporales);
			break;
		case 4:
			rotar90I(pixelesTemporales);
			break;
		case 5:
			rotar90D(pixelesTemporales);
			break;
		case 6:
			rotarI90InvertirY(pixelesTemporales);
			break;
		case 7:
			rotarD90InvertirY(pixelesTemporales);
			break;
		default:
			cargaNormal();
		}

	}

	private int[] iniciarPixelestemporales() {
		int[] pixelesTemporales = new int[lado * lado];
		for (int y = 0; y < lado; y++) {
			for (int x = 0; x < lado; x++) {
				pixelesTemporales[x + y * lado] = hoja.pixeles[(x + this.x) + (y + this.y) * hoja.obtenAncho()];
			}
		}
		return pixelesTemporales;
	}

	// 1
	private void invertirX(int[] pixelesTemporales) {
		int i = 0;
		for (int y = 0; y < lado; y++) {
			for (int x = lado - 1; x > -1; x--) {
				pixeles[i] = pixelesTemporales[x + y * lado];
				i++;
			}
		}
	}

	// 2
	private void invertirY(int[] pixelesTemporales) {
		int i = 0;
		for (int y = lado - 1; y > -1; y--) {
			for (int x = 0; x < lado; x++) {
				pixeles[i] = pixelesTemporales[x + y * lado];
				i++;
			}
		}
	}

	// 3
	private void invertirXY(int[] pixelesTemporales) {
		for (int i = 0; i < pixeles.length; i++) {
			pixeles[i] = pixelesTemporales[pixelesTemporales.length - 1 - i];
		}
	}

	// 4
	private void rotar90I(int[] pixelesTemporales) {
		int i = 0;
		for (int x = lado - 1; x > -1; x--) {
			for (int y = 0; y < lado; y++) {
				pixeles[i] = pixelesTemporales[x + y * lado];
				i++;
			}
		}
	}

	// 5
	private void rotar90D(int[] pixelesTemporales) {
		int i = 0;
		for (int x = 0; x < lado; x++) {
			for (int y = lado - 1; y > -1; y--) {
				pixeles[i] = pixelesTemporales[x + y * lado];
				i++;
			}
		}
	}

	// 6
	private void rotarI90InvertirY(int[] pixelesTemporales) {
		int i = 0;
		for (int x = 0; x < lado; x++) {
			for (int y = 0; y < lado; y++) {
				pixeles[i] = pixelesTemporales[x + y * lado];
				i++;
			}
		}
	}

	// 7
	private void rotarD90InvertirY(int[] pixelesTemporales) {
		int i = 0;
		for (int x = lado - 1; x > -1; x--) {
			for (int y = lado - 1; y > -1; y--) {
				pixeles[i] = pixelesTemporales[x + y * lado];
				i++;
			}
		}
	}
}
