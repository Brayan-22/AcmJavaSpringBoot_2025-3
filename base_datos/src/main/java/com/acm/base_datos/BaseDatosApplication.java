package com.acm.base_datos;

import com.acm.base_datos.entity.*;
import com.acm.base_datos.enums.RoleEnum;
import com.acm.base_datos.models.Usuario;
import com.acm.base_datos.repository.AlmacenProductoRepository;
import com.acm.base_datos.repository.UsuarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
@RequiredArgsConstructor
public class BaseDatosApplication {

	private static final Logger log = LoggerFactory.getLogger(BaseDatosApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BaseDatosApplication.class, args);
	}
	private final DataSource dataSource;

	@Bean
	@Order(1)
	ApplicationRunner runner(AlmacenProductoRepository almacenProductoRepository) {
		return args -> {
			List<ProductoEntity> products = List.of(
					ProductoEntity.builder()
							.nombre("Zapatos")
							.precio(200.0)
							.descripcion("Zapatos")
							.build(),
					ProductoEntity.builder()
							.nombre("Camisa")
							.descripcion("Camisa")
							.precio(50.0)
							.build()
			);
			List<AlmacenEntity> almacenes = List.of(
					AlmacenEntity.builder()
							.nombre("Almacen Central")
							.build(),
					AlmacenEntity.builder()
							.nombre("Almacen Secundario")
							.build()
			);

			List<AlmacenProductoEntity> almacenProductos = List.of(
					AlmacenProductoEntity.builder()
							.producto(products.get(0))
							.almacen(almacenes.get(0))
							.stock(100L)
							.build(),
					AlmacenProductoEntity.builder()
							.producto(products.get(1))
							.almacen(almacenes.get(0))
							.stock(200L)
							.build(),
					AlmacenProductoEntity.builder()
							.producto(products.get(0))
							.almacen(almacenes.get(1))
							.stock(150L)
							.build()
			);
			almacenProductoRepository.saveAll(almacenProductos);
		};

	}

	@Bean
	@Order(2)
	public ApplicationRunner runner2(AlmacenProductoRepository almacenProductoRepository) {
		return args -> {
			var almacenProductos = almacenProductoRepository.findByAlmacen_IdAndProducto_Id(1L,1L);
			almacenProductos.forEach(ap -> log.info("Almacen: {}", ap.getAlmacen().getNombre()));
			almacenProductos.forEach(ap -> log.info("Producto: {}", ap.getProducto().getNombre()));
		};
	}

	@Bean
	@Order(3)
	ApplicationRunner runner3(UsuarioRepository usuarioRepository){
		return args -> {
			var role = RolUsuarioEntity.builder()
					.role(RoleEnum.ADMIN).build();
			UsuarioEntity usuario = UsuarioEntity.builder()
					.nombre("Juan")
					.apellido("Perez")
					.password("1234")
					.email("juan@correo.com")
					.telefono("987654321")
					.username("juan")
					.rolUsuario(role)
					.build();
			List<UsuarioEntity> usuarios = new ArrayList<>();
			usuarios.add(usuario);
			usuarios.add(UsuarioEntity.builder()
							.nombre("Alejandro")
							.apellido("riveros")
							.password("5678").email("alejandro@correo.com")
							.telefono("123456789")
							.username("alejandro")
							.rolUsuario(role)
					.build());
			usuarioRepository.saveAll(usuarios);
		};
	}
	@Bean
	@Order(4)
	@Transactional
	ApplicationRunner runner4(){
		return args -> {
			try(Connection connection = dataSource.getConnection()){
				log.info("Conexion exitosa: {}", connection.getMetaData().getURL());
				PreparedStatement ps = connection.prepareStatement("SELECT * from users");
				ResultSet rs = ps.executeQuery();
				while (rs.next()){
					log.info("Usuario: {} - {}", rs.getInt("id"), rs.getString("nombre"));
				}
			}catch (Exception e){
				log.error("Error al obtener la conexion", e);
			}
		};
	}
}
