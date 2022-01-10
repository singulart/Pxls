package space.pxls.util;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.AttachmentKey;
import io.undertow.util.HeaderValues;
import space.pxls.App;

import java.util.List;

public class IPReader implements HttpHandler {
    public static final AttachmentKey<String> IP = AttachmentKey.create(String.class);

    private final HttpHandler next;

    public IPReader(HttpHandler next) {
        this.next = next;
    }


    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        String addr = exchange.getSourceAddress().getAddress().getHostAddress();

        for (String headerName : App.getConfig().getStringList("server.proxy.headers")) {
            HeaderValues header = exchange.getRequestHeaders().get(headerName);
            if (header != null && !header.isEmpty()) {
                String[] split = header.element().split(",");
                addr = split[split.length - 1];
            }
        }

        exchange.putAttachment(IP, addr);
        next.handleRequest(exchange);
    }
}
