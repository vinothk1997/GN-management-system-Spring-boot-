package com.GNManagementSystem.GnManagementSystem.controller;

import com.GNManagementSystem.GnManagementSystem.agent.GramaNiladhariAgent;
import com.GNManagementSystem.GnManagementSystem.dto.GramaNiladhariDto;
import com.GNManagementSystem.GnManagementSystem.dto.GramaNiladhariResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/grama-niladhari")
@Validated
public class GramaNildhariController {
    private final GramaNiladhariAgent gramaNiladhariAgent;

    @PostMapping
    public GramaNiladhariResponseDto saveGramaNiladhari(@RequestBody @Valid GramaNiladhariDto gramaNiladhariDto) {
        return gramaNiladhariAgent.saveGramaNiladhari(gramaNiladhariDto);
    }

    @GetMapping
    public List<GramaNiladhariResponseDto> getAllGramaNildhari() {
        return gramaNiladhariAgent.getAllGramaNildhari();
    }
}
