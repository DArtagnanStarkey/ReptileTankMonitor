package reptileTankMonitor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "reading")
public class Reading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Double temperature;
    
    @Column(nullable = false)
    private Double humidity;
    
    @Column(nullable = false)
    private LocalDateTime timestamp;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tank_id")
    @JsonIgnore
    private Tank tank;
    
    private String temperatureStatus;
    private String humidityStatus;
    
    // Constructors
    public Reading() {}
    
    public Reading(Double temperature, Double humidity, LocalDateTime timestamp, Tank tank) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.timestamp = timestamp;
        this.tank = tank;
        this.temperatureStatus = calculateTemperatureStatus(temperature, tank.getSpecies());
        this.humidityStatus = calculateHumidityStatus(humidity, tank.getSpecies());
    }
    
    // Helper methods to calculate status
    private String calculateTemperatureStatus(Double temp, Species species) {
        if (species == null) return "UNKNOWN";
        if (temp < species.getMinTemp()) return "TOO_LOW";
        if (temp > species.getMaxTemp()) return "TOO_HIGH";
        return "OPTIMAL";
    }
    
    private String calculateHumidityStatus(Double humidity, Species species) {
        if (species == null) return "UNKNOWN";
        if (humidity < species.getMinHumidity()) return "TOO_LOW";
        if (humidity > species.getMaxHumidity()) return "TOO_HIGH";
        return "OPTIMAL";
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Double getTemperature() { return temperature; }
    public void setTemperature(Double temperature) { 
        this.temperature = temperature;
        if (this.tank != null && this.tank.getSpecies() != null) {
            this.temperatureStatus = calculateTemperatureStatus(temperature, this.tank.getSpecies());
        }
    }
    
    public Double getHumidity() { return humidity; }
    public void setHumidity(Double humidity) { 
        this.humidity = humidity;
        if (this.tank != null && this.tank.getSpecies() != null) {
            this.humidityStatus = calculateHumidityStatus(humidity, this.tank.getSpecies());
        }
    }
    
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { 
        this.timestamp = timestamp;
    }
    
    public Tank getTank() { return tank; }
    public void setTank(Tank tank) { 
        this.tank = tank;
        if (tank != null && tank.getSpecies() != null) {
            this.temperatureStatus = calculateTemperatureStatus(this.temperature, tank.getSpecies());
            this.humidityStatus = calculateHumidityStatus(this.humidity, tank.getSpecies());
        }
    }
    
    public String getTemperatureStatus() { return temperatureStatus; }
    public void setTemperatureStatus(String temperatureStatus) { this.temperatureStatus = temperatureStatus; }
    
    public String getHumidityStatus() { return humidityStatus; }
    public void setHumidityStatus(String humidityStatus) { this.humidityStatus = humidityStatus; }
    
    // Helper method to get tank ID without causing recursion
    public Long getTankId() {
        return tank != null ? tank.getId() : null;
    }
    
    // Helper method to get tank name without causing recursion
    public String getTankName() {
        return tank != null ? tank.getName() : null;
    }
}