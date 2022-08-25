import java.util.Date

fun main() {
    val posts = WallService
    posts.add(Post(4, from = "Исмаил 1", text = "Новый пост 1", owner = "Аркадий", likes = Likes(10)))
    posts.add(Post(2, from = "Исмаил 2", text = "Новый пост 2", owner = "Арсен"))
    posts.add(Post(6, from = "Исмаил 3", text = "Новый пост 3", owner = "Петр"))
    println(posts.update(Post(id = 3, from = "Семен", text = "Новый Текст 10")))

    posts.add(
        Post(
            from = "Семен", text = "Новый Текст 10", attachments = arrayOf(
                Attachment.AudioAttachment(1, duration = 10),
                Attachment.VideoAttachment(1, "Название")
            )
        )
    )

    posts.print()
}

data class Post(
    val id: Int = 0,
    val owner: String = "Петров Сергей",
    val from: String,
    val text: String,
    val date: Date = Date(),
    val reply_owner: String = "Иванов Олег",
    val reply_post_id: Post? = null,
    val friends_only: Boolean = true,
    val likes: Likes = Likes(1),
    val copyright: String? = null,
    var attachments: Array<Attachment> = emptyArray()
)

data class Likes(
    val count: Int,
    val user_likes: Boolean = false,
    val can_like: Boolean = true,
    val can_publish: Boolean = true,
)

sealed class Attachment (val type: String) {
    data class VideoAttachment(
        val id: Int,
        val description: String,
        val title: String? = null,
        ) : Attachment("Видео")
    data class AudioAttachment(
        val id: Int,
        val artist: String? = null,
        val title: String? = null,
        val duration: Int
        ) : Attachment("Аудио")
    data class DocAttachment(
        val id: Int,
        val title: String,
        val size: Int,
        val url: Int,
        val ext: String
        ) : Attachment("Документы")
    data class PollAttachment(
        val id: Int,
        val votes: Int,
        val anonymous: Boolean,
        val multiple: Boolean,
        val question: String
        ) : Attachment("Опросы")
    data class NoteAttachment(
        val id: Int,
        val title: String,
        val text: String
        ) : Attachment("Заметка")
}

object WallService {
    private var posts = emptyArray<Post>()
    private var id_new: Int = 0

    fun print() {
        for (post in posts) {
            println(post)
        }
        println()
    }

    fun add(post: Post): Post {
        id_new += 1
        posts += post.copy(id = id_new)
        return posts.last()
    }

    fun update(post_new: Post): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (post.id == post_new.id) {
                posts[index] = post_new.copy(date = post.date, owner = post.owner)
                return true
            }
        }
        return false
    }
}

