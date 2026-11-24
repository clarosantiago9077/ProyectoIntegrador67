
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
<img width="1462" height="1765" alt="oe" src="https://github.com/user-attachments/assets/a1efa47d-37e4-427b-9b61-1a3305cf4fd2" />

## Cómo ejecutar el sistema

### Requisitos
- JDK 8 o superior
- NetBeans (cualquier versión reciente)

### Pasos en NetBeans
1. Clona el repositorio o descarga el ZIP y descomprímelo:
   ```
   git clone <URL-del-repositorio>
   ```
2. En NetBeans: `File > Open Project`, y selecciona la carpeta del proyecto (la que contiene `nbproject`, `build.xml` y `src`).
3. Si aparece un aviso de problemas, clic derecho en el proyecto > `Resolve Project Problems`, o revisa que la plataforma Java sea la de tu JDK.
4. Verifica la codificación: clic derecho en el proyecto > `Properties > Sources > Encoding > UTF-8` (para que se vean bien las tildes).
5. Clic derecho en el proyecto > `Clean and Build`.
6. Ejecuta con `Run Project` (F6). Si NetBeans pregunta por la clase principal, elige `ejecucion.Main`.

### Ejecución por consola (opcional)
Desde la carpeta `src`:
```
javac -encoding UTF-8 -d ../out ejecucion/*.java logica/*.java modelo/*.java
java -cp ../out ejecucion.Main
```

### Uso
Al iniciar, el sistema carga 6 productos y 3 clientes de ejemplo y muestra el menú:

| Opción | Función |
|---|---|
| 1 | Listar catálogo |
| 2 | Registrar producto |
| 3 | Buscar producto por código |
| 4 | Actualizar precio |
| 5 | Actualizar existencias |
| 6 | Listar existencias bajas |
| 7 | Registrar cliente |
| 8 | Listar clientes |
| 9 | Buscar cliente por documento |
| 10 | Nueva venta |
| 11 | Reporte de ventas |
| 0 | Salir |

Para una venta: elige la opción 10, ingresa el documento del cliente (`0` = cliente general), agrega productos por código y cantidad (deja el código vacío para terminar) y elige el medio de pago (efectivo o tarjeta). Se mostrará el recibo con su número consecutivo y el IVA del 19 %.

> Los datos se guardan solo en memoria: al cerrar el programa se pierden.


