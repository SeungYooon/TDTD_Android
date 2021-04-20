package com.tdtd.domain.repository

import com.tdtd.domain.entity.RoomEntity
import com.tdtd.domain.entity.RoomsEntity
import com.tdtd.domain.Result

/**
 * 즐겨찾기
 */
interface BookmarkRepository {

    /**
     * 즐겨찾기 목록을 보여준다.
     */
    suspend fun getUserBookmarkList(): Result<List<RoomEntity>>

    /**
     * 해당 room-code에 방을 즐겨찾기 목록에 추가한다.
     */
    suspend fun postBookmarkByRoomCode(roomCode: String): Result<RoomsEntity>

    /**
     * 해당 room-code에 방을 즐겨찾기 목록에서 삭제한다.
     */
    suspend fun deleteBookmarkByRoomCode(roomCode: String): Result<RoomsEntity>

}
