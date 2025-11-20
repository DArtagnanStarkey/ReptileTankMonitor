package reptileTankMonitor.repository;

import reptileTankMonitor.model.Tank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TankRepository extends JpaRepository<Tank, Long> {
    
    // ALL methods use @Query to avoid Spring Data JPA method naming issues
    @Query("SELECT t FROM Tank t WHERE t.species.id = :speciesId")
    List<Tank> findBySpeciesId(@Param("speciesId") Long speciesId);
}