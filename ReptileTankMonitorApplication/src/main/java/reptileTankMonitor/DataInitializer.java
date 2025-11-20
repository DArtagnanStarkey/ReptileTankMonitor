package reptileTankMonitor;

import reptileTankMonitor.model.Equipment;
import reptileTankMonitor.model.Reading;
import reptileTankMonitor.model.Species;
import reptileTankMonitor.model.Tank;
import reptileTankMonitor.model.TankMaintenance;
import reptileTankMonitor.repository.EquipmentRepository;
import reptileTankMonitor.repository.ReadingRepository;
import reptileTankMonitor.repository.SpeciesRepository;
import reptileTankMonitor.repository.TankMaintenanceRepository;
import reptileTankMonitor.repository.TankRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import java.time.LocalDateTime;
import java.util.Arrays;

//@Component
public class DataInitializer {
    
    @Autowired
    private SpeciesRepository speciesRepository;
    
    @Autowired
    private TankRepository tankRepository;
    
    @Autowired
    private ReadingRepository readingRepository;
    
    @Autowired
    private EquipmentRepository equipmentRepository;
    
    @Autowired
    private TankMaintenanceRepository tankMaintenanceRepository;
    
    @PostConstruct
    public void initData() {
        // Create species
        Species ballPython = new Species("Ball Python", 80.0, 90.0, 50.0, 60.0);
        Species crestedGecko = new Species("Crested Gecko", 72.0, 75.0, 70.0, 80.0);
        Species whitesTreeFrog = new Species("White's Tree Frog", 82.0, 84.0, 60.0, 70.0);
        
        speciesRepository.saveAll(Arrays.asList(ballPython, crestedGecko, whitesTreeFrog));
        
//         Create tanks
//        Tank pythonTank = new Tank("Python Paradise", "Living Room", "40 gallon glass tank", ballPython);
//        Tank geckoTank = new Tank("Gecko Garden", "Bedroom", "20 gallon vertical tank", crestedGecko);
//        Tank frogTank = new Tank("Frog Forest", "Office", "30 gallon bioactive tank", whitesTreeFrog);
//        
//        tankRepository.saveAll(Arrays.asList(pythonTank, geckoTank, frogTank));
//        
//        // Create equipment
//        Equipment heater1 = new Equipment("Reptile Heater 100W", "HEATER", "ACTIVE");
//        Equipment humidifier1 = new Equipment("Reptile Humidifier Pro", "HUMIDIFIER", "ACTIVE");
//        Equipment thermostat1 = new Equipment("Digital Thermostat", "THERMOSTAT", "ACTIVE");
//        
//        equipmentRepository.saveAll(Arrays.asList(heater1, humidifier1, thermostat1));
//        
//        // Create maintenance records
//        TankMaintenance maintenance1 = new TankMaintenance(pythonTank, heater1, LocalDateTime.now().minusDays(7), "CLEANING", "Routine heater cleaning");
//        TankMaintenance maintenance2 = new TankMaintenance(geckoTank, humidifier1, LocalDateTime.now().minusDays(3), "FILTER_CHANGE", "Changed humidifier filter");
//        
//        tankMaintenanceRepository.saveAll(Arrays.asList(maintenance1, maintenance2));
//        
//        // Create sample readings
//        createSampleReadings(pythonTank, 85.0, 55.0, 20);
//        createSampleReadings(geckoTank, 73.0, 75.0, 15);
//        createSampleReadings(frogTank, 83.0, 65.0, 18);
    }
    
    private void createSampleReadings(Tank tank, double baseTemp, double baseHumidity, int count) {
        LocalDateTime now = LocalDateTime.now();
        
        for (int i = 0; i < count; i++) {
            LocalDateTime timestamp = now.minusHours(i * 2);
            double tempVariation = (Math.random() - 0.5) * 4;
            double humidityVariation = (Math.random() - 0.5) * 10;
            
            Reading reading = new Reading(
                baseTemp + tempVariation,
                baseHumidity + humidityVariation,
                timestamp,
                tank
            );
            
            readingRepository.save(reading);
        }
    }
}