package com.machntek.community.domain.post

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PostRepository : JpaRepository<Post, Long> {

    @Query(
        "select p.id, p.userName, p.title, p.createdAt, p.editedAt, p.hit " +
                "from Post p " +
                "order by p.id desc"
    )
    fun findAllDesc(): List<Post>
}
