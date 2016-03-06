package pl.kamrar.zarcie.example.complex;

import io.vertx.core.json.Json;
import io.vertx.rxjava.ext.web.RoutingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.kamrar.zarcie.example.complex.entity.Product;

@Component
public class ProductHandler extends DefaultVerticle {

    private static final String API_V1_PRODUCT = "/api/v1/product";

    @Autowired
    private GlobalRouter globalRouter;

    private Product product = new Product(0, "test");

    @Override
    public void start() throws Exception {
        globalRouter.router().get(API_V1_PRODUCT).handler(this::getAll);
        globalRouter.router().get(API_V1_PRODUCT + "/:id").handler(this::getOne);
        globalRouter.router().post(API_V1_PRODUCT).handler(this::addOne);
    }

    private void getAll(RoutingContext routingContext) {
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(product));
    }

    private void getOne(RoutingContext routingContext) {
        final String id = routingContext.request().getParam("id");
        if (Integer.valueOf(id) != 0) {
            routingContext.response().setStatusCode(404).end();
        } else {
            routingContext.response()
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(Json.encodePrettily(product));
        }
    }

    private void addOne(RoutingContext routingContext) {
        routingContext.response()
                .putHeader("content-type", "text/html")
                .end("<h1>Chuja, nie dodam</h1>");
    }
}
