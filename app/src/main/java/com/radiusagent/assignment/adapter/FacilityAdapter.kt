package com.radiusagent.assignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.radiusagent.assignment.R
import com.radiusagent.assignment.databinding.ItemFacilitesBinding
import com.radiusagent.assignment.model.ExclusionsModel
import com.radiusagent.assignment.model.FacilitiesModel
import com.radiusagent.assignment.model.OptionsModel
import com.radiusagent.assignment.utils.Tools.Companion.getDrawableByName


class FacilityAdapter(
    val context: Context,
    var list: List<FacilitiesModel>,
    var exclusionlist: List<List<ExclusionsModel>>,
) :
    RecyclerView.Adapter<FacilityAdapter.MyViewHolder>() {
    var selected_options=ArrayList<String>()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_facilites, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = list[position]

        holder.binding.txtTitle.text = data.name

        val chipGroup = ChipGroup(context)
        chipGroup.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        chipGroup.isSingleSelection = true
        chipGroup.isSingleLine = false
        chipGroup.isSelectionRequired=true

        data.options.forEach {
            addToAllChip(it,chipGroup)
        }
        holder.binding.chipsOptions.addView(chipGroup)


    }
    fun addToAllChip(option: OptionsModel, chipGroup: ChipGroup) {
        val chip = Chip(context, null, com.google.android.material.R.style.Widget_MaterialComponents_Chip_Action)
        chip.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        chip.chipIcon=getDrawableByName(context,option.icon)
        chip.closeIcon=ContextCompat.getDrawable(context,R.drawable.baseline_check_24)
        chip.checkedIcon=null
        chip.text = option.name
        chip.isChipIconVisible = true
        chip.isCheckable = true
        chip.isClickable = true

        chip.setOnCheckedChangeListener { compoundButton, b ->
            if (checkExclution(option.id) && b) {
                chip.isCloseIconVisible=true
                selected_options.add(option.id)
                chip.isChecked=true
            }else{
                selected_options.remove(option.id)
                chip.isCloseIconVisible=false
                chip.isChecked=false
            }
        }

        chipGroup.addView(chip)
    }

    private fun checkExclution(current_id: String): Boolean {
        exclusionlist.forEach {
            val json=Gson().toJson(it)
            val sType = object : TypeToken<List<ExclusionsModel>>() { }.type
            val array=Gson().fromJson<List<ExclusionsModel>>(json,sType)
            if (selected_options.contains(array[0].options_id) && array[1].options_id.equals(current_id)){
                Toast.makeText(context, "You can’t select these options together.", Toast.LENGTH_SHORT).show()
                return false
            }
            if (selected_options.contains(array[1].options_id) && array[0].options_id.equals(current_id)){
                Toast.makeText(context, "You can’t select these options together.", Toast.LENGTH_SHORT).show()
                return false
            }
        }
        return true
    }


    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var binding: ItemFacilitesBinding

        init {
            binding = ItemFacilitesBinding.bind(view!!)
        }
    }
}