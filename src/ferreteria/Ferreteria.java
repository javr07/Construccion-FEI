
package ferreteria;

/**
 *Este es un programa para llevar un registro de una Ferretería, puede tener un 
 * registro de almacen y realizar ventas. En esta clase se inicia el programa.
 * @author javr
 */
public class Ferreteria {

    /**
     * @param args the command line arguments
     */
    //ProductoArray pArray = new ProductoArray();
    int opc = 1;
    int opc2 = 1;
    MensajesUsuario mensajes = new MensajesUsuario();
    /**
     * El siguiente método inicia el programa muestra los menues y envía las respuestas
     * de selección entre los menues.
     * @throws InterruptedException Aplicado para no tener problemas en el detalle 
     * gráfico de los puntos (diseño).
     */
    public void inicia() throws InterruptedException{
            
                mensajes.menuPrincipal();
                opc = mensajes.leerOpcion();
                while (opc  != 0 ) {
                    
                    switch (opc) {
                        case 1:
                            opc2 = 1;
                            while (opc2 != 0)  {
                                mensajes.realizarOpcionPrincipal(opc);
                                opc2 = mensajes.leerOpcion();
                                mensajes.realizarOpcionFerreteria(opc2);
                            } 
                            break;
                        case 2:
                            opc2 = 1;
                            while (opc2 != 0) {
                                mensajes.realizarOpcionPrincipal(opc);
                                opc2 = mensajes.leerOpcion();
                                mensajes.realizarOpcionVenta(opc2);
                            }
                            break;
                        default:
                            System.out.println("Esa opción no es válida");
                            break;
                    }
                    mensajes.menuPrincipal();
                    opc = mensajes.leerOpcion();
                    
                }
                if (opc == 0) {
                    System.out.print("Au revoir");
                    Thread.sleep(700);
                    System.out.print(".");
                    Thread.sleep(700);
                    System.out.print(".");
                    Thread.sleep(700);
                    System.out.println(".");
                }
            
        }
    public static void main(String[] args) throws InterruptedException {
        Ferreteria f = new Ferreteria();
        ProductoArray prodc = new ProductoArray();
        VentaDetalle vnt = new VentaDetalle();
        prodc.cargar();
        
        vnt.cargarVentas();
        
        f.inicia();
     
        
    }
    
}
