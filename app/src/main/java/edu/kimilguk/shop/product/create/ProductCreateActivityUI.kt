package edu.kimilguk.shop.product.create

import android.graphics.Color
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import edu.kimilguk.shop.R
import net.codephobia.ankomvvm.databinding.bindString
import net.codephobia.ankomvvm.databinding.bindStringEntries
import org.jetbrains.anko.*
import org.jetbrains.anko.design.textInputEditText
import org.jetbrains.anko.design.textInputLayout
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.sdk27.coroutines.onItemSelectedListener
import org.jetbrains.anko.sdk27.coroutines.textChangedListener

/**
 * 상품 등록 화면 디자인 구현
 * AnkoComponent<해당액티비티> 상속
 */
class ProductCreateActivityUI(
    private val viewModel: ProductCreateViewModel //데이터바인딩처리 객체생성(주입)
) : AnkoComponent<ProductCreateActivity> {
    override fun createView(ui: AnkoContext<ProductCreateActivity>): View {
        //TODO("레이아웃생성: 세로스크롤 뷰로 감싸주고, 리니어레이아웃 배치")
        return ui.scrollView {
            //상품정보UI(아래)
            verticalLayout {
                padding = dip(20)
                clipToPadding = false //패딩여백 영역자료도 보이기
                //상품 이미지가 보일 리니어레이아웃 추가(나중에 첨부파일 구현 후)
                //상품 정보 타이틀UI(아래)
                textView("상품명 및 설명") {
                    topPadding = dip(40)
                    textSize = 16f
                    textColorResource = R.color.purple_500
                }
                //상품명UI(아래)
                textInputLayout {
                    topPadding = dip(20)
                    textInputEditText {
                        hint = "상품명"
                        setSingleLine()
                        bindString(ui.owner, viewModel.productName)
                        //상품명 글자수 제한 이벤트처리(아래)
                        textChangedListener {
                            onTextChanged { _, _, _, _ ->
                                viewModel.checkProductNameLength()
                            }//람다식에서 파라미터 변수를 사용하지 않을 때 _ 사용
                        }
                    }//textInputEditText
                    textView("0/40") { //상품명 글자수 제한 실시간으로 확인
                        leftPadding = dip(4)
                        textSize = 12f
                        textColorResource = R.color.purple_500
                        bindString(ui.owner, viewModel.productNameLength)
                    }
                }//textInputLayout
                //상품설명UI(아래)
                textInputLayout {
                    topPadding = dip(20)
                    textInputEditText {
                        hint = "상품 설명"
                        maxLines = 6
                        bindString(ui.owner, viewModel.description)
                        textChangedListener {
                            onTextChanged { _, _, _, _ ->
                                viewModel.checkProductDescriptionLength()
                            }//람다식에서 파라미터 변수를 사용하지 않을 때 _ 사용
                        }
                    }//textInputEditText
                    textView("0/500") { //상품명 글자수 제한 실시간으로 확인
                        leftPadding = dip(4)
                        textSize = 12f
                        textColorResource = R.color.purple_500
                        bindString(ui.owner, viewModel.descriptionLength)
                    }
                }//textInputLayout
                //카테고리UI(아래)
                textView("카테고리") {
                    topPadding = dip(40)
                    textSize = 16f
                    textColorResource = R.color.purple_500
                }
                //카테고리 선택 스피너
                verticalLayout {
                    topPadding = dip(12)
                    bottomPadding = dip(12)
                    backgroundColor = 0xEEEEEEEE.toInt()
                    spinner {
                        bindStringEntries(ui.owner, viewModel.categories)
                        onItemSelectedListener {
                            onItemSelected { _, _, i, _ ->
                                viewModel.onCategorySelect(i)
                            }
                        }
                    }
                }.lparams(matchParent) {
                    topMargin = dip(20)
                }//verticalLayout
                //판매가격UI(아래)
                textView("판매 가격") {
                    topPadding = dip(40)
                    textSize = 16f
                    textColorResource = R.color.purple_500
                }
                textInputLayout {
                    topPadding = dip(20)
                    textInputEditText {
                        hint = "Ex) 1000"
                        setSingleLine()
                        inputType = InputType.TYPE_CLASS_NUMBER
                        bindString(ui.owner, viewModel.price)
                    }
                }
                //상품등록 버튼UI(아래)
                button("상품 등록") {
                    backgroundColorResource = R.color.purple_500
                    textColor = Color.WHITE
                    onClick { viewModel.createRequest() }
                }.lparams(matchParent, wrapContent) {
                    topMargin = dip(40)
                }
            }//verticalLayout 상품정보UI 끝
        }
    }
}