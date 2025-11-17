# Tutorial Completo: Jackson Library - Serialización y Deserialización en Java

## 📋 ¿Qué es Jackson?

Jackson es la librería más popular de Java para procesar JSON (JavaScript Object Notation). Permite convertir objetos Java a JSON (serialización) y JSON a objetos Java (deserialización) de forma automática y eficiente.

**Principales usos:**
- 🔄 Convertir objetos Java a JSON y viceversa
- 🌐 APIs REST (Spring Boot usa Jackson por defecto)
- 📁 Leer/escribir archivos JSON
- 🔧 Configuración de aplicaciones
- 📡 Integración con servicios externos

## 🚀 Instalación

### Maven
```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.15.2</version>
</dependency>

<!-- Para trabajar con fechas Java 8+ -->
<dependency>
    <groupId>com.fasterxml.jackson.datatype</groupId>
    <artifactId>jackson-datatype-jsr310</artifactId>
    <version>2.15.2</version>
</dependency>

```
### Gradle
```gradle
implementation 'com.fasterxml.jackson.core:jackson-databind:2.15.2'
implementation 'com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.2'
```
### Importar las librerias no es necesario si se usa el starter de Spring Boot:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```


## 🎯 Conceptos Básicos

### ObjectMapper - La Clase Principal

`ObjectMapper` es la clase central de Jackson. Es como un traductor entre Java y JSON.

```java
import com.fasterxml.jackson.databind.ObjectMapper;

ObjectMapper mapper = new ObjectMapper();
```

**💡 Tip**: Crea una sola instancia de ObjectMapper y reusala (es thread-safe después de configurarla).

## 📤 Serialización: Java → JSON

### Ejemplo Básico

```java
public class Usuario {
    private Long id;
    private String nombre;
    private String email;
    private int edad;
    
    // Constructores, getters y setters
}

// Serialización
ObjectMapper mapper = new ObjectMapper();
Usuario usuario = new Usuario(1L, "Juan Pérez", "juan@email.com", 25);

// Convertir a JSON String
String jsonString = mapper.writeValueAsString(usuario);
System.out.println(jsonString);
// Resultado: {"id":1,"nombre":"Juan Pérez","email":"juan@email.com","edad":25}

// Convertir a JSON con formato bonito (pretty print)
String jsonPretty = mapper.writerWithDefaultPrettyPrinter()
                          .writeValueAsString(usuario);
System.out.println(jsonPretty);
/* Resultado:
{
  "id" : 1,
  "nombre" : "Juan Pérez",
  "email" : "juan@email.com",
  "edad" : 25
}
*/

// Escribir a archivo
mapper.writeValue(new File("usuario.json"), usuario);

// Escribir a OutputStream
mapper.writeValue(new FileOutputStream("usuario.json"), usuario);
```

## 📥 Deserialización: JSON → Java

```java
// Desde String
String json = "{\"id\":1,\"nombre\":\"Juan Pérez\",\"email\":\"juan@email.com\",\"edad\":25}";
Usuario usuario = mapper.readValue(json, Usuario.class);

// Desde archivo
Usuario usuario = mapper.readValue(new File("usuario.json"), Usuario.class);

// Desde URL
Usuario usuario = mapper.readValue(new URL("http://api.example.com/user/1"), Usuario.class);

// Desde InputStream
Usuario usuario = mapper.readValue(new FileInputStream("usuario.json"), Usuario.class);
```

## 🏷️ Anotaciones de Jackson

### @JsonProperty - Mapear nombres diferentes

```java
public class Producto {
    @JsonProperty("product_id")
    private Long id;
    
    @JsonProperty("product_name")
    private String nombre;
    
    private Double precio; // Se mantiene el nombre por defecto
}

// JSON resultante:
// {"product_id":1,"product_name":"Laptop","precio":999.99}
```

### @JsonIgnore - Ignorar campos

```java
public class Usuario {
    private Long id;
    private String nombre;
    
    @JsonIgnore
    private String password; // NO se incluirá en el JSON
    
    @JsonIgnore
    private String tokenInterno; // Tampoco se serializa
}

// JSON resultante:
// {"id":1,"nombre":"Juan Pérez"}
```

### @JsonIgnoreProperties - Ignorar múltiples propiedades

```java
@JsonIgnoreProperties({"password", "tokenInterno", "secretKey"})
public class Usuario {
    private Long id;
    private String nombre;
    private String password;
    private String tokenInterno;
    private String secretKey;
}

// O ignorar propiedades desconocidas al deserializar
@JsonIgnoreProperties(ignoreUnknown = true)
public class Usuario {
    private Long id;
    private String nombre;
    // Si el JSON tiene más campos, no dará error
}
```

### @JsonFormat - Formatear fechas y números

```java
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class Evento {
    private String nombre;
    
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime fechaCreacion;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaInicio;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#,##0.00")
    private Double precio;
}

// JSON resultante:
// {
//   "nombre": "Conferencia",
//   "fechaCreacion": "17/11/2025 14:30:00",
//   "fechaInicio": "2025-12-01",
//   "precio": "1,500.00"
// }
```

### @JsonInclude - Controlar valores nulos

```java
import com.fasterxml.jackson.annotation.JsonInclude;

// A nivel de clase
@JsonInclude(JsonInclude.Include.NON_NULL) // No incluir nulls
public class Producto {
    private Long id;
    private String nombre;
    private String descripcion; // Si es null, no aparece en JSON
}

// A nivel de campo
public class Usuario {
    private String nombre;
    
    @JsonInclude(JsonInclude.Include.NON_EMPTY) // No incluir si está vacío
    private String apellido;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String telefono;
}

// Opciones de Include:
// - ALWAYS: Siempre incluir
// - NON_NULL: Excluir si es null
// - NON_EMPTY: Excluir si es null, "", [], {}
// - NON_DEFAULT: Excluir valores por defecto (0, false, etc.)
```

### @JsonAlias - Múltiples nombres para deserialización

```java
public class Producto {
    @JsonAlias({"product_name", "productName", "name"})
    private String nombre;
    
    // Acepta cualquiera de estos nombres en el JSON de entrada
}
```

### @JsonCreator y @JsonProperty - Control de constructores

```java
public class Usuario {
    private final Long id;
    private final String nombre;
    
    @JsonCreator
    public Usuario(
        @JsonProperty("id") Long id,
        @JsonProperty("nombre") String nombre
    ) {
        this.id = id;
        this.nombre = nombre;
    }
}
```

### @JsonGetter y @JsonSetter - Métodos personalizados

```java
public class Usuario {
    private String nombre;
    private String apellido;
    
    @JsonGetter("nombreCompleto")
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
    
    @JsonSetter("nombreCompleto")
    public void setNombreCompleto(String nombreCompleto) {
        String[] partes = nombreCompleto.split(" ");
        this.nombre = partes[0];
        this.apellido = partes.length > 1 ? partes[1] : "";
    }
}

// JSON: {"nombreCompleto":"Juan Pérez"}
```

### @JsonRawValue - Incluir JSON sin escapar

```java
public class Respuesta {
    private String mensaje;
    
    @JsonRawValue
    private String datosJson; // Se incluye como JSON válido
    
    // Si datosJson = "{\"key\":\"value\"}"
    // JSON: {"mensaje":"Ok","datosJson":{"key":"value"}}
}
```

### @JsonValue - Serializar objeto como valor simple

```java
public enum Estado {
    ACTIVO("A"),
    INACTIVO("I"),
    PENDIENTE("P");
    
    private String codigo;
    
    Estado(String codigo) {
        this.codigo = codigo;
    }
    
    @JsonValue
    public String getCodigo() {
        return codigo;
    }
}

// JSON: "A" en lugar de "ACTIVO"
```

## 🔄 Trabajar con Colecciones

### Listas

```java
// Serializar lista
List<Usuario> usuarios = Arrays.asList(
    new Usuario(1L, "Juan"),
    new Usuario(2L, "María")
);

String json = mapper.writeValueAsString(usuarios);
// [{"id":1,"nombre":"Juan"},{"id":2,"nombre":"María"}]

// Deserializar lista
String json = "[{\"id\":1,\"nombre\":\"Juan\"},{\"id\":2,\"nombre\":\"María\"}]";
List<Usuario> usuarios = mapper.readValue(
    json,
    new TypeReference<List<Usuario>>() {}
);
```

### Maps

```java
// Serializar Map
Map<String, Object> datos = new HashMap<>();
datos.put("nombre", "Juan");
datos.put("edad", 25);
datos.put("activo", true);

String json = mapper.writeValueAsString(datos);
// {"nombre":"Juan","edad":25,"activo":true}

// Deserializar Map
String json = "{\"nombre\":\"Juan\",\"edad\":25}";
Map<String, Object> datos = mapper.readValue(
    json,
    new TypeReference<Map<String, Object>>() {}
);
```

### Arrays

```java
// Deserializar array
String json = "[{\"id\":1},{\"id\":2}]";
Usuario[] usuarios = mapper.readValue(json, Usuario[].class);
```

## 🎨 Configuración de ObjectMapper

### Configuraciones Comunes

```java
ObjectMapper mapper = new ObjectMapper();

// Ignorar propiedades desconocidas al deserializar
mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

// No fallar con JSON vacío
mapper.configure(DeserializationFeature.FAIL_ON_EMPTY_BEANS, false);

// Permitir comentarios en JSON
mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);

// Permitir comillas simples
mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

// No incluir valores null
mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

// Pretty print por defecto
mapper.enable(SerializationFeature.INDENT_OUTPUT);

// Configurar fechas
mapper.registerModule(new JavaTimeModule());
mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
```

## 📅 Trabajar con Fechas

### Java 8+ (LocalDate, LocalDateTime, etc.)

```java
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

ObjectMapper mapper = new ObjectMapper();
mapper.registerModule(new JavaTimeModule());
mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

public class Evento {
    private LocalDateTime fecha;
    private LocalDate dia;
    private LocalTime hora;
}

// JSON: 
// {
//   "fecha": "2025-11-17T14:30:00",
//   "dia": "2025-11-17",
//   "hora": "14:30:00"
// }
```

### Date tradicional

```java
import java.text.SimpleDateFormat;

ObjectMapper mapper = new ObjectMapper();
mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
```

## 🌳 JsonNode - Manipular JSON sin POJOs

Útil cuando no quieres crear clases para el JSON.

```java
String json = "{\"nombre\":\"Juan\",\"edad\":25,\"direccion\":{\"ciudad\":\"Madrid\"}}";

// Leer como JsonNode
JsonNode nodo = mapper.readTree(json);

// Acceder a valores
String nombre = nodo.get("nombre").asText();
int edad = nodo.get("edad").asInt();
String ciudad = nodo.get("direccion").get("ciudad").asText();

// Modificar
ObjectNode nodoObjeto = (ObjectNode) nodo;
nodoObjeto.put("email", "juan@email.com");
nodoObjeto.remove("edad");

// Agregar objeto
ObjectNode direccion = nodoObjeto.putObject("direccion");
direccion.put("calle", "Principal");
direccion.put("numero", 123);

// Convertir de vuelta a String
String jsonModificado = mapper.writeValueAsString(nodo);
```

### Crear JSON desde cero

```java
ObjectMapper mapper = new ObjectMapper();
ObjectNode root = mapper.createObjectNode();

root.put("nombre", "Juan");
root.put("edad", 25);

ArrayNode hobbies = root.putArray("hobbies");
hobbies.add("Programar");
hobbies.add("Leer");

ObjectNode direccion = root.putObject("direccion");
direccion.put("ciudad", "Madrid");
direccion.put("pais", "España");

String json = mapper.writeValueAsString(root);
```

## 🔍 Casos de Uso Avanzados

### 1. Herencia y Polimorfismo

```java
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "tipo"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Perro.class, name = "perro"),
    @JsonSubTypes.Type(value = Gato.class, name = "gato")
})
public abstract class Animal {
    private String nombre;
}

public class Perro extends Animal {
    private String raza;
}

public class Gato extends Animal {
    private int vidas;
}

// JSON: {"tipo":"perro","nombre":"Firulais","raza":"Labrador"}
```

### 2. Campos Calculados

```java
public class Producto {
    private String nombre;
    private Double precioBase;
    private Double impuesto;
    
    @JsonProperty("precioTotal")
    public Double getPrecioTotal() {
        return precioBase + (precioBase * impuesto);
    }
    
    @JsonIgnore
    public Double getImpuesto() {
        return impuesto;
    }
}
```

### 3. Validación Durante Deserialización

```java
public class Usuario {
    private String email;
    
    @JsonSetter("email")
    public void setEmail(String email) {
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
        this.email = email;
    }
}
```

### 4. Serialización Condicional

```java
public class Usuario {
    private String nombre;
    private String password;
    private boolean admin;
    
    @JsonProperty
    public String getPassword() {
        return admin ? password : null;
    }
}
```

### 5. Conversión de Tipos Personalizados

```java
public class MonedaSerializer extends JsonSerializer<BigDecimal> {
    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, 
                         SerializerProvider serializers) throws IOException {
        gen.writeString("$" + value.setScale(2, RoundingMode.HALF_UP));
    }
}

public class Producto {
    @JsonSerialize(using = MonedaSerializer.class)
    private BigDecimal precio;
}

// JSON: {"precio":"$99.99"}
```

## 📊 Comparación con otras librerías

| Característica | Jackson | Gson | JSON-B |
|---------------|---------|------|--------|
| **Velocidad** | ⚡⚡⚡ Muy rápida | ⚡⚡ Rápida | ⚡⚡ Rápida |
| **Popularidad** | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐ |
| **Configuración** | Muy flexible | Simple | Estándar Java EE |
| **Anotaciones** | Extensas | Limitadas | Estándar |
| **Spring Boot** | ✅ Por defecto | ❌ | ❌ |
| **Streaming** | ✅ Sí | ✅ Sí | ✅ Sí |

## 🛠️ Manejo de Errores

```java
try {
    Usuario usuario = mapper.readValue(json, Usuario.class);
} catch (JsonProcessingException e) {
    // Error al procesar JSON
    System.err.println("Error de sintaxis JSON: " + e.getMessage());
} catch (JsonMappingException e) {
    // Error al mapear a objeto
    System.err.println("Error al mapear JSON: " + e.getMessage());
} catch (IOException e) {
    // Error de I/O
    System.err.println("Error de lectura: " + e.getMessage());
}
```

## 🎯 Mejores Prácticas

1. ✅ **Reutiliza ObjectMapper**: Es thread-safe y costoso de crear
2. ✅ **Usa @JsonIgnoreProperties(ignoreUnknown = true)**: Hace tu API más robusta
3. ✅ **Configura fechas apropiadamente**: Usa JavaTimeModule para Java 8+
4. ✅ **No uses @JsonAutoDetect sin necesidad**: Puede exponer campos privados
5. ✅ **Valida JSON de entrada**: Nunca confíes en datos externos
6. ✅ **Usa TypeReference para colecciones genéricas**: Evita problemas de tipos
7. ✅ **Implementa equals() y hashCode()**: Importante para comparaciones

## 💡 Ejercicios Prácticos

### Ejercicio 1: API de Usuarios
Crea un sistema que:
- Serialice/deserialice usuarios con nombre, email, edad
- Ignore el campo password en la salida
- Formatee la fecha de registro como "dd/MM/yyyy"
- Maneje usuarios sin email sin fallar

### Ejercicio 2: Catálogo de Productos
Implementa:
- Lista de productos con categorías (relaciónMany-to-Many)
- Precio formateado como moneda
- Campo calculado "disponible" (stock > 0)
- Ignorar propiedades desconocidas

### Ejercicio 3: Sistema de Órdenes
Desarrolla:
- Orden con lista de items
- Cálculo de total automático
- Serialización de enum Estado (PENDIENTE, ENVIADO, ENTREGADO)
- Fechas en formato ISO-8601

## 📚 Recursos Adicionales

- [Jackson Official Documentation](https://github.com/FasterXML/jackson-docs)
- [Jackson Annotations](https://github.com/FasterXML/jackson-annotations/wiki/Jackson-Annotations)
- [Baeldung Jackson Tutorials](https://www.baeldung.com/jackson)

## 🎓 Resumen de Métodos Clave

```java
// ObjectMapper
writeValueAsString(Object)      // Java → JSON String
writeValue(File, Object)        // Java → JSON File
readValue(String, Class)        // JSON String → Java
readValue(File, Class)          // JSON File → Java
readTree(String)                // JSON → JsonNode
convertValue(Object, Class)     // Object → Object (via JSON)

// Configuración
configure(Feature, boolean)     // Activar/desactivar features
registerModule(Module)          // Registrar módulos (fechas, etc)
setSerializationInclusion()     // Control de valores null
```

---

**Proyecto de ejemplo**: Consulta el código fuente en este repositorio para ver implementaciones completas de todos estos conceptos.