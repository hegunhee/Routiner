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
