# Instalación

## Requisitos
- Java SDK 1.8 o superior.
- Herramienta de construcción ant.
- Para el despliegue con docker-compose se requiere Docker y docker-compose.

## Preparación base de datos
- Instalar Postgres 9.3 o 9.5
- Crear rol 'gcar' con contraseña 'admin'
- Crear base de datos 'gcar'
- Ejecutar AR.sql en la base de datos 'gcar'

## Preparación WAR
- Para limpiar archivos temporales de compilación, ejecutar `ant -f build.xml clean`
- Para generar el archivo GCAR.war ejecutar `ant -f build.xml`

## Ejecutar todo con docker-compose

- Construir las imágenes requeridas: `docker-compose build`.

- Levantar los contenedores: `docker-compose up` o bien `docker-compose up -d`.
La primera vez que se levante la imagen de postgres, se creará la base de datos
con el usuario ya indicado.

### Cargar datos desde Adminer
- Conectarse al software Adminer, interface gráfica: http://localhost:9080.
- Ingresar con las credenciales:
    - Servidor: postgres
    - Usuario: gcar
    - Password: admin
- Seleccionar base de datos `gcar` y ejecutar contenidos de AR.sql.

### Ingresar a aplicación GCAR
- Ingresar a http://localhost:8080/GCAR
- Ingresar usuario y contraseña según los ingresados desde AR.sql

En caso de tener conflicto con los puertos, modificar el archivo docker-compose.yml.

# Para desarrollar

Cada vez que se hagan cambios en el código fuente, deben detenerse los
contenedores (`docker-compose down`) y generarse un nuevo archivo GCAR.war.
Luego de eso, volver a levantar todos los servicios (`docker-compose up`).
