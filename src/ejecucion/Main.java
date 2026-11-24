package ejecucion;

import java.util.Scanner;
import logica.Catalogo;
import logica.GestorVentas;
import modelo.Cliente;
import modelo.Efectivo;
import modelo.MedioPago;
import modelo.Tarjeta;
import modelo.Tenis;
import modelo.TenisCasual;
import modelo.TenisDeportivo;
import modelo.TenisSenderismo;
import modelo.Venta;

/**
 * Clase de ejecución del POS MCT PISOS: carga los datos y muestra el menú de consola.
 */
public class Main {

    private Catalogo catalogo;
    private GestorVentas gestorVentas;
    private Scanner entrada;

    /**
     * Crea el sistema con catálogo y gestor de ventas vacíos.
     */
    public Main() {
        this.catalogo = new Catalogo();
        this.gestorVentas = new GestorVentas();
        this.entrada = new Scanner(System.in);
    }

    /**
     * Punto de entrada del programa.
     *
     * @param args argumentos de consola (no se usan)
     */
    public static void main(String[] args) {
        Main app = new Main();
        app.cargarDatos();
        app.mostrarMenu();
    }

    /**
     * Carga los productos y clientes iniciales.
     */
    public void cargarDatos() {
        catalogo.registrarProducto(new TenisDeportivo("TD001", "Pegasus 40", "Nike", 42, "Negro",
                "Malla", 380000, 10, "Running", 3));
        catalogo.registrarProducto(new TenisDeportivo("TD002", "Predator", "Adidas", 41, "Blanco",
                "Sintético", 450000, 4, "Fútbol", 4));
        catalogo.registrarProducto(new TenisCasual("TC001", "Chuck 70", "Converse", 40, "Crema",
                "Lona", 320000, 8, false));
        catalogo.registrarProducto(new TenisCasual("TC002", "Old Skool Especial", "Vans", 42, "Negro",
                "Cuero", 290000, 3, true));
        catalogo.registrarProducto(new TenisSenderismo("TS001", "X Ultra", "Salomon", 43, "Gris",
                "Gore-Tex", 520000, 6, true));
        catalogo.registrarProducto(new TenisSenderismo("TS002", "Moab", "Merrell", 41, "Café",
                "Cuero", 410000, 12, false));

        catalogo.registrarCliente(new Cliente("0", "Cliente general", "N/A"));
        catalogo.registrarCliente(new Cliente("1053000111", "Laura Gómez", "3101234567"));
        catalogo.registrarCliente(new Cliente("1053000222", "Carlos Ruiz", "3207654321"));
        System.out.println();
    }

    /**
     * Muestra el menú principal y atiende las opciones hasta que se elija salir.
     */
    public void mostrarMenu() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n===== MCT PISOS - POS =====");
            System.out.println("1. Listar catálogo");
            System.out.println("2. Registrar producto");
            System.out.println("3. Buscar producto por código");
            System.out.println("4. Actualizar precio");
            System.out.println("5. Actualizar existencias");
            System.out.println("6. Listar existencias bajas");
            System.out.println("7. Registrar cliente");
            System.out.println("8. Listar clientes");
            System.out.println("9. Buscar cliente por documento");
            System.out.println("10. Nueva venta");
            System.out.println("11. Reporte de ventas");
            System.out.println("0. Salir");
            opcion = leerEntero("Opción: ");

            switch (opcion) {
                case 1:
                    catalogo.listarProductos();
                    break;
                case 2:
                    registrarProductoNuevo();
                    break;
                case 3:
                    buscarProducto();
                    break;
                case 4:
                    actualizarPrecio();
                    break;
                case 5:
                    actualizarExistencias();
                    break;
                case 6:
                    catalogo.listarExistenciasBajas();
                    break;
                case 7:
                    registrarClienteNuevo();
                    break;
                case 8:
                    catalogo.listarClientes();
                    break;
                case 9:
                    buscarCliente();
                    break;
                case 10:
                    nuevaVenta();
                    break;
                case 11:
                    reporteVentas();
                    break;
                case 0:
                    System.out.println("Hasta luego.");
                    break;
                default:
                    System.out.println("Opción inexistente. Intente de nuevo.");
            }
        }
    }

    private void registrarProductoNuevo() {
        System.out.println("Tipo: 1. Deportivo  2. Casual  3. Senderismo");
        int tipo = leerEntero("Tipo: ");
        if (tipo < 1 || tipo > 3) {
            System.out.println("Tipo inexistente.");
            return;
        }
        String codigo = leerTexto("Código: ");
        String nombre = leerTexto("Nombre: ");
        String marca = leerTexto("Marca: ");
        int talla = leerEntero("Talla: ");
        String color = leerTexto("Color: ");
        String material = leerTexto("Material: ");
        double precio = leerDecimal("Precio base: ");
        int existencias = leerEntero("Existencias: ");
        Tenis nuevo;
        if (tipo == 1) {
            String uso = leerTexto("Tipo de uso: ");
            int nivel = leerEntero("Nivel de resistencia (1-5): ");
            nuevo = new TenisDeportivo(codigo, nombre, marca, talla, color, material,
                    precio, existencias, uso, nivel);
        } else if (tipo == 2) {
            boolean limitada = leerSiNo("¿Edición limitada? (s/n): ");
            nuevo = new TenisCasual(codigo, nombre, marca, talla, color, material,
                    precio, existencias, limitada);
        } else {
            boolean impermeable = leerSiNo("¿Impermeable? (s/n): ");
            nuevo = new TenisSenderismo(codigo, nombre, marca, talla, color, material,
                    precio, existencias, impermeable);
        }
        catalogo.registrarProducto(nuevo);
    }

    private void buscarProducto() {
        Tenis t = catalogo.buscarProducto(leerTexto("Código: "));
        if (t != null) {
            System.out.println(t);
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private void actualizarPrecio() {
        String codigo = leerTexto("Código: ");
        double precio = leerDecimal("Nuevo precio base: ");
        catalogo.actualizarPrecio(codigo, precio);
    }

    private void actualizarExistencias() {
        String codigo = leerTexto("Código: ");
        int existencias = leerEntero("Nuevas existencias: ");
        catalogo.actualizarExistencias(codigo, existencias);
    }

    private void registrarClienteNuevo() {
        String documento = leerTexto("Documento: ");
        String nombre = leerTexto("Nombre: ");
        String telefono = leerTexto("Teléfono: ");
        catalogo.registrarCliente(new Cliente(documento, nombre, telefono));
    }

    private void buscarCliente() {
        Cliente c = catalogo.buscarCliente(leerTexto("Documento: "));
        if (c != null) {
            System.out.println(c);
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private void nuevaVenta() {
        Cliente cliente = catalogo.buscarCliente(leerTexto("Documento del cliente (0 = cliente general): "));
        if (cliente == null) {
            System.out.println("Cliente no registrado. Se usará el cliente general.");
            cliente = catalogo.buscarCliente("0");
        }
        Venta venta = new Venta(cliente);

        boolean seguir = true;
        while (seguir) {
            String codigo = leerTexto("Código del producto (vacío para terminar): ");
            if (codigo.isEmpty()) {
                seguir = false;
            } else {
                Tenis t = catalogo.buscarProducto(codigo);
                if (t == null) {
                    System.out.println("Producto no encontrado.");
                } else {
                    int cantidad = leerEntero("Cantidad: ");
                    if (gestorVentas.agregarItem(venta, t, cantidad)) {
                        System.out.println("Agregado: " + t.getNombre() + " x" + cantidad);
                    }
                }
            }
        }

        if (venta.getItems().isEmpty()) {
            System.out.println("Venta cancelada: no se agregaron productos.");
            return;
        }

        System.out.println(String.format("Subtotal: $%,.0f | IVA: $%,.0f | TOTAL: $%,.0f",
                venta.calcularSubtotal(), venta.calcularIva(), venta.calcularTotal()));
        MedioPago medio = elegirMedioPago(venta.calcularTotal());
        if (medio == null) {
            System.out.println("Venta cancelada.");
            return;
        }
        gestorVentas.finalizarVenta(venta, medio);
        System.out.println();
        System.out.println(venta.generarRecibo());
    }

    private MedioPago elegirMedioPago(double total) {
        MedioPago medio = null;
        boolean cancelar = false;
        while (medio == null && !cancelar) {
            System.out.println("Medio de pago: 1. Efectivo  2. Tarjeta  0. Cancelar venta");
            int op = leerEntero("Opción: ");
            if (op == 1) {
                double monto = leerDecimal("Monto recibido: ");
                if (monto < total) {
                    System.out.println("Monto insuficiente.");
                } else {
                    medio = new Efectivo(monto);
                }
            } else if (op == 2) {
                String digitos = leerTexto("Últimos 4 dígitos: ");
                int cuotas = leerEntero("Cuotas: ");
                medio = new Tarjeta(digitos, cuotas);
            } else if (op == 0) {
                cancelar = true;
            } else {
                System.out.println("Opción inexistente.");
            }
        }
        return medio;
    }

    private void reporteVentas() {
        System.out.println("--- Reporte de ventas ---");
        gestorVentas.listarVentas();
        System.out.println("Número de ventas: " + gestorVentas.contarVentas());
        System.out.println(String.format("Total vendido: $%,.0f", gestorVentas.calcularTotalVendido()));
    }

    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return entrada.nextLine().trim();
    }

    private int leerEntero(String mensaje) {
        System.out.print(mensaje);
        String linea = entrada.nextLine().trim();
        try {
            return Integer.parseInt(linea);
        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar un número entero.");
            return -1;
        }
    }

    private double leerDecimal(String mensaje) {
        System.out.print(mensaje);
        String linea = entrada.nextLine().trim();
        try {
            return Double.parseDouble(linea);
        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar un número.");
            return -1;
        }
    }

    private boolean leerSiNo(String mensaje) {
        String r = leerTexto(mensaje);
        return r.equalsIgnoreCase("s");
    }
}
