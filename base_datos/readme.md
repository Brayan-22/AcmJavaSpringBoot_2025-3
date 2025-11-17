# Tutorial: Spring Boot JPA - Sistema de Gestión de Ventas

## 📋 Descripción del Proyecto

Este proyecto es una aplicación de demostración desarrollada en clase para ilustrar el uso del ORM (Object-Relational Mapping) de Spring Boot con JPA/Hibernate.

## 🗂️ Estructura del Proyecto

```
com.acm.base_datos
├── controller/          # Controladores REST
├── entity/             # Entidades JPA
│   ├── AlmacenEntity
│   ├── AlmacenProductoEntity
│   ├── CategoriaEntity
│   ├── ProductoEntity
│   ├── RolUsuarioEntity
│   └── UsuarioEntity
├── enums/              # Enumeraciones
│   └── RoleEnum
├── models/             # DTOs y modelos
├── repository/         # Interfaces de repositorio
└── services/           # Lógica de negocio
```

## 📊 Diagrama de Base de Datos

El sistema maneja las siguientes entidades principales:

- **Usuario**: Gestión de usuarios del sistema
- **RolUsuario**: Roles asignados a usuarios
- **Departamento**: Ubicaciones geográficas
- **Ciudad**: Ciudades por departamento
- **Almacén**: Bodegas de almacenamiento
- **Producto**: Catálogo de productos
- **Categoría**: Clasificación de productos
- **AlmacenProducto**: Stock por almacén
- **Venta**: Registro de ventas
- **venta_producto**: Detalle de productos vendidos

## 🔗 Tipos de Relaciones en JPA

### 1. **@OneToMany** (Uno a Muchos)

Cuando una entidad puede tener múltiples instancias relacionadas de otra entidad.

```java
// En UsuarioEntity
@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
private List<VentaEntity> ventas;

// Un usuario puede tener muchas ventas
```

**Características:**
- `mappedBy`: Indica que la relación es bidireccional y está mapeada por el campo en la otra entidad
- `cascade`: Propaga operaciones (persist, merge, remove, etc.)
- `fetch`: LAZY (carga bajo demanda) o EAGER (carga inmediata)

### 2. **@ManyToOne** (Muchos a Uno)

El lado inverso de @OneToMany. Múltiples instancias relacionadas con una sola.

```java
// En VentaEntity
@ManyToOne
@JoinColumn(name = "idClienteFK")
private UsuarioEntity cliente;

// Muchas ventas pertenecen a un usuario
```

**Características:**
- `@JoinColumn`: Define el nombre de la columna de clave foránea
- Por defecto usa fetch = EAGER

### 3. **@ManyToMany** (Muchos a Muchos)

Cuando múltiples instancias de una entidad se relacionan con múltiples instancias de otra.

```java
// En ProductoEntity
@ManyToMany
@JoinTable(
    name = "ProductoCategoria",
    joinColumns = @JoinColumn(name = "idProducto"),
    inverseJoinColumns = @JoinColumn(name = "idCategoria")
)
private Set<CategoriaEntity> categorias;

// Un producto puede tener muchas categorías
// Una categoría puede tener muchos productos
```

**Características:**
- Requiere una tabla intermedia
- `@JoinTable`: Configura la tabla de unión
- Usar `Set` en lugar de `List` para evitar duplicados

### 4. **@OneToOne** (Uno a Uno)

Relación donde cada instancia de una entidad se relaciona con exactamente una instancia de otra.

```java
@OneToOne
@JoinColumn(name = "idRolFK")
private RolUsuarioEntity rol;
```

## 📦 Repositorios en Spring Data JPA

Los repositorios son interfaces que extienden de `JpaRepository` y proporcionan métodos CRUD automáticos.

```java
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    // Spring Data JPA proporciona automáticamente:
    // - save()
    // - findById()
    // - findAll()
    // - deleteById()
    // - count()
    // - existsById()
}
```

## 🔍 Derived Query Methods (Consultas Derivadas)

Spring Data JPA puede crear consultas automáticamente basándose en el nombre del método.

### Sintaxis y Ejemplos:

```java
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {
    
    // BÚSQUEDA POR CAMPO SIMPLE
    List<ProductoEntity> findByNombre(String nombre);
    
    // BÚSQUEDA CON LIKE (contiene)
    List<ProductoEntity> findByNombreContaining(String palabra);
    
    // BÚSQUEDA CON MÚLTIPLES CONDICIONES (AND)
    List<ProductoEntity> findByNombreAndPrecio(String nombre, BigDecimal precio);
    
    // BÚSQUEDA CON OR
    List<ProductoEntity> findByNombreOrDescripcion(String nombre, String descripcion);
    
    // BÚSQUEDA CON COMPARADORES
    List<ProductoEntity> findByPrecioGreaterThan(BigDecimal precio);
    List<ProductoEntity> findByPrecioLessThanEqual(BigDecimal precio);
    List<ProductoEntity> findByPrecioBetween(BigDecimal min, BigDecimal max);
    
    // BÚSQUEDA EN RELACIONES
    List<ProductoEntity> findByCategoriasNombre(String nombreCategoria);
    
    // ORDENAMIENTO
    List<ProductoEntity> findByNombreOrderByPrecioDesc(String nombre);
    
    // LIMITAR RESULTADOS
    List<ProductoEntity> findTop5ByOrderByPrecioDesc();
    ProductoEntity findFirstByNombre(String nombre);
    
    // EXISTENCIA
    boolean existsByNombre(String nombre);
    
    // CONTEO
    long countByCategoriasId(Long categoriaId);
    
    // ELIMINACIÓN
    void deleteByNombre(String nombre);
}
```

### Palabras Clave en Derived Queries:

| Palabra Clave | Descripción | Ejemplo |
|--------------|-------------|---------|
| `And` | Condición AND | `findByNombreAndPrecio` |
| `Or` | Condición OR | `findByNombreOrDescripcion` |
| `Between` | Entre dos valores | `findByPrecioBetween` |
| `LessThan` | Menor que | `findByPrecioLessThan` |
| `GreaterThan` | Mayor que | `findByPrecioGreaterThan` |
| `Like` | LIKE SQL | `findByNombreLike` |
| `Containing` | Contiene (%word%) | `findByNombreContaining` |
| `StartingWith` | Empieza con | `findByNombreStartingWith` |
| `EndingWith` | Termina con | `findByNombreEndingWith` |
| `IgnoreCase` | Ignora mayúsculas | `findByNombreIgnoreCase` |
| `OrderBy` | Ordenar por | `findByNombreOrderByPrecioDesc` |
| `Not` | Negación | `findByNombreNot` |
| `In` | En lista | `findByNombreIn(List<String>)` |
| `True/False` | Booleanos | `findByActivoTrue` |

## 📝 @Query - Consultas JPQL Personalizadas

Cuando las derived queries no son suficientes, usamos `@Query` con JPQL (Java Persistence Query Language).

```java
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    
    // CONSULTA JPQL BÁSICA
    @Query("SELECT u FROM UsuarioEntity u WHERE u.email = ?1")
    Optional<UsuarioEntity> buscarPorEmail(String email);
    
    // PARÁMETROS NOMBRADOS
    @Query("SELECT u FROM UsuarioEntity u WHERE u.nombre = :nombre AND u.apellido = :apellido")
    List<UsuarioEntity> buscarPorNombreCompleto(
        @Param("nombre") String nombre,
        @Param("apellido") String apellido
    );
    
    // JOINS
    @Query("SELECT u FROM UsuarioEntity u JOIN u.rol r WHERE r.rol = :rol")
    List<UsuarioEntity> buscarPorRol(@Param("rol") String rol);
    
    // PROYECCIONES (Seleccionar campos específicos)
    @Query("SELECT u.nombre, u.email FROM UsuarioEntity u WHERE u.id = :id")
    Object[] obtenerNombreYEmail(@Param("id") Long id);
    
    // SUBCONSULTAS
    @Query("SELECT p FROM ProductoEntity p WHERE p.precio > " +
           "(SELECT AVG(p2.precio) FROM ProductoEntity p2)")
    List<ProductoEntity> productosConPrecioMayorAlPromedio();
    
    // FUNCIONES AGREGADAS
    @Query("SELECT COUNT(v) FROM VentaEntity v WHERE v.cliente.id = :clienteId")
    Long contarVentasPorCliente(@Param("clienteId") Long clienteId);
    
    // OPERACIONES DE MODIFICACIÓN
    @Modifying
    @Transactional
    @Query("UPDATE ProductoEntity p SET p.precio = p.precio * 1.1 WHERE p.id = :id")
    void aumentarPrecio(@Param("id") Long id);
    
    // DELETE PERSONALIZADO
    @Modifying
    @Transactional
    @Query("DELETE FROM ProductoEntity p WHERE p.fechaCreacion < :fecha")
    void eliminarProductosAntiguos(@Param("fecha") LocalDateTime fecha);
}
```

### Ventajas de @Query:
- ✅ Mayor control sobre la consulta
- ✅ Consultas complejas con múltiples joins
- ✅ Subconsultas y funciones agregadas
- ✅ Proyecciones personalizadas
- ✅ Mejor rendimiento en consultas específicas

## 🔧 @NativeQuery - Consultas SQL Nativas

Para casos donde necesitas SQL directo (funciones específicas de la BD, optimizaciones, etc.).

```java
public interface AlmacenProductoRepository extends JpaRepository<AlmacenProductoEntity, Long> {
    
    // CONSULTA NATIVA BÁSICA
    @Query(value = "SELECT * FROM AlmacenProducto WHERE stock > ?1", nativeQuery = true)
    List<AlmacenProductoEntity> buscarConStockMayorA(Integer cantidad);
    
    // CON PARÁMETROS NOMBRADOS
    @Query(value = "SELECT * FROM AlmacenProducto ap " +
                   "WHERE ap.idAlmacenFK = :almacenId " +
                   "AND ap.stock BETWEEN :min AND :max",
           nativeQuery = true)
    List<AlmacenProductoEntity> buscarPorStockEnRango(
        @Param("almacenId") Long almacenId,
        @Param("min") Integer min,
        @Param("max") Integer max
    );
    
    // JOINS NATIVOS
    @Query(value = "SELECT p.nombre, ap.stock, a.nombre as almacen " +
                   "FROM Producto p " +
                   "INNER JOIN AlmacenProducto ap ON p.idProducto = ap.idProductoFK " +
                   "INNER JOIN Almacen a ON ap.idAlmacenFK = a.idAlmacen " +
                   "WHERE ap.stock < :stockMinimo",
           nativeQuery = true)
    List<Object[]> productosConBajoStock(@Param("stockMinimo") Integer stockMinimo);
    
    // FUNCIONES ESPECÍFICAS DE LA BASE DE DATOS
    @Query(value = "SELECT *, " +
                   "DATEDIFF(NOW(), fechaCreacion) as dias_desde_creacion " +
                   "FROM Producto " +
                   "WHERE DATEDIFF(NOW(), fechaCreacion) > :dias",
           nativeQuery = true)
    List<Object[]> productosCreacionAntigua(@Param("dias") Integer dias);
    
    // OPERACIONES DE MODIFICACIÓN NATIVAS
    @Modifying
    @Transactional
    @Query(value = "UPDATE AlmacenProducto SET stock = stock + :cantidad " +
                   "WHERE idAlmacenFK = :almacenId AND idProductoFK = :productoId",
           nativeQuery = true)
    void incrementarStock(
        @Param("almacenId") Long almacenId,
        @Param("productoId") Long productoId,
        @Param("cantidad") Integer cantidad
    );
    
    // PROCEDIMIENTOS ALMACENADOS
    @Query(value = "CALL calcular_inventario_total(:almacenId)", nativeQuery = true)
    Integer calcularInventarioTotal(@Param("almacenId") Long almacenId);
}
```

### Cuándo usar @NativeQuery:
- 🔹 Funciones específicas de la base de datos (MySQL, PostgreSQL, etc.)
- 🔹 Optimizaciones de rendimiento que requieren SQL específico
- 🔹 Consultas heredadas de sistemas antiguos
- 🔹 Operaciones masivas complejas
- 🔹 Procedimientos almacenados

### ⚠️ Advertencias:
- ❌ Menos portable entre diferentes bases de datos
- ❌ No se beneficia del mapeo automático de JPA
- ❌ Requiere conocimiento específico de SQL

## 📊 Comparación: Derived Query vs @Query vs @NativeQuery

| Característica | Derived Query | @Query (JPQL) | @NativeQuery |
|---------------|---------------|---------------|--------------|
| **Facilidad** | ⭐⭐⭐⭐⭐ Muy fácil | ⭐⭐⭐⭐ Fácil | ⭐⭐⭐ Moderado |
| **Portabilidad** | ✅ Total | ✅ Total | ❌ Limitada |
| **Flexibilidad** | ⭐⭐ Limitada | ⭐⭐⭐⭐ Alta | ⭐⭐⭐⭐⭐ Total |
| **Rendimiento** | ⭐⭐⭐⭐ Bueno | ⭐⭐⭐⭐ Bueno | ⭐⭐⭐⭐⭐ Óptimo |
| **Mantenimiento** | ✅ Excelente | ✅ Bueno | ⚠️ Requiere cuidado |

## 🎯 Ejemplos Prácticos del Proyecto

### 1. Buscar Productos por Categoría

```java
// Derived Query
List<ProductoEntity> findByCategoriasNombre(String nombreCategoria);

// @Query JPQL
@Query("SELECT p FROM ProductoEntity p JOIN p.categorias c WHERE c.nombre = :nombre")
List<ProductoEntity> buscarPorCategoria(@Param("nombre") String nombre);

// @NativeQuery
@Query(value = "SELECT p.* FROM Producto p " +
               "INNER JOIN ProductoCategoria pc ON p.idProducto = pc.idProducto " +
               "INNER JOIN Categoria c ON pc.idCategoria = c.idCategoria " +
               "WHERE c.nombre = :nombre",
       nativeQuery = true)
List<ProductoEntity> buscarPorCategoriaNativa(@Param("nombre") String nombre);
```

### 2. Obtener Ventas de un Cliente

```java
// Derived Query
List<VentaEntity> findByClienteId(Long clienteId);

// @Query con agregación
@Query("SELECT v FROM VentaEntity v WHERE v.cliente.id = :clienteId ORDER BY v.fechaVenta DESC")
List<VentaEntity> obtenerVentasRecientes(@Param("clienteId") Long clienteId);
```

### 3. Productos con Bajo Stock

```java
// Derived Query
List<AlmacenProductoEntity> findByStockLessThan(Integer cantidad);

// @Query con información adicional
@Query("SELECT ap FROM AlmacenProductoEntity ap " +
       "JOIN FETCH ap.producto p " +
       "JOIN FETCH ap.almacen a " +
       "WHERE ap.stock < :minimo")
List<AlmacenProductoEntity> productosBajoStock(@Param("minimo") Integer minimo);
```

## 🚀 Mejores Prácticas

1. **Usa Derived Queries** para consultas simples y directas
2. **Usa @Query JPQL** para consultas complejas con joins y lógica avanzada
3. **Usa @NativeQuery** solo cuando JPQL no sea suficiente
4. **Siempre usa @Param** para parámetros nombrados (más legible)
5. **Implementa paginación** para grandes volúmenes de datos:

```java
Page<ProductoEntity> findByNombreContaining(String nombre, Pageable pageable);
```

6. **Usa @Transactional** con operaciones de modificación:

```java
@Modifying
@Transactional
@Query("UPDATE ProductoEntity p SET p.stock = :stock WHERE p.id = :id")
void actualizarStock(@Param("id") Long id, @Param("stock") Integer stock);
```

## 📚 Recursos Adicionales

- [Spring Data JPA Documentation](https://spring.io/projects/spring-data-jpa)
- [JPQL Language Reference](https://docs.oracle.com/javaee/7/tutorial/persistence-querylanguage.htm)
- [Baeldung Spring Data JPA Guides](https://www.baeldung.com/spring-data-jpa-query)