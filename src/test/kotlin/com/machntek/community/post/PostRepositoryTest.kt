package com.machntek.community.post

import com.machntek.community.domain.post.Post
import com.machntek.community.domain.post.PostRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
class PostRepositoryTest {

    @Autowired
    lateinit var postRepository: PostRepository

    @Test
    fun dbCreateTest() {
        var createPost = Post("title", "content")
        var id: Long = postRepository.save(createPost).id

        println(id)
        var readPost: Post = postRepository.findById(id).orElse(Post("failedTitle", "failedContent"))
        assertThat(createPost.title).isEqualTo(readPost.title)
        assertThat(createPost.content).isEqualTo(readPost.content)
        assertThat(createPost.createdDatetime).isNotNull()

        println("테스트")
    }
}
