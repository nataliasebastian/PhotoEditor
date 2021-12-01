package packFuncionalidadImagenes;

import packTiposImagen.TipoImagenPGM;
import packTiposImagen.TipoListaImagenesPGM;

/**
 * Funcionalidad básica para la gestión de listas de imágenes.
 *
 */
public class FuncionalidadListas {
	/**
	 * Este metodo crea una lista vacia de imágenes PGM
	 * @return la lista de imágenes PGM
	 */
	public static TipoListaImagenesPGM crearListaImagenesPGM() {
		TipoListaImagenesPGM lista = new TipoListaImagenesPGM();
		lista .numImagenes = 0;
		lista.lista = new TipoImagenPGM[5];
		return lista ;
	}
	
	/**
	 * Este metodo añade una imagen a la lista. Si es necesario, amplía el tamañoo de la lista
	 * @param pLista la lista de imágenes
	 * @param pImagen la imagen
	 */
	public static void addImagen(TipoListaImagenesPGM pLista,
			TipoImagenPGM pImagen) {
		if (pLista.numImagenes == pLista.lista.length) {
			TipoImagenPGM[] lNueva = new TipoImagenPGM[pLista.numImagenes * 2];
			for (int i = 0; i < pLista.lista.length; i++) {
				lNueva[i] = pLista.lista[i];
			}
			pLista.lista = lNueva;
		}
		pLista.lista[pLista.numImagenes++] = pImagen;

	}
}
