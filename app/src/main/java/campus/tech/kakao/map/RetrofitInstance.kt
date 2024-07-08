package campus.tech.kakao.map

//객체인 Retrofit을 이용해서 네트워크 요청 처리 시 필요한 인스턴스 제시
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object RetrofitInstance { //싱글톤 객체 - 앱 내 하나의 인스턴스만 생성

    private const val BASE_URL = "https://dapi.kakao.com/"

    //로그 인터셉터 -> 네트워크 요청, 응답을 로그로 출력
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY //요청 및 응답 본문을 포한한 모든 정보 로그에 출력
    }

    //OkHttpClient 인스턴스 생성
    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor) //위의 로그 인터셉터 추가
        .build() //메서드 호출 -> OkHttpClient 인스턴스 생성

    //Retrofit 인스턴스 생성
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()) //json 데이터 -> kotlin 객체
        .client(httpClient) //okHttpClient 추가
        .build() //최종 retrofit 인스턴스 생성

    //kakaoapi인스턴스를 지연 초기화 방식으로 생성
    val api: KakaoApiService by lazy {
        retrofit.create(KakaoApiService::class.java)
    }
}
//네트워크 요청 처리 및 관리 - retrofit 인스턴스
//이 객체로 kakao api에 네트워크 요청 보내기 가능