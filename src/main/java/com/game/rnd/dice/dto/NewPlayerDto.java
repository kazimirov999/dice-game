package com.game.rnd.dice.dto;

import com.game.rnd.dice.type.Status;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class NewPlayerDto {

    @NotBlank
    @Size(max = 64)
    private String loginName;
    @NotBlank
    @Size(max = 64)
    private String firstName;
    @NotBlank
    @Size(max = 64)
    private String lastName;
    private Status status;

}
