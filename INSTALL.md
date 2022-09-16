# ZSSN APP

Para iniciar el backend, hay dos opciones, usar docker o hacerlo de forma manual

## Inicio con Docker

Primero accedemos a la carpeta /backend

    cd backend
    
Luego ejecutamos el docker compose dentro del directorio


    docker-compose up

Este comando levantará 3 contenedores(mysql, phpmyadmin, spring)

La base de datos se ejecutará en el puerto 3001, phpmyadmin en el puerto 9001 y spring boot en el puerto 8090 (Estos puertos se pueden actualizar en el archivo [docker-compose.yml](https://github.com/FernandoOrM/full-stack-recruitment-test/blob/main/backend/docker-compose.yml) que se encuentra en la carpeta backend)

Por lo que para acceder a el gestor de base de datos podemos acceder en el navegador en [http://localhost:9001](http://localhost:9001)

Y podemos acceder al API en [http://localhost:8090](http://localhost:8090)

La base de datos ya cuenta con algunos registros por lo que puede comenzar a probar el API.

Podemos acceder a la documentación del API en [http://localhost:8090/api/swagger-ui.html](http://localhost:8090/api/swagger-ui.html)

Nota: cualquier cambio en el código del backend, por ejemplo, actualizar el archivo application.properties no se verá reflejado hasta ejecutar de nuevo el comando 


    docker-compose up --build

## Inicio manual

Para esta opción es necesario contar con java 17 y mysql instalado en el equipo

Y para usar los datos precargados en la base de datos podemos usar el archivo [zssn.sql](https://github.com/FernandoOrM/full-stack-recruitment-test/blob/main/backend/zssn.sql) que se encuentra en la carpeta backend

También es necesario actualizar el archivo [application.properties](https://github.com/FernandoOrM/full-stack-recruitment-test/blob/main/backend/src/main/resources/application.properties) dentro del proyecto de spring boot con las credenciales de la base de datos


    spring.datasource.url=jdbc:mysql://zssn_db/zssn?useSSL=false
    spring.datasource.dbname=zssn
    spring.datasource.username=root
    spring.datasource.password=root_password
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver


Por último ejecutamos el comando para inciar Spring


    mvn spring-boot:run


## Ejecución de la app

Para ejecutar la aplicación antes debemos actualizar la url de el API, para ello vamos al archivo [SurvivorClient.kt](https://github.com/FernandoOrM/full-stack-recruitment-test/blob/main/app/app/src/main/java/com/fernando/zssn/model/SurvivorClient.kt), y actualizamos la linea 8 con la URL del API


    .baseUrl("http://ip_local:8090/api/")
