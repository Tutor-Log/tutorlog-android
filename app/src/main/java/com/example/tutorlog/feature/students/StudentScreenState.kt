package com.example.tutorlog.feature.students

import com.example.tutorlog.domain.types.BottomBarTabTypes

data class StudentScreenState(
    val name: String = "",
    val selectedIndex: Int = 0,
    val selectedBottomTab: BottomBarTabTypes = BottomBarTabTypes.STUDENTS,
    val studentList: List<Triple<String, String, String>> = listOf(
        Triple("Alice Johnson", "1234567890", "0"),
        Triple("Bob Smith", "9876543210", "1"),
        Triple("Charlie Brown", "5555555555", "1"),
        Triple("David Lee", "1111111111", "0"),
        Triple("Eve Davis", "9999999999", "0"),
    ),
    val groupList: List<Triple<String, String, String>> = listOf(
        Triple("Math Group", "5 Students", "0"),
        Triple("Science Group", "8 Students", "1"),
        Triple("History Group", "4 Students", "2"),
        Triple("Art Group", "6 Students", "3"),
        Triple("Music Group", "3 Students", "4"),
    )
)
