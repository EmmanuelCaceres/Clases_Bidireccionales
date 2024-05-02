package com.example.buensaborback;

import com.example.buensaborback.domain.entities.*;
import com.example.buensaborback.domain.entities.enums.Estado;
import com.example.buensaborback.domain.entities.enums.FormaPago;
import com.example.buensaborback.domain.entities.enums.TipoEnvio;
import com.example.buensaborback.domain.entities.enums.TipoPromocion;
import com.example.buensaborback.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BuenSaborBackApplication {
// Aca tiene que inyectar todos los repositorios
// Es por ello que deben crear el paquete reositorio

// Ejemplo  @Autowired
//	private ClienteRepository clienteRepository;

	private static final Logger logger = LoggerFactory.getLogger(BuenSaborBackApplication.class);

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ArticuloManufacturadoDetalleRepository articuloManufacturadoDetalleRepository;

	@Autowired
	private PaisRepository paisRepository;

	@Autowired
	private ProvinciaRepository provinciaRepository;

	@Autowired
	private LocalidadRepository localidadRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private SucursalRepository	sucursalRepository;

	@Autowired
	private DomicilioRepository domicilioRepository;

	@Autowired
	private UnidadMedidaRepository unidadMedidaRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ArticuloInsumoRepository articuloInsumoRepository;

	@Autowired
	private ArticuloManufacturadoRepository articuloManufacturadoRepository;

	@Autowired
	private ImagenRepository imagenRepository;

	@Autowired
	private PromocionRepository promocionRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private  FacturaRepository facturaRepository;

	@Autowired
	private  PedidoRepository pedidoRepository;

	@Autowired
	private DetallePedidoRepository detallePedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(BuenSaborBackApplication.class, args);
		logger.info("Estoy activo en el main");
	}


	@Bean
	CommandLineRunner init() {
		return args -> {
			logger.info("----------------ESTOY----FUNCIONANDO---------------------");


			Factura facturaNueva = Factura.builder()
					.fechaFacturacion(LocalDate.now())
					.formaPago(FormaPago.Efectivo)
					.mpMerchantOrderId(23)
					.mpPreferenceId("Prueba de mpref")
					.mpPaymentType("Tipo de mpPayment")
					.mpPaymentId(2)
					.pedido(Pedido.builder()
							.total(50d)
							.estado(Estado.Pendiente)
							.tipoEnvio(TipoEnvio.Delivery)
							.formaPago(FormaPago.MercadoPago)
							.totalCosto(32d)
							.fechaPedido(LocalDate.now())
							.horaEstimadaFinalizacion(LocalTime.now())
							.cliente(Cliente.builder()
									.nombre("Emmanuel")
									.apellido("Caceres")
									.email("emmanuel@gmail.com")
									.telefono("2615120063")
									.fechaNacimiento(LocalDate.of(1998,11,7))
									.usuario(Usuario.builder()
											.auth0Id("Auth0")
											.username("nombre de usuario prueba")
											.build())
									.build())
							.domicilio(Domicilio.builder()
								.localidad(Localidad.builder()
								.provincia(Provincia.builder()
									.nombre("Nombre de provincia").pais(Pais.builder()
											.nombre("Nombre de pais")
											.build())
									.build())
								.nombre("nombre de localidad")
								.build())
								.calle("Calle de prueba")
								.numero(32)
								.cp(5533)
								.build())
							.build())
					.build();


			DetallePedido detallePedidoNuevo = DetallePedido.builder()
					.cantidad(2)
					.subTotal(245d)
					.articulo(ArticuloManufacturado.builder()
							.unidadMedida(UnidadMedida.builder()
									.denominacion("Porcion")
									.build())
							.precioVenta(32.1)
							.denominacion("Pizza Lomo")
							.descripcion("sanguche de lomo pero en vez de pan, pizzas")
							.preparacion("Preparacion de prueba")
							.tiempoEstimadoMinutos(20)
							.categoria(Categoria.builder()
									.denominacion("Categoria de prueba")
									.build())
							.build())
					.pedido(facturaNueva.getPedido())
					.build();
			facturaNueva.getPedido().getDetallePedidos().add(detallePedidoNuevo);

			facturaRepository.save(facturaNueva);

//			Domicilio domicilioprueba = Domicilio.builder()
//					.localidad(Localidad.builder()
//							.provincia(Provincia.builder()
//									.nombre("Nombre de provincia").pais(Pais.builder()
//											.nombre("Nombre de pais")
//											.build())
//									.build())
//							.nombre("nombre de localidad")
//							.build())
//					.calle("Calle de prueba")
//					.numero(32)
//					.cp(5533)
//					.build();
//
//			domicilioRepository.save(domicilioprueba);


//			ArticuloManufacturado articuloManufacturadoNuevo = ArticuloManufacturado.builder()
//					.unidadMedida(UnidadMedida.builder()
//							.denominacion("Porcion")
//							.build())
//					.precioVenta(32.1)
//					.denominacion("Prueba de articulo")
//					.descripcion("Esto es una descripcion")
//					.preparacion("Preparacion de prueba")
//					.tiempoEstimadoMinutos(20)
//					.categoria(Categoria.builder()
//							.denominacion("Categoria de prueba")
//							.build())
//					.build();
//
//			DetallePedido detallePedido = DetallePedido.builder()
//					.cantidad(4)
//					.subTotal(34.5)
//					.articulo(articuloManufacturadoNuevo)
//					.pedido(Pedido.builder()
//							.total(21d)
//							.totalCosto(40d)
//							.estado(Estado.Pendiente)
//							.tipoEnvio(TipoEnvio.Delivery)
//							.formaPago(FormaPago.Efectivo)
//							.fechaPedido(LocalDate.now())
//							.horaEstimadaFinalizacion(LocalTime.now())
//							.cliente(Cliente.builder()
//									.nombre("Emmanuel")
//									.apellido("Caceres")
//									.email("emmanuel@gmail.com")
//									.telefono("2615120063")
//									.fechaNacimiento(LocalDate.of(1998,11,7))
//									.usuario(Usuario.builder()
//											.auth0Id("Auth0")
//											.username("nombre de usuario prueba")
//											.build())
//									.build())
//							.factura(Factura.builder()
//									.fechaFacturacion(LocalDate.now())
//									.formaPago(FormaPago.Efectivo)
//									.mpMerchantOrderId(32131)
//									.mpPaymentId(212)
//									.mpPaymentType("Prueba tipo mp")
//									.mpPreferenceId("Prueba preference mp")
//									.build())
//							.build())
//					.build();
//
//			articuloManufacturadoNuevo.getDetallesPedido().add(detallePedido);
//
//			articuloManufacturadoRepository.save(articuloManufacturadoNuevo);

		};
	}
}







