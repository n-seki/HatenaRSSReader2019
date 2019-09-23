package seki.com.re.hatenarssreader.data

data class Article(
    val title: String,
    val link: String,
    val description: String,
    val thumbnailUrl: String,
    val bookmarkCount: Int
)