# BDA_CTRL2

Pasos para correr el programa

1.- Es necesario abrir pgadmin

2.- Hay que abrir la cmd con la direccion de la carpeta DatabaseCtrl2

3.- En la consola hay que ejecutar este comando : psql -U "el nombre de usuario con el cual se instalo postgres" -h localhost -f dbCreate.sql

4.- Ahora en intelliv abra la carpeta BackendCtrl2 y edite application.properties.

5.- Dentro de las variables hay que escribir: 

- DB_URL=jdbc:postgresql://localhost:5432/ctrl2bda;

- USER="el nombre de usuario con el cual se instalo postgres";

- PASSWORD="La contraseña con el cual se instalo postgres"

6.- Ahora con esto se puede iniciar el proceso con Run y con esto ya esta funcionando el BackEnd

7.- Ahora en visual estudio abra la carpeta FrontendCtrl2.

8.- Para iniciar el frontend dentro de la consola escriba "npm run dev" y con ctrl + click izquierdo abra la direccion de localhost
