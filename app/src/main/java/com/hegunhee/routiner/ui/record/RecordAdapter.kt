package com.hegunhee.routiner.ui.record

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hegunhee.routiner.R
import com.hegunhee.routiner.data.entity.Routine
import com.hegunhee.routiner.databinding.RecordItemBinding

class RecordAdapter(
    private var recordRoutineList : List<Routine>
) : RecyclerView.Adapter<RecordAdapter.RecordViewHolder>(){

    inner class RecordViewHolder(private val binding : RecordItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bindView(routine: Routine) = with(binding){
            title.text = routine.text
            root.backgroundTintList = if(routine.isFinished){
                ColorStateList.valueOf(ContextCompat.getColor(root.context,R.color.success_color))
            }else{
                ColorStateList.valueOf(ContextCompat.getColor(root.context,R.color.fail_color))
            }
            if(routine.category ==""){

            }else{
                category.visibility = View.VISIBLE
                category.text = routine.category
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        return RecordViewHolder(RecordItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.bindView(recordRoutineList[position])
    }

    override fun getItemCount(): Int {
        return recordRoutineList.size
    }

    fun setList(recordList : List<Routine>){
        this.recordRoutineList = recordList
        notifyDataSetChanged()
    }
}