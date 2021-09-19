package operations;

import java.io.Serializable;

public enum ResponseStatus implements Serializable {
    Success, UnAuthorized, ServerError, NotFound
}
