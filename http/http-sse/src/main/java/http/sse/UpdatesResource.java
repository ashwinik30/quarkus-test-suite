package http.sse;

import java.util.concurrent.locks.LockSupport;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.sse.OutboundSseEvent;
import jakarta.ws.rs.sse.Sse;
import jakarta.ws.rs.sse.SseEventSink;

@ApplicationScoped
@Path("updates")
@Produces(MediaType.SERVER_SENT_EVENTS)
public class UpdatesResource {

    @Context
    Sse sse;

    @GET
    public void updates(@Context SseEventSink eventSink) {
        eventSink.send(createEvent("test234", "test"));
        LockSupport.parkNanos(1_000_000_000L);
    }

    private OutboundSseEvent createEvent(String name, String data) {
        return sse.newEventBuilder()
                .name(name)
                .data(data)
                .build();
    }

}
