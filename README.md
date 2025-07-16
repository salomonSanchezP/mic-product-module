mic-product-module: Microservicio de Gestión de Productos
Este repositorio contiene el microservicio mic-product-module, diseñado para gestionar la información de productos. El proyecto sigue una arquitectura modular, utilizando Spring Boot para la implementación del API REST, OpenAPI para la especificación y generación de la API, y JaCoCo para la generación de reportes de cobertura de código de pruebas unitarias.

🌟 Características
API RESTful Completa: Operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para productos.

Especificación OpenAPI: Definición clara y concisa de la API utilizando OpenAPI 3.0, lo que permite la generación automática de código.

Generación de Código: Utiliza openapi-generator-maven-plugin para generar la especificación de la API y los DTOs.

Cobertura de Código: Reportes detallados de cobertura de pruebas unitarias generados con JaCoCo.

Persistencia de Datos: Integración con MongoDB para el almacenamiento de la información de productos.

Modularidad: Estructura de proyecto multi-módulo para una mejor organización y mantenimiento.

🏗️ Estructura y Arquitectura del Proyecto (Arquitectura Hexagonal)
El proyecto está organizado en los siguientes módulos Maven y sigue los principios de la Arquitectura Hexagonal (también conocida como Arquitectura de Puertos y Adaptadores). Esta arquitectura busca aislar la lógica de negocio central de las preocupaciones técnicas externas (como la base de datos, la interfaz de usuario, o servicios externos), haciendo el sistema más flexible, testable y mantenible.

mic-product-module/
├── .idea/                      # Archivos de configuración de IDE (IntelliJ IDEA)
├── jacoco-report-aggregate/    # Módulo para la agregación de reportes de cobertura JaCoCo
│   └── pom.xml
├── product/                    # Módulo principal con la implementación del microservicio
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/mic/product/
│   │   │   │       ├── application/  # Lógica de negocio y servicios (Puerto de Aplicación)
│   │   │   │       ├── commons/      # Utilidades y manejo de excepciones
│   │   │   │       ├── domain/       # Modelos de dominio (Entidades de Dominio - DO) y mappers (Núcleo del Negocio)
│   │   │   │       └── infrastructure/ # Adaptadores de infraestructura (REST, persistencia - Adaptadores)
│   │   │   └── resources/
│   │   └── test/
│   └── pom.xml
├── product-api-spec/           # Módulo para la definición y generación de la especificación OpenAPI (Puerto de la API)
│   ├── src/
│   │   ├── main/
│   │   │   └── resources/
│   │   │       └── openapi/
│   │   │           ├── common.yml
│   │   │           ├── openapi-rest.yml
│   │   │           └── product/
│   │   │               ├── product-api.yml
│   │   │               └── product-components.yml
│   │   └── test/
│   └── pom.xml
└── pom.xml                     # POM padre para el proyecto multi-módulo

Conceptos Clave en la Arquitectura Hexagonal:
Núcleo del Negocio (Domain): Contiene la lógica de negocio principal y las entidades de dominio (DO). Es completamente independiente de cualquier tecnología externa. En este proyecto, se encuentra en com.mic.product.domain.

DO (Domain Object): Representan los objetos de negocio puros, sin acoplamiento a la infraestructura. Son la verdad del negocio y no deben contener anotaciones de frameworks específicos de persistencia o web. Por ejemplo, ProductDO en com.mic.product.domain.model.

Puertos (Ports): Son interfaces que definen cómo el núcleo del negocio interactúa con el mundo exterior. Hay dos tipos principales:

Puertos de Aplicación (Driven Ports): Interfaces que el núcleo de negocio expone para ser "manejado" por el exterior (ej. ProductService en com.mic.product.application).

Puertos de Infraestructura (Driving Ports): Interfaces que el núcleo de negocio necesita para "manejar" la infraestructura (ej. ProductRepository en com.mic.product.application).

Adaptadores (Adapters): Implementan los puertos y conectan el núcleo del negocio con tecnologías específicas.

Adaptadores de Entrada (Driving Adapters): Implementan los puertos de aplicación y exponen la funcionalidad del negocio a clientes externos (ej. ProductController en com.mic.product.infrastructure.rest). Aquí es donde los DTOs son cruciales.

Adaptadores de Salida (Driven Adapters): Implementan los puertos de infraestructura y permiten que el negocio interactúe con sistemas externos como bases de datos (ej. ProductRepositoryAdapter en com.mic.product.infrastructure.persistence).

DTOs (Data Transfer Objects)
Los DTOs son objetos simples que se utilizan para transferir datos entre diferentes capas de la aplicación o entre la aplicación y el cliente externo. En este proyecto:

Se definen en la especificación OpenAPI (product-api-spec/src/main/resources/openapi/product/product-components.yml).

Son generados automáticamente por openapi-generator-maven-plugin en el paquete com.mic.product.infrastructure.domain.dto.

Se utilizan en la capa REST (ProductController) para recibir y enviar datos a través de la API.

Los mappers (ej. ProductRestMapper y ProductMapper) se encargan de transformar los DTOs a DOs (objetos de dominio) y viceversa, asegurando que la lógica de negocio opere solo con los objetos de dominio puros. Esto desacopla la API de la lógica de negocio, permitiendo cambios en la estructura de la API sin afectar el dominio.

🛠️ Tecnologías Utilizadas
Java: Versión 21

Spring Boot: Framework para el desarrollo de aplicaciones basadas en Spring.

Maven: Herramienta de gestión de proyectos y construcción.

MongoDB: Base de datos NoSQL para la persistencia de datos.

OpenAPI Generator: Para la generación de código a partir de la especificación OpenAPI.

Springdoc OpenAPI: Para la documentación interactiva de la API.

MapStruct: Para la generación automática de mappers entre DTOs y entidades de dominio.

JaCoCo: Para la generación de reportes de cobertura de código.

Lombok: Para reducir el código repetitivo (boilerplate code).

Docker: Para la gestión del entorno de base de datos.

🚀 API Documentation
La API de productos está definida utilizando OpenAPI 3.0. Los archivos de especificación se encuentran en product-api-spec/src/main/resources/openapi/.

Endpoints Principales
GET /product/v1/data/: Obtener todos los productos.

POST /product/v1/data/: Crear un nuevo producto.

GET /product/v1/data/{id}: Obtener un producto por su SKU.

PUT /product/v1/data/{id}: Actualizar un producto existente por su SKU.

PATCH /product/v1/data/{id}: Actualizar parcialmente un producto por su SKU.

DELETE /product/v1/data/{id}: Eliminar un producto por su SKU.

Para una documentación interactiva de la API (Swagger UI), una vez que el servicio esté en ejecución, puedes acceder a: http://localhost:9090/swagger-ui.html (o la URL base configurada).

📊 Reportes de Cobertura (JaCoCo)
El proyecto está configurado para generar reportes de cobertura de código utilizando JaCoCo. El módulo jacoco-report-aggregate se encarga de consolidar los reportes de todos los submódulos.

Para generar el reporte, ejecuta el siguiente comando desde el directorio raíz del proyecto:

mvn clean install

Una vez completada la construcción, el reporte de cobertura agregado estará disponible en:

jacoco-report-aggregate/target/site/jacoco-aggregate/index.html

A continuación, se muestra un ejemplo del reporte de cobertura generado por JaCoCo:

! (http://googleusercontent.com/file_content/1)

Exclusiones de Cobertura:
Algunas clases son excluidas del reporte de cobertura para enfocarse en la lógica de negocio relevante. Estas incluyen:

Clases de aplicación (*Application.class)

Clases de configuración (*Configuration.class)

Clases de excepción (*Exception.class, *ExceptionHandler.class)

Clases de enumeración (*Enum.class, Enum*.class)

Clases de entidad (*Entity.class, *DO.class)

Clases de repositorio (*Repository.class)

Clases de utilidad (*Util.class)

Clases de API generadas (*Api.class)

Clases de constantes (*Constant.class)

Clases DTO (*DTO.class)

Clases de verificación de inicio (*StartupCheck.class)

⚙️ Cómo Empezar
Sigue estos pasos para levantar y ejecutar el microservicio en tu entorno local.

Prerrequisitos
Asegúrate de tener instalado lo siguiente:

Java Development Kit (JDK): Versión 21

Apache Maven: Versión 3.x o superior

Docker: Para ejecutar la base de datos MongoDB

Construcción del Proyecto
Clona este repositorio:

git clone <URL_DEL_REPOSITORIO>
cd mic-product-module

Navega a la raíz del proyecto y construye todos los módulos:

mvn clean install

Este comando compilará el código, ejecutará las pruebas y generará los JARs de cada módulo, incluyendo la generación de la especificación OpenAPI.

Configuración de la Base de Datos (MongoDB con Docker Compose)
La base de datos MongoDB se ejecuta utilizando Docker Compose, lo que facilita la configuración y el levantamiento del entorno de desarrollo.

Crea un archivo docker-compose.yml en la raíz del proyecto (o en un directorio dedicado para la infraestructura) con el siguiente contenido:

version: '3.8'

services:
  mongo:
    image: mongo:latest
    container_name: mongodb
    restart: always
    ports:
      - "27017:27017"
    environment:
      MONGO_INITDB_ROOT_USERNAME: root
      MONGO_INITDB_ROOT_PASSWORD: pass
    volumes:
      - mongo_data:/data/db

  mongo-express:
    image: mongo-express:latest
    container_name: mongo-express
    restart: always
    depends_on:
      - mongo
    ports:
      - "8081:8081"
    environment:
      ME_CONFIG_MONGODB_ADMINUSERNAME: root
      ME_CONFIG_MONGODB_ADMINPASSWORD: pass
      ME_CONFIG_MONGODB_SERVER: mongodb

volumes:
  mongo_data:
    driver: local

Asegúrate de que Docker esté corriendo en tu sistema.

Desde el directorio donde guardaste docker-compose.yml, levanta los servicios de Docker Compose:

docker-compose up -d

Esto iniciará un contenedor de MongoDB en el puerto 27017 y una interfaz web para MongoDB (mongo-express) en el puerto 8081. Puedes acceder a mongo-express en http://localhost:8081 para visualizar y gestionar la base de datos.

Ejecución del Microservicio
Puedes ejecutar el microservicio de dos maneras:

Desde el JAR generado:
Navega al directorio del módulo product y ejecuta el JAR:

cd product
java -jar target/product-0.0.1-SNAPSHOT.jar

Usando Maven Spring Boot Plugin:
Desde el directorio del módulo product:

cd product
mvn spring-boot:run

El microservicio estará disponible en http://localhost:9090 (o el puerto configurado en application.properties).

