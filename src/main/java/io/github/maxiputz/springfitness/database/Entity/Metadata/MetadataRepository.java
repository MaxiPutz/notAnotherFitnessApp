package io.github.maxiputz.springfitness.database.Entity.Metadata;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;


@Transactional
public interface MetadataRepository extends JpaRepository<Metadata, Long>{

    @Modifying
    @Query("update Metadata md set md.count = :count, md.WorkoutDataCSV = :CSV where md.id = :id")
    public void setWorkoutData(@Param("id") long metadataId, @Param("CSV") String CSV, @Param("count") int cout);

  
}
