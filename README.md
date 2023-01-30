# Online Game Book
선택지에 따라 페이지를 넘기는 형태의 책인 게임북을 온라인에서 제작하고 플레이할 수 있는 웹 사이트의 제작을 목적으로한 개인 프로젝트입니다.
# Description
* 진행 기간 : 2023-01-01 ~ (진행중)
* 참여 인원 : 1인 (개인 프로젝트)
* 사용 기술
  * Back : Java, Spring, SpringBoot
  * DB : H2 Database, Jpa
  * Front : HTML, CSS, JavaScript, Jquery, Ajax
# Project Views and Description
* 사이트 메인 페이지(로그인 안됨)<br>

![로그아웃홈](https://user-images.githubusercontent.com/96877973/215478771-ae161cb0-4bd5-4669-ac6c-ed6811d8b957.PNG)

* 회원가입 페이지

회원가입 시 id, 비밀번호, 닉네임을 입력한다. 입력하지 않은 칸이 있거나, 기존과 중복된 ID나 닉네임을 입력한 경우 경고 메세지를 띄워준다.

![회원가입](https://user-images.githubusercontent.com/96877973/215478797-21a98e07-b446-44ab-b73f-a46147993e6b.PNG)

* 로그인 페이지

기존에 회원 가입한 ID, 비밀번호와 비교하여 로그인을 진행한다. 잘못된 ID 또는 비밀번호를 입력하면 경고 메세지를 띄워준다.

![로그인](https://user-images.githubusercontent.com/96877973/215478778-1b7e8727-f15d-47ba-8350-187aa2824c1c.PNG)

* 로그인 후 메인 페이지

마이 페이지, 로그아웃, 검색, 랭킹 순 조회, 게임북 제작 등이 가능하다.

![로그인 홈](https://user-images.githubusercontent.com/96877973/215478773-3ab5a3eb-c728-4c9a-900b-8a2b8a08c44d.PNG)

* 게임북 제작 페이지 

게임북의 썸네일로 사용할 사진과 제목을 입력하고 '새 게임북 만들기' 버튼을 누르면 새 게임북의 제작이 시작된다.

![게임북제작1](https://user-images.githubusercontent.com/96877973/215478764-569d43ce-e473-4130-a530-cd49d1751c9f.PNG)

* 새 페이지 제작

게임북의 새 페이지를 만든다. 사진, 내용, 선택지를 추가할 수 있다. 선택지는 최대 3개까지 추가할 수 있으며, 선택지는 기존에 제작된 페이지 목록 또는 메인 페이지 중에서 선택할 수 있다. 

![새페이지](https://user-images.githubusercontent.com/96877973/215478785-597614b9-52aa-40d5-87a9-1dc4961c9e49.PNG)

* 페이지 목록

페이지 목록에서는 제작한 페이지의 목록을 볼 수 있다. 페이지를 클릭하면 해당 페이지를 수정할 수 있다.

![페이지 목록](https://user-images.githubusercontent.com/96877973/215478790-5bc132c9-8d53-4ce4-9707-13fe78628b6e.PNG)

* 페이지 수정

기존에 제작한 페이지의 내용을 수정할 수 있다.

![선택지수정](https://user-images.githubusercontent.com/96877973/215478787-c6a5e853-dc36-4e62-a930-1503b7030fa6.png)

* 마이 페이지

마이 페이지에서는 회원 정보 수정, 회원 탈퇴, 좋아요 리스트 조회, 내가 제작한 게임북 조회가 가능하다.

![마이페이지](https://user-images.githubusercontent.com/96877973/215478780-0929e819-a6c2-40b2-93c3-063a2989cf01.PNG)

* 회원 정보 수정

비밀번호 또는 닉네임을 변경할 수 있다.

![회원정보 변경](https://user-images.githubusercontent.com/96877973/215478798-30034c34-2908-413c-96df-a09856bf3822.PNG)

* 회원 탈퇴

회원 탈퇴가 가능하다.

![회원탈퇴](https://user-images.githubusercontent.com/96877973/215478800-5609d3a1-6737-4bf3-b4d6-627bb686837f.PNG)

* 내가 제작한 게임북

내가 제작한 게임북의 목록을 확인할 수 있으며 게임북을 클릭 시 해당 게임북의 메인 페이지로 이동한다. 또한 게임북을 수정할 수 있다.

![내가 제작한 리스트](https://user-images.githubusercontent.com/96877973/215480971-5389aeb0-b7ee-4f38-ac55-75e4ee890c54.PNG)

* 좋아요 리스트 조회

내가 좋아요를 준 게임북의 리스트를 조회할 수 있다. 클릭 시 해당 게임북의 메인 페이지로 이동한다.

![좋아요 조회](https://user-images.githubusercontent.com/96877973/215481404-1c0b313c-99ef-472a-a8d6-2cda92b4c333.PNG)


* 게임북 수정하기

기존에 제작한 게임북을 수정할 수 있다. 썸네일 또는 제목을 수정할 수 있으며 게임북의 공개 상태를 전환할 수 있다. 게임북이 비공개 상태라면 다른 사람들이 검색 또는 랭킹 조회에서 조회되지 않는다.\
페이지 목록으로 이동하여 게임북의 페이지들도 수정할 수 있다.

![게임북 수정](https://user-images.githubusercontent.com/96877973/215478761-0b4b4c26-58cf-4711-a09e-de4272bbeca1.PNG)

* 검색하기

게임북의 이름과 게임북 제작자의 닉네임 중 하나를 검색 조건으로 선택하여 검색할 수 있다.

![검색조건](https://user-images.githubusercontent.com/96877973/215478754-e8088516-36c7-48ee-aae5-34b1773095a5.png)

* 검색 결과 화면

검색 조건을 입력하고 검색을 하게 되면 해당하는 검색 결과가 표시된다. 클릭 시 해당 게임북의 메인 페이지로 이동한다.

![검색](https://user-images.githubusercontent.com/96877973/215478748-ef1ae727-91fe-4e1d-9308-8234cebeb213.PNG)

* 랭킹 조회

제작된 게임북들을 좋아요의 내림차순으로 정렬하여 랭킹을 조회한다. 클릭 시 해당 게임북의 메인 페이지로 이동한다.

![랭킹](https://user-images.githubusercontent.com/96877973/215478767-2afe2240-a24f-43cc-84d8-14a22370ed5f.PNG)

* 게임북 메인 페이지

게임북의 메인 페이지에서는 게임북의 정보와 댓글 등록 좋아요 및 게임북 플레이가 가능하다. 자신이 등록한 댓글은 삭제할 수 있으며 좋아요를 취소할 수도 있다.

![게임북 메인페이지](https://user-images.githubusercontent.com/96877973/215478758-21c1691b-230d-4ff9-8eda-8319df7fe6d4.PNG)

* 게임북 플레이 화면 1

게임북플레이 화면에서는 게임북의 내용과 사진, 선택지 등을 볼 수 있다. 원하는 선택지를 클릭하여 해당 페이지로 이동한다.

![플레이 1](https://user-images.githubusercontent.com/96877973/215478794-a3790179-0182-4cd1-8b9c-f0243344b632.PNG)

* 게임북 플레이 화면 2

메인 페이지로 이동하는 선택지를 누르게 되면 게임북의 메인 페이지로 이동한다. 아래의 사진은 임의로 구현한 엔딩 화면이다. 

![플레이완](https://user-images.githubusercontent.com/96877973/215478795-9a425c43-e8b9-4aa6-a5a9-52317b70c7f4.PNG)
