package modelo;

/**
 * Tenis casual. Si es de edición limitada su precio sube 20%.
 */
public class TenisCasual extends Tenis {

    private boolean edicionLimitada;

    /**
     * Crea unos tenis casuales.
     *
     * @param codigo código único
     * @param nombre nombre del producto
     * @param marca marca
     * @param talla talla
     * @param color color
     * @param material material principal
     * @param precioBase precio base
     * @param existencias unidades disponibles
     * @param edicionLimitada true si es edición limitada
     */
    public TenisCasual(String codigo, String nombre, String marca, int talla, String color,
            String material, double precioBase, int existencias, boolean edicionLimitada) {
        super(codigo, nombre, marca, talla, color, material, precioBase, existencias);
        this.edicionLimitada = edicionLimitada;
    }

    /**
     * Precio base, con 20% adicional si es edición limitada.
     *
     * @return precio de venta
     */
    @Override
    public double calcularPrecioVenta() {
        if (edicionLimitada) {
            return getPrecioBase() * 1.20;
        }
        return getPrecioBase();
    }

    /**
     * Describe el producto indicando si es edición limitada.
     *
     * @return texto del producto
     */
    @Override
    public String toString() {
        return "Casual " + super.toString() + " | Ed. limitada: " + (edicionLimitada ? "Sí" : "No");
    }
}
