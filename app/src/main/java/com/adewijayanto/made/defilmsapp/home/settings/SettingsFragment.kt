package com.adewijayanto.made.defilmsapp.home.settings

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adewijayanto.made.defilmsapp.databinding.ActivityDetailBinding
import com.adewijayanto.made.defilmsapp.databinding.FragmentMovieBinding
import com.adewijayanto.made.defilmsapp.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private var binding: FragmentSettingsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeLanguageSetting()
    }

    private fun changeLanguageSetting() {
        binding?.groupLanguage?.setOnClickListener {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}