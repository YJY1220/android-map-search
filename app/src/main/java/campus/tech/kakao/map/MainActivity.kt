package campus.tech.kakao.map

<<<<<<< HEAD
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
=======
import android.content.Intent
>>>>>>> f43e96d1f8d8dd4db3d9bfc0d0851b18671eeaa9
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
<<<<<<< HEAD
import campus.tech.kakao.map.R
import com.kakao.vectormap.MapView
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.KakaoMap
import java.security.MessageDigest

class MainActivity : AppCompatActivity() {

    private lateinit var mapView: MapView
=======
import net.daum.mf.map.api.MapView
import campus.tech.kakao.map.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
>>>>>>> f43e96d1f8d8dd4db3d9bfc0d0851b18671eeaa9

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

<<<<<<< HEAD
        mapView = findViewById(R.id.map_view)

        mapView.start(object : MapLifeCycleCallback() {
            override fun onMapDestroy() {
                // 지도 API 가 정상적으로 종료될 때 호출됨
            }

            override fun onMapError(error: Exception) {
                // 인증 실패 및 지도 사용 중 에러가 발생할 때 호출됨
            }
        }, object : KakaoMapReadyCallback() {
            override fun onMapReady(kakaoMap: KakaoMap) {
                // 인증 후 API 가 정상적으로 실행될 때 호출됨
            }
        })

        // 앱 키 해시를 로그로 출력
        getAppKeyHash()
    }

    override fun onResume() {
        super.onResume()
        mapView.resume()  // MapView 의 resume 호출
    }

    override fun onPause() {
        super.onPause()
        mapView.pause()  // MapView 의 pause 호출
    }

    private fun getAppKeyHash() {
        try {
            val info: PackageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val something = String(Base64.encode(md.digest(), 0))
                Log.d("Hash key", something)
            }
        } catch (e: Exception) {
            Log.e("not found", e.toString())
        }
    }
=======
        val mapView = MapView(this)
        binding.mapViewContainer.addView(mapView)

        binding.searchButton.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.mapViewContainer.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapViewContainer.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapViewContainer.onDestroy()
    }
>>>>>>> f43e96d1f8d8dd4db3d9bfc0d0851b18671eeaa9
}
