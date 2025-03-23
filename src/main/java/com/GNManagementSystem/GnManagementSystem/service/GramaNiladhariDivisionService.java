package com.GNManagementSystem.GnManagementSystem.service;

import com.GNManagementSystem.GnManagementSystem.converter.GnDivisionConverter;
import com.GNManagementSystem.GnManagementSystem.dto.GnDivisionDto;
import com.GNManagementSystem.GnManagementSystem.dto.ResponseDto;
import com.GNManagementSystem.GnManagementSystem.entity.GramaNiladhariDivision;
import com.GNManagementSystem.GnManagementSystem.repository.GramaNiladhariDivisionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class GramaNiladhariDivisionService {
    private final GramaNiladhariDivisionRepository gramaNiladhariDivisionRepository;
    private final GnDivisionConverter gnDivisionConverter;
    public GramaNiladhariDivision saveGnDivision(GramaNiladhariDivision gramaNiladhariDivision) {
        return gramaNiladhariDivisionRepository.save(gramaNiladhariDivision);
    }

    public List<GramaNiladhariDivision> getAllGnDivisions() {
        return gramaNiladhariDivisionRepository.findAll();
    }

    public Optional<GramaNiladhariDivision> getGnDivisionById(String id) {
        return gramaNiladhariDivisionRepository.findById(id);
    }

    public ResponseDto deleteGnDivision(String id) {
        gramaNiladhariDivisionRepository.deleteById(id);
        return new ResponseDto("GnDivision deleted successfully");
    }

    public GramaNiladhariDivision updateGramaNiladhariDivision(GnDivisionDto gnDivisionDto) {
        GramaNiladhariDivision gramaNiladhariDivision = gnDivisionConverter.convert(gnDivisionDto);
        log.info("gn divison,{}", gramaNiladhariDivision);
        return gramaNiladhariDivisionRepository.save(gramaNiladhariDivision);
    }
}
