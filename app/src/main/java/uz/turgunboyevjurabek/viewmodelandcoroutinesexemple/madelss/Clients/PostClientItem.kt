package uz.turgunboyevjurabek.viewmodelandcoroutinesexemple.madelss.Clients


import com.google.gson.annotations.SerializedName

data class PostClientItem(
    @SerializedName("fam")
    val fam: String,
    @SerializedName("ism")
    val ism: String,
    @SerializedName("manzil")
    val manzil: String,
    @SerializedName("tel")
    val tel: String,
    @SerializedName("umumiy_summa")
    val umumiySumma: Int
)