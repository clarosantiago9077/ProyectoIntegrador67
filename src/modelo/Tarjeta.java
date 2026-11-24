package modelo;

/**
 * Medio de pago con tarjeta. Guarda los últimos dígitos y el número de cuotas.
 */
public class Tarjeta implements MedioPago {

    private String ultimosDigitos;
    private int cuotas;

    /**
     * Crea un pago con tarjeta.
     *
     * @param ultimosDigitos últimos dígitos de la tarjeta
     * @param cuotas número de cuotas (si es menor que 1 se usa 1)
     */
    public Tarjeta(String ultimosDigitos, int cuotas) {
        this.ultimosDigitos = ultimosDigitos;
        if (cuotas < 1) {
            this.cuotas = 1;
        } else {
            this.cuotas = cuotas;
        }
    }

    /**
     * Devuelve el nombre del medio de pago.
     *
     * @return la palabra "Tarjeta"
     */
    @Override
    public String getNombre() {
        return "Tarjeta";
    }

    /**
     * Describe el cobro con tarjeta y el valor de cada cuota.
     *
     * @param total valor a pagar
     * @return detalle con los últimos dígitos, las cuotas y el valor por cuota
     */
    @Override
    public String procesarPago(double total) {
        double valorCuota = total / cuotas;
        return "Tarjeta ****" + ultimosDigitos + " | " + cuotas
                + " cuota(s) de $" + String.format("%,.0f", valorCuota);
    }
}
