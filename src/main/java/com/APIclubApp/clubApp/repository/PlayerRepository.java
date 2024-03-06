package com.APIclubApp.clubApp.repository;

import com.APIclubApp.clubApp.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    Player findByUserDni(String dni);
    @Query(value="select p.* from clubapp.players p join clubapp.categories c on p.category_id = c.category_id where c.category_id=?1", nativeQuery = true)
    List<Player> findAllByCategoryId(Long category_id);

}