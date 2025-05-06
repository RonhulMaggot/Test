package main.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"sum", "code", "description"})
public class ResponseSum extends Response {
    private final int sum;

    public ResponseSum(int code, int firstNum, int secondNum) {
        super(code);
        sum = firstNum + secondNum;
    }

    public ResponseSum(int code) {
        super(code);
        sum = 0;
    }
}
