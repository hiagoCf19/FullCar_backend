package com.fullCar.FullCar.repository;
import com.fullCar.FullCar.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long > {
    List<Image> findByAdId(Long ad_id);
}
