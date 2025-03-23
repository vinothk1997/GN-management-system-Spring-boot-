package com.GNManagementSystem.GnManagementSystem.agent;

import com.GNManagementSystem.GnManagementSystem.converter.GnDivisionConverter;
import com.GNManagementSystem.GnManagementSystem.dto.GnDivisionDto;
import com.GNManagementSystem.GnManagementSystem.dto.ResponseDto;
import com.GNManagementSystem.GnManagementSystem.entity.GramaNiladhariDivision;
import com.GNManagementSystem.GnManagementSystem.service.GramaNiladhariDivisionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class GramaNildhariDivisionAgent {
    private final GnDivisionConverter gnDivisionConverter;
    private final GramaNiladhariDivisionService gnDivisionService;
    private final GramaNiladhariDivisionService gramaNiladhariDivisionService;

    public GramaNiladhariDivision saveGramaNiladhariDivision(GnDivisionDto gnDivisionDto){
        GramaNiladhariDivision gramaNiladhariDivision = gnDivisionConverter.convert(gnDivisionDto);
        return  gnDivisionService.saveGnDivision(gramaNiladhariDivision);
    }

    public List<GramaNiladhariDivision> getAllGnDivisions() {
        return gramaNiladhariDivisionService.getAllGnDivisions();
    }

    public Optional<GramaNiladhariDivision> getGnDivisionById(String id) {
        return gramaNiladhariDivisionService.getGnDivisionById(id);
    }

    public ResponseDto deleteGnDivision(String id) {
        gramaNiladhariDivisionService.deleteGnDivision(id);
        return new ResponseDto("GnDivision deleted successfully");
    }

    public GramaNiladhariDivision updateGramaNiladhariDivision(GnDivisionDto gnDivisionDto) {
        return gramaNiladhariDivisionService.updateGramaNiladhariDivision(gnDivisionDto);
    }
}
