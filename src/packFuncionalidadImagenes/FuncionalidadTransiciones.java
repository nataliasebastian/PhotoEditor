package packFuncionalidadImagenes;

import packTiposImagen.TipoImagenPGM;

public class FuncionalidadTransiciones {
	
	/**
	 * Devuelve la cantidad de transiciones que son necesarias para
	 * convertir la imagen inicial en la imagen final. 
	 * 
	 * PRE: pImagenInicial y pImagenFinal contienen imágenes en formato PGM ya cargadas. 
	 * Ambas imágenes tienen dimensiones iguales. pCoef tiene un valor entre 1 y 20.
	 * 
	 * @param pImagenInicial
	 *            la imagen inicial
	 * @param pImagenFinal
	 *            la imagen final
	 * @param pCoef
	 *            máximo en el que se cambia el valor de cada pixel de
	 *            una transición a otra
	 * @return la cantidad de transiciones a realizar.
	 *        
	 */
	
	public static int calcularCantidadTransiciones(TipoImagenPGM pImagenInicial, TipoImagenPGM pImagenFinal,
			int pCoef) {
		int numeroTransiciones;
		int maximaDiferencia = 0, diferenciaPixel;
		
		for (int i = 0; i < pImagenInicial.alto; i++) {
			for (int j = 0; j < pImagenInicial.ancho; j++) {
				diferenciaPixel = Math.abs(pImagenInicial.imagen[i][j] - pImagenFinal.imagen[i][j]);
				if (diferenciaPixel > maximaDiferencia) {
					maximaDiferencia = diferenciaPixel;
				}
			}
		}
		numeroTransiciones = (maximaDiferencia / pCoef);
		
		return numeroTransiciones;
	}
}
