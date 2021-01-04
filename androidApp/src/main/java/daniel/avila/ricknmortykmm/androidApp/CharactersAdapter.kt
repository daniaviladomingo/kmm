package daniel.avila.ricknmortykmm.androidApp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import daniel.avila.ricknmortykmm.androidApp.databinding.CharacterItemBinding

class CharactersAdapter(
    private val onClickCharacter: (Character) -> Unit
) : ListAdapter<Character, CharactersAdapter.ViewHolder>(DiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_item, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
        holder.itemView.setOnClickListener { onClickCharacter(character) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CharacterItemBinding.bind(view)

        fun bind(character: Character) = with(binding) {
            Glide.with(image).load(character.image).into(image)
            name.text = character.name
        }
    }
}

private object DiffUtilCallback : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
        oldItem == newItem
}



