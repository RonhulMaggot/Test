package main.dto;

import lombok.Getter;

@Getter
public class Response {
    private final int code;
    private final String description;

    public Response(int code) {
        this.code = code;
        switch (code) {
            case 0:
                description = "OK";
                break;
            case 1:
                description = "This NAME is already exists!";
                break;
            case 2:
                description = "Entity must not be null!";
                break;
            case 3:
                description = "There is no pair of this names!";
                break;
            case 4:
                description = "There is no such name!";
                break;
            case 5:
                description = "Wrong input!";
                break;
            default:
                description = "Something wrong!";
                break;
        }
    }
}
