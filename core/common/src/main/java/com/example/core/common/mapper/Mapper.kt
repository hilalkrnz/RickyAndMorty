package com.example.core.common.mapper

interface Mapper<I, O> {
    fun map(input: I?): O
}