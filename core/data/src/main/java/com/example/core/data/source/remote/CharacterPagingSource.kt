package com.example.core.data.source.remote

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core.common.utils.NameState
import com.example.core.common.utils.ONE_VALUE
import com.example.core.common.utils.StatusState
import com.example.core.data.api.RickAndMortyApi
import com.example.core.data.dto.Character
import retrofit2.HttpException
import java.io.IOException

class CharacterPagingSource(
    private val rickAndMortyApi: RickAndMortyApi,
    private val nameState: NameState,
    private val statusState: StatusState
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val pageNumber = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = rickAndMortyApi.getAllCharacters(
                page = pageNumber,
                name = nameState.nameTitle,
                status = statusState.statusTitle
            )

            var nextPageNumber: Int? = null

            if (response.info?.next != null) {
                val uri = Uri.parse(response.info.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }

            LoadResult.Page(
                data = response.characters ?: emptyList(),
                prevKey = if (pageNumber == STARTING_PAGE_INDEX) null else pageNumber.minus(Int.ONE_VALUE),
                nextKey = nextPageNumber
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(Int.ONE_VALUE) ?: anchorPage?.nextKey?.minus(Int.ONE_VALUE)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}