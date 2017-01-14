package graficos; 


public class Pantalla { 


	private final int ancho; 
	private final int alto; 
	 
	public final int[] pixeles; 
	 
 	public Pantalla(final int ancho, final int alto){ 
 		this.ancho = ancho; 
 		this.alto  = alto; 
 		 
 		pixeles = new int [ancho*alto]; 
 	} 
 	 
 	public void limpiar(){ 
 		for(int i = 0; i < pixeles.length; i++){ 
 			pixeles[i]=0; 
 		} 
 	} 
 	 public void mostrar(){ 
 		  
 	 } 
 } 
