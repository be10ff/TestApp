package ru.be10ff.testapp.ui.main

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_full_screen.*
import ru.be10ff.testapp.R
import ru.be10ff.testapp.domain.model.Item


private const val ARG_PARAM_BITMAP = "bitmap_param"

/**
 * A simple [Fragment] subclass.
 * Use the [FullScreenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FullScreenFragment : Fragment() {

    private var bitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            bitmap = it.getParcelable<Bitmap>(ARG_PARAM_BITMAP)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_full_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ivFull.setImageBitmap(bitmap)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment FullScreenFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(bitmap: Bitmap) =
            FullScreenFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM_BITMAP, bitmap)
                }
            }
    }
}