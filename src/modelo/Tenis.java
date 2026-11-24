package modelo;

/**
 * Superclase abstracta de todos los tipos de tenis que vende MCT PISOS.
 * Cada subclase calcula su precio de venta de forma distinta.
 */
public abstract class Tenis {

    private String codigo;
    private String nombre;
    private String marca;
    private int talla;
    private String color;
    private String material;
    private double precioBase;
    private int existencias;

    /**
     * Crea un par de tenis. El precio y las existencias se validan (no negativos).
     *
     * @param codigo código único del producto
     * @param nombre nombre del producto
     * @param marca marca
     * @param talla talla
     * @param color color
     * @param material material principal
     * @param precioBase precio base antes de recargos o descuentos
     * @param existencias unidades disponibles
     */
    public Tenis(String codigo, String nombre, String marca, int talla, String color,
            String material, double precioBase, int existencias) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.marca = marca;
        this.talla = talla;
        this.color = color;
        this.material = material;
        this.precioBase = 0;
        this.existencias = 0;
        setPrecioBase(precioBase);
        setExistencias(existencias);
    }

    /**
     * Devuelve el código del producto.
     *
     * @return código del producto
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Devuelve el nombre del producto.
     *
     * @return nombre del producto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el precio base.
     *
     * @return precio base
     */
    public double getPrecioBase() {
        return precioBase;
    }

    /**
     * Devuelve las unidades disponibles.
     *
     * @return existencias
     */
    public int getExistencias() {
        return existencias;
    }

    /**
     * Cambia el precio base. No acepta valores negativos.
     *
     * @param precioBase nuevo precio base
     */
    public final void setPrecioBase(double precioBase) {
        if (precioBase < 0) {
            System.out.println("El precio no puede ser negativo. No se modificó.");
        } else {
            this.precioBase = precioBase;
        }
    }

    /**
     * Cambia las existencias. No acepta valores negativos.
     *
     * @param existencias nuevas existencias
     */
    public final void setExistencias(int existencias) {
        if (existencias < 0) {
            System.out.println("Las existencias no pueden ser negativas. No se modificó.");
        } else {
            this.existencias = existencias;
        }
    }

    /**
     * Calcula el precio de venta. Las subclases lo sobrescriben.
     *
     * @return precio de venta (en esta clase, el precio base)
     */
    public double calcularPrecioVenta() {
        return precioBase;
    }

    /**
     * Describe el producto en una línea.
     *
     * @return texto con los datos del producto y su precio de venta
     */
    @Override
    public String toString() {
        return "[" + codigo + "] " + nombre + " (" + marca + ") | Talla " + talla
                + " | " + color + " | " + material
                + " | Precio: $" + String.format("%,.0f", calcularPrecioVenta())
                + " | Existencias: " + existencias;
    }
}
