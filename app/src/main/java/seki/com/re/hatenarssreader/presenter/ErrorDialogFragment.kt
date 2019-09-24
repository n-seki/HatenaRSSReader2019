package seki.com.re.hatenarssreader.presenter

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import seki.com.re.hatenarssreader.R
import seki.com.re.hatenarssreader.data.Error

class ErrorDialogFragment : DialogFragment() {

    companion object {
        private const val TITLE = "title"
        private const val MESSAGE = "message"

        fun newInstance(error: Error): DialogFragment {
            return ErrorDialogFragment().apply {
                arguments = bundleOf(
                    TITLE to error.title,
                    MESSAGE to error.message
                )
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
                builder.setTitle(arguments?.getString(TITLE))
                    .setMessage(arguments?.getString(MESSAGE))
                    .setNeutralButton(R.string.close) { dialog, _ ->
                        dialog.dismiss()
                    }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}