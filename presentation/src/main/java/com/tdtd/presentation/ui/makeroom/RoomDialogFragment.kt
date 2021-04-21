package com.tdtd.presentation.ui.makeroom

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tdtd.presentation.R
import com.tdtd.presentation.databinding.RoomBottomSheetBinding
import com.tdtd.presentation.ui.recordvoice.RecordVoiceDialogFragment
import com.tdtd.presentation.ui.writetext.WriteTextDialogFragment
import com.tdtd.presentation.util.Constants
import com.tdtd.presentation.util.dpToPx
import com.tdtd.presentation.util.getBottomNavigationBarHeight
import com.tdtd.presentation.util.initParentHeight

class RoomDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: RoomBottomSheetBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.room_bottom_sheet, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), theme).apply {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.peekHeight = 0
            behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                        behavior.state = BottomSheetBehavior.STATE_HIDDEN
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                }
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initParentHeight(requireActivity(), view)
        setBottomSheetPadding(view)
        setRoomEditFocus()
        setRoomEditView()
    }

    private fun setRoomEditFocus() {
        binding.roomNameEditView.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                binding.roomNameEditView.hint = null
                view.setBackgroundResource(R.drawable.background_beige2_stroke1_gray2_radius16)
            } else {
                binding.roomNameEditView.hint = getString(R.string.room_name_hint_title)
                view.setBackgroundResource(R.drawable.background_beige2_stroke1_beige3_radius16)
            }
        }
    }

    private fun setRoomEditView() {
        binding.roomNameEditView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.textNumberTextView.text =
                        getString(R.string.initial_and_max_input_number, s?.length)

                if (s!!.isNotEmpty()) {
                    onClickVoice()
                    onClickText()
                } else emptyRoomNameEdit()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun emptyRoomNameEdit() {
        binding.apply {
            voiceImageView.isEnabled = false
            textImageView.isEnabled = false
            makeRoomButton.isEnabled = false
            voiceImageView.setBackgroundResource(R.drawable.background_beige2_stroke1_beige3_radius16)
            textImageView.setBackgroundResource(R.drawable.background_beige2_stroke1_beige3_radius16)
            makeRoomButton.setBackgroundResource(R.drawable.background_grayscale1_radius12)
        }
    }

    private fun onClickVoice() {
        binding.voiceImageView.isEnabled = true

        binding.voiceImageView.setOnClickListener {
            it.setBackgroundResource(R.drawable.background_beige2_stroke1_gray2_radius16)
            binding.textImageView.setBackgroundResource(R.drawable.background_beige2_stroke1_beige3_radius16)
            binding.makeRoomButton.apply {
                isEnabled = true
                setBackgroundResource(R.drawable.backgroud_grayscale1_radius12_click)
                setOnClickListener {
                    showRecordVoiceDialogFragment()
                }
            }
        }
    }

    private fun onClickText() {
        binding.textImageView.isEnabled = true

        binding.textImageView.setOnClickListener {
            it.setBackgroundResource(R.drawable.background_beige2_stroke1_gray2_radius16)
            binding.voiceImageView.setBackgroundResource(R.drawable.background_beige2_stroke1_beige3_radius16)
            binding.makeRoomButton.apply {
                isEnabled = true
                setBackgroundResource(R.drawable.backgroud_grayscale1_radius12_click)
                setOnClickListener {
                    showWriteTextDialogFragment()
                }
            }
        }
    }

    private fun showRecordVoiceDialogFragment() {
        findNavController().navigate(R.id.action_roomDialogFragment_to_recordVoiceDialogFragment)
    }

    private fun showWriteTextDialogFragment() {
        findNavController().navigate(R.id.action_roomDialogFragment_to_writeTextDialogFragment)
    }


    private fun setBottomSheetPadding(view: View) {
        if (getBottomNavigationBarHeight(view) < Constants.BOTTOM_NAVIGATION_HEIGHT) {
            binding.roomBottomSheet.setPadding(dpToPx(view, 16), dpToPx(view, 16), dpToPx(view, 24), dpToPx(view, 32))
        }
    }
}
