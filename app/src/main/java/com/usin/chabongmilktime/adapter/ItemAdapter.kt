package com.usin.chabongmilktime.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.usin.chabongmilktime.CellClickListener
import com.usin.chabongmilktime.MainActivity
import com.usin.chabongmilktime.R
import com.usin.chabongmilktime.model.MilkTime
import com.usin.chabongmilktime.databinding.ItemListBinding
import com.usin.chabongmilktime.utils.Const

/**
 * Adapter for the [RecyclerView] in MainActivity. Displays [MilkTime] data object.
 */
class ItemAdapter(
    private val context: Context,
    private val dataSet: ArrayList<MilkTime>,
    private val cellClickListener: CellClickListener
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just an Affirmation object.
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
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
            binding.txtMilkTime.text = Const.shortDateFormat.format(dataSet[position].time)
            if (dataSet[position].amount!! > 0)
                binding.txtMilkAmount.text = holder.itemView.context.getString(
                    R.string.text_milk_amount, dataSet[position].amount
                )
            else
                binding.txtMilkAmount.text = ""

            binding.imgDiaper.visibility =
                if (dataSet[position].diaper!!) View.VISIBLE else View.INVISIBLE

            binding.imgVitamind.visibility =
                if (dataSet[position].vitaminD!!) View.VISIBLE else View.INVISIBLE

            binding.lnItemList.setOnClickListener {
                cellClickListener.onCellClickListener(dataSet[position])
            }
        }
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    override fun getItemCount() = dataSet.size

}