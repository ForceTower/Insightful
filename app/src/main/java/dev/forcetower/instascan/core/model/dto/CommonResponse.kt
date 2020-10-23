package dev.forcetower.instascan.core.model.dto

data class CommonResponse<T>(
    val data: T,
    val paging: ResponsePaging?
)

data class ResponsePaging(
    val cursors: PagingCursors
)

data class PagingCursors(
    val before: String?,
    val after: String?
)