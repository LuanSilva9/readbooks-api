package com.readbooks.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.readbooks.api.model.SummaryDaily;

public interface SummaryDailyRepository extends JpaRepository<SummaryDaily, UUID>{
    
}
