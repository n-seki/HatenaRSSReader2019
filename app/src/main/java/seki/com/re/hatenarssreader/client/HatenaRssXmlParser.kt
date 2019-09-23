package seki.com.re.hatenarssreader.client

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import seki.com.re.hatenarssreader.data.Article
import java.io.Reader

object HatenaRssXmlParser {

    private object Tag {
        const val ITEM = "item"
        const val TITLE = "title"
        const val LINK = "link"
        const val DESCRIPTION = "description"
        const val IMAGE_URL = "imageurl"
        const val BOOKMARK_COUNT = "bookmarkcount"
    }

    fun parse(reader: Reader?): List<Article> {
        val parser = XmlPullParserFactory.newInstance().apply {
            isNamespaceAware = true
        }.newPullParser().apply {
            setInput(reader)
        }
        val articles = mutableListOf<Article>()
        while (parser.eventType != XmlPullParser.END_DOCUMENT) {
            if (parser.eventType == XmlPullParser.START_TAG && parser.name == Tag.ITEM) {
                val article = parseArticle(parser)
                articles += article
            }
            parser.next()
        }
        return articles
    }

    private fun parseArticle(parser: XmlPullParser): Article {
        fun endOfArticle(parer: XmlPullParser): Boolean {
            return parer.eventType == XmlPullParser.END_TAG &&
                    parer.name == Tag.ITEM
        }

        var title = ""
        var link = ""
        var description = ""
        var imageUrl = ""
        var bookmarkCount = 0

        while (!endOfArticle(parser)) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                parser.next()
                continue
            }
            when (parser.name) {
                Tag.TITLE -> {
                    parser.next()
                    title = parser.text
                }
                Tag.LINK -> {
                    parser.next()
                    link = parser.text
                }
                Tag.DESCRIPTION -> {
                    parser.next()
                    description = parser.text
                }
                Tag.IMAGE_URL -> {
                    parser.next()
                    imageUrl = parser.text
                }
                Tag.BOOKMARK_COUNT -> {
                    parser.next()
                    bookmarkCount = parser.text.toInt()
                }
            }
            parser.next()
        }
        if (title.isEmpty()) {
            throw IllegalStateException("Title must not empty.")
        }
        return Article(title, link, description, imageUrl, bookmarkCount)
    }
}