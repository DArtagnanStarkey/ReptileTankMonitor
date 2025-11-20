package reptileTankMonitor.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "species")
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    private Double minTemp;
    private Double maxTemp;
    private Double minHumidity;
    private Double maxHumidity;
    
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "species", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Tank> tanks = new ArrayList<>();
    
    //Many-to-Many relationship with Equipment
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany
    @JoinTable(
        name = "species_equipment",
        joinColumns = @JoinColumn(name = "species_id"),
        inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    @JsonIgnore
    private List<Equipment> recommendedEquipment = new ArrayList<>();
    
    // Constructors
    public Species() {}
    
    public Species(String name, Double minTemp, Double maxTemp, Double minHumidity, Double maxHumidity) {
        this.name = name;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.minHumidity = minHumidity;
        this.maxHumidity = maxHumidity;
    }
    
    // Getters and Setters
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
    
    public List<Tank> getTanks() { return tanks; }
    public void setTanks(List<Tank> tanks) { this.tanks = tanks; }
    
    //Getter and Setter for recommended equipment
    public List<Equipment> getRecommendedEquipment() { return recommendedEquipment; }
    public void setRecommendedEquipment(List<Equipment> recommendedEquipment) { 
        this.recommendedEquipment = recommendedEquipment; 
    }
    
    // Helper methods for the many-to-many relationship
    public void addEquipment(Equipment equipment) {
        this.recommendedEquipment.add(equipment);
        equipment.getCompatibleSpecies().add(this);
    }
    
    public void removeEquipment(Equipment equipment) {
        this.recommendedEquipment.remove(equipment);
        equipment.getCompatibleSpecies().remove(this);
    }
}