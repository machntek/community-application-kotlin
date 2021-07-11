package com.machntek.community.service.post

import com.machntek.community.controller.post.dto.SavePostReq
import com.machntek.community.controller.post.dto.UpdatePostReq
import com.machntek.community.domain.post.Post
import com.machntek.community.domain.post.PostRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class PostServiceTest {
    @Mock
    lateinit var postRepository: PostRepository

    @InjectMocks
    lateinit var postService: PostService

    lateinit var posts: List<Post>

    @BeforeEach
    fun setUp() {
        posts = listOf(
            Post(1, "김씨", "1월", "1월입니다."),
            Post(2, "이씨", "2월", "2월입니다."),
            Post(3, "박씨", "3월", "3월입니다."),
        )
    }

    @Test
    fun getPosts_success() {
        // given
        given(postRepository.findAllDesc()).willReturn(posts)

        // when
        val result = postService.getPosts()

        // then
        assertThat(result.size).isEqualTo(3)
    }

    @Test
    fun getPost_success() {
        // given
        val post = Optional.ofNullable(posts[0])
        given(postRepository.findById(0)).willReturn(post)

        // when
        val result = postService.getPost(0)

        // then
        assertThat(result).isNotNull()
    }

    @Test
    fun savePost_success() {
        // given
        val request = SavePostReq(555, "machntek", "제목", "내용")
        val post = request.toEntity()
        // TODO : 매쳐 다시
        given(postRepository.save(any())).willReturn(post)

        // when
        val postId = postService.save(request)

        // then
        assertThat(postId).isEqualTo(0)
    }

    @Test
    fun updatePost_success() {
        // given
        val request = UpdatePostReq("newTitle", "newContent")
        val post = Optional.ofNullable(posts[0])
        given(postRepository.findById(0)).willReturn(post)
        val updatedPost = post.get().update(request.title, request.content)
        given(postRepository.save(any())).willReturn(updatedPost)

        // when
        val postId = postService.update(0, request)

        // then
        assertThat(postId).isEqualTo(0)
        assertThat(updatedPost.title).isEqualTo("newTitle")
        assertThat(updatedPost.content).isEqualTo("newContent")
        assertThat(updatedPost.editedAt).isNotNull()
    }
}
