package reptileTankMonitor.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "tank_maintenance")
public class TankMaintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tank_id")
    @JsonBackReference("tank-maintenance")  // Back reference
    private Tank tank;
    
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;
    
    private LocalDateTime maintenanceDate;
    private String maintenanceType;
    private String notes;

    
    // Constructors
    public TankMaintenance() {}
    
    public TankMaintenance(Tank tank, Equipment equipment, LocalDateTime maintenanceDate, String maintenanceType, String notes) {
        this.tank = tank;
        this.equipment = equipment;
        this.maintenanceDate = maintenanceDate;
        this.maintenanceType = maintenanceType;
        this.notes = notes;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Tank getTank() { return tank; }
    public void setTank(Tank tank) { this.tank = tank; }
    
    public Equipment getEquipment() { return equipment; }
    public void setEquipment(Equipment equipment) { this.equipment = equipment; }
    
    public LocalDateTime getMaintenanceDate() { return maintenanceDate; }
    public void setMaintenanceDate(LocalDateTime maintenanceDate) { this.maintenanceDate = maintenanceDate; }
    
    public String getMaintenanceType() { return maintenanceType; }
    public void setMaintenanceType(String maintenanceType) { this.maintenanceType = maintenanceType; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}