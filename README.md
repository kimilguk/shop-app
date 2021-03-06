### 책 [코틀린으로 쇼핑몰앱 만들기] 소스를 분석 하고 있습니다.
- 기술참조: 코틀린으로 쇼핑몰 앱 만들기(터닝포인트출판사:이현석 저자)
- 소스참조(스프링부트API): https://github.com/benimario/parayo-api
- 소스참조(안드로이드앱): https://github.com/benimario/parayo-android
- 신규작업(스프링부트API) : https://github.com/kimilguk/shop-api.git
- 신규작업(안드로이드앱) : https://github.com/kimilguk/shop-app.git
- 인텔리J 에서 깃 암호 저장하지 않게 설정(아래)
- https://stackoverflow.com/questions/28142361/change-remote-repository-credentials-authentication-on-intellij-idea-14
- 작업기간: 00일 ( 20220000 ~ 20220000 )

### 학습목차

### 안드로이드 앱(shop-app) 프로젝트에서 구현된 내역(아래)
- 개발툴: 안드로이드 스튜디오 범블비, 빌드툴버전: 그래들7.2, 코틀린 1.5.30, JVM 1.8
- 사용된 기술: 기본 로그 수준은 INFO입니다. 그래서, AnkoLogger 사용시 info()함수를 사용
- 구현1: 스프링부트API 실행상태로 안드로이드 앱에서 Retrofit모듈(okhttpt사용)으로 /api/hello 접속시 json 데이터를 안드로이드 콘솔에 출력
- 구현2: Model(API인터페이스) - ViewModel(데이터바인딩) - Activity(화면)
- 구현3: 회원 로그인 액티비티 구현

### 20220604(토) 상품 상세 내용 출력 작업예정
- product/detail 패키지 내용 추가

### 20220603(금) 상품 리스트 내용 출력용 RecyclerView(상하스크롤) 만들기4
- ProductListUI.kt 생성: ProductListFragment + 리사이클러 뷰 로 구성된 앙코 UI클래스
- ProductListFragment.kt 의 fun onCreateView 에서 ProductListUI 사용

### 20220602(목) 상품 리스트 내용 출력용 RecyclerView(상하스크롤) 만들기3
- RecyclerView에 ProductListPagedAdapter를 바인딩하는 ProductListViewModel 수정.
- ProductDetailActivity.kt 액티비티인 상품상세 페이지는 나중에 작업예정

### 20220601(수) 상품 리스트 내용 출력용 RecyclerView(상하스크롤) 만들기2
- 전처리, 스프링부트 API 에서 정의한 상품 판매 상태값 나열값을 정의한 ProductStatus.kt 싱글톤 상수생성
- 리사이클러뷰에 들어갈 상하스크롤 시 데이터를 구현한 ProductListPagedAdapter 어댑터클래스 생성(아래) 
- 작업1, 리사이클러뷰에 들어갈 상하스크롤 시 데이터를 구현한 페이지드리스트어댑터 클래스
- 작업2, 상품이미지를 어댑터뷰홀더에 바인딩 시키기 위한 bumptech.glide 외부 모듈 추가

### 20220531(화) 상품 리스트 내용 출력용 RecyclerView(상하스크롤) 만들기1
- 앱수준의 build.gradle에 리사이클러뷰와 페이징 라이브러러 사용 의존성 모듈추가
- 스프링부트에서 반환받은 상품값을 안드로이드에서 처리하는 api를 api/response/ProductListItemResponse.kt 생성
- 스프링부트API 쇼핑 기능에 접근하는 인터페이스인 ShopApi에 @GET으로 상품리스트에 접근하는 코드 추가
- 필요한 기능 클래스: ViewHolder(뷰유지), RecyclerView.Adapter -> PagedListAdapter(페이징구현)
- 페이징 기능 클래스: PageKeyedDataSource(초기데이터, next데이터, prev데이터)
- 위 클래스를 상속한 ProductListItemDataSource 클래스 생성.
- 페이징 데이터 빌더 인터페이스: LiveDataPagedListBuilder(최종데이터인 LiveData<PagedList>를 만드는 인터페이스)
- 최종데이터인 LiveData<PagedList>를 만드는 ProductListItemUI 클래스 생성.

### 20220530(월) 상품 리스트 기본 틀 만들기(스프링부트API먼저 작업 후)
- 작업1: ProductMainActivityUI 상품메인액티비티에 카테고리 탭과 상품리스트 뷰페이저 자바디자인 추가
- 작업2: MVVM틀(ViewModel,Activity,UI) 중 ProductListViewModel.kt 상품리스트 클릭 틀 생성
- 작업3: 뷰페이저(좌우스크롤)에 들어갈 상품 리스트를 출력해주는 ProductListFragment를 생성.
- 작업4: 뷰페이저에 들어갈 데이터를 구현한 ProductListPagerAdapter 뷰페이저 어댑터 클래스
- 작업5: ProductMainActivity 에 뷰페이저어댑터객체 생성 후 카테고리 탭레이아웃과 뷰페이저 연동

### 20220403(일) 상품 등록 MVVM 클래스 구성4(권한)
- ApiTokenInterceptor.kt 추가: 스프링부트API로 보내는 전송헤더에 토큰값을 추가하는 클래스
- TokenRefreshInterceptor.kt 추가: 토큰갱신 요청 전에 refreshToken 을 추가하고
-- 토큰갱신 요청의 응답 코드가 401(권한없음)인 경우 로그인 화면으로 이동시켜주는 클래스
- ApiGenerator.kt 수정: 토큰갱신요청 API 인터셉터(가로채기) 추가
- TokenRefreshApi.kt 추가: 스프링부트API 리프레시 토큰 기능에 접근하는 인터페이스
- TokenAuthenticator.kt 추가: 스프링부트API 응답코드가 401(권한없음)인 경우
-- SharedPreferences객체에 저장된 refreshToken정보로 토큰갱신 후 새 request 반환
- ApiGenerator.kt 수정: 모든 httpClient()에 토큰갱신이 필요할 때 인증함수에 토큰인증 클래스를 추가

### 20220402(토) 상품 등록 MVVM 클래스 구성3(UI)
- ShopApi.kt 수정: 스프링부트의 ProductApiController 와 매칭되는 상품등록경로추가
- ProductCreateViewModel.kt 수정: 상품등록 비지니스 로직함수 시작
- ProductCreateActivityUI.kt 수정: 상품등록 UI 추가.

### 20220401(금) 상품 등록 MVVM 클래스 구성2(기본MVVM틀) 순서
- ProductCreateViewModel.kt 추가: 초기 빈 클래스 제작
- ProductCreateActivity.kt 추가: 액션바 코딩만 추가(AndroidManifest에 액션바 테마추가)
- ProductCreateActivityUI.kt 추가: 초기 레이아웃만 구성 후 액티비티.kt에서 디자인 UI와 매칭 추가

### 20220331(목) 상품 등록 MVVM 클래스 구성1(VO): 뷰모델에서 데이터와 바인딩 처리
- CategoryVO.kt: 상품카테고리는 숫자이고, 숫자와 매칭되는 카테고리명을 가지는 데이터클래스
- ProductCreateVO.kt: 스프링부트 API 와 입출력시 데이터를 임시 저장한다.

### 20220330(수) 상품 메인 MVVM 클래스 구성2 순서
- ProductMainActivityUI.kt 수정: 네비게이션 드로우어에 메뉴 추가
- ProductMainNaviHeader.kt 추가: ProductMainActivityUI의 navigationView{} 에 addHeaderView() 추가
- ProductMainActivityUI.kt 수정: 로그아웃 구현
- ProductMainActivityUI.kt 수정: 상품등록 버튼 추가

### 20220329(화) 상품 메인 MVVM 클래스 구성1 순서
- ProductMainViewModel.kt 추가: 상품 진입 로직을 실행하는 클래스
- ProductMainActivity.kt 추가: 상품 메인레이아웃 클래스(AndroidManifest.xml에 등록)
- ProductMainActivityUI.kt 추가: 상품 메인 화면 디자인 구현
- IntroActivity.kt 수정: SharedPreferences에 등록된 토큰이 있다면 상품메인화면으로 이동 추가
- SigninViewModel.kt 수정: 로그인 후 현재화면 종료 및 상폼 메인화면으로 입장 추가
- SignupViewModel.kt 수정: finishActivity() //회원가입 후 현재 화면 제거 코딩 추가
- 드로우어 레이아웃에 툴바에 검색메뉴 및 햄버거 버튼 추가

### 20220322(화) 회원로그인 후 웹서버세션 대신에 토큰값을 저장하는 로직 구현
- 로그인 시 토큰정보를 저장하여 여러 액티비티에서 토큰인증이 필요한 스프링부트API로 토큰정보를 보낼 때
- SharedPreferences 클래스객체(txt파일)에 저장된 토큰정보를 사용하는 코딩

### 20220321(월) 회원로그인2차 순서
- SigninViewModel.kt, SigninActivity, SigninActivityUI 순서로 작업한다.

### 20220319(토) 회원로그인1차 순서
- SigninRequestVO.kt, SigninResponseVO.kt, ShopApi(@POST매핑)

### 20220318(금) 회원등록
- MVVM(Model-View-ViewModel)이란?
- Model(API/DB데이터), View(UI), ViewModel(로직서비스) 로 프로그램 구조를 분리해 서로간의 의존성을 줄이고,
- 뷰를 단순화시켜 유지보수성을 높일 수 있는 프로그래밍 패턴이다.
- AnkoMVVM구조는 View(Activity ankoLayout화면) - ViewModel(데이터바인딩) - Model(API인터페이스)
- build.gradle 에 AnkoMVVM 템플릿 모듈추가
- ankoMVVM 템플릿구성: Activity(Fragment화면위화면구성), ViewModel + UI(xml이 아닌 AnkoLayout코드로레이아웃구성)
- 작업순서: build.gradle(ankoMVVM 모듈추가)-VO(앱클래스간 데이터전송시 임시저장클래스)-ViewModel(서비스로직을 실행하는 클래스)-Activity(화면띄우는 클래스)-ActivityUI(xml대신 화면객채생성 클래스)
- 작업내역: build.gradle(ankoMVVM 모듈추가)-SignupVO(앱클래스간 데이터전송시 임시저장클래스)-SignupViewModel(서비스로직을 실행하는 클래스)-SignupActivity(화면띄우는 클래스)-SignupActivityUI(xml대신 화면객채생성 클래스)-IntroActivity.kt에서 스레드 1초 대기 후 회원가입 액티비티(SignupActivity) 띄우기 코드추가

### 20220316(수) 스프링부트와 안드로이드 앱 연동
- 스프링부트API 실행상태로 안드로이드 앱에서 Retrofit모듈(okhttpt사용)으로 /api/hello 접속시 json 데이터를 안드로이드 콘솔에 출력