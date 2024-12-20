package com.session02.newpos.Repo;

import com.session02.newpos.entities.ConfigTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@EnableJpaRepositories

@Service
public interface ConfigSysDataRepo extends JpaRepository<ConfigTicket,Integer> {

}
