package uz.turgunboyevjurabek.viewmodelandcoroutinesexemple.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import uz.turgunboyevjurabek.viewmodelandcoroutinesexemple.databinding.ItemRvBinding
import uz.turgunboyevjurabek.viewmodelandcoroutinesexemple.madels.GetClients
import uz.turgunboyevjurabek.viewmodelandcoroutinesexemple.madels.GetClientsItem

class RvAdapter(val list:GetClients):RecyclerView.Adapter<RvAdapter.Vh>() {
    inner class Vh(val itemRvBinding: ItemRvBinding):ViewHolder(itemRvBinding.root){
        fun onBind(getClientsItem: GetClientsItem){
            Glide.with(itemRvBinding.root.context)
                .load(getClientsItem.clientRasm)
                .into(itemRvBinding.itemImg)

            itemRvBinding.itemName.text=getClientsItem.ism
            itemRvBinding.itemLastName.text=getClientsItem.fam
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }
}