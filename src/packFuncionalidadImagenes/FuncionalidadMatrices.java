package packFuncionalidadImagenes;

/**
 * Funcionalidad asociada a las operaciones con matrices y vectores
 *
 */
public class FuncionalidadMatrices {
	/**
	 * Multiplica una matriz por un vector
	 * PRE: el número de columnas de la matriz es igual al número de filas del vector
	 * @param pMatr1 - una matriz de dimensiones MxN
	 * @param pCoord - un vector de dimensiones Nx1
	 * @return devuelve un vector de dimensiones Mx1
	 */
	public static float[] producto(float[][] pMatr1, float[] pCoord) {
		 float[] vectorProducto = new float[pMatr1.length];
	        for(int i=0; i<pMatr1.length; i++){
	            for(int j=0; j<pCoord.length; j++){
	                vectorProducto[i]+=pMatr1[i][j]*pCoord[j];
	            }
	        }
	        return vectorProducto;
	}


}
