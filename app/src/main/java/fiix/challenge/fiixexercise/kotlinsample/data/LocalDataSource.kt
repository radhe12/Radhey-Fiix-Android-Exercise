package fiix.challenge.fiixexercise.kotlinsample.data

import fiix.challenge.fiixexercise.dp.DataSource
import fiix.challenge.fiixexercise.kotlinsample.data.model.TriviaQuestion

class LocalDataSource(private val mockRepo: MockRepo) : DataSource{
    override fun getData(): List<String> {
        return listOf(
                "7",
                "Dr. Seuss",
                "A Study in Scarlet",
                "88",
                "1986",
                "My Neighbor Totoro",
                "True",
                "San Fransokyo",
                "Camp Crystal Lake",
                "32",
                "10",
                "Crete"
        )
    }

    override fun getQuestions(): List<TriviaQuestion> {
        return mockRepo.triviaQuestions
    }
}