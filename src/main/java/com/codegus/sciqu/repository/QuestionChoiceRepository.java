package com.codegus.sciqu.repository;

import org.springframework.stereotype.Repository;

import com.codegus.sciqu.entity.QuestionChoiceEntity;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface QuestionChoiceRepository extends JpaRepository<QuestionChoiceEntity, Integer> {

	
}
