package com.machntek.community.domain.post

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
class PostRepositoryTest() {

    @Autowired
    lateinit var postRepository: PostRepository

    lateinit var post1: Post

    @BeforeEach
    fun setUp() {
        post1 = Post(555, "machntek", "title", "content")
        postRepository.deleteAll()
    }

    @Test
    fun createPostTest_success() {
        // given
        val noneCount = postRepository.count()

        // when
        val initPost = postRepository.save(post1)

        // then
        val createdCount = postRepository.count()
        val readPost = postRepository.findById(initPost.id)
            .orElse(Post(0, "failedUser", "failedTitle", "failedContent"))

        assertThat(noneCount).isEqualTo(0)
        assertThat(createdCount).isEqualTo(noneCount + 1)
        assertThat(post1.title).isEqualTo(readPost.title)
        assertThat(post1.content).isEqualTo(readPost.content)
        assertThat(post1.createdAt).isNotNull()
    }

    @Test
    fun updatePostTest_success() {
        // given
        val initPost = postRepository.save(post1)
        val initCount = postRepository.count()
        val updatedTitle = "newTitle"
        val updatedContent = "newContent"

        // when
        initPost.updatePost(updatedTitle, updatedContent)
        postRepository.save(initPost)

        // then
        val updatedPost = postRepository.findById(initPost.id)
            .orElse(Post(0, "failedUser", "failedTitle", "failedContent"))
        val updatedCount = postRepository.count()

        assertThat(updatedCount).isEqualTo(initCount)
        assertThat(updatedPost.id).isEqualTo(initPost.id)
        assertThat(updatedPost.title).isEqualTo(updatedTitle)
        assertThat(updatedPost.content).isEqualTo(updatedContent)
    }

    @Test
    fun deletePostTest_success() {
        // given
        val initPost = postRepository.save(post1)
        val initCount = postRepository.count()

        // when
        postRepository.delete(initPost)

        // then
        val deletedCount = postRepository.count()
        assertThat(deletedCount).isEqualTo(initCount - 1)
    }
}
