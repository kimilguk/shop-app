package edu.kimilguk.shop.product.list

import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet.PARENT_ID
import edu.kimilguk.shop.R
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.ConstraintSetBuilder.Side.*
import org.jetbrains.anko.constraint.layout.applyConstraintSet
import org.jetbrains.anko.constraint.layout.constraintLayout

/**
 * 최종데이터인 LiveData<PagedList>를 만드는 ProductListItemUI 화면클래스
 */
class ProductListItemUI : AnkoComponent<ViewGroup> {
    //ViewHolder로 출력할 프로퍼티 멤버변수 선언
    lateinit var imageView: ImageView //현재 상품이미지는 더미로 만들어 놓는다
    lateinit var productName: TextView
    lateinit var price: TextView //lateinit(초기화 지연) 사용할지 모르는 데이터를 미리 초기화할 필요가 없다.(메모리 성능향상)

    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return ui.constraintLayout {
            topPadding = dip(20)
            bottomPadding = dip(20)
            clipToOutline = false //둥근모서리 레이아웃 사용안함
            //현재 상품이미지는 더미로 만들어 놓는다(아래)
            imageView = imageView {
                id = View.generateViewId()
                scaleType = ImageView.ScaleType.CENTER_CROP
            }.lparams(dip(80), dip(80))
            productName = textView("-") {
                id = View.generateViewId()
                textSize = 16f
                typeface = Typeface.DEFAULT_BOLD
                textAlignment = TextView.TEXT_ALIGNMENT_TEXT_START
            }.lparams(0, wrapContent)
            price = textView("-") {
                id = View.generateViewId()
                textColorResource = R.color.black
                textSize = 14f
            }
            //id를 가진 뷰들의 상대적인 위치를 지정(아래)
            applyConstraintSet {
                //현재 상품이미지는 더미로 만들어 놓는다(아래)
                imageView.id {
                    connect(
                        TOP to TOP of PARENT_ID, //이미지 위쪽이 부모 컨테이너의 위쪽에 붙는다.
                        START to START of PARENT_ID margin dip(20), //왼쪽 마진 20dip
                        BOTTOM to BOTTOM of PARENT_ID //아래쪽 부모 컨테이너의 아래쪽에 붙는다.
                    )
                }
                productName.id {
                    connect(
                        TOP to TOP of imageView.id margin dip(4),
                        // productName의 위쪽이 imageView 상단을 기준으로 4dip 떨어진다.
                        END to END of PARENT_ID margin dip(20),
                        START to END of imageView.id margin dip(10)
                    )
                }
                price.id {
                    connect(
                        TOP to BOTTOM of productName.id margin dip(4),
                        START to END of imageView.id margin dip(10)
                    )
                }
            }
            //하단에 1dp 짜리 선 만들기(아래-inline 뷰 만들기)
            view {
                backgroundColor = Color.parseColor("#DDDDDD")
            }.lparams(matchParent, dip(1))
        }
    }
}