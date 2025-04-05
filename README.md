# Routiner  [앱링크](https://play.google.com/store/apps/details?id=com.hegunhee.routiner)
## 2022.06.21 ~ 2022.07.11(앱출시) 지속적으로 업데이트중  
## 소개  
루티너는 습관을 등록하고 나중에 확인할 수 있는 앱입니다.  
## 사용된 라이브러리  
* JetPack  
  * AAC-ViewModel  
  * Room
  * Compose  
* Coroutine  
  * Flow  
* Hilt  
* SharedPreference  
## 기술 정보  
* **Multi-Module** 적용  
 Android Clean Architecture에 따라 data, domain, presentation layer로 분류하였습니다.  
 Multi-Module을 사용하므로서 의존성을 확실히 분리할 수 있고 layer분리를 시각적으로 볼 수 있습니다.  
 빌드 시간을 줄일 수 있습니다.  
* **AAC-ViewModel** 적용  
 Activity나 Fragment에 보여줄 data를 가지고있습니다.  
 Activity나 Fragment가 화면 회전 등의 이유로 Destroy 상태가 되어도  
 AAC-ViewModel은 재 생성되지 않기때문에 데이터를 쉽게 관리할 수 있습니다.
 그리고 ViewModelScope를 사용해 비동기 코드를 쉽게 작성할 수 있습니다.  
* **Flow** 적용  
  UI State로 Flow 사용중 안드로이드 의존성을 줄일 수 있고 다양한 연산자를 지원해  
  더 다양한 연산을 사용 가능   
* **Jetpack Compose** 적용
  선언형 UI인 Jetpack Compose를 도입해서 코드수를 줄이고 xml과 Kotlin 코드를 번갈아가면서 사용하지않아도 됨
  보다 직관적으로 코드를 볼 수 있고 유연하게 코드를 재사용 가능
  그에따라서 DataBinding을 걷어냄  
* **Hilt** 적용  
 의존성 주입을 사용해서 테스트를 쉽게 사용하고 코드 내부에서 의존관계를 만들지 않고 관심사를 외부로 돌립니다.  
* **Room Database** 적용  
 루틴들을 저장하고 불러오는데 Room Database를 사용하였습니다.
* **Uizard** 적용
  UI를 개선하기 위해 Uizard를 사용하였습니다.
## 진척도  
https://github.com/hegunhee/Routiner/issues/55

## 정보들  
1. Entity들  
   * https://github.com/hegunhee/Routiner/issues/1  
2. 실기기 ActionBar 관련 에러 수정  
   * https://github.com/hegunhee/Routiner/issues/2  
3. test Branch가 존재하는 이유  
   * https://github.com/hegunhee/Routiner/issues/3  
4. Chip Widget을 제거한 이유  
   * https://github.com/hegunhee/Routiner/issues/37
   
## 느낀점  
처음으로 미니프로젝트를 진행하면서 에뮬레이션이 아닌 실제 기기에서 테스트해봤고 앱을 배포해봤습니다.  
덕분에 UI, 데이터 저장 수정 관련해서 더 신경을 썼고 앞으로도 앱을 더 개선할 것입니다.  

## 앱 사진  
![routiner_main](https://github.com/hegunhee/Routiner/assets/57277631/53fd0ae5-57f3-417a-af72-8efaac0793f6)  
오늘 루틴    
![record_routine](https://github.com/hegunhee/Routiner/assets/57277631/0865c68f-09be-4d2b-9e36-c5477c6a8b4a)  
지난 기록  
![repeat_routine](https://github.com/hegunhee/Routiner/assets/57277631/07a3e8fc-58d9-4dbc-8187-dd09525f4617)  
반복 루틴  
![routine_insert](https://github.com/hegunhee/Routiner/assets/57277631/0a7c7314-e141-45d7-ab7b-fc838a1b1429)  
루틴 추가  
## 앱 개인정보 설명  
https://hegunhee.tistory.com/27
