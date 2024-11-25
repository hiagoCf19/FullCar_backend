package com.fullCar.FullCar.repository;

import com.fullCar.FullCar.model.Ads;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AdsRepository extends JpaRepository<Ads, Long > {
    @Query("SELECT a FROM Ads a JOIN FETCH a.user_id WHERE a.id = :adId")
    Optional<Ads> findByIdWithUser(@Param("adId") Long adId);

    @Query("SELECT a FROM Ads a WHERE a.user_id.id = :userId")
    List<Ads> getAdsByUser(@Param("userId") Long userId);

}
