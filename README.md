# planning portal importer

Automates process of importing planning data from proprietary database (eg idox).

At the moment, latches onto outgoing requests and re-requests the data as GeoJSON.

I access the site via Firefox, proxy the requests via Proxyman, and use the following script:

```
async function onRequest(context, url, request) {
  var param = {
    body: request
  }
  var output = await $http.post("http://endpoint:8080/api/requests?authority=<authority_name>", param);
  return request;
}
```

You'll need ES up and running somewhere, and an index set up and ready to use:

```
./src/main/resources/refresh.sh http://es-instance:9200
```

