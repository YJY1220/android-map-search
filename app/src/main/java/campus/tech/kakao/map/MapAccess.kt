package campus.tech.kakao.map

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.util.Log

class MapAccess(context: Context) {

    // 검색어 기반 항목 검색 suspend 함수
    // 검색어가 주어지면 (query) -> api로 장소 검색 후 결과 반환
    suspend fun searchItems(query: String, page: Int = 1, size: Int = 15): List<MapItem> {
        return withContext(Dispatchers.IO) {
            //api 키 가져와서 헤더 추가
            val apiKey = "KakaoAK ${BuildConfig.KAKAO_REST_API_KEY}"

            //Retrofit으로 api 호출
            val response = RetrofitInstance.api.searchPlaces(apiKey, query, page, size)

            // 응답이 성공적이면 결과를 로그에 출력하고 반환
            if (response.isSuccessful) {
                // 성공적으로 응답을 받았다는 로그 출력
                Log.d("MapAccess", "Response: ${response.body()?.documents}")

                // 응답 본문을 가져옴
                val responseBody = response.body()

                // 응답 본문이 null이 아닌 경우
                if (responseBody != null) {
                    // 응답 본문에서 documents 필드를 가져옴
                    val documents = responseBody.documents

                    // documents 필드가 null이 아닌 경우
                    if (documents != null) {
                        // documents를 반환
                        documents
                    } else {
                        // documents 필드가 null인 경우 빈 리스트 반환
                        emptyList()
                    }
                } else {
                    // 응답 본문이 null인 경우 빈 리스트 반환
                    emptyList()
                }
            } else {
                // 오류일 시 오류 메시지 출력
                val errorBody = response.errorBody()?.string()
                Log.e("MapAccess", "Error: $errorBody")

                // 빈 리스트 반환
                emptyList()
            }
        }
    }
}
