package packFuncionalidadImagenes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import packTiposImagen.TipoImagenPGM;
import packTiposImagen.TipoImagenPPM;
import packTiposImagen.TipoListaImagenesPGM;
import packTiposImagen.TipoPixel;

/**
 * Funcionalidad asociada al tratamiento de las imágenes
 */
public class FuncionalidadImagenes {

	/**
	 * Este subpograma lee el contenido del fichero indicado y devuelve la
	 * imagen PPM correspondiente.
	 * 
	 * @param pFichero
	 *            - El fichero que contiene la imagen a cargar
	 * @return La imagen cargada
	 * @throws FileNotFoundException
	 *             si el fichero no existe
	 */
	public static TipoImagenPPM cargarImagenPPM(String pFichero) throws FileNotFoundException {

		try (Scanner entrada = new Scanner(new File(pFichero))) {
			TipoImagenPPM imagenCargada = new TipoImagenPPM();
			String cod = entrada.nextLine();
			String linea = entrada.nextLine();
			while (linea.startsWith("#") || linea.startsWith(" ")) {
				linea = entrada.nextLine();
			}
			Scanner lectorDimensiones = new Scanner(linea);
			imagenCargada.ancho = lectorDimensiones.nextInt();
			imagenCargada.alto = lectorDimensiones.nextInt();
			imagenCargada.numColores = entrada.nextInt();
			imagenCargada.imagen = new TipoPixel[imagenCargada.alto][imagenCargada.ancho];
			for (int i = 0; i < imagenCargada.alto; i++) {
				for (int j = 0; j < imagenCargada.ancho; j++) {
					imagenCargada.imagen[i][j] = new TipoPixel();
					imagenCargada.imagen[i][j].rojo = entrada.nextInt();
					imagenCargada.imagen[i][j].verde = entrada.nextInt();
					imagenCargada.imagen[i][j].azul = entrada.nextInt();
				}
			}
			return imagenCargada;
		}
	}

	/**
	 * Este subpograma lee el contenido del fichero indicado y devuelve la
	 * imagen PGM correspondiente.
	 * 
	 * @param pFichero
	 *            - El fichero que contiene la imagen a cargar
	 * @return La imagen cargada
	 * @throws FileNotFoundException
	 *             si el fichero no existe
	 */
	public static TipoImagenPGM cargarImagenPGM(String pFichero) throws FileNotFoundException {
		try (Scanner entrada = new Scanner(new File(pFichero))) {
			TipoImagenPGM imagenCargada = new TipoImagenPGM();

			String cod = entrada.nextLine();
			String linea = entrada.nextLine();
			while (linea.startsWith("#") || linea.startsWith(" ")) {
				linea = entrada.nextLine();
			}
			Scanner lectorDimensiones = new Scanner(linea);
			imagenCargada.ancho = lectorDimensiones.nextInt();
			imagenCargada.alto = lectorDimensiones.nextInt();
			imagenCargada.numColores = entrada.nextInt();
			imagenCargada.imagen = new int[imagenCargada.alto][imagenCargada.ancho];
			for (int i = 0; i < imagenCargada.alto; i++) {
				for (int j = 0; j < imagenCargada.ancho; j++) {
					imagenCargada.imagen[i][j] = entrada.nextInt();
				}
			}
			return imagenCargada;
		}
	}

	/**
	 * El subpprograma guarda la imagen PPM en el fichero indicado.
	 * 
	 * @param pFichero
	 *            - el fichero en el que se va a guardar la imagen
	 * @param pImagen
	 *            - la imagen a guardar
	 * @throws FileNotFoundException
	 *             - si la dirección indicada en pFichero no es
	 *             válida
	 */
	public static void guardarImagen(String pFichero, TipoImagenPPM pImagen) throws FileNotFoundException {
		try (PrintWriter salida = new PrintWriter(pFichero)) {
			salida.println("P3");
			salida.println("# Imagen generada por nuestro codigo");
			salida.println(String.format("%1$d %2$d", pImagen.ancho, pImagen.alto));
			salida.println(pImagen.numColores);
			for (int i = 0; i < pImagen.alto; i++) {
				for (int j = 0; j < pImagen.ancho; j++) {
					salida.println(pImagen.imagen[i][j].rojo);
					salida.println(pImagen.imagen[i][j].verde);
					salida.println(pImagen.imagen[i][j].azul);
				}
			}
		}
	}

	/**
	 * El subpprograma guarda la imagen PGM en el fichero indicado.
	 * 
	 * @param pFichero
	 *            - el fichero en el que se va a guardar la imagen
	 * @param pImagen
	 *            - la imagen a guardar
	 * @throws FileNotFoundException
	 *             - si la dirección indicada en pFichero no es
	 *             válida
	 */
	public static void guardarImagen(String pFichero, TipoImagenPGM pImagen) throws FileNotFoundException {

		try (PrintWriter salida = new PrintWriter(pFichero)) {
			salida.println("P2");
			salida.println("# Imagen nueva creada por mi programa");
			salida.println(pImagen.ancho + " " + pImagen.alto);
			salida.println(pImagen.numColores);
			for (int i = 0; i < pImagen.alto; i++) {
				System.out.print(".");
				for (int j = 0; j < pImagen.ancho; j++) {
					salida.println(pImagen.imagen[i][j]);
				}
			}
		}
	}

	/**
	 * Este subprograma devuelve la imagen convertida a escala de grises
	 * 
	 * PRE: pImagen contiene una imagen en formato PPM cargada
	 *
	 * @param pImagen
	 *            la imagen a convertir
	 * @return la imagen en escala de grises
	 */
	public static TipoImagenPGM convertirEscalaGris(TipoImagenPPM pImagen) {
		TipoImagenPGM imagenGenerada = new TipoImagenPGM();
		imagenGenerada.ancho=pImagen.ancho;
		imagenGenerada.alto=pImagen.alto;
		imagenGenerada.numColores=pImagen.numColores;
		imagenGenerada.imagen = new int[imagenGenerada.alto][imagenGenerada.ancho];
		for (int i = 0; i<imagenGenerada.alto; i++) {
			for (int j=0; j<imagenGenerada.ancho; j++) {
				imagenGenerada.imagen[i][j]= (int) ((int) pImagen.imagen[i][j].rojo*0.3f + 
						pImagen.imagen[i][j].verde*0.59f + pImagen.imagen[i][j].azul*0.11f);			 
			}
		}
		return imagenGenerada;
	}

	/**
	 * Devuelve la imagen que se obtiene al invertir los tonos de gris de la
	 * imagen original.
	 * 
	 * PRE: pImagen contiene una imagen en formato PGM ya cargada
	 * 
	 * @param pImagen
	 *            la imagen que se quiere negativizar
	 * @return la imagen negativizada
	 */
	public static TipoImagenPGM negativizar(TipoImagenPGM pImagen) {
		TipoImagenPGM imagenGenerada = new TipoImagenPGM();
		imagenGenerada.alto=pImagen.alto;
		imagenGenerada.ancho=pImagen.ancho;
		imagenGenerada.numColores=pImagen.numColores;
		imagenGenerada.imagen = new int[imagenGenerada.alto][imagenGenerada.ancho];
		for (int i = 0; i<imagenGenerada.alto; i++) {
			for (int j=0; j<imagenGenerada.ancho; j++) {
				imagenGenerada.imagen[i][j]=255-pImagen.imagen[i][j];
			}
		}
		return imagenGenerada;
	}

	/**
	 * Este subprograma rota la imagen pAng grados
	 * 
	 * PRE: pImagen contiene una imagen en formato PGM ya cargada
	 * 
	 * 
	 * @param pImagen
	 *            la imagen a rotar
	 * @param pAng
	 *            el angulo en grados a rotar (en el sentido del las agujas del reloj)
	 * @return la imagen rotada
	 */
	public static TipoImagenPGM rotarImagen(TipoImagenPGM imOrig, float pAng) {
		float anguloRadianes = FuncionalidadAngulos.convertirRadianes(pAng);
		float[][] matrizRotar = {{(float) Math.cos(anguloRadianes), (float) -Math.sin(anguloRadianes)}, {(float) Math.sin(anguloRadianes), (float) Math.cos(anguloRadianes)}};
		TipoImagenPGM imagenGenerada = new TipoImagenPGM();
		imagenGenerada.alto=imOrig.alto;
		imagenGenerada.ancho=imOrig.ancho;
		imagenGenerada.numColores = imOrig.numColores;
		imagenGenerada.imagen = new int[imOrig.alto][imOrig.ancho];
		for (int i = 0; i < imOrig.alto; i++) {
			for (int j = 0; j < imOrig.ancho; j++) {
				float[] imgCentro = {i-imOrig.alto/2, j-imOrig.ancho/2};
				float[] imgRotadaCentro = FuncionalidadMatrices.producto(matrizRotar, imgCentro);
				int[] imgRotadaEsquinaIzq = {Math.round(imgRotadaCentro[0] + imOrig.alto/2), Math.round(imgRotadaCentro[1] + imOrig.ancho/2)};
				if (imgRotadaEsquinaIzq[0] >= 0 && imgRotadaEsquinaIzq[1] >= 0 && imgRotadaEsquinaIzq[0] < imOrig.alto && imgRotadaEsquinaIzq[1] < imOrig.ancho) {
					imagenGenerada.imagen[i][j] = imOrig.imagen[imgRotadaEsquinaIzq[0]][imgRotadaEsquinaIzq[1]];
				}
				else {
					imagenGenerada.imagen[i][j] = 255;
				}
			}
		}
		return imagenGenerada;
	}

	/**
	 * Devuelve la lista de imágenes que componen las transiciones de la imagen inicial a la final
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
	 * @return la lista de imágenes que componen las transiciones de la
	 *         imagen original a la final, cambiando los píxeles en pCoef
	 *        
	 */
	public static TipoListaImagenesPGM crearTransiciones(TipoImagenPGM pImagenInicial, TipoImagenPGM pImagenFinal,
			int pCoef) {
		
		TipoListaImagenesPGM listaImagenes = FuncionalidadListas.crearListaImagenesPGM();
		TipoImagenPGM[] transiciones = new TipoImagenPGM[FuncionalidadTransiciones.calcularCantidadTransiciones(pImagenInicial, pImagenFinal, pCoef) + 1];	

		for (int contador = 0; contador <= FuncionalidadTransiciones.calcularCantidadTransiciones(pImagenInicial, pImagenFinal, pCoef); contador++) {
			transiciones[contador] = new TipoImagenPGM();
			transiciones[contador].alto=pImagenInicial.alto;
			transiciones[contador].ancho=pImagenInicial.ancho;
			transiciones[contador].numColores = pImagenInicial.numColores;
			transiciones[contador].imagen = new int[transiciones[contador].alto][transiciones[contador].ancho];
			
			if (contador == 0) {
				for(int i = 0; i < pImagenInicial.alto; i++) {
					for (int j = 0; j < pImagenInicial.ancho; j++) {
						transiciones[contador].imagen[i][j]= pImagenInicial.imagen[i][j];
					}
				}
			}
			else {
				for (int k = 0; k < pImagenInicial.alto; k++) {
					for (int l = 0; l < pImagenInicial.ancho; l++) {
						
						if (pImagenInicial.imagen[k][l] <= pImagenFinal.imagen[k][l] && (pImagenFinal.imagen[k][l] - transiciones[contador-1].imagen[k][l] > pCoef)) {
							transiciones[contador].imagen[k][l] = transiciones[contador-1].imagen[k][l] + pCoef;
						}
						
						else if (transiciones[contador-1].imagen[k][l] - pImagenFinal.imagen[k][l] > pCoef) {
							transiciones[contador].imagen[k][l] = transiciones[contador-1].imagen[k][l] - pCoef;
						}
							
						else {
							transiciones[contador].imagen[k][l] = pImagenFinal.imagen[k][l];
						}	
					}
				}		
			}	
			FuncionalidadListas.addImagen(listaImagenes, transiciones[contador]);
		}
			return listaImagenes;
	}	
}


	
	