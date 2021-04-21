package com.tdtd.domain.repository

import com.tdtd.domain.entity.ReplyUserEntity
import com.tdtd.domain.entity.RoomsEntity
import com.tdtd.domain.Result
import com.tdtd.domain.entity.ReplyUserCommentWithFileEntity

/**
 * 답장
 */
interface ReplyRepository {

    /**
     * 사용자가 답장을 작성한다.
     */
    suspend fun postReplyUserComment(
        roomCode: String,
        replyUserCommentEntity: ReplyUserCommentWithFileEntity
    ): Result<ReplyUserEntity>

    /**
     * 사용자가 작성한 답장을 삭제한다.
     */
    suspend fun deleteReplyUserComment(): Result<RoomsEntity>

    /**
     * 사용자가 작성된 답장을 신고한다.
     */
    suspend fun postReportUserByCommentId(commentId: Long): Result<RoomsEntity>

}
