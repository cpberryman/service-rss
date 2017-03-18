A generic Spring Boot RSS endpoint with the following RESTful JSON API:

- add a given feed url
- retrieve all feed urls
- retrieve a given number of entries for each feed
- retrieve a given number of entries for a given feed
 
A scheduled task concurrently loads new RSS entries for each feed into the cache.
