package modelo;

/**
 * Tenis de senderismo. Si es impermeable su precio sube 15%.
 */
public class TenisSenderismo extends Tenis {

    private boolean impermeable;

    /**
     * Crea unos tenis de senderismo.
     *
     * @param codigo código único
     * @param nombre nombre del producto
     * @param marca marca
     * @param talla talla
     * @param color color
     * @param material material principal
     * @param precioBase precio base
     * @param existencias unidades disponibles
     * @param impermeable true si es impermeable
     */
    public TenisSenderismo(String codigo, String nombre, String marca, int talla, String color,
            String material, double precioBase, int existencias, boolean impermeable) {
        super(codigo, nombre, marca, talla, color, material, precioBase, existencias);
        this.impermeable = impermeable;
    }

    /**
     * Precio base, con 15% adicional si es impermeable.
     *
     * @return precio de venta
     */
    @Override
    public double calcularPrecioVenta() {
        if (impermeable) {
            return getPrecioBase() * 1.15;
        }
        return getPrecioBase();
    }

    /**
     * Describe el producto indicando si es impermeable.
     *
     * @return texto del producto
     */
    @Override
    public String toString() {
        return "Senderismo " + super.toString() + " | Impermeable: " + (impermeable ? "Sí" : "No");
    }
}
