package ur.lab3.predictmatchresult.services.predict;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
public class NoTrainSetException extends RuntimeException {

    public NoTrainSetException(String message) {
        super(message);
    }
}
