# EIST Travel App [![Build Status](https://travis-ci.org/Reisz/EIST-Travel-App.svg)](https://travis-ci.org/Reisz/EIST-Travel-App)
A Google-Appengine app to route you to your destination using a chain of multiple different means of transportation.

## Compilation

We have hidden our api-keys by putting them in a class named ApiKey that is included in the `.gitignore`. You need to add it yourself to be able to compile this project:
```java
package de.tum.in.eist;

public class ApiKey {
	public static final String GOOGLE = "<your_api_key>";
}
```

We use maven to handle our dependencies.

* Install [Maven 3](http://maven.apache.org/download.html)
* Check out this repo and: `mvn clean install`

## Javascript Libraries
* [jQuery](https://jquery.com/)
* [Bootstrap](http://getbootstrap.com/)
* [AngularJS](https://angularjs.org/)
* [Leaflet](http://leafletjs.com/) Map-viewer
