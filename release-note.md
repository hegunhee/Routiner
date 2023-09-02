Routiner 1.3.1 release note
=========================
접근성 개선, 앱 크기 축소 및 난독화, android 8.0 이전 버전에서 간헐적으로 앱이 죽는 현상 수정

- 오류 수정  
현재 시간 측정시 android 8.0 이전 버전에서 앱이 죽는 현상 수정

- 접근성 개선  
클릭 가능한 위젯 크기 변경, 글자 크기 기기별 동일하게 변경

- 앱 크기 축소  
proguard 적용으로 인해 크기 축소 및 난독화

개발자 노트
--- 
viewModel 단의 UI event 및 UI State 수정  
https://github.com/hegunhee/Routiner/pull/40

android 8.0 이전에서는 시간 함수가 작동하지못하고 앱이 죽었지만
8.0 이전 버전에서 작동하는 시간 코드를 추가해 앱이 죽지않게 변경  
https://github.com/hegunhee/Routiner/pull/41

proguard 적용으로 인해 앱 크기가 4MB에서 2MB로 축소 및 난독화  
https://github.com/hegunhee/Routiner/pull/43

접근성 개선 (구글 권고사항)  
https://github.com/hegunhee/Routiner/pull/42



Routiner 1.3 release note
=========================  
최소 sdk 버전 상승으로 인해 android 7버전 이하 기기들은 설치가 불가능합니다.

- 오류 수정  
카테고리 추가에서 카테고리가 삭제되지 않던 오류 수정

- UI 개선  
텍스트가 보다 잘보이게 수정  
텍스트 색상을 한가지로 통일  
android 12 이상 기기 시작화면 표시  
다크모드 비활성화  

개발자 노트
----
기존의 단일 모듈 앱에서 multi module로 각 모듈들을 분리했습니다.  
https://github.com/hegunhee/Routiner/pull/13  

Chip widget을 걷어내고 style을 적용한 TextView로 변경했습니다.  
https://github.com/hegunhee/Routiner/pull/29  

버전 카탈로그로 빌드를 이전해 멀티모듈에서도 종속 항목을 쉽고 간단하게 사용하게 변경했습니다.  
https://github.com/hegunhee/Routiner/pull/9  

Custom-plugin을 적용해 hilt나 feature모듈만을 위한 build.gradle 설정을 작성하였습니다.  
https://github.com/hegunhee/Routiner/pull/19  

UI 상태를 Flows로 변경했습니다.  
https://github.com/hegunhee/Routiner/pull/17  

android 12버전 이상의 스플래시 스크린을 적용했습니다.  
https://github.com/hegunhee/Routiner/pull/30  

targetSdk Version을 33(android 13)으로 지정했습니다.  
https://github.com/hegunhee/Routiner/pull/38  

java Version을 1.7에서 11로 변경했습니다.  

다크모드를 비활성화 했습니다.
