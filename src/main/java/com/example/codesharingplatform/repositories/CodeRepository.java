package com.example.codesharingplatform.repositories;

import com.example.codesharingplatform.entities.CodeSnippet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository extends JpaRepository<CodeSnippet, Long> {
}
