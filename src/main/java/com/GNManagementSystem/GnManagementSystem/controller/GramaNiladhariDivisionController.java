package com.GNManagementSystem.GnManagementSystem.controller;

import com.GNManagementSystem.GnManagementSystem.agent.GramaNildhariDivisionAgent;
import com.GNManagementSystem.GnManagementSystem.dto.GnDivisionDto;
import com.GNManagementSystem.GnManagementSystem.dto.ResponseDto;
import com.GNManagementSystem.GnManagementSystem.entity.GramaNiladhariDivision;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/gn-division")
@RequiredArgsConstructor
public class GramaNiladhariDivisionController {
    private final GramaNildhariDivisionAgent gramaNildhariDivisionAgent;
    @PostMapping()
    public GramaNiladhariDivision saveGNDivision(@RequestBody GnDivisionDto gnDivisionDto) {
        return gramaNildhariDivisionAgent.saveGramaNiladhariDivision(gnDivisionDto);
    }

    @GetMapping()
    public List<GramaNiladhariDivision> getAllGnDivisions() {
        return gramaNildhariDivisionAgent.getAllGnDivisions();
    }

    @GetMapping("/{id}")
    public Optional<GramaNiladhariDivision> getGnDivisionById(@PathVariable("id") String id) {
        return gramaNildhariDivisionAgent.getGnDivisionById(id);
    }
    @DeleteMapping
    public ResponseDto deleteGnDivision(String id) {
        gramaNildhariDivisionAgent.deleteGnDivision(id);
        return new ResponseDto("GnDivision deleted successfully");
    }
    @PutMapping
    public GramaNiladhariDivision updateGramaNiladhariDivision(@RequestBody GnDivisionDto gnDivisionDto) {
        return gramaNildhariDivisionAgent.updateGramaNiladhariDivision(gnDivisionDto);
    }

    @DeleteMapping("/{id}")
    public ResponseDto deleteGramaNiladhariDivision(@PathVariable("id") String id) {
        return gramaNildhariDivisionAgent.deleteGnDivision(id);
    }
}
