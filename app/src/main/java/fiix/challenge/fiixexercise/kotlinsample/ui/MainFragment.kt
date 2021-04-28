package fiix.challenge.fiixexercise.kotlinsample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.kotlinsample.adapter.QuestionAnswerAdapter
import fiix.challenge.fiixexercise.kotlinsample.data.model.TriviaQuestion
import fiix.challenge.fiixexercise.kotlinsample.util.TopSpacingItemDecorator
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MainFragment : Fragment(), QuestionAnswerAdapter.Interaction {


    lateinit var recyclerAdapter: QuestionAnswerAdapter

    private val mainViewModel: MainViewModel by sharedViewModel()

    private fun initRecyclerView() {
        mainList.apply {
            layoutManager = LinearLayoutManager(activity)
            val topSpacingItemDecorator = TopSpacingItemDecorator(20)
            addItemDecoration(topSpacingItemDecorator)
            recyclerAdapter = QuestionAnswerAdapter(this@MainFragment)
            adapter = recyclerAdapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress.visibility = View.VISIBLE
        mainViewModel.loadInitialData()
        initRecyclerView()
        mainViewModel.questionAnswers.observe(viewLifecycleOwner, Observer {
            progress.visibility = View.GONE
            recyclerAdapter.submitList(it)
        })
    }

    override fun onItemSelected(position: Int, item: TriviaQuestion) {
        print("Click : $position")
        mainViewModel.getQuestionAnswerDetails(position)
        findNavController().navigate(R.id.action_mainFragment_to_questionDetailFragment)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
}