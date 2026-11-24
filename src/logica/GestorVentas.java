package logica;

import java.util.ArrayList;
import modelo.ItemVenta;
import modelo.MedioPago;
import modelo.Tenis;
import modelo.Venta;

/**
 * Administra las ventas: valida existencias, finaliza ventas y genera el reporte.
 */
public class GestorVentas {

    private ArrayList<Venta> ventas;

    /**
     * Crea un gestor sin ventas.
     */
    public GestorVentas() {
        this.ventas = new ArrayList<Venta>();
    }

    /**
     * Agrega un producto a la venta si hay existencias suficientes.
     * Tiene en cuenta lo que ya se agregó del mismo producto en esa venta.
     *
     * @param venta venta en curso
     * @param tenis producto a agregar
     * @param cantidad unidades solicitadas
     * @return true si se agregó, false si se rechazó
     */
    public boolean agregarItem(Venta venta, Tenis tenis, int cantidad) {
        if (venta == null || tenis == null) {
            return false;
        }
        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor que cero.");
            return false;
        }
        int yaEnVenta = 0;
        for (ItemVenta item : venta.getItems()) {
            if (item.getTenis() == tenis) {
                yaEnVenta += item.getCantidad();
            }
        }
        if (cantidad + yaEnVenta > tenis.getExistencias()) {
            System.out.println("Venta rechazada: existencias insuficientes de " + tenis.getNombre()
                    + ". Disponibles: " + tenis.getExistencias() + ", solicitadas: " + (cantidad + yaEnVenta));
            return false;
        }
        venta.agregarItem(new ItemVenta(tenis, cantidad));
        return true;
    }

    /**
     * Finaliza la venta: asigna el medio de pago, descuenta existencias y la guarda.
     *
     * @param venta venta a finalizar
     * @param medioPago medio de pago elegido
     */
    public void finalizarVenta(Venta venta, MedioPago medioPago) {
        if (venta == null || medioPago == null || venta.getItems().isEmpty()) {
            System.out.println("No se puede finalizar la venta.");
            return;
        }
        for (ItemVenta item : venta.getItems()) {
            Tenis t = item.getTenis();
            t.setExistencias(t.getExistencias() - item.getCantidad());
        }
        venta.setMedioPago(medioPago);
        ventas.add(venta);
    }

    /**
     * Lista las ventas realizadas.
     */
    public void listarVentas() {
        if (ventas.isEmpty()) {
            System.out.println("Aún no hay ventas.");
            return;
        }
        for (Venta v : ventas) {
            System.out.println(String.format("Recibo %04d | %s | Total: $%,.0f",
                    v.getNumeroRecibo(), v.getCliente().getNombre(), v.calcularTotal()));
        }
    }

    /**
     * Cuenta las ventas realizadas.
     *
     * @return número de ventas
     */
    public int contarVentas() {
        return ventas.size();
    }

    /**
     * Suma el total de todas las ventas.
     *
     * @return total vendido (con IVA)
     */
    public double calcularTotalVendido() {
        double suma = 0;
        for (Venta v : ventas) {
            suma += v.calcularTotal();
        }
        return suma;
    }
}
