package edu.kimilguk.shop.product.list

import android.view.ViewGroup
import android.widget.AdapterView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import edu.kimilguk.shop.api.ApiGenerator
import edu.kimilguk.shop.api.response.ProductListItemResponse
import edu.kimilguk.shop.common.LiveDataPagedListBuilder
import edu.kimilguk.shop.product.ProductStatus
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.text.NumberFormat


/**
 * 리사이클러뷰에 들어갈 상하스크롤 시 데이터를 구현한 어댑터 클래스
 * PagedListAdapter 를 상속한 클래스
 * ViewHolder 를 상속한 ProductItemViewHolder 내부클래스 사용
 */
class ProductListPagedAdapter( //1
    private val listener: OnItemClickListener
    //PagedListAdapter 클래스는 동일한 데이터의 경우 뷰를 다시 그리지 않고, 기존 뷰를 가져올 때, 데이터 변동이 있는지 검사하기 위한 콜백(자동실행) 메서드를 구현한다.
) : PagedListAdapter<ProductListItemResponse, ProductListPagedAdapter.ProductItemViewHolder>(DIFF_CALLBACK){ //2

    companion object {//8 static 대신 companion 을 사용, object는 싱글톤 클래스로 실행과 동시에 1개의 객체가 생성된다.
    val DIFF_CALLBACK = //위 PagedListAdapter에서 사용할 뷰홀더 데이터 변동 검사 기능의 콜백변수
        object : DiffUtil.ItemCallback<ProductListItemResponse>() {
            override fun areItemsTheSame(
                oldItem: ProductListItemResponse,
                newItem: ProductListItemResponse,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ProductListItemResponse,
                newItem: ProductListItemResponse,
            ): Boolean {
                return oldItem.toString() == newItem.toString()
            }

        }
    }
    //5 ProductListItemUI 디자인에 데이터를 바인딩 시키는 뷰홀더 클래스이다(아래)
    class ProductItemViewHolder(
        parent: ViewGroup,
        private val listener: OnItemClickListener,
        private val ui: ProductListItemUI = ProductListItemUI()
    ) : RecyclerView.ViewHolder(
        ui.createView(AnkoContext.create(parent.context, parent))
    ){
        //7 onBindViewHolder 콜백에서 사용, 응답 item이 있다면 let실행
        fun bind(item: ProductListItemResponse?) = item?.let {
            this.productId = item.id
            val soldOutString =
                if(ProductStatus.SOLD_OUT == item.status) {
                    "{품절}"
                }else{
                    ""
                }
            val commaSeparatedPrice = NumberFormat.getNumberInstance().format(item.price)
            ui.productName.text = item.name
            ui.price.text = "\$commaSeparatedPrice $soldOutString"
            //상품 이미지는 나중에 사용예정(아래)
            Glide.with(ui.imageView)
                .load("${ApiGenerator.HOST}${item.imagePaths.firstOrNull()}")
                .centerCrop()
                .into(ui.imageView)
        }
        var productId: Long? = null
        init {
            itemView.onClick { listener.onClickProduct(productId) }
            //6 위 ui의 클릭 시 리스너를 이용해 productId의 상품을 보여주는 함수를 실행한다.
        }
    }
    //9 productId의 상품을 보여주는 인터페이스 함수
    interface OnItemClickListener {
        fun onClickProduct(productId: Long?)
    }
    //3 아래 뷰홀더 콜백함수는 리사이클러뷰가 새 뷰홀더를 요구할 때 자동으로 실행된다.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        return ProductItemViewHolder(parent, listener)
    }
    //4 아래 바인드뷰홀더 콜백함수는 리사이클러뷰가가 특정 위치의 데이터를 출력해주려 할 때 실행된다.
    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        //ProductItemViewHolder에서 가지고 있는 itemView에 데이터를 표시해 준다.
        holder.bind(getItem(position)) //ProductItemViewHolder의 bind함수를 호출한다.
    }
    //10 앞서 만든 LiveDataPagedListBuilder를 상속해 상품 목록에 필요한 LiveData<PagedList<ProductListItemResponse>>를 생성해줄 인터페이스(아래)
    interface ProductLiveDataBuilder : LiveDataPagedListBuilder<Long, ProductListItemResponse>
}