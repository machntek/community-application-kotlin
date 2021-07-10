package com.machntek.community.service.post

import com.machntek.community.controller.post.dto.GetPostsRes
import com.machntek.community.controller.post.dto.SavePostReq
import com.machntek.community.domain.post.PostRepository
import org.springframework.stereotype.Service

@Service
class PostService(private val postRepository: PostRepository) {

    fun getPosts(): List<GetPostsRes> {
        return postRepository.findAllDesc().map { GetPostsRes(it) }
    }

    fun getPost() {

    }

    fun save(request: SavePostReq): Long {
        return postRepository.save(request.toEntity()).id
    }

    fun update() {

    }

    fun delete() {

    }
}
