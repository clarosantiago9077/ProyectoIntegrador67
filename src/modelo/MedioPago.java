package modelo;

/**
 * Contrato que deben cumplir todos los medios de pago del POS.
 */
public interface MedioPago {

    /**
     * Devuelve el nombre del medio de pago.
     *
     * @return nombre del medio de pago (por ejemplo "Efectivo")
     */
    String getNombre();

    /**
     * Procesa el pago de un valor y describe el resultado.
     *
     * @param total valor a pagar
     * @return texto con el detalle del pago
     */
    String procesarPago(double total);
}
