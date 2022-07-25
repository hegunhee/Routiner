# Routiner  [앱링크](https://play.google.com/store/apps/details?id=com.hegunhee.routiner)
## 2022.06.21 ~ 2022.07.11(앱출시) 지속적으로 업데이트중  
## 소개  
루티너는 습관을 등록하고 나중에 확인할 수 있는 앱입니다.  
## 앱 사진  
![routiner_main](https://user-images.githubusercontent.com/57277631/178176431-c87501f5-396b-400b-b2e5-79c754965fde.PNG)  
오늘 루틴    
![record_routine](https://user-images.githubusercontent.com/57277631/178176436-b04eb935-91cf-4006-bd01-6b8b316e732e.jpg)  
지난 기록  
![repeat_routine](https://user-images.githubusercontent.com/57277631/178176451-54c389ed-66f9-4563-a245-1360058f25f7.jpg)  
반복 루틴  
## 사용된 라이브러리  
* JetPack  
  * AAC-ViewModel  
  * LiveData  
  * Room  
* Coroutine  
  * Flow  
* Hilt  
* SharedPreference  
## 기술 정보  
* **AAC-ViewModel** 적용  
 Activity나 Fragment에 보여줄 data를 가지고있습니다.  
 Activity나 Fragment가 화면 회전 등의 이유로 Destroy 상태가 되어도  
 AAC-ViewModel은 재 생성되지 않기때문에 데이터를 쉽게 관리할 수 있습니다.
 그리고 ViewModelScope를 사용해 비동기 코드를 쉽게 작성할 수 있습니다.  
* **Flow** 적용  
 지속적으로 데이터를 받을 수 있는 Flow를 사용해 오늘의 루틴을 받을 수 있습니다.  
* **DataBinding** 적용  
 dataBinding을 사용하여 findViewById를 사용하지 않으며 xml 파일과 데이터 객체를 연결해줍니다.  
* **Hilt** 적용  
 의존성 주입을 사용해서 테스트를 쉽게 사용하고 코드 내부에서 의존관계를 만들지 않고 관심사를 외부로 돌립니다.  
* **Room Database** 적용  
 루틴들을 저장하고 불러오는데 Room Database를 사용하였습니다.  
## 진척도  
1. DrawerLayout 등록  
    * 오늘의 루틴, 기록  
2. 오늘의 데일리 루틴 등록기능  
    * SharedPreference (날짜 저장)  
    * Room  
3. 기록 탭  
    * 리뷰 등록  
    * 기록 열람  
4. Core Routine(요일별로 자동으로 등록되는 루틴)  

5. 앱 출시  

    -------------------------------------------  
6. Category 설정  
    * 공부, 운동 이런 카테고리들을 설정
    * 만약 된다면 db 버전을 올려야함  
7. 매일 오전 10시 오후 10시에 알람이 가는 notification  
8. Google Login(Firebase)  


## 정보들  
1. Entity들  
   * https://github.com/hegunhee/Routiner/issues/1  
2. 실기기 ActionBar 관련 에러 수정  
   * https://github.com/hegunhee/Routiner/issues/2  
3. test Branch가 존재하는 이유  
   * https://github.com/hegunhee/Routiner/issues/3  
   
## 느낀점  
처음으로 미니프로젝트를 진행하면서 에뮬레이션이 아닌 실제 기기에서 테스트해봤고  
앱을 배포해봤습니다. 덕분에 UI, 데이터 저장 수정 관련해서 더 신경을 썼고 앞으로도 앱을 더 개선할 것입니다.  
