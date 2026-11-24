package modelo;

/**
 * Cliente del POS, identificado por su número de documento.
 */
public class Cliente {

    private String documento;
    private String nombre;
    private String telefono;

    /**
     * Crea un cliente.
     *
     * @param documento número de documento
     * @param nombre nombre completo
     * @param telefono número de teléfono
     */
    public Cliente(String documento, String nombre, String telefono) {
        this.documento = documento;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    /**
     * Devuelve el número de documento.
     *
     * @return documento del cliente
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * Devuelve el nombre del cliente.
     *
     * @return nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Cambia el teléfono del cliente. Se ignora si es nulo o vacío.
     *
     * @param telefono nuevo teléfono
     */
    public void setTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            System.out.println("Teléfono inválido, no se modificó.");
        } else {
            this.telefono = telefono;
        }
    }

    /**
     * Describe el cliente en una línea.
     *
     * @return texto con documento, nombre y teléfono
     */
    @Override
    public String toString() {
        return "Doc: " + documento + " | " + nombre + " | Tel: " + telefono;
    }
}
