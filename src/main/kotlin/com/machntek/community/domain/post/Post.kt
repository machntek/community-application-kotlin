package com.machntek.community.domain.post

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Post(userId: Long, userName: String, title: String, content: String) {
    @Id
    @GeneratedValue
    val id: Long = 0

    @Column(nullable = false)
    val userId: Long = userId

    @Column(nullable = false)
    val userName: String = userName

    @Column(nullable = false)
    var title: String = title
        protected set

    @Column(nullable = false, length = 3000)
    var content: String = content
        protected set

    @Column
    var hit: Int = 0

    @Column
    val createdAt: LocalDateTime = LocalDateTime.now()

    @Column
    val editedAt: LocalDateTime? = null

    fun updatePost(title: String, content: String) {
        this.title = title
        this.content = content
    }
}
