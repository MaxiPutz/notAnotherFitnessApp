package io.github.maxiputz.springfitness.database.Entity.UsersMetadatas;

import org.springframework.stereotype.Service;

import java.util.List;

import io.github.maxiputz.springfitness.database.Entity.User.User;
import io.github.maxiputz.springfitness.database.Entity.Metadata.Metadata;


@Service
public interface UsersMetadataService {
    public List<UsersMetadata> getAllMetadataFromUser(User user);

    public List<UsersMetadata> saveAll(User user, List<Metadata> metadatas);
}
