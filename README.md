# RetoTweets
 Prueba Técnica twitter

● Configuraciones del ejercicio: Se hacen desde el application.properties

- Solo se deben persistir aquellos tweets cuyos usuarios superen un número N de
seguidores (default 1500).
twitter.filter.minFollowers = 1500

- Solo se deben persistir aquellos tweets cuyo idioma esté en una lista de idiomas
permitidos (default español, francés, italiano). twitter.filter.allowedLangs = es,it,fr
  
- Consultar una clasificación de los N hashtags más usados (default 10). twitter.filter.maxHashtags = 10

● Confgiuracion extra para cargar perfiles de twitter:
twitter.loader.users


Se puede probar la aplicación con una herramienta como postman o con el swagger integrado:
http://localhost:8081/swagger-ui/#/

La aplicion usa una BBDD h2, la consola esta activa, para acceder:
http://localhost:8081/h2-console/login.jsp?jsessionid=1000495385872c6767f4e1c959d44121

credenciales admin/admin
