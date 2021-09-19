package operations;

import java.io.Serializable;

public final class Response implements Serializable {
    private String body;
    private ResponseStatus status;

    public Response(String body, ResponseStatus status) {
        this.body = body;
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public static Response serverErrorResponse() {
        return new Response("Error reading request", ResponseStatus.ServerError);
    }
}
