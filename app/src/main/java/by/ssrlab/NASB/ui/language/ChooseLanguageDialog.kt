package by.ssrlab.NASB.ui.language

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import by.ssrlab.NASB.MainActivity
import by.ssrlab.NASB.data.Language
import by.ssrlab.NASB.databinding.DialogLangBinding
import java.util.*

class ChooseLanguageDialog : DialogFragment() {
    private var _binding: DialogLangBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = DialogLangBinding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.btnBel.setOnClickListener {
            (requireActivity() as MainActivity).setLanguage(Language.BEL)
            (requireActivity() as MainActivity).changeLang(Language.BEL.locale)
            dismiss()
        }

        binding.btnEng.setOnClickListener {
            (requireActivity() as MainActivity).setLanguage(Language.ENG)
            (requireActivity() as MainActivity).changeLang(Language.ENG.locale)
            dismiss()
        }
        return root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}