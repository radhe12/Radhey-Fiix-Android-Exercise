package fiix.challenge.fiixexercise.kotlinsample.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.kotlinsample.util.hideKeyboard
import kotlinx.android.synthetic.main.fragment_question_detail.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates


class QuestionDetailFragment() : Fragment() {

    private val mainViewModel by sharedViewModel<MainViewModel>()
    private var questionIndex by Delegates.notNull<Int>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question_detail, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        mainViewModel.selectedQuestion.observe(viewLifecycleOwner, Observer {
            if(it!= null) {
            question_et.setText(it.first?.question)
            answer_et.setText(it.first?.answer)
            questionIndex = it.second
            }
        })
    }

    private fun backButtonClick() {
        mainViewModel.updateQAndA(questionIndex, question_et.text.toString(), answer_et.text.toString())
        hideKeyboard()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                backButtonClick()
                findNavController().navigateUp()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }


}