# 개인 프로젝트 - 할 일 카드를 작성, 수정, 삭제, 조회 api만들기

## 🔍 루시드 다이어그램
![K-126](https://github.com/DanDanjoo/Todo/assets/162088392/59cff2b1-89b9-4c8d-9193-7e65b7643bf7)



### 😊 설명
Task 항목을 관리하고, 댓글 기능이 있는 RESTful API를 제공합니다.  
주요 기능은 Task, Comment 항목 생성(Create), 조회(Get), 수정(Update), 삭제(Delete) 입니다.  



### 😊 구현 과정 및 어려웠던 점  

#### 생성자 관련 이슈 해결
```
plugins {
    kotlin("plugin.noarg") version "1.9.23"
}

noArg {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}


allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

- build.gradle.kts
```
No-Arg plugin은 JPA 엔티티 클래스에 기본 생성자를 자동으로 추가해주는 플러그인입니다.  
또한, JPA 엔티티 클래스의 메서드 상속을 가능하게 만들고, Kotlin과 JPA간의 호환성을 향상시키는 plugin입니다.  
```
   override fun updateTask(taskId: Long, request: UpdateTaskRequest): TaskResponse {
        val task = taskRepository.findByIdOrNull(taskId) ?: throw ModelNotFoundException(taskId)

        task.title = request.title
        task.username = request.username
        task.description = request.description
        task.dueDate = request.dueDate
        task.completed = request.completed


        return taskRepository.save(task).toResponse()
    }

-TaskServiceImpl
```
return taskRepository.save(task).toResponse()을 return task.toResponse()로 줄여도 작동이 됩니다.  
이것을 전문 용어로 ' 더티 체킹 ' 이라고 하는데, 동작 방식을 제대로 이해하고 운용하려고 합니다.  
*영속성 컨텍스트와 .persist(), .merge(), save()의 개념과 차이를 알아보도록 하겠습니다. 


#### Cascade 설정
```
    @OneToMany(mappedBy = "task", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var comment : MutableList<Comment> = mutableListOf(),

- Task.kt
```
Task엔티티와 Comment엔티티는 일대다 관계를 갖고 cascade = [CascadeType.ALL]으로 설정 되어,  
Task엔티티에 대한 모든 작업들이 연관된 Comment 엔티티에도 전달 된다.  

#### 연관 관계 설정
```
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    var task: Task

- Comment.kt
```
Comment엔티티는 Task엔티티와 다대일 관계를 갖고 있다. (연관 관계의 주인)  
연관 관계에 대해서 더 알아봐야 하지만, 'Comment가 Task의 생명 주기를 따라간다.' 라는 것을 제대로 이해했습니다.   
#### 😭 어려웠던 점..
DTO에 대한 전반적인 이해는 됐지만, Response DTO에 들어있는 createdAt을 어떻게 운용해야하는지 잘 모르겠습니다.  
정확히는 DTO에 담아야 할 것과 Entity에 정의해야할 것의 구분의 이해도가 부족합니다.  
댓글 수정과 삭제 시 username과 password를 받고, 다를 시 exception을 통해 예외처리하는 걸 구현하려고 했는데, 조금 어려웠습니다! 또한 Service와 ServiseImpl을 나누는 이유에 대해서 제대로 이해하지 못했습니다.  
DI와 다형성에 대해서 좀 더 알아보고 이해하려고 합니다. 


### 😊 개선할 점 및 추가 계획  
테스트하는 과정이 정말 중요하다고 느꼈습니다. 비즈니스 로직을 제대로 구현 했는지? 상황에 따른 코드가 적절하게 반환 되는지?   
또한, 백엔드에서 가장 중요한 요소인 보안에 대해서 확실하게 배울 필요성을 느꼈습니다.  
댓글 수정, 삭제같은 경우에 username과 password이 다를 경우 어떤 예외처리를 던져야하고?  
dto는 어떻게 활용해야 할 것인지 이해하도록 더 연습해보고 고민해보려고 합니다.


#### 테스트 할 때 뭘해야 할까?

1. 정상 상황일 때 Response Body가 적절한지?, Status Code가 적절한지?
2. 비정상 상황일 때  Response Body가 적절한지
3. 비상 상황일 때 Status Code가 적절한지







## 💻 실행 예시  
#### ❗task 생성 및 조회
![Desktop 2024 05 24 - 11 14 13 02 (1)_Joined (1) (1) (1)](https://github.com/DanDanjoo/Todo/assets/162088392/bfc17e41-e417-41d4-ade7-df6c6f0d16ff)

#### ❗comment 생성 및 조회
![Desktop 2024 05 24 - 11 14 13 02 (1)_Joined (1) (1) (1) (1) (1) (1) (1) (1) (1) (1) (1)](https://github.com/DanDanjoo/Todo/assets/162088392/2da71898-3aca-4f94-961d-0c79b78048ab)

#### ❗Comment가 Task의 생명 주기를 따라간다.
![Desktop 2024 05 24 - 11 14 13 02 (1)_Joined (1) (1) (1) (1) (1) (1) (1) (1) (1) (1) (1) (1)](https://github.com/DanDanjoo/Todo/assets/162088392/523e4f2f-26e7-44b1-814d-dd9f5ee3f233)



## 💻 피드백 받고 싶은 것
#### 😓Task 수정을 했는데 DB에 반영이..
![Desktop 2024 05 24 - 11 14 13 02 (1)_Joined (1) (1) (1) (1) (1)](https://github.com/DanDanjoo/Todo/assets/162088392/5229f09c-a838-4019-af9b-8e69432721a4)

#### 😓password가 달라도 수정이 된다..? 
![Desktop 2024 05 24 - 10 04 38 01 (1) (1) (1)](https://github.com/DanDanjoo/Todo/assets/162088392/23764725-83b9-45f7-b68a-a974406c94f5)



## 📈 과제 요구사항

### 필수 구현 기능

- [ ]  할일카드 작성 기능
    - `할 일 제목`, `할일 내용`, `작성일`, `작성자 이름` 을 저장할 수 있습니다.
    - 저장된 할 일의 정보를 반환 받아 확인할 수 있습니다.
- [ ]  선택한 할 일 조회 기능
    - 선택한 할 일의 정보를 조회할 수 있습니다.
    - 반환 받은 할 일 정보에는 `할 일 제목`,`할일 내용`, `작성일`, `작성자 이름`정보가 들어있습니다.
- [ ]  할 일카드 목록 조회 기능
    - 등록된 할 일 전체를 조회할 수 있습니다.
    - 조회된 할 일 목록은 작성일 기준 내림차순으로 정렬 되어있습니다.
- [ ]  선택한 할 일 수정 기능
    - 선택한 할 일의 `할 일 제목`, `작성자명`, `작성 내용`을 수정할 수 있습니다.
    - 수정된 할 일의 정보를 반환 받아 확인할 수 있습니다.
- [ ]  선택한 할 일 삭제 기능
    - 선택한 게시글을 삭제할 수 있습니다.

### 선택 구현 기능
- [ ]  **할일카드 완료 기능 API**
    - 완료처리 한 할일카드는 목록조회시 `완료 여부`필드가 TRUE 로 내려갑니다.
    - `완료 여부` 기본값은 FALSE
- [ ]  **댓글 작성 API**
    - 댓글을 작성할 때 `작성자 이름`과 `비밀번호`를 함께 받기
    - 선택한 할 일의 DB 저장 유무를 확인하기
    - 선택한 할 일이 DB에 저장되어 있다면 댓글을 등록하고 등록된 댓글 반환하기
        - 응답에서 `비밀번호`는 제외하고 반환해주세요.
- [ ]  **댓글 수정 API**
    - 선택한 댓글의 DB 저장 유무를 확인하기
    - `작성자 이름`과 `비밀번호`를 함께 받아 저장된 값과 일치하면 수정 가능
    - 선택한 댓글이 있다면 댓글 수정하고 수정된 댓글 반환하기
        - 응답에서 `비밀번호`는 제외하고 반환해주세요.
- [ ]  **댓글 삭제 API**
    - 선택한 댓글의 DB 저장 유무를 확인하기
    - `작성자 이름`과 `비밀번호`를 함께 받아 저장된 값과 일치하면 수정 가능
    - 선택한 댓글이 있다면 댓글 삭제하고 Client 로 성공했다는 메시지, 상태코드 반환하기
- [ ]  선택한 할 일 조회 기능 응답에 연관된 댓글 목록 추가하기
    - **STEP 1**에서 만든 선택한 할 일 조회 api의 응답에 연관된 댓글 목록을 추가해주세요
    - 연관되지 않은 댓글은 포함되지 않아야 합니다.
    - 할 일 목록 api에는 추가하지 말아주세요.
      

## ✉️ 과제 제출 방법
- 과제 제출은 2회차에 나누어 제출합니다.
- 제출 링크 : https://nbcamp.spartacodingclub.kr/mypage/assignments
- 1차 제출 기한 : 05/17(금) 14:00
    - 1차 목표는 STEP 1까지.
    - 제출 이후, 피드백이 제공됩니다. 피드백을 기반으로 과제 보완 및 추가 STEP을 도전합니다.
- 2차 제출 기한 : 05/24(금) 14:00
    - 제출 이후, 피드백 및 과제 해설 세션이 진행됩니다.
 
      
## 환경설정
Language : Kotlin  
IDEA : IntelliJ  
JDK : 17.0.10  
Database : super base&postgre sql  
springframework.boot : 3.2.5
