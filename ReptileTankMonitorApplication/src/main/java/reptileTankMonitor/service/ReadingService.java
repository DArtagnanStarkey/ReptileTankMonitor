package reptileTankMonitor.service;

import reptileTankMonitor.dto.ReadingDTO;
import reptileTankMonitor.model.Reading;
import reptileTankMonitor.model.Tank;
import reptileTankMonitor.repository.ReadingRepository;
import reptileTankMonitor.repository.TankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReadingService {
    
    @Autowired
    private ReadingRepository readingRepository;
    
    @Autowired
    private TankRepository tankRepository;
    
    // CREATE
    public Reading createReading(Long tankId, Reading reading) {
        Tank tank = tankRepository.findById(tankId)
            .orElseThrow(() -> new RuntimeException("Tank not found with id: " + tankId));
        
        if (reading.getTimestamp() == null) {
            reading.setTimestamp(LocalDateTime.now());
        }
        
        reading.setTank(tank);
        return readingRepository.save(reading);
    }
    
    // READ - Return DTOs instead of entities
    public List<ReadingDTO> getReadingsByTank(Long tankId) {
        return readingRepository.findByTankId(tankId).stream()
            .map(ReadingDTO::new)
            .collect(Collectors.toList());
    }
    
    public List<ReadingDTO> getReadingsByTankAndDate(Long tankId, LocalDate date) {
        return readingRepository.findByTankIdAndDate(tankId, date).stream()
            .map(ReadingDTO::new)
            .collect(Collectors.toList());
    }
    
    public List<ReadingDTO> getReadingsByTankAndDateRange(Long tankId, LocalDate startDate, LocalDate endDate) {
        return readingRepository.findByTankIdAndDateRange(tankId, startDate, endDate).stream()
            .map(ReadingDTO::new)
            .collect(Collectors.toList());
    }
    
    public List<ReadingDTO> getProblematicReadings(Long tankId) {
        return readingRepository.findProblematicReadingsByTankId(tankId).stream()
            .map(ReadingDTO::new)
            .collect(Collectors.toList());
    }
    
    public List<ReadingDTO> getLast24HourReadings(Long tankId) {
        LocalDateTime twentyFourHoursAgo = LocalDateTime.now().minusHours(24);
        return readingRepository.findLast24HourReadings(tankId, twentyFourHoursAgo, LocalDateTime.now()).stream()
            .map(ReadingDTO::new)
            .collect(Collectors.toList());
    }
    
    public Map<String, Object> getTankStatusSummary(Long tankId) {
        Optional<Tank> tankOpt = tankRepository.findById(tankId);
        if (tankOpt.isEmpty()) {
            throw new RuntimeException("Tank not found with id: " + tankId);
        }
        
        Tank tank = tankOpt.get();
        Optional<Reading> latestReading = readingRepository.findLatestByTankId(tankId);
        
        Map<String, Object> summary = new HashMap<>();
        summary.put("tankId", tankId);
        summary.put("tankName", tank.getName());
        summary.put("species", tank.getSpecies().getName());
        
        if (latestReading.isPresent()) {
            Reading reading = latestReading.get();
            summary.put("latestReading", new ReadingDTO(reading));
            summary.put("lastUpdated", reading.getTimestamp());
            summary.put("currentStatus", 
                "TEMP: " + reading.getTemperatureStatus() + 
                ", HUMIDITY: " + reading.getHumidityStatus());
        } else {
            summary.put("latestReading", null);
            summary.put("lastUpdated", null);
            summary.put("currentStatus", "NO_READINGS");
        }
        
        // Updated: Use today's date with timestamp filtering
        LocalDate today = LocalDate.now();
        List<Reading> todayReadings = readingRepository.findByTankIdAndDate(tankId, today);
        summary.put("readingsToday", todayReadings.size());
        
        return summary;
    }
    
    // UPDATE
    public Reading updateReading(Long id, Reading readingDetails) {
        Reading reading = readingRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Reading not found with id: " + id));
        
        reading.setTemperature(readingDetails.getTemperature());
        reading.setHumidity(readingDetails.getHumidity());
        
        if (readingDetails.getTimestamp() != null) {
            reading.setTimestamp(readingDetails.getTimestamp());
        }
        
        return readingRepository.save(reading);
    }
    
    // DELETE
    public void deleteReading(Long id) {
        Reading reading = readingRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Reading not found with id: " + id));
        readingRepository.delete(reading);
    }
}