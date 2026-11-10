
# MCT PISOS
## Creado por Santiago Claro Agudelo, Miguel Angel Blandon Zuluaga, TOMAS SANTIAGO GOMÉZ SARMIENTO 
nuestro negocio vende tenis de alta calidad de tipo running , casuales , futbol , entrenamiento , tenis de cancha y senderismo y nuestros productos el precio se diferencia por los materiales , diseño , resistencia y el tipo de uso y los medios de pago que nuestro negocio maneja
son efectivo , tarjeta solamente estos 2

# Identificación de clases

## Paquete `modelo`

| Clase | Atributos (private) | Métodos |
|---|---|---|
| **Tenis** (abstracta) | codigo, nombre, marca, talla, color, material, precioBase, existencias | constructor, get/set (set con validación), `calcularPrecioVenta()` abstracto, `toString()` |
| **TenisDeportivo** | tipoUso, nivelResistencia | constructor, `calcularPrecioVenta()` (+4% × nivel) |
| **TenisCasual** | edicionLimitada | constructor, `calcularPrecioVenta()` (+10% si es limitada) |
| **TenisSenderismo** | impermeable | constructor, `calcularPrecioVenta()` (×1,20 o ×1,08) |
| **Cliente** | documento, nombre, telefono | constructor, get, `setTelefono()`, `toString()` |
| **ItemVenta** | tenis, cantidad | constructor, get, `calcularSubtotal()` |
| **Venta** | numeroRecibo, cliente, items, medioPago, `static consecutivo`, `static final IVA` | constructor, `agregarItem()`, `calcularSubtotal()`, `calcularIva()`, `calcularTotal()`, `setMedioPago()`, `generarRecibo()` |
| **MedioPago** (interfaz) | - | `getNombre()`, `procesarPago(total)` |
| **Efectivo** | montoRecibido | `getNombre()`, `procesarPago()` (calcula el cambio) |
| **Tarjeta** | ultimosDigitos, cuotas | `getNombre()`, `procesarPago()` |

## Paquete `logica`

| Clase | Atributos | Métodos |
|---|---|---|
| **Catalogo** | `ArrayList<Tenis>` productos, `ArrayList<Cliente>` clientes | `registrarProducto()`, `listarProductos()`, `buscarProducto(codigo)`, `actualizarPrecio()`, `actualizarExistencias()`, `listarExistenciasBajas()`, `registrarCliente()`, `listarClientes()`, `buscarCliente(documento)` |
| **GestorVentas** | `ArrayList<Venta>` ventas | `agregarItem()`, `finalizarVenta()`, `listarVentas()`, `contarVentas()`, `calcularTotalVendido()` |

> Las búsquedas devuelven `null` cuando no encuentran el elemento.

## Paquete `ejecucion`

| Clase | Atributos | Métodos |
|---|---|---|
| **Main** | catalogo, gestorVentas | `main()`, `cargarDatos()`, `mostrarMenu()` y un método por opción del menú |

## Relaciones

| Tipo | Entre |
|---|---|
| Herencia | `TenisDeportivo`, `TenisCasual`, `TenisSenderismo` → `Tenis` |
| Realización | `Efectivo`, `Tarjeta` → `MedioPago` |
| Composición | `Venta` ◆— `ItemVenta` (1 a muchos) |
| Asociación | `Venta` → `Cliente`, `Venta` → `MedioPago`, `ItemVenta` → `Tenis` |
| Colecciones | `Catalogo` tiene muchos `Tenis` y `Cliente`; `GestorVentas` tiene muchas `Venta` |

## UML Diagrama
<img width="1642" height="2389" alt="UML_MCT" src="https://github.com/user-attachments/assets/f2b5cdfe-ef8b-4ce4-bb11-af7bcd0a069c" />

