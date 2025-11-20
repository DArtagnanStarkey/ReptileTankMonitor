package reptileTankMonitor.repository;

import reptileTankMonitor.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Long> {
    
    @Query("SELECT s FROM Species s WHERE s.name = :name")
    Optional<Species> findByName(@Param("name") String name);
}