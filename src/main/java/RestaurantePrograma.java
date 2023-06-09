


public class RestaurantePrograma {
    private final Autenticacion autenticacion;
    private final TomaPedidos tomaPedidos;
    private final SeguimientoOrden seguimientoOrden;
    private final EstimacionEntrega estimacionEntrega;
    private final RegistroCliente registroCliente;

    public  RestaurantePrograma  (Autenticacion autenticacion, TomaPedidos tomaPedidos,
                                     SeguimientoOrden seguimientoOrden, EstimacionEntrega estimacionEntrega,
                                     RegistroCliente registroCliente) {
        this.autenticacion =autenticacion;
        this.tomaPedidos =tomaPedidos;
        this.seguimientoOrden =seguimientoOrden;
        this.estimacionEntrega =estimacionEntrega;
        this.registroCliente =registroCliente;

    }


    protected void iniciar() {
        boolean sesionIniciada = autenticacion.iniciarSesion("nombreUsuario", "contraseñaUsuario");

        if (sesionIniciada) {
            tomaPedidos.tomarPedido("nombreCliente");
            seguimientoOrden.seguirOrden(123);
            estimacionEntrega.estimarEntrega(123);
            registroCliente.registrarCliente("nombreCliente", "direccionCliente", "telefonoCliente");
        } else {
            System.out.println("Credenciales incorrectas. Programa finalizado.");
        }
    }

    public static void main(String[] args) {
        Autenticacion autenticacion = new AutenticacionDB();
        TomaPedidos tomaPedidos = new Pedido();
        SeguimientoOrden seguimientoOrden = new Pedido();
        EstimacionEntrega estimacionEntrega = new Pedido();
        RegistroCliente registroCliente = new Cliente();

        RestaurantePrograma programa = new RestaurantePrograma(autenticacion, tomaPedidos, seguimientoOrden,
                estimacionEntrega, registroCliente); programa.iniciar();
    }
}


