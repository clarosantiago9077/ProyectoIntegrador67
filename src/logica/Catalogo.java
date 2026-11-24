package logica;

import java.util.ArrayList;
import modelo.Cliente;
import modelo.Tenis;

/**
 * Administra el catálogo de productos y los clientes del POS.
 */
public class Catalogo {

    /** Existencias por debajo de este valor se consideran bajas. */
    public static final int MINIMO_EXISTENCIAS = 5;

    private ArrayList<Tenis> productos;
    private ArrayList<Cliente> clientes;

    /**
     * Crea un catálogo vacío.
     */
    public Catalogo() {
        this.productos = new ArrayList<Tenis>();
        this.clientes = new ArrayList<Cliente>();
    }

    /**
     * Registra un producto si su código no existe.
     *
     * @param tenis producto a registrar
     */
    public void registrarProducto(Tenis tenis) {
        if (buscarProducto(tenis.getCodigo()) != null) {
            System.out.println("Ya existe un producto con el código " + tenis.getCodigo());
        } else {
            productos.add(tenis);
            System.out.println("Producto registrado: " + tenis.getNombre());
        }
    }

    /**
     * Lista todos los productos.
     */
    public void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }
        for (Tenis t : productos) {
            System.out.println(t);
        }
    }

    /**
     * Busca un producto por código.
     *
     * @param codigo código a buscar
     * @return el producto, o null si no existe
     */
    public Tenis buscarProducto(String codigo) {
        for (Tenis t : productos) {
            if (t.getCodigo().equalsIgnoreCase(codigo)) {
                return t;
            }
        }
        return null;
    }

    /**
     * Actualiza el precio base de un producto.
     *
     * @param codigo código del producto
     * @param precio nuevo precio base
     */
    public void actualizarPrecio(String codigo, double precio) {
        Tenis t = buscarProducto(codigo);
        if (t == null) {
            System.out.println("No se encontró el producto " + codigo);
        } else {
            t.setPrecioBase(precio);
            System.out.println("Precio base actual de " + t.getNombre() + ": $"
                    + String.format("%,.0f", t.getPrecioBase()));
        }
    }

    /**
     * Actualiza las existencias de un producto.
     *
     * @param codigo código del producto
     * @param existencias nuevas existencias
     */
    public void actualizarExistencias(String codigo, int existencias) {
        Tenis t = buscarProducto(codigo);
        if (t == null) {
            System.out.println("No se encontró el producto " + codigo);
        } else {
            t.setExistencias(existencias);
            System.out.println("Existencias actuales de " + t.getNombre() + ": " + t.getExistencias());
        }
    }

    /**
     * Lista los productos con existencias por debajo del mínimo.
     */
    public void listarExistenciasBajas() {
        boolean hay = false;
        for (Tenis t : productos) {
            if (t.getExistencias() < MINIMO_EXISTENCIAS) {
                System.out.println(t);
                hay = true;
            }
        }
        if (!hay) {
            System.out.println("No hay productos con existencias bajas (mínimo " + MINIMO_EXISTENCIAS + ").");
        }
    }

    /**
     * Registra un cliente si su documento no existe.
     *
     * @param cliente cliente a registrar
     */
    public void registrarCliente(Cliente cliente) {
        if (buscarCliente(cliente.getDocumento()) != null) {
            System.out.println("Ya existe un cliente con el documento " + cliente.getDocumento());
        } else {
            clientes.add(cliente);
            System.out.println("Cliente registrado: " + cliente.getNombre());
        }
    }

    /**
     * Lista todos los clientes.
     */
    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }
        for (Cliente c : clientes) {
            System.out.println(c);
        }
    }

    /**
     * Busca un cliente por documento.
     *
     * @param documento documento a buscar
     * @return el cliente, o null si no existe
     */
    public Cliente buscarCliente(String documento) {
        for (Cliente c : clientes) {
            if (c.getDocumento().equals(documento)) {
                return c;
            }
        }
        return null;
    }
}
