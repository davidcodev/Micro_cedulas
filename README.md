# Proyecto Cedulas

Este proyecto es un microservicio que utiliza el framework Quarkus como base y sirve como práctica para generar y consultar cedulas válidas para el Ecuador tomando como parámetro la provincia; así mismo se puede generar una o varias cédulas aleatorias.

## Ejecución en modo dev:

Se puede utilizar el siguiente comando para ejecutar:
```shell script
.\mvnw quarkus:dev
```

> **_NOTA:_**  Quarkus dispone de una interfaz de usuario para desarrolladores, disponible en modo dev solamente desde:  http://localhost:8080/q/dev/.

## Empaquetado y ejecución

La aplicación se puede empaquetar usando el siguiente comando:
```shell script
.\mvnw package
```
Esto genera un archvo `quarkus-run.jar` en el directorio `target/quarkus-app/`.
Tomar en cuenta que no es un _über-jar_ ya que las dependecias se copian en el directorio `target/quarkus-app/lib/`.

La aplicación se ejecutará como cualquier archivo _.jar_ por ejemplo:

`java -jar target/quarkus-app/quarkus-run.jar`.

