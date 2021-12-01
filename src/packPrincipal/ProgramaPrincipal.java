package packPrincipal;

import java.io.File;
import java.util.Scanner;

import packFuncionalidadImagenes.FuncionalidadImagenes;
import packTiposImagen.TipoImagenPGM;
import packTiposImagen.TipoImagenPPM;
import packTiposImagen.TipoListaImagenesPGM;

public class ProgramaPrincipal {
	public static final String CARPETA_IMAGENES_PPM = "PPMImages";
	public static final String CARPETA_IMAGENES_PGM = "PGMImages";
	public static final String CARPETA_GENERADAS = "GeneratedImages";

	public static final String IMG_1 = "mansion";
	public static final String[] IMG_SET_PPM = new String[] { "BigBeng",
			"CaprichoGaudi", "GoldenGate", "GoldenTemple", "Leuven", "Moscu",
			"MurallaChina", "ZabaletaDorrea" };
	public static final String[] IMG_SET_PGM = new String[] { "A_letra",
			"B_letra", "flor3", "mansion", "mar_contraluz" };
	public static final String[] IMG_SET_TRANS = new String[] { "Img1", "Img2",
			"Img3", "Img4", "Img5" };

	/**
	 * Programa principal - permite ejecutar las operaciones soportadas para
	 * comprobar que funcionan adecuadamente
	 */
	public static void main(String[] args) {
		inicializarCarpetas();
		int opcion = mostrarMenuYLeerOpcion();
		while (opcion != 0) {
			switch (opcion) {
			case 1:
				probarConvertirImagenGrises();

				break;
			case 2:
				probarNegativizar();
				break;
			case 3:
				probarRotar();
				break;
			case 4:
				probarCrearTransiciones();
				break;
			}
			opcion = mostrarMenuYLeerOpcion();
		}
	}

	/**
	 * Inicializa la carpeta donde se almacenan las imágenes generadas. Si ya
	 * existe, borra todos las imágenes almacenadas
	 */
	public static void inicializarCarpetas() {
		File carpeta = new File(CARPETA_GENERADAS);
		if (carpeta.exists()) {
			for (File fichero : carpeta.listFiles()) {
				fichero.delete();
			}
		} else {
			carpeta.mkdir();
		}

	}

	/**
	 * Pruebas para crear transiciones entre dos imágenes
	 */
	public static void probarCrearTransiciones() {
		try {
			System.out.println("Probando Crear Transiciones");
			for (int i = 0; i < IMG_SET_TRANS.length; i++) {
				int posFinal = (i + 1) % IMG_SET_TRANS.length;

				String fichOrig = String.format("%1$s/%2$s.pgm",
						CARPETA_IMAGENES_PGM, IMG_SET_TRANS[i]);
				String fichFinal = String.format("%1$s/%2$s.pgm",
						CARPETA_IMAGENES_PGM, IMG_SET_TRANS[posFinal]);

				System.out.println("\nProbando crear transiciones entre "
						+ fichOrig + " y " + fichFinal);

				TipoImagenPGM imOriginal = FuncionalidadImagenes
						.cargarImagenPGM(fichOrig);

				TipoImagenPGM imFinal = FuncionalidadImagenes
						.cargarImagenPGM(fichFinal);

				TipoListaImagenesPGM listaImagenes = FuncionalidadImagenes
						.crearTransiciones(imOriginal, imFinal, 10);

				for (int j = 0; j < listaImagenes.numImagenes; j++) {
					String fichTrans = String.format("%1$s/Trans%2$d_%3$d.pgm",
							CARPETA_GENERADAS, i, j);
					System.out.println("\nGuardando en: " + fichTrans);
					FuncionalidadImagenes.guardarImagen(fichTrans,
							listaImagenes.lista[j]);
				}

				listaImagenes = null;
				System.gc();
			}
			System.out.println("\nPrueba finalizada\n\n");
		} catch (Exception e) {
			e.printStackTrace();
			System.err
					.println("Error al probar crear transiciones de imágenes\n"
							+ e.getMessage());
		}
	}

	/**
	 * Pruebas para negativizar imágenes en escala de grises
	 */
	public static void probarNegativizar() {
		try {
			System.out.println("Probando negativizar imágenes");
			for (int i = 0; i < IMG_SET_PGM.length; i++) {
				String fich = String.format("%1$s/%2$s.pgm",
						CARPETA_IMAGENES_PGM, IMG_SET_PGM[i]);
				System.out.println("\nProbando negativizar imagen: " + fich);

				TipoImagenPGM imOriginal = FuncionalidadImagenes
						.cargarImagenPGM(fich);
				TipoImagenPGM imNueva = FuncionalidadImagenes
						.negativizar(imOriginal);
				String fichNeg = String.format("%1$s/Neg%2$s.pgm",
						CARPETA_GENERADAS, IMG_SET_PGM[i]);
				System.out.println("\nGuardando en: " + fichNeg);
				FuncionalidadImagenes.guardarImagen(fichNeg, imNueva);
				imNueva = null;
				System.gc();
			}
			System.out.println("\nPrueba finalizada\n\n");
		} catch (Exception e) {
			System.err.println("Error al probar negativizar imagenes\n"
					+ e.getMessage());
		}
	}

	/**
	 * Pruebas de convertir imágenes a escala de grises
	 */
	public static void probarConvertirImagenGrises() {
		TipoImagenPPM imOriginal = null;
		TipoImagenPGM imNueva = null;
		try {
			System.out.println("\nProbando convertir imagen en escala de grises");
			for (int i = 0; i < IMG_SET_PPM.length; i++) {
				String fich = String.format("%1$s/%2$s.ppm",
						CARPETA_IMAGENES_PPM, IMG_SET_PPM[i]);
				System.out.println("\nProcesando " + fich);
				imOriginal = FuncionalidadImagenes.cargarImagenPPM(fich);
				imNueva = FuncionalidadImagenes.convertirEscalaGris(imOriginal);
				String fichNuevo = String.format("%1$s/Gray_%2$s.pgm",
						CARPETA_GENERADAS, IMG_SET_PPM[i]);
				System.out.println("\nGuardando en: " + fichNuevo);
				FuncionalidadImagenes.guardarImagen(fichNuevo, imNueva);
				imNueva = null;
				imOriginal = null;
				System.gc();
			}
			System.out.println("\nPrueba finalizada\n\n");
		} catch (Exception e) {
			System.err
					.println("\nError al probar convertir imagenes en escala de grises\n"
							+ e.getMessage());
		}
	}

	/**
	 * Pruebas de rotar imagen
	 */
	public static void probarRotar() {
		String img = String
				.format("%1$s/%2$s.pgm", CARPETA_IMAGENES_PGM, IMG_1);
		try {
			System.out.println("\nProbando rotar imagen: " + img);
			System.out.println("\nRotando imagen " + img);
			TipoImagenPGM imOriginal = FuncionalidadImagenes
					.cargarImagenPGM(img);
			TipoImagenPGM imNueva = null;
			for (int ang = 15; ang <= 360; ang += 15) {
				System.out.println("\t" + ang + " grados");
				imNueva = FuncionalidadImagenes.rotarImagen(imOriginal, ang);
				String fichRot = String.format("%1$s/%2$s_%3$d.pgm",
						CARPETA_GENERADAS, IMG_1, ang);
				System.out.println("\nGuardando en: " + fichRot);
				FuncionalidadImagenes.guardarImagen(fichRot, imNueva);
				imNueva = null;
				System.gc();
			}
			System.out.println("\nPrueba finalizada\n\n");
		} catch (Exception e) {
			System.err.println("\nError al probar rotar imagenes\n"
					+ e.getMessage());
		}
	}

	/**
	 * Esta funcion muestra el menú; de opciones y devuelve la opción
	 * seleccionada por el usuario o la usuario
	 * <p>
	 * <strong>POST:</strong> El valor devuelto es un valor en el rango [0,4]
	 * </p>
	 * 
	 * @return la opción seleccionada por el usuario o la usuario
	 */
	public static int mostrarMenuYLeerOpcion() {
		Scanner entrada = new Scanner(System.in);
		final String MENU = "1.- Coloured images to grayscale\n"
				+ "2.- Negative images\n"
				+ "3.- Rotate images\n"
				+ "4.- Transitions between 2 images\n"
				+ "0.- Exit\n"
				+ "Choose the operation you want to do [0,4]";

		int opcion = 0;
		System.out.println(MENU);
		opcion = entrada.nextInt();
		while (opcion < 0 || opcion > 4) {
			System.err.println("Opción no válida");
			System.out.println(MENU);
			opcion = entrada.nextInt();

		}
		return opcion;
	}
}
