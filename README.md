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
- 사용된 기술:
- 구현1: 스프링부트API 실행상태로 안드로이드 앱에서 Retrofit모듈(okhttpt사용)으로 /api/hello 접속시 json 데이터를 안드로이드 콘솔에 출력
- 구현2: Model(API인터페이스) - ViewModel(데이터바인딩) - Activity(화면)
- 구현3: 회원 로그인 액티비티 구현

### 20220323(수) 작업예정
- 상품 등록과 검색 구현

### 20220322(화) 회원로그인 후 웹서버세션 대신에 토큰값을 저장하는 로직 구현
- 로그인 시 토큰정보를 저장하여 여러 액티비티에서 토큰인증이 필요한 스프링부트API로 토큰정보를 보낼 때
- SharedPreferences 클래스객체(txt파일)에 저장된 토큰정보를 사용하는 코딩

### 20220321(월) 회원로그인2차
- SigninViewModel.kt, SigninActivity, SigninActivityUI 순서로 작업한다.

### 20220319(토) 회원로그인1차
- SigninRequestVO.kt, SigninResponseVO.kt, ShopApi(@POST매핑)

### 20220318(금) 회원등록
- MVVM(Model-View-ViewModel)이란?
- Model, View, ViewModel 로 프로그램 구조를 분리해 서로간의 의존성을 줄이고,
- 뷰를 단순화시켜 유지보수성을 높일 수 있는 프로그래밍 패턴이다.
- AnkoMVVM구조는 View(Activity ankoLayout화면) - ViewModel(데이터바인딩) - Model(API인터페이스)
- build.gradle 에 AnkoMVVM 템플릿 모듈추가
- ankoMVVM 템플릿구성: Activity(Fragment화면위화면구성), ViewModel + UI(xml이 아닌 AnkoLayout코드로레이아웃구성)
- 작업순서: build.gradle(ankoMVVM 모듈추가)-VO(앱클래스간 데이터전송시 임시저장클래스)-ViewModel(서비스로직을 실행하는 클래스)-Activity(화면띄우는 클래스)-ActivityUI(xml대신 화면객채생성 클래스)
- 작업내역: build.gradle(ankoMVVM 모듈추가)-SignupVO(앱클래스간 데이터전송시 임시저장클래스)-SignupViewModel(서비스로직을 실행하는 클래스)-SignupActivity(화면띄우는 클래스)-SignupActivityUI(xml대신 화면객채생성 클래스)-IntroActivity.kt에서 스레드 1초 대기 후 회원가입 액티비티(SignupActivity) 띄우기 코드추가

### 20220316(수) 스프링부트와 안드로이드 앱 연동
- 스프링부트API 실행상태로 안드로이드 앱에서 Retrofit모듈(okhttpt사용)으로 /api/hello 접속시 json 데이터를 안드로이드 콘솔에 출력