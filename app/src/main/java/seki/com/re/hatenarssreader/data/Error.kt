package seki.com.re.hatenarssreader.data

data class Error(
    val errorCode: Int? = null,
    val title: String,
    val message: String
)