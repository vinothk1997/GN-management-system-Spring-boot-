package com.GNManagementSystem.GnManagementSystem.service;

import com.GNManagementSystem.GnManagementSystem.converter.GramaNildhariConverter;
import com.GNManagementSystem.GnManagementSystem.dto.GramaNiladhariDto;
import com.GNManagementSystem.GnManagementSystem.dto.GramaNiladhariResponseDto;
import com.GNManagementSystem.GnManagementSystem.entity.GramaNiladhari;
import com.GNManagementSystem.GnManagementSystem.repository.GramaNildhariRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class GramaNildhariService {
    private final GramaNildhariConverter gramaNildhariConverter;
    private final GramaNildhariRepository gramaNildhariRepository;
    public GramaNiladhariResponseDto saveGramaNiladhari(GramaNiladhariDto gramaNiladhariDto) {
        GramaNiladhari gramaNiladhari=  gramaNildhariRepository.save(gramaNildhariConverter.convertToGramaNiladhari(gramaNiladhariDto));
        return gramaNildhariConverter.convertToGramaNiladhariResponseDto(gramaNiladhari);
    }

    public List<GramaNiladhariResponseDto> getAllGramaNildhari() {
        return gramaNildhariRepository.findAll().stream().map(gramaNildhariConverter::convertToGramaNiladhariResponseDto).toList();
    }
}
