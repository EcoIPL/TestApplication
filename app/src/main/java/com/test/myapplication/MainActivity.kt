package com.test.myapplication


import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.stream.Collectors


class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var mainAdapter: MainAdapter
    var dataClass: MutableList<DataClass> = mutableListOf()
    var orderList: MutableList<String> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sendPostRequest()
        recyclerView = findViewById(R.id.recylerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun sendPostRequest() {
        CoroutineScope(Dispatchers.IO).launch {
            var httpsURLConnection: HttpURLConnection? = null
            val mURL =
                URL("http://api.saralerp.com//api/app/GetOrderDetailsForSaral?authkey=VNTdzp8a8PHvNQwyqYA3e[...]419C6GLzF2wbvgcmvaZPUpa%20BRZXkvY00=&userlogin_keySaralweb=18210")

            try {

                httpsURLConnection = mURL.openConnection() as HttpURLConnection

                if (httpsURLConnection.responseCode != 200) {
                    throw IOException("Something went wrong !!")
                }

                val bufferedReader = BufferedReader(
                    InputStreamReader(httpsURLConnection.inputStream)
                )

                val jsonStringHolder = StringBuilder()
                while (true) {
                    val readLine = bufferedReader.readLine() ?: break
                    jsonStringHolder.append(readLine)
                }
                withContext(Dispatchers.Main) {
                    val a = jsonStringHolder.substring(19, jsonStringHolder.length - 4).toString()
                    val data = a.split("!!")
                    for (i in data.indices) {
                        if (i != 0) {
                            dataClass.add(
                                DataClass(
                                    data[i].split("|")[0],
                                    data[i].split("|")[1],
                                    data[i].split("|")[2],
                                    data[i].split("|")[3],
                                    data[i].split("|")[4],
                                    data[i].split("|")[5],
                                    data[i].split("|")[6],
                                    data[i].split("|")[7],
                                    data[i].split("|")[8],
                                    data[i].split("|")[9],
                                    data[i].split("|")[10]
                                )
                            )
                        }


                    }
                    val groupData: Map<String, List<DataClass>> =
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            dataClass.stream().collect(
                                Collectors.groupingBy(DataClass::ordno)
                            )
                        } else {
                            TODO("VERSION.SDK_INT < N")
                        }
                    println(groupData)

                    mainAdapter = MainAdapter(groupData)
                    recyclerView.adapter = mainAdapter

                }

            } catch (e: Exception) {
                println(e)
            } finally {
                httpsURLConnection?.disconnect()
            }
        }
    }
}
