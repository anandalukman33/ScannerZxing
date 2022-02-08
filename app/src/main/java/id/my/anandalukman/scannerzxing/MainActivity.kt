package id.my.anandalukman.scannerzxing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator

class MainActivity : AppCompatActivity() {

    lateinit var btnBarcode: Button
    lateinit var textView: TextView

    private var switch: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "LukmanTestScan"
        btnBarcode = findViewById(R.id.button)
        textView = findViewById(R.id.txtContent)
        switch = findViewById(R.id.switchAct)
        btnBarcode?.setOnClickListener {
            val intent = IntentIntegrator(this@MainActivity)
            intent.setBeepEnabled(false)
            intent.setCameraId(0)
            intent.setPrompt("SCAN")
            intent.setBarcodeImageEnabled(false)
            intent.initiateScan()
        }
        switch?.setOnClickListener {
            var intent = Intent(this, ScannerCode::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Dibatalin", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("TestScan", "Discan!!!")
                Toast.makeText(this, "HasilScan : ${result.contents}", Toast.LENGTH_SHORT).show()
                textView.text = String.format("Hasil scan adalah : $result")
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}