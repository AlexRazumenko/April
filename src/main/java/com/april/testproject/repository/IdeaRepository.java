package com.april.testproject.repository;

import com.april.testproject.entity.Idea;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.math.BigInteger;
import java.util.List;

public interface IdeaRepository extends JpaRepository<Idea, Long> {

	@Query(value = "SELECT i FROM Idea i WHERE i.userId = ?1")
	List<Idea> findByUserId(String userId);

	@Query(name = "findAll", value = "SELECT i FROM Idea i ORDER BY i.creationDate DESC")
	List<Idea> findAll();

	@Query(value = "SELECT i FROM Idea i WHERE i.header like %?1%")
	List<Idea> findInHeader(String world);

	@Query(value = "SELECT i FROM Idea i ORDER BY i.creationDate DESC")
	List<Idea> getIdeasPageByDate(Pageable pageable);

	@Query(value = "SELECT COUNT(i) FROM Idea i")
	int getNumberAllIdeas();

	@Query(value = "select idea_id from ideas_tags where tag_id = ?1", nativeQuery = true)
	List<BigInteger> getIdeasByTag(Long tagId);
}
