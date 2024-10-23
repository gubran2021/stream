package com.CXXX

import com.lagradost.cloudstream3.HomePageList
import com.lagradost.cloudstream3.HomePageResponse
import com.lagradost.cloudstream3.LoadResponse
import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.MainPageRequest
import com.lagradost.cloudstream3.SearchResponse
import com.lagradost.cloudstream3.SubtitleFile
import com.lagradost.cloudstream3.TvType
import com.lagradost.cloudstream3.app
import com.lagradost.cloudstream3.fixUrlNull
import com.lagradost.cloudstream3.mainPageOf
import com.lagradost.cloudstream3.network.CloudflareKiller
import com.lagradost.cloudstream3.newHomePageResponse
import com.lagradost.cloudstream3.newMovieLoadResponse
import com.lagradost.cloudstream3.newMovieSearchResponse
import com.lagradost.cloudstream3.utils.ExtractorLink
import com.lagradost.cloudstream3.utils.loadExtractor
import kotlinx.coroutines.delay
import org.jsoup.nodes.Element

class Perverzija : MainAPI() {
    override var name = "Perverzija"
    override var mainUrl = "https://tube.perverzija.com/"
    override val supportedTypes = setOf(TvType.NSFW)

    override val hasDownloadSupport = true
    override val hasMainPage = true

    private val cfInterceptor = CloudflareKiller()

    override val mainPage = mainPageOf(
        "$mainUrl/page/%d/" to "Home",
        "$mainUrl/studio/page/%d/?orderby=view" to "Most Viewed",
        "$mainUrl/studio/page/%d/?orderby=like" to "Most Liked",
        "$mainUrl/featured-scenes/page/%d/?orderby=date" to "Featured",
        "$mainUrl/featured-scenes/page/%d/?orderby=view" to "Featured Most Viewed",
        "$mainUrl/featured-scenes/page/%d/?orderby=like" to "Featured Most Liked",
        "$mainUrl/full-movie/page/%d/?orderby=date" to "Latest Movies",
        "$mainUrl/full-movie/page/%d/?orderby=view" to "Most Viewed Movies",
        "$mainUrl/full-movie/page/%d/?orderby=like" to "Most Liked Movies",
        "$mainUrl/studio/onlyfans/page/%d/?orderby=date" to "Recent Onlyfans",
        "$mainUrl/studio/onlyfans/page/%d/?orderby=view" to "Most Viewed Onlyfans",
        "$mainUrl/studio/onlyfans/page/%d/?orderby=like" to "Most Liked Onlyfans",
        "$mainUrl/studio/onlyfans/page/%d/?orderby=comment" to "Most Commented Onlyfans",
        "$mainUrl/studio/vxn/page/%d/?orderby=date" to "Recent Vxn",
        "$mainUrl/studio/vxn/page/%d/?orderby=view" to "Most Viewed Vxn",
        "$mainUrl/studio/vxn/page/%d/?orderby=like" to "Most Liked Vxn",
        "$mainUrl/studio/vxn/page/%d/?orderby=comment" to "Most Commented Vxn",
        "$mainUrl/studio/wicked/page/%d/?orderby=date" to "Wicked",
        "$mainUrl/studio/twistysnetwork/page/%d/?orderby=date" to "Twistys Network",
        "$mainUrl/studio/naughtyamerica/tonightsgirlfriend/page/%d/?orderby=date" to "Tonight's Girlfriend",
        "$mainUrl/studio/nubiles/stepsiblingscaught/page/%d/?orderby=date" to "Stepsiblings Caught",
        "$mainUrl/studio/penthousegold/page/%d/?orderby=date" to "Penthouse Gold",
        "$mainUrl/studio/pornpros/page/%d/?orderby=date" to "PornPros",
        "$mainUrl/studio/dogfart/page/%d/?orderby=date" to "Dog Fart",
        "$mainUrl/studio/cherrypimps/page/%d/?orderby=date" to "Cherry Pimps",
        "$mainUrl/tag/wife/page/%d/?orderby=date" to "Wife",
        "$mainUrl/tag/pov/page/%d/?orderby=date" to "POV",
        "$mainUrl/tag/18-year-old/page/%d/?orderby=date" to "18 Year Old",
        "$mainUrl/tag/watching/page/%d/?orderby=date" to "Watching",
        "$mainUrl/tag/taboo/page/%d/?orderby=date" to "Taboo",
        "$mainUrl/tag/sister/page/%d/?orderby=date" to "Siter",
        "$mainUrl/tag/double-penetration/page/%d/?orderby=date" to "DP",
        "$mainUrl/studio/teamskeet/page/%d/?orderby=date" to "Team Skeet",
        "$mainUrl/studio/private/page/%d/?orderby=date" to "Private",
        "$mainUrl/studio/brazzers/page/%d/?orderby=date" to "Brazzers",
        "$mainUrl/studio/bangbros/page/%d/?orderby=date" to "Bang Bros",
        "$mainUrl/studio/realitykings/page/%d/?orderby=date" to "Reality Kings",
        "$mainUrl/studio/naughtyamerica/page/%d/?orderby=date" to "NaughtyAmerica",
        "$mainUrl/studio/nubiles/page/%d/?orderby=date" to "Nubiles",
        "$mainUrl/studio/adulttime/page/%d/?orderby=date" to "Adult Time",
        "$mainUrl/studio/vxn/tushy/page/%d/?orderby=date" to "Tushy",
        "$mainUrl/studio/dorcelclub/page/%d/?orderby=date" to "DorcelClub",
        "$mainUrl/studio/pornworld/ddfnetwork/page/%d/?orderby=date" to "DDFNetwork",
        "$mainUrl/studio/mylf/page/%d/?orderby=date" to "Mylf",
        "$mainUrl/studio/pornpros/page/%d/?orderby=date" to "PornPros",
        "$mainUrl/studio/evilangel/page/%d/?orderby=date" to "EvilAngel",
        "$mainUrl/studio/digitalplayground/page/%d/?orderby=date" to "Digital Playground",
        "$mainUrl/studio/kbproductions/page/%d/?orderby=date" to "KBProductions",
        "$mainUrl/studio/fakehub/page/%d/?orderby=date" to "FakeHub",
        "$mainUrl/studio/sexyhub/page/%d/?orderby=date" to "SexyHub",
        "$mainUrl/studio/milehighmedia/page/%d/?orderby=date" to "MileHighMedia",
        "$mainUrl/studio/mofos/page/%d/?orderby=date" to "Mofos",
        "$mainUrl/studio/adulttime/21sextury/page/%d/?orderby=date" to "21Sextury",
        "$mainUrl/studio/fullpornnetwork/page/%d/?orderby=date" to "FullPornNetwork",
        "$mainUrl/studio/vxn/blacked/page/%d/?orderby=date" to "Blacked",
        "$mainUrl/studio/bang/page/%d/?orderby=date" to "Bang",
        "$mainUrl/studio/newsensations/page/%d/?orderby=date" to "NewSensations",
        "$mainUrl/studio/letsdoeit/page/%d/?orderby=date" to "LetsDoeIt",
        "$mainUrl/studio/julesjordan/page/%d/?orderby=date" to "JulesJordan",
        "$mainUrl/studio/xempire/page/%d/?orderby=date" to "XEmpire",
        "$mainUrl/studio/sexmex/page/%d/?orderby=date" to "SexMex",
        "$mainUrl/studio/pornworld/page/%d/?orderby=date" to "PornWorld",
        "$mainUrl/studio/hustler/page/%d/?orderby=date" to "Hustler",
        "$mainUrl/studio/penthousegold/page/%d/?orderby=date" to "PenthouseGold",
        "$mainUrl/studio/babesnetwork/page/%d/?orderby=date" to "BabesNetwork",
        "$mainUrl/studio/pornfidelity/page/%d/?orderby=date" to "PornFidelity",
        "$mainUrl/studio/mypervyfamily/page/%d/?orderby=date" to "MyPervyFamily",
        "$mainUrl/studio/adulttime/puretaboo/page/%d/?orderby=date" to "PureTaboo",
        "$mainUrl/studio/teamskeet/familystrokes/page/%d/?orderby=date" to "FamilyStrokes",
        "$mainUrl/studio/teamskeet/daughterswap/page/%d/?orderby=date" to "DaughterSwap",
        "$mainUrl/studio/adulttime/21sextury/dpfanatics/page/%d/?orderby=date" to "DPFanatics",
    )

    override suspend fun getMainPage(
        page: Int,
        request: MainPageRequest
    ): HomePageResponse {
        val document = app.get(request.data.format(page), interceptor = cfInterceptor).document
        val home = document.select("div.row div div.post").mapNotNull {
            it.toSearchResult()
        }

        return newHomePageResponse(
            list = HomePageList(
                name = request.name, list = home, isHorizontalImages = true
            ), hasNext = true
        )
    }

    private fun Element.toRecommendationResult(): SearchResponse? {
        val posterUrl = fixUrlNull(this.select("dt a img").attr("src"))
        val title = this.select("dd a").text() ?: return null
        val href = fixUrlNull(this.select("dt a").attr("href")) ?: return null

        return newMovieSearchResponse(title, href, TvType.NSFW) {
            this.posterUrl = posterUrl
        }

    }

    private fun Element.toSearchResult(): SearchResponse? {
        val posterUrl = fixUrlNull(this.select("div.item-thumbnail img").attr("src"))
        val title = this.select("div.item-head a").text() ?: return null
        val href = fixUrlNull(this.select("div.item-head a").attr("href")) ?: return null

        return newMovieSearchResponse(title, href, TvType.NSFW) {
            this.posterUrl = posterUrl
        }

    }

    override suspend fun search(query: String): List<SearchResponse> {
        val searchResponse = mutableListOf<SearchResponse>()
        val maxPages = if (query.contains(" ")) 6 else 20
        for (i in 1..maxPages) {
            val url = if (query.contains(" ")) {
            "$mainUrl/page/$i/?s=${query.replace(" ", "+")}&orderby=date"
            } else {
                "$mainUrl/tag/$query/page/$i/"
            }

            val results = app.get(url, interceptor = cfInterceptor).document
                .select("div.row div div.post").mapNotNull {
                    it.toSearchResult()
                }.distinctBy { it.url }
            if (results.isEmpty()) break
            else delay((100L..500L).random())
            searchResponse.addAll(results)
        }
        return searchResponse.distinctBy { it.url }
    }

    override suspend fun load(url: String): LoadResponse {
        val document = app.get(url, interceptor = cfInterceptor).document

        val poster = document.select("div#featured-img-id img").attr("src")
        val title = document.select("div.title-info h1.light-title.entry-title").text()
        val pTags = document.select("div.item-content p")
        val description = StringBuilder().apply {
            pTags.forEach {
                append(it.text())
            }
        }.toString()

        val tags = document.select("div.item-tax-list div a").map { it.text() }

        val recommendations =
            document.select("div.related-gallery dl.gallery-item").mapNotNull {
                it.toRecommendationResult()
            }

        return newMovieLoadResponse(title, url, TvType.NSFW, url) {
            this.posterUrl = poster
            this.plot = description
            this.tags = tags
            this.recommendations = recommendations
        }
    }

    override suspend fun loadLinks(
        data: String,
        isCasting: Boolean,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ): Boolean {
        val response = app.get(data)
        val document = response.document

        val iframeUrl = document.select("div#player-embed iframe").attr("src")

        return loadExtractor(iframeUrl, subtitleCallback, callback)
    }
}
