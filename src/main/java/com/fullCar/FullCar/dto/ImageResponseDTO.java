package com.fullCar.FullCar.dto;

import com.fullCar.FullCar.model.Image;

public record ImageResponseDTO(Long id, String url, Long ad_id) {
  public ImageResponseDTO(Image image) {
    this(image.getId(), image.getUrl(), image.getAd().getId());
  }
  }
