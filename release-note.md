Routiner 1.6.0 release note
===========================
- Android 15 대응
  현재 타겟 버전을 15버전으로 설정했습니다
  edgeToEdge 대응
  16KB 페이지 대응
- 루틴을 맨 하단까지 추가할경우 플로팅액션버튼이 삭제되던 문제 수정  
  현재 타겟 버전을 14버전으로 설정하였습니다.
- 앱 바 아이콘이 회색이라 가시성이 떨어지던 문제 수정

개발자 노트
--- 
Android 15 대응
https://github.com/hegunhee/Routiner/issues/171

FAB가 잘리던 문제 수정
https://github.com/hegunhee/Routiner/issues/176

앱 바 아이콘의 가시성이 떨어지는 이슈 수정
https://github.com/hegunhee/Routiner/issues/174

Routiner 1.5.0 release note
===========================
- 알람 권한 요청
앱을 처음 시작하거나 알람을 설정할 때 알람 권한 요청
- Android 14 대응 
현재 타겟 버전을 14버전으로 설정하였습니다.

개발자 노트
---
UI를 Jetpack Compose로 변경했습니다.
https://github.com/hegunhee/Routiner/milestone/2

전반적인 테스트코드를 작성하였습니다.
https://github.com/hegunhee/Routiner/milestone/1

알람 권한을 요청합니다.
https://github.com/hegunhee/Routiner/issues/147

targetSdk를 34로 지정하였습니다.
따로 대응할것은 없었습니다.
https://github.com/hegunhee/Routiner/issues/152

앱의 딥링크의 스킴을 routiner로 설정했습니다.
https://github.com/hegunhee/Routiner/issues/153

Routiner 1.4.0 release note
=========================
UI 개편 (기존의 theme에서 다크한 theme으로 변경), 기록 화면에서 상단에 날짜를 직접 클릭할 수 있도록 수정  
지정한 시간에 매일 알람이 울리는 기능 추가 (오늘 일의 진척도마다 알림 메시지가 다름)  

- UI 개선  
기존의 theme에서 검정색과 그레이색상의 theme으로 변경, daily화면, 기록화면 변경  
기록화면에서 날짜를 옆으로 넘기는것이 아닌 직접 선택 가능  
  
- 알람 기능 추가  
setting 화면에서 설정 가능하며 지정한 시간마다 매일 알람이 울림  

개발자 노트
---
Uizard로 UI를 개선했습니다.  
https://github.com/hegunhee/Routiner/pull/54  

기록칸 상단에 선택가능한 날짜가 보이며 선택시 해당 날짜의 기록이 보입니다.  
https://github.com/hegunhee/Routiner/pull/56  

알람 기능 추가  
https://github.com/hegunhee/Routiner/pull/57

Routiner 1.3.2 release note
=========================
최소 sdk 버전 수정으로 android 8이하 버전에서 설치 불가능, android 12 이상 버전에서 첫 실행시 Guide Dialog가 두번 나오는 현상 수정

- 오류 수정
android 12 이상 버전에서 첫 실행시 Guide Dialog가 두번 나오는 현상 수정

- 글자 크기 수정
글자 크기 단위를 dp에서 sp로 변경해 해상도에 따라 글자 크기가 변경됨

- 성능 개선
앱이 백그라운드 상태에서 데이터를 수집하던걸 방지해
메모리 누수가 방지됩니다.

개발자 노트
---
LiveData 레거시를 모두 걷어냈습니다.
https://github.com/hegunhee/Routiner/pull/46

android 12 이상 버전에서 첫 실행시 Guide Dialog가 두번 나오는 현상 수정
https://github.com/hegunhee/Routiner/pull/51

build-logic 모듈을 리팩토링하여 feature모듈만의 gradle 파일 생성
android 모듈용 library 모듈 생성
https://github.com/hegunhee/Routiner/pull/47

navigation, util 코드만을 위한 모듈 생성
https://github.com/hegunhee/Routiner/pull/48

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
