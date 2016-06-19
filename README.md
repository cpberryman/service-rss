An RSS service which preloads a cache with RSS entries by retrieving RSS URLs storred in MongoDB. It provides a RESTful endpoint which offers request mappings to retrieve the following:

- all entries
- a given number of entries for all feeds 
- all entries for a given feed
- a given number of entries for a given feed
 
A scheduled task loads new RSS entries into the cache at a certain time interval concurrently. The following dependencies are used in the project:

- spring-boot-starter-ws
- spring-boot-starter-web
- spring-boot-starter-tomcat
- spring-boot-starter-data-mongodb
- ehcache
- rome
