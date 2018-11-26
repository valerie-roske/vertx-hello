package io.vertx.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.StaticHandler;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainVerticle extends AbstractVerticle {

  private Map<Integer, Airplane> airplanes = new LinkedHashMap<>();

  @Override
  public void start(Future<Void> future) throws Exception {
    createSomeData();

    Router router = Router.router(vertx);

    router.route().handler(StaticHandler.create());

    router.get("/airplanes").handler(this::getAllAirplanes);

    router.route("/").handler(routingContext -> {
      HttpServerResponse response = routingContext.response();

      response.sendFile("webroot/index.html");
    });

    vertx
      .createHttpServer()
      .requestHandler(router::accept)
      .listen(
        config().getInteger("http.port", 3001),
        result -> {
          if (result.succeeded()) {
            future.complete();
          } else {
            future.fail(result.cause());
          }
        }
      );

  }

  private void createSomeData() {
    Airplane airplane1 = new Airplane("N4567SW", "Dallas, Texas");
    airplanes.put(airplane1.getId(), airplane1);
    Airplane airplane2 = new Airplane("N567AC", "San Francisco, California");
    airplanes.put(airplane2.getId(), airplane2);
  }

  private void getAllAirplanes(RoutingContext routingContext) {
    routingContext.response()
      .putHeader("content-type", "application/json; charset=utf-8")
      .end(Json.encodePrettily(airplanes.values()));
  }

}
