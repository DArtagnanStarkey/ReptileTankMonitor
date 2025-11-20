package reptileTankMonitor.dto;

import java.time.LocalDateTime;

public class ReadingDTO {
    private Long id;
    private Double temperature;
    private Double humidity;
    private LocalDateTime timestamp;
    private String temperatureStatus;
    private String humidityStatus;
    private Long tankId;
    private String tankName;

    // Default constructor
    public ReadingDTO() {}

    // Constructor from Reading entity
    public ReadingDTO(reptileTankMonitor.model.Reading reading) {
        this.id = reading.getId();
        this.temperature = reading.getTemperature();
        this.humidity = reading.getHumidity();
        this.timestamp = reading.getTimestamp();
        this.temperatureStatus = reading.getTemperatureStatus();
        this.humidityStatus = reading.getHumidityStatus();
        this.tankId = reading.getTank() != null ? reading.getTank().getId() : null;
        this.tankName = reading.getTank() != null ? reading.getTank().getName() : null;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Double getTemperature() { return temperature; }
    public void setTemperature(Double temperature) { this.temperature = temperature; }
    
    public Double getHumidity() { return humidity; }
    public void setHumidity(Double humidity) { this.humidity = humidity; }
    
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    
    public String getTemperatureStatus() { return temperatureStatus; }
    public void setTemperatureStatus(String temperatureStatus) { this.temperatureStatus = temperatureStatus; }
    
    public String getHumidityStatus() { return humidityStatus; }
    public void setHumidityStatus(String humidityStatus) { this.humidityStatus = humidityStatus; }
    
    public Long getTankId() { return tankId; }
    public void setTankId(Long tankId) { this.tankId = tankId; }
    
    public String getTankName() { return tankName; }
    public void setTankName(String tankName) { this.tankName = tankName; }
}