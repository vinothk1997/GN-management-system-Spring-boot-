package com.GNManagementSystem.GnManagementSystem.converter;

import com.GNManagementSystem.GnManagementSystem.dto.GnDivisionDto;
import com.GNManagementSystem.GnManagementSystem.entity.GramaNiladhariDivision;
import org.springframework.stereotype.Component;

@Component
public class GnDivisionConverter {
    public GramaNiladhariDivision convert(GnDivisionDto gnDivisionDto) {
        return GramaNiladhariDivision.builder()
                .id(gnDivisionDto.getId())
                .name(gnDivisionDto.getName())
                .code(gnDivisionDto.getCode())
                .address(gnDivisionDto.getAddress())
                .phone(gnDivisionDto.getPhone())
                .build();
    }
}
