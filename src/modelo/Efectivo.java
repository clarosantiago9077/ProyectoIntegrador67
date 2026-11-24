package modelo;

/**
 * Medio de pago en efectivo. Guarda el dinero recibido para calcular el cambio.
 */
public class Efectivo implements MedioPago {

    private double montoRecibido;

    /**
     * Crea un pago en efectivo.
     *
     * @param montoRecibido dinero entregado por el cliente (no puede ser negativo)
     */
    public Efectivo(double montoRecibido) {
        if (montoRecibido < 0) {
            this.montoRecibido = 0;
        } else {
            this.montoRecibido = montoRecibido;
        }
    }

    /**
     * Devuelve el nombre del medio de pago.
     *
     * @return la palabra "Efectivo"
     */
    @Override
    public String getNombre() {
        return "Efectivo";
    }

    /**
     * Calcula el cambio a devolver.
     *
     * @param total valor a pagar
     * @return detalle con el dinero recibido y el cambio, o aviso si no alcanza
     */
    @Override
    public String procesarPago(double total) {
        if (montoRecibido < total) {
            return "Pago insuficiente. Recibido: $" + String.format("%,.0f", montoRecibido);
        }
        double cambio = montoRecibido - total;
        return "Recibido: $" + String.format("%,.0f", montoRecibido)
                + " | Cambio: $" + String.format("%,.0f", cambio);
    }
}
