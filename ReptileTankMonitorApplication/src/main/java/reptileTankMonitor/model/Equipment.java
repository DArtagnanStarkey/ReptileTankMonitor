package reptileTankMonitor.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "equipment")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    private String type;
    private String status;
    
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "equipment")
    @JsonIgnore
    private List<TankMaintenance> tankMaintenances = new ArrayList<>();
    
    //Many-to-Many relationship with Species
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "recommendedEquipment")
    @JsonIgnore
    private List<Species> compatibleSpecies = new ArrayList<>();
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public List<TankMaintenance> getTankMaintenances() { return tankMaintenances; }
    public void setTankMaintenances(List<TankMaintenance> tankMaintenances) { this.tankMaintenances = tankMaintenances; }
    
    //Getter and Setter for compatible species
    public List<Species> getCompatibleSpecies() { return compatibleSpecies; }
    public void setCompatibleSpecies(List<Species> compatibleSpecies) { 
        this.compatibleSpecies = compatibleSpecies; 
    }
}