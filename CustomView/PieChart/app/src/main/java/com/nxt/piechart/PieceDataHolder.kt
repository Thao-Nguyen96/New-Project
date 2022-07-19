package com.nxt.piechart


/**
 * Nắm giữ thôn tin từng phần của biểu đồ tròn
 */
data class PieceDataHolder(
    val value: Int,//giá trị của từng lĩnh vực
    val color: Int,
    val marker: String,//dấu của mỗi khối
)
