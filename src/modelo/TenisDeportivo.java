package modelo;

/**
 * Tenis deportivo (running, fútbol, entrenamiento, cancha).
 * Su precio sube 5% por cada nivel de resistencia.
 */
public class TenisDeportivo extends Tenis {

    private String tipoUso;
    private int nivelResistencia;

    /**
     * Crea unos tenis deportivos.
     *
     * @param codigo código único
     * @param nombre nombre del producto
     * @param marca marca
     * @param talla talla
     * @param color color
     * @param material material principal
     * @param precioBase precio base
     * @param existencias unidades disponibles
     * @param tipoUso deporte o uso (por ejemplo "Running")
     * @param nivelResistencia nivel de 1 a 5 (si es inválido se usa 1)
     */
    public TenisDeportivo(String codigo, String nombre, String marca, int talla, String color,
            String material, double precioBase, int existencias,
            String tipoUso, int nivelResistencia) {
        super(codigo, nombre, marca, talla, color, material, precioBase, existencias);
        this.tipoUso = tipoUso;
        if (nivelResistencia < 1 || nivelResistencia > 5) {
            this.nivelResistencia = 1;
        } else {
            this.nivelResistencia = nivelResistencia;
        }
    }

    /**
     * Precio base más 5% por cada nivel de resistencia.
     *
     * @return precio de venta
     */
    @Override
    public double calcularPrecioVenta() {
        return getPrecioBase() * (1 + 0.05 * nivelResistencia);
    }

    /**
     * Describe el producto agregando el uso y la resistencia.
     *
     * @return texto del producto
     */
    @Override
    public String toString() {
        return "Deportivo " + super.toString() + " | Uso: " + tipoUso
                + " | Resistencia: " + nivelResistencia;
    }
}
