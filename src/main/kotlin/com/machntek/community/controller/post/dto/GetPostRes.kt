package com.machntek.community.controller.post.dto

import com.machntek.community.domain.post.Post
import java.time.LocalDateTime

data class GetPostRes(
    var id: Long,
    var userName: String,
    var title: String,
    var content: String,
    var cratedAt: LocalDateTime,
    var editedAt: LocalDateTime?,
    var hit: Int
) {
    constructor(post: Post) : this(post.id, post.userName, post.title, post.content, post.createdAt, post.editedAt, post.hit)
}
