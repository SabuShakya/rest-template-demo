# Rest Template #

RestTemplate is also a high-level API, which in turn is based on an HTTP client.
Rest Template is used to create applications that consume RESTful Web Services.


## RestTemplate Methods ##

|Method|Description|
|:---|:---|
|void delete |	Executes a DELETE request and returns nothing.|
|ResponseEntity<T> exchange | Executes a specified HTTP method, such as GET or POST, and returns a ResponseEntity that contains both the HTTP status code and the resource as an object.|
|T execute | Works similar to exchange, but expects an additional RequestCallback and a ResultSetExtractor as parameters. This is useful, for example, if you frequently create complex requests or want to process complex responses.|
|ResponseEntity<T> getForEntity | Executes a GET request and returns a ResponseEntity that contains both the status code and the resource as an object.|
|T getForObject | Works similar to getForEntity, but returns the resource directly.|
|HttpHeaders headForHeaders | Executes a HEAD request and returns all HTTP headers for the specified URL.|
|Set<HttpMethod> optionsForAllow | Executes an OPTIONS request and uses the Allow header to return which HTTP methods are allowed under the specified URL.|
|T patchForObject | Executes a PATCH request and returns the representation of the resource from the response. The JDK HttpURLConnection does not support PATCH, but Apache HttpComponents and others do.|
|ResponseEntity<T> postForEntity | Executes a POST request and returns a ResponseEntity which contains the status code as well as the resource as an object.|
|URI postForLocation | Works like postForEntity, but returns the Location header from the response, which indicates under which URI the newly created resource can be reached.|
|T postForObject | Works like postForEntity, but returns the resource directly.|
|void put | Executes a PUT request and returns nothing.|

References:

- https://www.youtube.com/watch?v=ADtm_HXHyB8&t=294s&ab_channel=JavaGuides
- https://springframework.guru/using-resttemplate-in-spring/
- https://www.baeldung.com/rest-template
- https://www.baeldung.com/spring-rest-template-list