package main.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestTwo extends Request {
    private String second;


    public RequestTwo(String first, String second) {
        super(first);
        this.second = second;
    }
}
