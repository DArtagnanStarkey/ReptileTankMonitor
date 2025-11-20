package reptileTankMonitor.controller;

import reptileTankMonitor.dto.ReadingDTO;
import reptileTankMonitor.model.Reading;
import reptileTankMonitor.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/readings")
@CrossOrigin(origins = "*")
public class ReadingController {
    
    @Autowired
    private ReadingService readingService;
    
    // CREATE - Create a new reading for a specific tank
    @PostMapping("/tank/{tankId}")
    public ResponseEntity<Reading> createReading(@PathVariable Long tankId, @RequestBody Reading reading) {
        try {
            Reading createdReading = readingService.createReading(tankId, reading);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdReading);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // READ - Get all readings for a specific tank (returns DTOs)
    @GetMapping("/tank/{tankId}")
    public ResponseEntity<List<ReadingDTO>> getReadingsByTank(@PathVariable Long tankId) {
        try {
            List<ReadingDTO> readings = readingService.getReadingsByTank(tankId);
            return ResponseEntity.ok(readings);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // READ - Get readings for a specific tank and date (returns DTOs)
    @GetMapping("/tank/{tankId}/date/{date}")
    public ResponseEntity<List<ReadingDTO>> getReadingsByTankAndDate(
            @PathVariable Long tankId, 
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        try {
            List<ReadingDTO> readings = readingService.getReadingsByTankAndDate(tankId, date);
            return ResponseEntity.ok(readings);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // READ - Get readings for a specific tank within a date range (returns DTOs)
    @GetMapping("/tank/{tankId}/date-range")
    public ResponseEntity<List<ReadingDTO>> getReadingsByTankAndDateRange(
            @PathVariable Long tankId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        try {
            List<ReadingDTO> readings = readingService.getReadingsByTankAndDateRange(tankId, startDate, endDate);
            return ResponseEntity.ok(readings);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // READ - Get readings for the last 24 hours for a specific tank (returns DTOs)
    @GetMapping("/tank/{tankId}/last-24-hours")
    public ResponseEntity<List<ReadingDTO>> getLast24HourReadings(@PathVariable Long tankId) {
        try {
            List<ReadingDTO> readings = readingService.getLast24HourReadings(tankId);
            return ResponseEntity.ok(readings);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // READ - Get problematic readings for a specific tank (returns DTOs)
    @GetMapping("/tank/{tankId}/problems")
    public ResponseEntity<List<ReadingDTO>> getProblematicReadings(@PathVariable Long tankId) {
        try {
            List<ReadingDTO> readings = readingService.getProblematicReadings(tankId);
            return ResponseEntity.ok(readings);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // READ - Get tank status summary
    @GetMapping("/tank/{tankId}/summary")
    public ResponseEntity<Map<String, Object>> getTankStatusSummary(@PathVariable Long tankId) {
        try {
            Map<String, Object> summary = readingService.getTankStatusSummary(tankId);
            return ResponseEntity.ok(summary);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // UPDATE - Update an existing reading
    @PutMapping("/{id}")
    public ResponseEntity<Reading> updateReading(@PathVariable Long id, @RequestBody Reading readingDetails) {
        try {
            Reading updatedReading = readingService.updateReading(id, readingDetails);
            return ResponseEntity.ok(updatedReading);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // DELETE - Delete a reading
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReading(@PathVariable Long id) {
        try {
            readingService.deleteReading(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}