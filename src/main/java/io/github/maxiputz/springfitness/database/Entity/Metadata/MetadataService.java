package io.github.maxiputz.springfitness.database.Entity.Metadata;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.maxiputz.springfitness.controller.API.workoutInsert.struct.WorkoutCreateRequest;
import io.github.maxiputz.springfitness.database.Entity.User.User;
import io.github.maxiputz.springfitness.database.Entity.UsersMetadatas.UsersMetadata;

@Service
public interface MetadataService {
    public List<Metadata> getAllMetadataFromUserId(User user);
    public Optional< Metadata> getMetadataFromUserAndMetadataId(User user, String workoudId);

    public Optional<Metadata> setWorkoutdata(User u, WorkoutCreateRequest request);

    public List<Metadata> saveAll(List<Metadata> metadatas);
}
