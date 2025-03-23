package com.GNManagementSystem.GnManagementSystem.agent;

import com.GNManagementSystem.GnManagementSystem.dto.GramaNiladhariDto;
import com.GNManagementSystem.GnManagementSystem.dto.GramaNiladhariResponseDto;
import com.GNManagementSystem.GnManagementSystem.entity.GramaNiladhari;
import com.GNManagementSystem.GnManagementSystem.service.GramaNildhariService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class GramaNiladhariAgent {
    private final GramaNildhariService gramaNildhariService;
    public GramaNiladhariResponseDto saveGramaNiladhari(GramaNiladhariDto gramaNiladhariDto){
        return gramaNildhariService.saveGramaNiladhari(gramaNiladhariDto);
    }

    public List<GramaNiladhariResponseDto> getAllGramaNildhari() {
        return gramaNildhariService.getAllGramaNildhari();
    }
}
