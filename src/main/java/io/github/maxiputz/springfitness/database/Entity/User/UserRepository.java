package io.github.maxiputz.springfitness.database.Entity.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select mu from User mu where mu.id = 1")
    List<User> getAllUser();


    @Modifying
    @Query("update User u set u.clientId = :clientId, u.clientSecret = :clientSecret where u.id = :id")
    void setOauthInfo(@Param("id") Long id, @Param("clientId") String clientId, @Param("clientSecret") String clientSecret);


    @Query("select mu from User mu where mu.username like :username")
    List<User> getUserByUsername(@Param("username") String username);
}
