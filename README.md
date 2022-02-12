# Disney_api 
CHALLENGE BACKEND - Java Spring Boot (API) .Esta aplicación se desarrolló con fines educativos para presentar en la aceleración de alkemy.org 
<img align="left" height="200px" width="200px" src="https://github.com/adriangh1321/disney-api/blob/master/alkemyLogo.svg">

# Descripción
La API permite conocer el mundo de Disney mediante el acceso a diversos end-points.
# Detalles

La aplicación cuenta con servicios para gestionar los personajes, películas y géneros del mundo de Disney.
Para acceder a ellos se requiere previamente que el usuario se registre en la aplicación y se autentique. Para esto se le entregará un token JWT válido por 10 horas.
Dicho token es solicitado en todos los servicios de la API. Para más detalles consultar la documentación adjunta en la carpeta postman.

Con la API podrá:
- Usuarios (registrarse , autenticarse)
- Personaje (crear, modificar, leer , borrar, agregar y borrar películas)
- Movie (crear, modificar, leer , borrar, agregar y borrar personajes)
- Genre (crear, modificar, leer , borrar)

Además es posible realizar busquedas por filtros (edad, peliculas, nombre, etc) y ordenar los resultados de forma ASC o DESC.

Para la creación se utilizo:
```
NetBeans IDE 8.2 (Build 201609300101)
Java: 1.8.0_111  
Spring Boot 2.6.1 (Java 8 y empaquetado JAR)
Apache-maven-3.8.2
MySQL Workbench 8.0.26
Postman  9.8.2
```

# Uso
Para correr la aplicación debe descargar el repository de forma local. Antes de lanzarla realize los siguiente pasos:

1. Abrir Workbeach y crear la base de datos disney.
2. En la carpeta database se encuentra el archivo `DumpApiDisney.sql` , corra las query en él para generar las tablas con valores insertados.
3. En application.properties defina las siguientes propiedades:

- `spring.datasource.username:` //aquí ingrese el usuario de su gestor de base de datos
- `spring.datasource.password:` //aquí ingrese el password de su gestor de base de datos
- `sendgrid.email.sender:` //aquí ingrese el email que utilizará con sendgrid para enviar el email de bienvenida
- `sendgrid.email.enabled:`   //puede activar el servicio de email desde aquí cambiando a true, si lo desea.
- `sendgrid.api.key:`  //ingrese aquí su api key de sendgrid
 
4. Para ejecutar la aplicación abra la ventana de comandos de windows e ingrese :
```
cd target
```
Ejemplo:
cd C:\Users\adria\Documents\NetBeansProjects\GuiaSpring\DisneyApplication

```
mvnw spring-boot-run
```

5. Para probar la aplicación puede utilizar postman y acceder a los end-points, la url para las requests es http://localhost:8080/

Debe importar el archivo `DISNEY API.postman_collection.json` dentro de la carpeta postman. Una vez dentro de postman debe crear una variable de entorno llamada `token`,esto es  para inyectar automaticamente el token en cada request que realize .Luego vaya a `USER API REQUEST/ SIGN IN` para autenticarse. Puede utilizar el siguiente usuario precargado:
```
username:user@gmail.com
password:12345678
```
Sino también puede generar su propio usuario en `USER API REQUEST/ SIGN UP`
Si se autenticó correctamente ya debe poder usar todos los demás end-points dentro de:
```
CHARACTER API REQUEST
MOVIE API REQUEST
GENRE API REQUEST
```

