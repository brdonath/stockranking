package com.dontah.repository;

import com.dontah.domain.ResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Bruno on 10/08/14.
 */
public interface ResultsRepository extends JpaRepository<ResultEntity, Long> {

    ResultEntity findOneByCodBolsa(String codBolsa);

}
