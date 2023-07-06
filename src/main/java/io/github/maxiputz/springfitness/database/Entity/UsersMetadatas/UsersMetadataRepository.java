package io.github.maxiputz.springfitness.database.Entity.UsersMetadatas;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.github.maxiputz.springfitness.database.Entity.Metadata.Metadata;

@Repository
public interface UsersMetadataRepository extends JpaRepository<UsersMetadata, Long>{

    @Query("select um from UsersMetadata um where um.userId = :id")
    public List<UsersMetadata> findAllByUserId(@Param("id") Long id );

    @Query("select md from UsersMetadata md where md.userId = :userId and md.metadataId = :metadataId")
    public Optional<UsersMetadata> findByMetadataIdUserId(@Param("userId") long userId, @Param("metadataId") Long metadataId);
    
}
