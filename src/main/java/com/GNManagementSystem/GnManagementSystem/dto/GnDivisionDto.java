package com.GNManagementSystem.GnManagementSystem.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GnDivisionDto {
    private String id;
    private String name;
    private String address;
    private String code;
    private String phone;
}
