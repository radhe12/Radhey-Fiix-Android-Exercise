package fiix.challenge.fiixexercise.kotlinsample.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.kotlinsample.data.model.TriviaQuestion
import kotlinx.android.synthetic.main.question_custom_row_layout.view.*

class QuestionAnswerAdapter(private val interaction: Interaction? = null) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TriviaQuestion>() {

        override fun areItemsTheSame(oldItem: TriviaQuestion, newItem: TriviaQuestion): Boolean {
            return oldItem.question == newItem.question
        }

        override fun areContentsTheSame(oldItem: TriviaQuestion, newItem: TriviaQuestion): Boolean {
            return oldItem.question == newItem.question && oldItem.answer == newItem.answer
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return TriviaViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.question_custom_row_layout,
                        parent,
                        false
                ),
                interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TriviaViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<TriviaQuestion>) {
        differ.submitList(list)
    }

    class TriviaViewHolder
    constructor(
            itemView: View,
            private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: TriviaQuestion) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(bindingAdapterPosition, item)
            }

            itemView.question.text = item.question

            itemView.answer_btn.setOnClickListener {
                itemView.answer_btn.visibility = View.GONE
                itemView.answer_text.visibility = View.VISIBLE
                itemView.answer_text.text = item.answer
            }
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: TriviaQuestion)
    }
}