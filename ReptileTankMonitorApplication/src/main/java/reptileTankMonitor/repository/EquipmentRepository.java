package reptileTankMonitor.repository;

import reptileTankMonitor.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    
    @Query("SELECT e FROM Equipment e WHERE e.type = :type")
    List<Equipment> findByType(@Param("type") String type);
}