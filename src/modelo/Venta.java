package modelo;

import java.util.ArrayList;

/**
 * Venta del POS. Está compuesta por ítems y asociada a un cliente.
 * El consecutivo del recibo y el IVA son estáticos.
 */
public class Venta {

    /** Porcentaje de IVA (19%). */
    public static final double IVA = 0.19;

    private static int consecutivo = 1;

    private int numeroRecibo;
    private Cliente cliente;
    private ArrayList<ItemVenta> items;
    private MedioPago medioPago;

    /**
     * Crea una venta y le asigna el siguiente número de recibo.
     *
     * @param cliente cliente de la venta (o el cliente general)
     */
    public Venta(Cliente cliente) {
        this.numeroRecibo = consecutivo;
        consecutivo++;
        this.cliente = cliente;
        this.items = new ArrayList<ItemVenta>();
        this.medioPago = null;
    }

    /**
     * Devuelve el número de recibo.
     *
     * @return número consecutivo del recibo
     */
    public int getNumeroRecibo() {
        return numeroRecibo;
    }

    /**
     * Devuelve el cliente de la venta.
     *
     * @return cliente asociado
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Devuelve los ítems de la venta.
     *
     * @return lista de ítems
     */
    public ArrayList<ItemVenta> getItems() {
        return items;
    }

    /**
     * Agrega un ítem a la venta.
     *
     * @param item ítem a agregar
     */
    public void agregarItem(ItemVenta item) {
        items.add(item);
    }

    /**
     * Suma los subtotales de todos los ítems.
     *
     * @return subtotal de la venta sin IVA
     */
    public double calcularSubtotal() {
        double suma = 0;
        for (ItemVenta item : items) {
            suma += item.calcularSubtotal();
        }
        return suma;
    }

    /**
     * Calcula el IVA sobre el subtotal.
     *
     * @return valor del IVA
     */
    public double calcularIva() {
        return calcularSubtotal() * IVA;
    }

    /**
     * Calcula el total a pagar.
     *
     * @return subtotal más IVA
     */
    public double calcularTotal() {
        return calcularSubtotal() + calcularIva();
    }

    /**
     * Asigna el medio de pago de la venta.
     *
     * @param medioPago medio de pago elegido
     */
    public void setMedioPago(MedioPago medioPago) {
        this.medioPago = medioPago;
    }

    /**
     * Genera el recibo de la venta.
     *
     * @return texto del comprobante con ítems, subtotal, IVA, total y medio de pago
     */
    public String generarRecibo() {
        StringBuilder sb = new StringBuilder();
        sb.append("================ MCT PISOS ================\n");
        sb.append("Recibo No. ").append(String.format("%04d", numeroRecibo)).append("\n");
        sb.append("Cliente: ").append(cliente.getNombre())
                .append(" (Doc: ").append(cliente.getDocumento()).append(")\n");
        sb.append("-------------------------------------------\n");
        for (ItemVenta item : items) {
            sb.append(String.format("%-26s x%-3d $%,.0f%n",
                    item.getTenis().getNombre(), item.getCantidad(), item.calcularSubtotal()));
        }
        sb.append("-------------------------------------------\n");
        sb.append(String.format("Subtotal: $%,.0f%n", calcularSubtotal()));
        sb.append(String.format("IVA (%.0f%%): $%,.0f%n", IVA * 100, calcularIva()));
        sb.append(String.format("TOTAL: $%,.0f%n", calcularTotal()));
        if (medioPago != null) {
            sb.append("Medio de pago: ").append(medioPago.getNombre()).append("\n");
            sb.append(medioPago.procesarPago(calcularTotal())).append("\n");
        }
        sb.append("===========================================");
        return sb.toString();
    }
}
