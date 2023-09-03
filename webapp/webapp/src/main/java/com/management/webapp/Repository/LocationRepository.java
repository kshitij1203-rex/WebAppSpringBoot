package com.management.webapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.webapp.entity.Location;

public interface LocationRepository extends JpaRepository<Location,Integer>{

    Location findByCity(String city);
    
}
