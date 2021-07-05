package com.machntek.community.domain.post

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Post(title: String, content: String) {
    @Id
    @GeneratedValue
    var id: Long = 0

    @Column(nullable = false)
    var userId: Long = 0

    @Column(nullable = false)
    var title: String = title

    @Column(nullable = false, length = 3000)
    var content: String = content

    @Column
    var createdDatetime: LocalDateTime = LocalDateTime.now()
}
