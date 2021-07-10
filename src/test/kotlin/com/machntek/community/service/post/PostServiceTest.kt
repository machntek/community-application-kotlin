package com.machntek.community.service.post

import com.machntek.community.controller.post.dto.SavePostReq
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
}
