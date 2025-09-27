package com.hungernet.hungernet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuSectionDtoCreate {

    @NotNull @NotBlank
    private String sectionName;
    @NotNull @NotBlank
    private Long menuId;

}
