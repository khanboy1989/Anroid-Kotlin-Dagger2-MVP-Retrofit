package net.cocooncreations.dimvparchitecture.network

import io.reactivex.Observable
import net.cocooncreations.dimvparchitecture.ui.models.Post
import retrofit2.http.GET
import java.util.*

/**
 * The interface which provides methods to get result of webservices
 */
interface PostApi {

    @GET(Endpoints.POSTS)
    fun getPosts():Observable<List<Post>>
}