package main.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestTwo extends Request {
    private String secondName;


    public RequestTwo(String firstName, String secondName) {
        super(firstName);
        this.secondName = secondName;
    }
}
