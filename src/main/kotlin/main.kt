import java.util.Date

fun main() {
    val posts = WallService
    println(posts.add(Post(4, from = "Исмаил 1", text = "Новый пост 1", owner = "Аркадий", likes = Likes(10))))
    println(posts.add(Post(2, from = "Исмаил 2", text = "Новый пост 2", owner = "Арсен")))
    println(posts.add(Post(6, from = "Исмаил 3", text = "Новый пост 3", owner = "Петр")))

    println(posts.update(Post(id = 3, from = "Семен", text = "Новый Текст 10")))
}

data class Post(
    val id: Int,
    val owner: String = "Петров Сергей",
    val from: String,
    val text: String,
    val date: Date = Date(),
    val reply_owner: String = "Иванов Олег",
    val reply_post_id: Int = 100,
    val friends_only: Boolean = true,
    val likes: Likes = Likes(1)
)

data class Likes(
    val count: Int,
    val user_likes: Boolean = false,
    val can_like: Boolean = true,
    val can_publish: Boolean = true,
)

object WallService {
    private var posts = emptyArray<Post>()
    private var id_new: Int = 0
    private var upd = false

    fun add(post: Post): Post {
        id_new += 1
        posts += post.copy(id = id_new)
        return posts.last()
    }

    fun update(post_new: Post): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (post.id == post_new.id) {
                posts[index] = post_new.copy(date = post.date, owner = post.owner)
                upd = true
            }
        }
        return upd
    }
}

