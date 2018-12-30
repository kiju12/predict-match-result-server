package ur.lab3.predictmatchresult.easportsapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class IncorrectTeamIdException extends RuntimeException {
}
