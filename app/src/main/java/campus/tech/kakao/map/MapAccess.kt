package campus.tech.kakao.map

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.util.Log

class MapAccess(context: Context) {

    // 검색어 기반 항목 검색 suspend 함수
    suspend fun searchItems(query: String, page: Int = 1, size: Int = 15): List<MapItem> {
        return withContext(Dispatchers.IO) {
            val apiKey = "KakaoAK ${BuildConfig.KAKAO_REST_API_KEY}"
            val response = RetrofitInstance.api.searchPlaces(apiKey, query, page, size)
            if (response.isSuccessful) {
                Log.d("MapAccess", "Response: ${response.body()?.documents}")
                response.body()?.documents ?: emptyList()
            } else {
                Log.e("MapAccess", "Error: ${response.errorBody()?.string()}")
                emptyList()
            }
        }
    }
}
