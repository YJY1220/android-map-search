import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import campus.tech.kakao.map.SearchActivity
import campus.tech.kakao.map.databinding.ActivityMainBinding
import com.kakao.vectormap.MapView
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mapView: MapView
    private var kakaoMap: KakaoMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize and add MapView to the container
        mapView = MapView(this)
        binding.mapView.addView(mapView)

        // Start the map and set empty callbacks
        mapView.start(object : MapLifeCycleCallback() {
            override fun onMapDestroy() {
                // Called when the map is destroyed
                Log.d("MapLifeCycle", "Map destroyed")
            }

            override fun onMapError(error: Exception) {
                // Called when an error occurs
                Log.e("MapError", "Error: ${error.message}")
            }
        }, object : KakaoMapReadyCallback() {
            override fun onMapReady(map: KakaoMap) {
                // Called when the map is ready
                kakaoMap = map
                Log.d("MapLifeCycle", "Map is ready")
            }
        })

        // Search button click listener
        binding.searchButton.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        // Get app key hash for debug (optional)
        // getAppKeyHash()
    }

    override fun onResume() {
        super.onResume()
        mapView.resume() // MapView resume
    }

    override fun onPause() {
        super.onPause()
        mapView.pause() // MapView pause
    }

    /*private fun getAppKeyHash() {
        try {
            val info = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
            for (i in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(i.toByteArray())

                val something = String(Base64.encode(md.digest(), 0)!!)
                Log.e("Debug key", something)
            }
        } catch (e: Exception) {
            Log.e("Not found", e.toString())
        }
    }*/
}
