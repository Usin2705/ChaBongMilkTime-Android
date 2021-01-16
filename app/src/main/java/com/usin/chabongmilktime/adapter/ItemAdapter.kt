package com.usin.chabongmilktime.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.usin.chabongmilktime.R
import com.usin.chabongmilktime.model.MilkTime
import com.usin.chabongmilktime.databinding.ItemListBinding

/**
 * Adapter for the [RecyclerView] in MainActivity. Displays [MilkTime] data object.
 */
class ItemAdapter(
    private val context: Context,
    private val dataSet: List<MilkTime>
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just an Affirmation object.
    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemListBinding.bind(view)
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.item_list, parent, false)
        )

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.txtMilkTime.text = dataSet[position].stringMilkTime
            if (dataSet[position].intMilkAmount > 0)
                binding.txtMilkAmount.text = holder.itemView.context.getString(
                        R.string.text_milk_amount, dataSet[position].intMilkAmount)
            else
                binding.txtMilkAmount.text = ""

            binding.imgDiaper.visibility =
                if (dataSet[position].isDiaperChange) View.VISIBLE else View.INVISIBLE

            binding.imgVitamind.visibility =
                if (dataSet[position].isVitaminD) View.VISIBLE else View.INVISIBLE
        }
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    override fun getItemCount() = dataSet.size

}