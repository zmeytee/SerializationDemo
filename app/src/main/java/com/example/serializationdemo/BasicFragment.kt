package com.example.serializationdemo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.serializationdemo.databinding.FragmentBasicBinding
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class BasicFragment : Fragment(R.layout.fragment_basic) {

    //Ignore unknown keys true (else exception)
    private val jsonFormat = Json { ignoreUnknownKeys = true }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentBasicBinding.bind(view)

        // From object to JSON
        val json = jsonFormat.encodeToString(data)

        // From JSON to object
        val obj = jsonFormat.decodeFromString<Data>(jsonData)

        binding.textView.text = buildString {
            append("JSON:\n")
            append(json)
            append("\n")
            append("\n")
            append("OBJECT:\n")
            append(obj)
        }
    }

    private companion object {
        private val data = Data(
            id = 1,
            name = "Name",
            hasLastName = false,
            type = Type.ONLINE,
            list = listOf(
                Details(
                    id = 666,
                    name = "Details",
                    list = listOf(7, 8)
                )
            ),
            map = mapOf(1 to true, 2 to false),
            set = setOf(1, 3, 3, 3, 5)
        )

        private val jsonData = """
        {
            "id": 13,
            "user_name": "Test",
            "hasLastName": true,
            "type": "offline",
            "list": [
                        {
                            "id": 555,
                            "name": "Test details",
                            "list": [3, 4, 5, 6, 7]
                        }
                    ],
            "set": [1, 2, 3]
        }
    """.trimIndent()
    }

}