package reptileTankMonitor.repository;

import reptileTankMonitor.model.Reading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReadingRepository extends JpaRepository<Reading, Long> {
    
    // ALL methods use @Query to avoid Spring Data JPA method naming issues
    @Query("SELECT r FROM Reading r WHERE r.tank.id = :tankId ORDER BY r.timestamp DESC")
    List<Reading> findByTankId(@Param("tankId") Long tankId);
    
    // Updated: Use timestamp instead of readingDate
    @Query("SELECT r FROM Reading r WHERE r.tank.id = :tankId AND DATE(r.timestamp) = :date")
    List<Reading> findByTankIdAndDate(@Param("tankId") Long tankId, @Param("date") LocalDate date);
    
    // Updated: Use timestamp instead of readingDate for date range
    @Query("SELECT r FROM Reading r WHERE r.tank.id = :tankId AND DATE(r.timestamp) BETWEEN :startDate AND :endDate")
    List<Reading> findByTankIdAndDateRange(@Param("tankId") Long tankId, 
                                          @Param("startDate") LocalDate startDate, 
                                          @Param("endDate") LocalDate endDate);
    
    @Query("SELECT r FROM Reading r WHERE r.tank.id = :tankId AND r.timestamp BETWEEN :start AND :end")
    List<Reading> findLast24HourReadings(@Param("tankId") Long tankId, 
                                        @Param("start") LocalDateTime start, 
                                        @Param("end") LocalDateTime end);
    
    @Query("SELECT r FROM Reading r WHERE r.tank.id = :tankId AND (r.temperatureStatus != 'OPTIMAL' OR r.humidityStatus != 'OPTIMAL')")
    List<Reading> findProblematicReadingsByTankId(@Param("tankId") Long tankId);
    
    @Query("SELECT r FROM Reading r WHERE r.tank.id = :tankId ORDER BY r.timestamp DESC LIMIT 1")
    Optional<Reading> findLatestByTankId(@Param("tankId") Long tankId);
}