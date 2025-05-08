package com.example.myapplication.data

object MemberRepository {
    val members = listOf(
        Member("1234", "홍길동"),
        Member("5678", "김철수"),
        Member("9999", "이영희")
    )

    // 번호로 회원 찾기
    fun findMemberByNumber(number: String): Member? {
        return members.find { it.number == number }
    }
}
