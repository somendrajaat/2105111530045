package com.averagecalculator.Repository;

import com.averagecalculator.ResponseObject.WindowState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WindowStateRepository extends JpaRepository<WindowState, Long> {
    Optional<WindowState> findTopByOrderByIdDesc();
}