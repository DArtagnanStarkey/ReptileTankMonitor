package reptileTankMonitor.controller;

import reptileTankMonitor.dto.SpeciesDTO;
import reptileTankMonitor.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/species")
@CrossOrigin(origins = "*")
public class SpeciesController {
    
    @Autowired
    private SpeciesService speciesService;
    
    @GetMapping
    public ResponseEntity<List<SpeciesDTO>> getAllSpecies() {
        try {
            List<SpeciesDTO> species = speciesService.getAllSpecies();
            return ResponseEntity.ok(species);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}