package com.techspider.restuarentlisting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techspider.restuarentlisting.entity.Restuarent;

@Repository
public interface RestuarentRepository extends JpaRepository<Restuarent, Integer> {

}
