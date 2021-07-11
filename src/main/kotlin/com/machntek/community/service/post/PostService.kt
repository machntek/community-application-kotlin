package com.machntek.community.service.post

import com.machntek.community.controller.post.dto.GetPostRes
import com.machntek.community.controller.post.dto.SavePostReq
import com.machntek.community.domain.post.PostRepository
import org.springframework.stereotype.Service

@Service
class PostService(private val postRepository: PostRepository) {

    fun getPosts(): List<GetPostRes> {
        return postRepository.findAllDesc().map { GetPostRes(it) }
    }

    fun getPost(postId: Long): GetPostRes {
        val post = postRepository.findById(postId).orElseThrow { IllegalArgumentException("해당 게시글이 없습니다. id=${postId}") }
        return GetPostRes(post)
    }

    fun save(request: SavePostReq): Long {
        return postRepository.save(request.toEntity()).id
    }

    fun update() {

    }

    fun delete() {

    }
}
