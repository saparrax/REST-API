#Algoritmo de Visibilidad

Aplicación de Spring Boot que implementa un algoritmo para filtrar los productos
visibles basandose de datos proporcionados en archivos CSV.

La aplicación tiene los siguientes package:
    -controller: contiene controladores y el handler de las excepciones.
    -service: contiene las interfaces de los servicios, las implementaciones y los DTO que usan estos.
    -swagger: configuración del swagger.

Dentro de resources tenemos:
    -data/csv: archivos CSV que se procesarán.
    -data/csv/test: archivos CSV para los test unitarios.

#Configuración proyecto
-Java 11
-Maven
-Spring Boot 2.7.12
-Packaging Jar

#Ejecución servicio

1-Verifica que tienes los archivos CSV necesarios ('product.csv', 'size.csv' y 'stock.csv') en la ruta '/data/csv'
dentro de resources.
2-Arrancar la aplicación.
3-Lanzar un request GET a la siguiente URL 'http://localhost:8080/api/products'.

#Test unitarios
La aplicación tiene test unitarios de todos los servicios y el controller:
También hay un test de integración para probar la request.
Test:
    -CsvReaderServiceImplTest
    -VisibilityServiceImplTest
    -VisibilityControllerIT

#Docker
La aplicación esta dockerizada y el archivo 'Dockerfile' se encuentra en la raiz el proyecto.
Comandos para desplegar en docker, debemos estar en la raíz del proyecto y ejecutar los
siguentes comandos en un terminal:    
    docker build -t spring-boot-docker .
    docker run -p 8080:8080 spring-boot-docker

#Swagger
Configuración básica de Swagger para gestionar las request.
Para acceder a swagger debemos arrancar la aplicación y acceder a la siguiente URL 'http://localhost:8080/swagger-ui/#/'

#Logger
Logger en los servicios y controllers para registrar información de la aplicación.
Log configurado a DEBUG en el properties de la aplicación(application.properties).

#Estructuras de datos utilizadas en el algoritmo
1- List: con esta estructura puedo almacenar y iterar los productos.
2- Map: con esta estructura puedo buscar y acceder de forma rápida a las tallas asociadas al Producto.
o el stock asociado a las tallas. En vez de recorrer una List puedo filtrar directamente con la clave
para obtener los elementos asociados. Con el uso del Map evito recorrer elementos que no me interesan.

#Complejidad temporal del algoritmo
La complejidad temporal es O(n*m), donde 'n' representa los productos y 'm' el número de tallas.
El peor caso sería recorrer todas las tallas por cada producto.
En el segundo bucle gracias al Map itero únicamente las tallas asociadas al producto, de esta manera reduzco la
lista de tallas a iterar.
Para evitar un tercer bucle he implementado un Map con el stock asociado a las tallas. De esta manera puedo consultar
directamente el stock a partir de la key y evito recorrer toda la lista.
