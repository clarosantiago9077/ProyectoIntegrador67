package modelo;

/**
 * Línea de una venta: un producto y la cantidad comprada.
 */
public class ItemVenta {

    private Tenis tenis;
    private int cantidad;

    /**
     * Crea un ítem de venta.
     *
     * @param tenis producto vendido
     * @param cantidad unidades (si es menor que 1 se usa 1)
     */
    public ItemVenta(Tenis tenis, int cantidad) {
        this.tenis = tenis;
        if (cantidad < 1) {
            this.cantidad = 1;
        } else {
            this.cantidad = cantidad;
        }
    }

    /**
     * Devuelve el producto del ítem.
     *
     * @return producto vendido
     */
    public Tenis getTenis() {
        return tenis;
    }

    /**
     * Devuelve la cantidad del ítem.
     *
     * @return unidades vendidas
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Calcula el subtotal del ítem usando el precio de venta del producto.
     *
     * @return precio de venta por cantidad
     */
    public double calcularSubtotal() {
        return tenis.calcularPrecioVenta() * cantidad;
    }
}
