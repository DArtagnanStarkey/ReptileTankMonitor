package reptileTankMonitor.service;

import reptileTankMonitor.dto.TankDTO;
import reptileTankMonitor.model.Tank;
import reptileTankMonitor.repository.TankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TankService {
    
    @Autowired
    private TankRepository tankRepository;
    
    // CREATE
    public Tank createTank(Tank tank) {
        return tankRepository.save(tank);
    }
    
    // READ - Return DTOs instead of entities
    public List<TankDTO> getAllTanks() {
        return tankRepository.findAll().stream()
            .map(TankDTO::new)
            .collect(Collectors.toList());
    }
    
    public Optional<TankDTO> getTankById(Long id) {
        return tankRepository.findById(id)
            .map(TankDTO::new);
    }
    
    // UPDATE
    public Tank updateTank(Long id, Tank tankDetails) {
        Tank tank = tankRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tank not found with id: " + id));
        
        tank.setName(tankDetails.getName());
        tank.setLocation(tankDetails.getLocation());
        tank.setDescription(tankDetails.getDescription());
        
        if (tankDetails.getSpecies() != null) {
            tank.setSpecies(tankDetails.getSpecies());
        }
        
        return tankRepository.save(tank);
    }
    
    // DELETE
    public void deleteTank(Long id) {
        Tank tank = tankRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tank not found with id: " + id));
        tankRepository.delete(tank);
    }
    
    public List<TankDTO> getTanksBySpecies(Long speciesId) {
        return tankRepository.findBySpeciesId(speciesId).stream()
            .map(TankDTO::new)
            .collect(Collectors.toList());
    }
}