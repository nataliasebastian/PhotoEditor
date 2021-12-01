package packFuncionalidadImagenes;
/**
 * Funcionalidad asociada a las operaciones con ángulos.
 *
 */
public class FuncionalidadAngulos {
	/**
	 * Este subpograma convierte ángulos de grados a radianes.
	 * 
	 * @param pAng
	 *            Recoge el ángulo en grados
	 * @return Devuelve el ángulo en radianes
	 */
	public static float convertirRadianes(float pAng) {
		float anguloRadianes = (float) (pAng * Math.PI/180);
		return anguloRadianes;
	}

}
