# Build And Run Using Docker

```bash
$ docker image build -t sa .
```

Once the image is ready execute below command and that will start your spring boot app.

```bash
$ docker run -p 8080:8080 -t sa
```

Open browser and enter http://localhost:8080/app, this will start the application.
