package com.APIclubApp.clubApp.repository;

import com.APIclubApp.clubApp.dto.CoachCategoryDTO;
import com.APIclubApp.clubApp.model.Coach;
import com.APIclubApp.clubApp.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoachRepository extends JpaRepository<Coach,Long> {

    Coach findByUserDni(String dni);

    //@Query(value="select c.* FROM clubapp.coaches c join clubapp.categories ca on c.user_dni = ca.user_dni where c.user_dni=?1", nativeQuery = true)
    //@Query(value="SELECT c.*, c.category.category_id AS category_id FROM clubapp.coaches c WHERE c.user_dni = :dni", nativeQuery = true)
    //@Query(value = "SELECT c.*, cat.category_id AS category_id FROM clubapp.coaches c JOIN clubapp.categories cat ON c.coach_number = cat.coach_number WHERE c.user_dni = :dni", nativeQuery = true)
    @Query(value = "SELECT c.*, cat.category_id AS category_id FROM clubapp.coaches c JOIN clubapp.categories cat ON c.coach_number = cat.coach_number WHERE c.user_dni = ?1", nativeQuery = true)
    Optional<Coach> findByUserDniCat(String dni);

}
