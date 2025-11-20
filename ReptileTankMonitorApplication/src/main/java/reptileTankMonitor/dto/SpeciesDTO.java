package reptileTankMonitor.dto;

public class SpeciesDTO {
    private Long id;
    private String name;
    private Double minTemp;
    private Double maxTemp;
    private Double minHumidity;
    private Double maxHumidity;

    // Constructor from Species entity
    public SpeciesDTO(reptileTankMonitor.model.Species species) {
        this.id = species.getId();
        this.name = species.getName();
        this.minTemp = species.getMinTemp();
        this.maxTemp = species.getMaxTemp();
        this.minHumidity = species.getMinHumidity();
        this.maxHumidity = species.getMaxHumidity();
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Double getMinTemp() { return minTemp; }
    public void setMinTemp(Double minTemp) { this.minTemp = minTemp; }
    
    public Double getMaxTemp() { return maxTemp; }
    public void setMaxTemp(Double maxTemp) { this.maxTemp = maxTemp; }
    
    public Double getMinHumidity() { return minHumidity; }
    public void setMinHumidity(Double minHumidity) { this.minHumidity = minHumidity; }
    
    public Double getMaxHumidity() { return maxHumidity; }
    public void setMaxHumidity(Double maxHumidity) { this.maxHumidity = maxHumidity; }
}