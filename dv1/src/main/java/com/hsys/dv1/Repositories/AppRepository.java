package com.hsys.dv1.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hsys.dv1.Entities.App;

@Repository
public interface AppRepository extends JpaRepository<App, Long>{

    App findByName(String name);

    List<App> findByIsPublicTrue();

}
