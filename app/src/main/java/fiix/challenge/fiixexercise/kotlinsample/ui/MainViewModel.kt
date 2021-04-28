package fiix.challenge.fiixexercise.kotlinsample.ui

import android.content.ContentResolver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.kotlinsample.data.model.TriviaQuestion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val dataProcessor: DataProcessor): ViewModel() {

    var questionAnswers = MutableLiveData<List<TriviaQuestion>>()
    var selectedQuestion = MutableLiveData<Pair<TriviaQuestion?, Int>>()
    var initialQuestionList: List<TriviaQuestion> = arrayListOf()

    private suspend fun setQuestionAnswers() = withContext(Dispatchers.IO) {
        if(initialQuestionList.isNullOrEmpty()) {
            initialQuestionList = dataProcessor.getQuestions()
            val answers = dataProcessor.getAnswers()

            for (i in initialQuestionList.indices) {
                    initialQuestionList[i].answer = answers[i]
            }
        }
        withContext(Dispatchers.Main) {
            questionAnswers.value = initialQuestionList
        }
    }

    fun loadInitialData() {
        viewModelScope.launch {
            setQuestionAnswers()
        }
    }

    fun getQuestionAnswerDetails(questionIndex: Int) {
        selectedQuestion.value = Pair(questionAnswers.value?.get(questionIndex), questionIndex)
    }

    fun updateQAndA(questionIndex: Int, question: String?, answer: String?) {

        questionAnswers.value?.get(questionIndex)?.question = question!!
        questionAnswers.value?.get(questionIndex)?.answer = answer!!

    }

}