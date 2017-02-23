
package ferreteria;

/**
 *En esta clase se describen todas las interacciones que se envían al usuario y
 * en algunos casos parte de la información que se solicita.
 * @author javr
 */
public class MensajesUsuario {
   Teclado teclado = new Teclado();
   ProductoArray proArray = new ProductoArray();
   VentaDetalle venta = new VentaDetalle();
   int recordar;
   
   
    /**
     * Función para mostrar el menú al usuario del inventario de la ferretería
     */
    public void menuFerreteria(){
        System.out.println("\n\t\t\033[36mProductos Ferretería\n");
        System.out.println("1)Agregar producto\t\t 2)Eliminar producto");
        System.out.println("3)Editar producto\t\t 4)Realizar búsqueda");
        System.out.println("5)Mostrar odenado por nombre\t 6)Suma activos en productos");
        System.out.println("\033[33m0)Regresar");
    }
    /**
     * Función para mostrar el menú principal del programa  
     */
    public void menuPrincipal(){
        System.out.println("\033[34m_________________BIENVENIDO_________________");
        System.out.println("1)Inventario\t2)Venta");
        System.out.println("\033[33m0)Salir");
        System.out.println("\033[34m____________________________________________");
    }
    /**
     * Función para mostrar el menú de ventas
     */
    public void menuVentas(){
        System.out.println("\n\t\t\033[36mVenta productos\n");
        System.out.println("1)Registrar venta\t\t 2)Resumen de productos vendidos");
        System.out.println("3)Consultar código de producto ");
        System.out.println("\033[33m0)Regresar");
        
    }
    
    /**
     * Retorna la opción elegida por el usuario
     * @return entero de la opcion elegida
     */
    public int leerOpcion(){
        System.out.println("\n¿Cual es tu opción?");
        return teclado.leerEnteros();
    }
    /**
     * Función para avanzar entre menú principal y submenú, recibe la opción del 
     * usuario. 
     * @param opc
     * @throws InterruptedException Aplicado para no tener problemas en el detalle 
     * gráfico de los puntos (diseño). 
     */
    public void realizarOpcionPrincipal(int opc) throws InterruptedException{
        switch (opc) {
            case 1:
                menuFerreteria();
                break;
            case 2:
                menuVentas();
                break;
            case 0:
                
                break;
            default:
                System.out.println("Esa opción no tiene sentido, no soy mago :(");
                break;
        }
    }
    /**
     * La siguiente función lanza la opción que ha seleccionado el usuario en el 
     * submenú de Ferretería (almacén)
     * @param opc la respuesta del usuario
     */
    public void realizarOpcionFerreteria(int opc) throws InterruptedException{
        int check = 2;
        switch (opc){
            //OPCIÓN AGREGAR PRODUCTO
            case 1:
                System.out.println("¿Añadirás un producto nuevo? 1)Sí 2)No");
                int res = teclado.leerEnteros();
                switch (res) {
                    case 1:
                        proArray.agregarProducto();
                        break;
                    case 2:
                        System.out.println("¿Recuerdas la clave del producto a ingresar? 1)Sí 2)No");
                        recordar = teclado.leerEnteros();
                        if (recordar == 1) {
                            System.out.print("Escribe la clave: ");
                            int clave = teclado.leerEnteros();
                            int posicion = proArray.buscarClaveAux(clave);
                            if (posicion != -1) {
                                System.out.println("¿Este es el producto a aumentar? 1)sí 2)No");
                                check = teclado.leerEnteros();
                                if (check == 1) {
                                    System.out.println("¿Cuántas unidades más se agregarán?");
                                    int cant = teclado.leerEnteros();
                                    proArray.aumentarProducto(posicion, cant);
                                    proArray.guardarCasoEsp();
                                }
                            } else {
                             System.out.println("Algo debe estar mal, esa clave NO existe");
                            }
                        } else {
                            
                            int posicion;
                            teclado.leerString();
                            while (check == 2) {
                                System.out.println("Escribe una palabra clave del producto: ");
                                String busDescripcion = teclado.leerString();
                                posicion = proArray.buscarDescripcionAux(busDescripcion);
                                if (posicion != -1) {
                                    System.out.println("¿Este es el producto a aumentar? 1)sí 2)No");
                                    check = teclado.leerEnteros();
                                    if (check == 1) {
                                        System.out.println("¿Cuántas unidades más se agregarán?");
                                        int cant = teclado.leerEnteros();
                                        proArray.aumentarProducto(posicion, cant);
                                    }
                                } else {
                                    System.out.println("No hay coinciencias");
                                }
                            }
                        }
                    break;
                }
                
                break;
            //OPCIÓN ELIMINAR PRODUCTO
            case 2:
                
                System.out.println("¿Recuerdas la clave del producto a eliminar? 1)Sí 2)No");
                recordar = teclado.leerEnteros();
                if (recordar == 1) {
                    System.out.print("Escribe la clave: ");
                    int clave = teclado.leerEnteros();
                    int posicion = proArray.buscarClaveAux(clave);
                    if (posicion != -1) {
                        System.out.println("¿Este es el producto a eliminar? 1)sí 2)No");
                        check = teclado.leerEnteros();
                        if (check == 1) {  
                           proArray.eliminarProducto(posicion);
                        }
                    } else {
                        System.out.println("Tus intenciones eran malas, esa clave NO existe");
                    }
                } else {
                    
                    int posicion;
                    teclado.leerString();
                    while (check == 2) {
                        System.out.println("Escribe una palabra clave del producto: ");
                        String busDescripcion = teclado.leerString();
                        posicion = proArray.buscarDescripcionAux(busDescripcion);
                        if (posicion != -1) {
                            System.out.println("¿Este es el producto a eliminar? 1)sí 2)No");
                            check = teclado.leerEnteros();
                            teclado.leerString();
                            if (check == 1) {
                                proArray.eliminarProducto(posicion);
                            }
                        } else {
                            System.out.println("No hay coincidencias");
                        }
                    }
                }
                break;
            //OPCIÓN EDITAR PRODUCTO
            case 3:   
                System.out.println("¿Recuerdas la clave del producto a editar? 1)Sí 2)No");
                recordar = teclado.leerEnteros();
                if (recordar == 1) {
                    System.out.print("Escribe la clave: ");
                    int clave = teclado.leerEnteros();
                    int posicion = proArray.buscarClaveAux(clave);
                    if (posicion != -1) {
                       System.out.println("¿Este es el producto a editar? 1)sí 2)No");
                       check = teclado.leerEnteros();
                        if (check == 1) {
                            proArray.editarProducto(posicion);
                        }
                    } else {
                        System.out.println("Tus intenciones eran malas, esa clave NO existe");
                    }
                } else {
                    
                    int bClave;
                    teclado.leerString();
                    while (check == 2) {
                        System.out.println("Escribe una palabra clave del producto: ");
                        String busDescripcion = teclado.leerString();
                        bClave = proArray.buscarDescripcionAux(busDescripcion);
                        if (bClave != -1) {
                            System.out.println("¿Este es el producto a editar? 1)sí 2)No");
                            check = teclado.leerEnteros();
                            teclado.leerString();
                            if (check == 1) {
                                int posicion = proArray.buscarPosicion(bClave);
                                proArray.editarProducto(posicion);
                            }
                        } else {
                            System.out.println("No hay coincidencias");
                        }
                    }
                }
                break;
            //OPCIÓN BUSCAR PRODUCTO POR CRITERIO
            case 4:
                System.out.println("Buscar por 1)Clave 2)Nombre 3)Descripción");
                int opcBus = teclado.leerEnteros();
                teclado.leerString();
                switch (opcBus) {
                    case 1:
                        System.out.print("Escribe la clave a buscar: ");
                        int busClave = teclado.leerEnteros();
                        proArray.buscarClave(busClave);
                        break;
                    case 2:
                        System.out.print("Escribe el nombre a buscar: ");
                        String busNombre = teclado.leerString();
                        proArray.buscarNombre(busNombre);
                        break;
                    case 3:
                        System.out.print("Escribe una palabra clave del producto: ");
                        String busDescripcion = teclado.leerString();
                        proArray.buscarDescripcion(busDescripcion);
                        break;
                    default:
                        System.out.println("No es una opción valida. Regresando");
                        break;
                }
                break;
            //OPCIÓN ORDENA PRODUCTO A-Z
            case 5:
                proArray.ordenarNombre();
                break;
            //OPCIÓN SUMA PRECIOS
            case 6:
                proArray.sumaActivos();
                break;
            //OPCIÓN SALIR
            case 0:
                System.out.print("Regresando");
                Thread.sleep(500);
                System.out.print(".");
                Thread.sleep(500);
                System.out.print(".");
                Thread.sleep(500);
                System.out.println(".");
                break;
            default:
                System.out.println("Esa opción no tiene sentido, no soy mago :(");
                break;
                
        }
    }
    /**
     * La siguiente función lanza la opción que ha seleccionado el usuario en el 
     * submenú de Venta. Recibe la opción del usuario
     * @param opc
     * @throws InterruptedException 
     */
    public void realizarOpcionVenta(int opc) throws InterruptedException{
        switch (opc) {
            case 1:
                venta.registrarVenta();
                break;
            case 2:
                System.out.println("\033[34m-------------Registro de ventas-------------");
                System.out.println("Mostrar registros: 1)Hoy 2)Ayer 3)Mes");//AÑADIR
                int res = teclado.leerEnteros();
                switch (res) {
                    case 1:
                        venta.detalleDia();
                        break;
                    case 2:
                        venta.detalleAyer();
                        break;
                    case 3:
                        System.out.println("Escribe el número del mes a buscar. Ejemplo: 1 = Enero");
                        int mes = teclado.leerEnteros();
                        mes = mes - 1;
                        venta.detalleMes(mes);
                        break;
                    default:
                        System.out.println("Opción no válida");
                }
                break;
            case 3:
                venta.consultarCodigos();
                break;
            case 0:
                System.out.print("Regresando");
                Thread.sleep(500);
                System.out.print(".");
                Thread.sleep(500);
                System.out.print(".");
                Thread.sleep(500);
                System.out.println(".");
                break;
            default:
                System.out.println("Esa opción no tiene sentido, no soy mago :(");
                break;
        }    
    }
}
