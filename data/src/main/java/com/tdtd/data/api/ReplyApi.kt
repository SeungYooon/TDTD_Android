package com.tdtd.data.api

import com.tdtd.data.response.room.DeleteResponse
import com.tdtd.data.response.room.RoomDetailResponse
import okhttp3.RequestBody
import retrofit2.http.*

interface ReplyApi {

    @Multipart
    @POST("api/v1/comments/{roomCode}")
    suspend fun postReplyUserComment(
        @Path("roomCode") roomCode: String,
        @PartMap params: HashMap<String, RequestBody>
    ): DeleteResponse

    @DELETE("/api/v1/comments/{commentId}")
    suspend fun deleteReplyUserComment(
        @Path("commentId") commentId: Long
    ): RoomDetailResponse

    @POST("/api/v1/reports/{commentId}")
    suspend fun postReportUserByCommentId(
        @Path("commentId") commentId: Long
    ) : DeleteResponse
}
