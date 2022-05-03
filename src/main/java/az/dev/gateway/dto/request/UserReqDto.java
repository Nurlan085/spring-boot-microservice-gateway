package az.dev.gateway.dto.request;

import lombok.Data;

@Data
public class UserReqDto {

    private String username;
    private String password;
    private String name;
}
