package com.example.rickyandmorty.data.mapper

interface Mapper<I, O> {
    fun map(input: I?): O
}