import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {
    @Test
    fun add() {
        val service = WallService
        val post = service.add(Post(3, from = "Исмаил 2", text = "Новый пост 2", owner = "Арсен"))
        assertTrue(post.id > 0)
    }

    @Test
    fun update_false() {
        val service = WallService

        service.add(Post(2, from = "Исмаил 2", text = "Новый пост 2", owner = "Арсен"))
        service.add(Post(4, from = "Исмаил 1", text = "Новый пост 1", owner = "Арсен"))

        val update = Post(id = 4, from = "Семен", text = "Новый Текст 10")
        val result = service.update(update)
        assertFalse(result)
    }

    @Test
    fun update_true() {
        val service = WallService

        service.add(Post(2, from = "Исмаил 2", text = "Новый пост 2", owner = "Арсен"))
        service.add(Post(4, from = "Исмаил 1", text = "Новый пост 1", owner = "Арсен"))

        val update = Post(id = 1, from = "Семен", text = "Новый Текст 10")
        val result = service.update(update)
        assertTrue(result)
    }

    @Test(expected = NotFoundException::class)
    fun shouldThrow() {
        val service = WallService
        service.createComment(100, Comment(text = "Привет"))
    }

    @Test
    fun addComment() {
        val service = WallService
        service.add(Post(3, from = "Исмаил 2", text = "Новый пост 2", owner = "Арсен"))
        val comment = service.createComment(1, Comment(text = "Привет"))
        assertTrue(comment.id > 0)
    }

    @Test(expected = NotFoundException::class)
    fun shouldThrowReason() {
        val service = WallService
        service.add(Post(3, from = "Исмаил 2", text = "Новый пост 2", owner = "Арсен"))
        service.createComment(1, Comment(text = "Привет"))
        service.wallReportComment(reportComment(comment_id = 1, reason = 60))
    }

    @Test(expected = NotFoundException::class)
    fun shouldThrowCommentId() {
        val service = WallService
        service.wallReportComment(reportComment(comment_id = 10, reason = 6))
    }

    @Test
    fun addReportComment() {
        val service = WallService
        service.add(Post(3, from = "Исмаил 2", text = "Новый пост 2", owner = "Арсен"))
        service.createComment(1, Comment(text = "Привет"))
        service.wallReportComment(reportComment(comment_id = 1, reason = 6))
    }
}