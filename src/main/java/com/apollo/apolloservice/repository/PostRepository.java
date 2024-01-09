package com.apollo.apolloservice.repository;

import com.apollo.apolloservice.model.entity.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends ListCrudRepository<Post, Long> {

    List<Post> findAllByUserId(Long userId);

    @Query("SELECT post.id FROM Post post WHERE post.id IN ?1")
    List<Long> findAllIdsInList(List<Long> ids);

}
