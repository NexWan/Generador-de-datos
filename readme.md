# Generador de nombres
Este proyecto tiene la finalidad de crear datos para bases de datos.
<br>
Este proyecto funcionara con una lista de texto que tenga ciertos datos y numeros random
que se crearan a partir de codigo.<br>

## DATOS QUE GENERA DE MOMENTO
- Nombre
- Apellido Paterno
- Apellido Materno
- Telefono
- Numeros random (puede usarse como un id)
- Correo

## Que archivos hay?
El archivo cuenta solamente con 2 archivos de texto, uno para los [nombres](https://github.com/NexWan/Generador-de-datos/blob/master/src/main/resources/com/nexwan/generadornombres/nombres.txt) y otro
para los [apellidos](https://github.com/NexWan/Generador-de-datos/blob/master/src/main/resources/com/nexwan/generadornombres/apellidos.txt).<br>
Cabe mencionar que estos estan basados en los mas comunes de Mexico.<br>
Los datos como telefono y numero random son generados mediante codigo.<br>
El dato de correo es generado mediante un substring del nombre, apellido y numeros random, agregandole un @ declarado en el codigo.

## Screenshots
### Interfaz Principal con sus opciones
![Interfaz principal](https://i.imgur.com/6v9fPRj.png)

### Se pregunta cuantos datos quiere generar
![datos](https://i.imgur.com/Dqc7qCR.png)

### Configuracion de la tabla
Aqui en esta parte se tiene que espeficificar tomando en cuenta el nombre del atributo en tu tabla de Oracle!<br>
![tablas](https://user-images.githubusercontent.com/89736703/230891449-5dd5de2d-e843-4dc0-834d-ff56b847b39d.png)

### Script resultante
Te da un script con la insercion de los datos que pediste ya formateado con su nombre de tabla y de los atributos<br>
![image](https://user-images.githubusercontent.com/89736703/230891740-da37a940-1b95-4b9f-b6dd-61d28a976cf9.png)
