package io.github.maxiputz.springfitness.database.Entity.UsersMetadatas.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.maxiputz.springfitness.database.Entity.Metadata.Metadata;
import io.github.maxiputz.springfitness.database.Entity.Metadata.MetadataRepository;
import io.github.maxiputz.springfitness.database.Entity.User.User;
import io.github.maxiputz.springfitness.database.Entity.UsersMetadatas.UsersMetadata;
import io.github.maxiputz.springfitness.database.Entity.UsersMetadatas.UsersMetadataRepository;
import io.github.maxiputz.springfitness.database.Entity.UsersMetadatas.UsersMetadataService;

@Service
public class ImpUsersMetadataService implements UsersMetadataService {

    @Autowired
    public  UsersMetadataRepository usersMetadataRepository;

    @Override
    public List<UsersMetadata> getAllMetadataFromUser(User user) {
        List<UsersMetadata> tmp = usersMetadataRepository.findAllByUserId(user.getId());
        return tmp;
    }

    @Override
    public List<UsersMetadata> saveAll(User user, List<Metadata> metadatas) {
        var um = metadatas.stream().map(ele -> new UsersMetadata(user.getId(), ele.getId())).toList();

        return usersMetadataRepository.saveAll(um);
    }
    
}
