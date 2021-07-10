package com.machntek.community.controller.post.dto

import com.machntek.community.domain.post.Post

data class SavePostReq(
    val userId: Long,
    val userName: String,
    val title: String,
    val content: String
) {
    fun toEntity(): Post {
        return Post(this.userId, this.userName, this.title, this.content)
    }
}
