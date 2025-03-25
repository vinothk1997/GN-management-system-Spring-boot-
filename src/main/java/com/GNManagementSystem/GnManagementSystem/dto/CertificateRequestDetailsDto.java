package com.GNManagementSystem.GnManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CertificateRequestDetailsDto {
    private String firstName;
    private String lastName;
    private String email;
    private String nic;
    private String address;
    private String city;
    private String purpose;
    private String gnDivision;
    private String gnName;
    private String serialNo;
}
