# :computer: Backend de mi portfolio | Argentina programa
:small_orange_diamond:Bienvenidos al repositorio de mi backend para el trabajo final integradod de #YoProgramo del curso de Argentina Programa.

:small_orange_diamond:Link para acceder al repositorio de mi [Frontend](https://github.com/BreiNus/programa-2022-frontendAngular)

:small_orange_diamond:Aclaracion: a dia de la fecha 14-06-2023, todo esta funcionando correctamente


## :key: Detalles a tener en cuenta:
:small_orange_diamond: Render puede fallar por causas que me exceden y arrojar el siguiente error, adjunto el link del error en el foro de render [click aqui](https://community.render.com/t/service-not-ready/11856/55)
![721e1fce49c731c7952fee0a3c60d94e](https://github.com/BreiNus/CrudBackendArgentinaPrograma/assets/113384178/07782e4f-9010-41ba-a8c1-01b4b31568ce) 
:white_check_mark: Una *solucion* provisoria es seguir las instrucciones del apartado de Instalacion y ejecutar el back de forma local.

:small_orange_diamond: Otro 2 errores que estan sucediendo es que falle el host de la DB en clever-cloud y arroja el siguien error:
![f9fceacfa2b2339581ba54f45f8914d4](https://github.com/BreiNus/CrudBackendArgentinaPrograma/assets/113384178/2d2bfd8c-260d-467e-a931-c75e13989709)

o el otro error es que se exceda el max de usuarios conectados y no funciona el matar todas las conecciones desde clever-cloud, como se ilustra en la siguiente imagen
![5da0849052cf2dc70dc6ce2b1c24c383](https://github.com/BreiNus/CrudBackendArgentinaPrograma/assets/113384178/ed26a2d6-1447-4cf3-aca8-3f5233da40d4)
:white_check_mark: Una *solucion* provisoria a este ultima error es descargar e importar mi [DataBase](https://mega.nz/file/54cB1KCT#pddyGSxxhikGwe3T146etijqQ-wV8mHr1V0zgd0ufgM) a un host local, como puede ser XAMP o WAMP, entro otros.

## :bulb: Instalacion:
:small_blue_diamond:Clonar el repositorio desde GIT o descargar el archivo ZIP: `git clone https://github.com/BreiNus/CrudBackendArgentinaPrograma.git`

:small_blue_diamond:Instalar las dependencias de maven: `mvn install`

:small_blue_diamond:Configurar el archivo application.properties si se usa otra DB.

:small_blue_diamond:Si por alguna razon el host de firebase deja de funcionar cambiar @CrossOrigin(origins = "https://mi-portfolio-web-6e5bd.web.app") por @CrossOrigin(origins = "*")

:small_blue_diamond:Ejecutar la app iniciando el archivo `BackEndCrudApplication.Java`

:heavy_exclamation_mark: Aclaracion Importante: Si es la primera vez que se corre el backend en una DB desde cero, antes de arrancar el proyecto descomentar el codigo del achivo `CreateRoles.java` que esta en `src/main/java/Backend/example/BackEndCRUD/util` para que cree los roles de ADMIN y USER,  <strong>luego volverlo a comentar, porque sino al ejecutar otra vez el back se volveran a crear</strong>

