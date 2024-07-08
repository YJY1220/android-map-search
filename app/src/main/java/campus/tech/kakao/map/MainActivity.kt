package campus.tech.kakao.map

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.daum.mf.map.api.MapView
import campus.tech.kakao.map.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
}
