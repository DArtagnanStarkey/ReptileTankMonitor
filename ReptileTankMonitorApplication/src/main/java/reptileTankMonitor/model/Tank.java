package reptileTankMonitor.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tank")
public class Tank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    private String location;
    private String description;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "species_id")
    private Species species;
    
    @OneToMany(mappedBy = "tank", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore  // This prevents infinite recursion
    private List<Reading> readings = new ArrayList<>();
    
    @OneToMany(mappedBy = "tank", cascade = CascadeType.ALL)
    @JsonIgnore  // This prevents infinite recursion
    private List<TankMaintenance> maintenanceRecords = new ArrayList<>();
    
    // Constructors
    public Tank() {}
    
    public Tank(String name, String location, String description, Species species) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.species = species;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Species getSpecies() { return species; }
    public void setSpecies(Species species) { this.species = species; }
    
    public List<Reading> getReadings() { return readings; }
    public void setReadings(List<Reading> readings) { this.readings = readings; }
    
    public List<TankMaintenance> getMaintenanceRecords() { return maintenanceRecords; }
    public void setMaintenanceRecords(List<TankMaintenance> maintenanceRecords) { this.maintenanceRecords = maintenanceRecords; }
    
    // Helper method to get species ID without causing recursion
    public Long getSpeciesId() {
        return species != null ? species.getId() : null;
    }
    
    // Helper method to get species name without causing recursion
    public String getSpeciesName() {
        return species != null ? species.getName() : null;
    }
}