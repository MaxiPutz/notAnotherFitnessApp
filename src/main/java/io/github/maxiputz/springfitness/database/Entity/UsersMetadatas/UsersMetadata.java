package io.github.maxiputz.springfitness.database.Entity.UsersMetadatas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;





@Entity
@Table(name = "users_metadata")
@Getter
@Setter
@AllArgsConstructor
public class UsersMetadata {

    public UsersMetadata() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long metadataId;

    public UsersMetadata(Long userId, Long metadataId) {
        this.userId = userId;
        this.metadataId = metadataId;
    }
}
