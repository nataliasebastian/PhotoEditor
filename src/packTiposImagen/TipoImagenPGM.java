package packTiposImagen;

/**
 * Tipo de datos que representa una imagen en formato PGM
 * (escala de grises)
 */
public class TipoImagenPGM {
  /**
   * La anchura de la imagen
   */
   public int ancho;
   /**
    * La altura de la imagen
    */
   public int alto;
   /**
    * La escala de colores (tonos de gris) de la imagen. Normalmente toma el valor 255.
    */
   public int numColores;
   /**
    * La matriz de p&iacute;xeles que representa el contenido de la imagen. La matriz
    * tiene el n&uacute;mero de filas indicado en el campo <strong>ancho</strong> y el
    * n&uacute;mero de columnas es el indicado en el campo <strong>alto</strong>.
    * Cada elemento de la matriz contiene el tono de gris del pixel (un valor
    * entre 0 y 255).
    */
   public int imagen[][]; // alto, ancho
}
