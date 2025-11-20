package reptileTankMonitor.dto;

import java.util.List;
import java.util.stream.Collectors;

public class TankDTO {
    private Long id;
    private String name;
    private String location;
    private String description;
    private Long speciesId;
    private String speciesName;
    private List<ReadingDTO> readings;

    // Constructor from Tank entity
    public TankDTO(reptileTankMonitor.model.Tank tank) {
        this.id = tank.getId();
        this.name = tank.getName();
        this.location = tank.getLocation();
        this.description = tank.getDescription();
        this.speciesId = tank.getSpecies() != null ? tank.getSpecies().getId() : null;
        this.speciesName = tank.getSpecies() != null ? tank.getSpecies().getName() : null;
        
        // Convert readings to DTOs to avoid recursion
        if (tank.getReadings() != null) {
            this.readings = tank.getReadings().stream()
                .map(ReadingDTO::new)
                .collect(Collectors.toList());
        }
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Long getSpeciesId() { return speciesId; }
    public void setSpeciesId(Long speciesId) { this.speciesId = speciesId; }
    
    public String getSpeciesName() { return speciesName; }
    public void setSpeciesName(String speciesName) { this.speciesName = speciesName; }
    
    public List<ReadingDTO> getReadings() { return readings; }
    public void setReadings(List<ReadingDTO> readings) { this.readings = readings; }
}