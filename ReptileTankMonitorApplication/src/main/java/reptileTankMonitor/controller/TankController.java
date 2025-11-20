package reptileTankMonitor.controller;

import reptileTankMonitor.dto.TankDTO;
import reptileTankMonitor.model.Tank;
import reptileTankMonitor.service.TankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tanks")
@CrossOrigin(origins = "*")
public class TankController {
    
    @Autowired
    private TankService tankService;
    
    // CREATE - Create a new tank
    @PostMapping
    public ResponseEntity<Tank> createTank(@RequestBody Tank tank) {
        try {
            Tank createdTank = tankService.createTank(tank);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTank);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    // READ - Get all tanks (returns DTOs)
    @GetMapping
    public ResponseEntity<List<TankDTO>> getAllTanks() {
        try {
            List<TankDTO> tanks = tankService.getAllTanks();
            return ResponseEntity.ok(tanks);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // READ - Get tank by ID (returns DTO)
    @GetMapping("/{id}")
    public ResponseEntity<TankDTO> getTankById(@PathVariable Long id) {
        try {
            Optional<TankDTO> tank = tankService.getTankById(id);
            if (tank.isPresent()) {
                return ResponseEntity.ok(tank.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // UPDATE - Update an existing tank
    @PutMapping("/{id}")
    public ResponseEntity<Tank> updateTank(@PathVariable Long id, @RequestBody Tank tankDetails) {
        try {
            Tank updatedTank = tankService.updateTank(id, tankDetails);
            return ResponseEntity.ok(updatedTank);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // DELETE - Delete a tank
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTank(@PathVariable Long id) {
        try {
            tankService.deleteTank(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // READ - Get tanks by species (returns DTOs)
    @GetMapping("/species/{speciesId}")
    public ResponseEntity<List<TankDTO>> getTanksBySpecies(@PathVariable Long speciesId) {
        try {
            List<TankDTO> tanks = tankService.getTanksBySpecies(speciesId);
            return ResponseEntity.ok(tanks);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}