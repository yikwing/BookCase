package com.rongyi.bookcase.bean

/**
 * Demo class
 *
 * @author yikwing
 * @date 2017/12/20
 */
data class BookBean(val rating: RatingBean,
                    val subtitle: String,
                    val author: List<String>,
                    val pubdate: String,
                    val tags: List<TagsBean>,
                    val origin_title: String,
                    val image: String,
                    val binding: String,
                    val translator: List<String>,
                    val catalog: String,
                    val pages: String,
                    val images: ImagesBean,
                    val alt: String,
                    val id: String,
                    val publisher: String,
                    val isbn10: String,
                    val isbn13: String,
                    val title: String,
                    val url: String,
                    val alt_title: String,
                    val author_intro: String,
                    val summary: String,
                    val series: SeriesBean,
                    val price: String) {

    data class RatingBean(val max: Int,
                          val numRaters: Int,
                          val average: String,
                          val min: Int)

    data class TagsBean(val count: Int,
                        val name: String,
                        val title: String)

    data class ImagesBean(val small: String,
                          val large: String,
                          val medium: String)

    data class SeriesBean(val id: String,
                          val title: String)
}
