package io.github.maxiputz.springfitness.database.Entity.Metadata.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.maxiputz.springfitness.controller.API.workoutInsert.struct.WorkoutCreateRequest;
import io.github.maxiputz.springfitness.database.Entity.Metadata.Metadata;
import io.github.maxiputz.springfitness.database.Entity.Metadata.MetadataRepository;
import io.github.maxiputz.springfitness.database.Entity.Metadata.MetadataService;
import io.github.maxiputz.springfitness.database.Entity.User.User;
import io.github.maxiputz.springfitness.database.Entity.UsersMetadatas.UsersMetadata;
import io.github.maxiputz.springfitness.database.Entity.UsersMetadatas.UsersMetadataRepository;

@Service
public class ImpMetadataService implements MetadataService {

    @Autowired
    MetadataRepository metadataRepository;

    @Autowired
    UsersMetadataRepository usersMetadataRepository;

    @Override
    public List<Metadata> getAllMetadataFromUserId(User user) {
        List<Long> metadataIds = usersMetadataRepository.findAllByUserId(user.getId()).stream()
                .map(ele -> ele.getMetadataId()).toList();

        return metadataRepository.findAllById(metadataIds);
    }

    @Override
    public Optional<Metadata> getMetadataFromUserAndMetadataId(User user, String workoudId) {
        return this.getAllMetadataFromUserId(user).stream().filter(ele -> ele.getWorkoutId().equals(workoudId))
                .findAny();
    }

    @Override
    public Optional<Metadata> setWorkoutdata(User u, WorkoutCreateRequest request) {

        var usersmMetadatalist = usersMetadataRepository.findAllByUserId(u.getId());

        List<Long> a = usersmMetadatalist.stream().map(ele -> ele.getMetadataId()).toList();

        Optional<Metadata> m = metadataRepository.findAllById(a).stream()
                .filter(ele -> ele.getWorkoutId().equals(request.getId())).findAny();

        if (m.isEmpty()) {
            System.out.println("is empty");
            return m;
        }

        metadataRepository.setWorkoutData(m.get().getId(), request.getCsv(), request.getCsv().split("\n").length);

        System.out.println("was setted");
        return m;

    }

    @Override
    public List<Metadata> saveAll(List<Metadata> metadatas) {
        var tmp = this.metadataRepository.saveAll(metadatas);

        return tmp;
    }

}
