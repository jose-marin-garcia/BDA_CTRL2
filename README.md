# BDA_CTRL2

Pasos para correr el programa

1.- Es necesario abrir pgadmin

2.- Hay que abrir la cmd con la direccion de la carpeta DatabaseCtrl2

3.- En la consola hay que ejecutar este comando : psql -U el nombre de usuario con el cual se instalo postgres -h localhost -f dbCreate.sql 
(Cuando se le pida ingresar la contraseña ingrese la que designo al instalar postgres)

4.- Ahora en intelliJ abra la carpeta BackendCtrl2 y edite application.properties.

5.- Dentro de las variables hay que escribir: 

- DB_URL=jdbc:postgresql://localhost:5432/ctrl2bda

- USER=El nombre de usuario con el cual se instalo postgres

- PASSWORD=La contraseña con el cual se instalo postgres

6.- Ahora con esto se puede iniciar el proceso con Run y con esto ya esta funcionando el BackEnd

7.- Ahora en visual estudio abra la carpeta FrontendCtrl2.

8.- En el terminal ejecute este comando "npm install --legacy-peer-deps" (para que se instale nuxt)

9.- Luego en la terminal ejecute este comando "npm audit fix --force"

10.- Para iniciar el frontend dentro de la terminal escriba "npm run dev" luego con ctrl + click izquierdo abra la direccion "(http://localhost:3000/)".
