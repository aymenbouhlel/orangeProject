package com.orangeproject.orangebank.ui.transaction.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.orangeproject.databinding.ItemTransactionBinding
import com.orangeproject.orangebank.business.models.OrangeTransaction

class TransactionListAdapter(
    private val context: Context,
    private val onClickCallback: (OrangeTransaction) -> Unit
) : ListAdapter<OrangeTransaction, RecyclerView.ViewHolder>(
    TaskDiffCallBack()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return createCharacterHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as CharacterViewHolder).apply {
            bindItem(
                item,
                onClickCallback
            )
        }
    }

    private fun createCharacterHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val itemCharacterBinding: ItemTransactionBinding =
            ItemTransactionBinding.inflate(layoutInflater, parent, false)
        return CharacterViewHolder(
            itemCharacterBinding
        )
    }

    class CharacterViewHolder(private val itemCharacterBinding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(itemCharacterBinding.root) {
        fun bindItem(character: OrangeTransaction, onClickCallback: (OrangeTransaction) -> Unit) {
            itemCharacterBinding.item = character
            itemCharacterBinding.card.setOnClickListener {
                onClickCallback.invoke(character)
            }
            itemCharacterBinding.executePendingBindings()
        }
    }

    class TaskDiffCallBack : DiffUtil.ItemCallback<OrangeTransaction>() {
        override fun areItemsTheSame(
            oldItem: OrangeTransaction,
            newItem: OrangeTransaction
        ): Boolean {
            return oldItem.transactionId == newItem.transactionId
        }

        override fun areContentsTheSame(
            oldItem: OrangeTransaction,
            newItem: OrangeTransaction
        ): Boolean {
            return oldItem == newItem
        }
    }
}