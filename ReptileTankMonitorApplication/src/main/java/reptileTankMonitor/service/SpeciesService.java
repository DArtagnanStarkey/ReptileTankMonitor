package reptileTankMonitor.service;

import reptileTankMonitor.dto.SpeciesDTO;
import reptileTankMonitor.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpeciesService {
    
    @Autowired
    private SpeciesRepository speciesRepository;
    
    public List<SpeciesDTO> getAllSpecies() {
        return speciesRepository.findAll().stream()
            .map(SpeciesDTO::new)
            .collect(Collectors.toList());
    }
}