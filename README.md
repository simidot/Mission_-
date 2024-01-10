#  🍳Overview

### 익명 의견 교환 게시판
사용자들이 자기 자신의 정보를 드러낼 필요 없이 의견을 교환할 수 있는 웹페이지입니다.

단, 작성한 사람이 원한다면 수정, 삭제가 가능합니다!

#  🚩Project
<details>
<summary><strong>SKILL</strong></summary>
<div markdown="1">       

**[Front-end]**  
<img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> 
<img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white">
<img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white" /> 
<img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white">


**[Back-end]**   
<img src="https://img.shields.io/badge/java%2017-007396?style=for-the-badge&logo=java&logoColor=white"> 
<img src="https://img.shields.io/badge/sqlite-%2307405e.svg?style=for-the-badge&logo=sqlite&logoColor=white"> 
<img src="https://img.shields.io/badge/spring%20boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/apache%20tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=white"> 
<img src="https://img.shields.io/badge/JPA-005F0F?style=for-the-badge&logo=jpa&logoColor=white">
<img src="https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white">

**[Tool & Environment]**  
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> 
<img src="https://img.shields.io/badge/IntelliJ%20IDEA-CB5B8D?style=for-the-badge&logo=intellijidea&logoColor=white">
</div>
</details>

<details>
<summary><strong>ERD</strong></summary>
<div markdown="1"> 
  <img width="772" alt="image" src="https://github.com/simidot/Mission_youshin/assets/114278754/0068d3e6-0c6d-4316-92b4-8c24874b2387">
</div>
</details>

<details>
  <summary><strong>간단한 화면 구상</strong></summary>
<div markdown="1">
  <img src="https://github.com/simidot/Mission_youshin/assets/114278754/7dc7dc9f-aebd-431b-94dd-cfa7b0fd937f">
</div>
</details>


#  📍 주요 기능

## 필수 과제
### 1. 게시판 조회 기능
- 사용자는 같은 주제로 작성된 게시글들을 모아 조회할 수 있다.
- 또한 전체 게시글을 위한 전체 게시판이 존재한다.
- 자유/개발/일상/사건사고 게시판이 존재한다.

### 2. 게시글 작성 기능
- 사용자는 하나의 주제에 대한 의견 교환 글을 작성할 수 있다.
- 제목, 내용으로 구성되어 있고, 본인확인을 위한 비밀번호를 작성해야 한다.

### 3. 게시글 조회 및 수정/삭제 기능
- 모든 게시글을 조회할 수 있다.
- 본인확인 비밀번호 입력 후 게시글을 수정/삭제 할 수 있다. 

### 4. 댓글 기능
- 사용자는 게시글 조회 페이지에서 댓글을 작성할 수 있다.
- 댓글 작성자는 본인확인을 위한 비밀번호도 함께 작성해야 한다.
- 댓글 목록은 조회 페이지에서 확인이 가능하다.
- 댓글 삭제는 본인확인 비밀번호 입력 후 삭제가 가능하다. 

## 도전 과제
### 1. 검색 기능
- 사용자는 게시글 목록 페이지와 개별 게시판 조회 페이지에서 검색이 가능하다.
- 검색은 전체/제목/내용을 기준으로 검색이 가능하다.
- 개별 게시판이 선택된 상태에서는 해당 게시판 내에서만 검색이 가능하다.

#  🚀 참여자 (24.01.08 ~ 24.01.12)

|<img src="https://github.com/Team-Solar-Powers/eco_reading/assets/74632395/5ad2d7ab-16af-485d-a650-44cb5f833b6f" width="120" height="160"/><br/>BE 강유신 <a href="https://github.com/simidot">GitHub</a>|
|:---:|

#  💊 진행 중 발생한 어려움 

<details>
<summary><strong>1. 초기 데이터 설정하기 (Board의 Category)</strong></summary>

<div markdown="1"> 
1. Board 테이블의 카테고리들은 변하지 않는 파트로 한 번 입력된 후 변경 가능성이 거의 없다.

처음에는 **전체 게시판 카테고리 불러오기 메서드** 안에서 네가지 카테고리를 입력하고, 그것들을 불러오는 로직을 짰다.

```java
// BoardService class

    // 전체 게시판 카테고리 불러오기
    // 게시판의 카테고리가 정해져있기 때문에 불러오면서 바로 카테고리를 저장하도록 했다.
    // todo: 고민점은 카테고리를 저장하는게 맞는지가 ? 고민... 확장성도 고려하고 싶은데..
	public List<Board> readBoardCategories() {
        Board board1 = Board.builder().category(BoardCategory.자유).build();
        Board board2 = Board.builder().category(BoardCategory.개발).build();
        Board board3 = Board.builder().category(BoardCategory.일상).build();
        Board board4 = Board.builder().category(BoardCategory.사건사고).build();
        boardRepository.save(board1);
        boardRepository.save(board2);
        boardRepository.save(board3);
        boardRepository.save(board4);
        return boardRepository.findAll();
    }
    
// BoardController class
// 게시판 목록 전체 보기
    @GetMapping
    public String showBoardList(Model model) {
        model.addAttribute("boards", boardService.readBoardCategories());
        return "boardList";
    }
```

결과 : /boards endpoint로 새로고침하여 들어갈 때마다 계속해서 카테고리들이 insert되었다.

2. 그렇다면 단 한번 입력하도록 카테고리 입력 메서드를 따로 만들고, 카테고리가 비어있을 때만 추가하는 조건 걸기 & 입력 시점은 BoardController가 생성되는 시점에 입력한다.
```java
// BoardService class
    // 게시판의 카테고리가 정해져있기 때문에 카테고리를 저장하는 메서드가 필요.
    public void createCategories() {
        // board 카테고리가 비어있을 때에만 추가!
        if (boardRepository.findAll().isEmpty()) {
            Board board1 = Board.builder().category(BoardCategory.자유).build();
            Board board2 = Board.builder().category(BoardCategory.개발).build();
            Board board3 = Board.builder().category(BoardCategory.일상).build();
            Board board4 = Board.builder().category(BoardCategory.사건사고).build();
            boardRepository.save(board1);
            boardRepository.save(board2);
            boardRepository.save(board3);
            boardRepository.save(board4);
        }
    }
    
// BoardController class
    // BoardController가 생성될 때 카테고리가 저장되도록 한다.
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
        boardService.createCategories();
    }
```
결과 : 새로고침을 아무리 하여도 추가되지 않는다! 그러나, 확장성을 고려하면 비효율적인 코드이다. 

게시판 특성상 여러가지 카테고리를 새로 추가할 수도 있어야하는데, 그러면 이미 board Entity가 채워져 있는 상태에서는 추가를 어떻게하지 깔끔하지 못한 코드같았다.

3. 조건문을 다 다르게 달아주었다. 
```java
// BoardService class
	public void createCategories() {
        // board 카테고리가 비어있을 때에만 추가!
        if (!boardRepository.findAll().contains(BoardCategory.자유)) {
            Board board1 = new Board(BoardCategory.자유);
            boardRepository.save(board1);
        }
        if (!boardRepository.findAll().contains(BoardCategory.개발)) {
            Board board2 = new Board(BoardCategory.개발);
            boardRepository.save(board2);
        }
        if (!boardRepository.findAll().contains(BoardCategory.일상)) {
            Board board3 = new Board(BoardCategory.일상);
            boardRepository.save(board3);
        }
        if (!boardRepository.findAll().contains(BoardCategory.사건사고)) {
            Board board4 = new Board(BoardCategory.사건사고);
            boardRepository.save(board4);
        }
    }
```
결과 : 이역시 서버 실행하면 1회 생성. 새로고침하면 생성되지 않음. 그러나, 서버 재실행하면 다시 1회 생성되어 또다시 카테고리가 늘어난다.
애플리케이션 재실행마다 새로운 세션에서 findAll()하며 가져오기 때문에 이전에 저장된 보드들이 무시되고 새로운 보드가 항상 추가된다는 문제점이 있었다.

4. **마지막 시도!** 
초기 데이터를 설정하기 위한 data.sql 파일을 추가해주었다. 

스프링 애플리케이션 실행시 resources 경로에 있는 schema.sql(DDL), data.sql(DML) 스크립트를 실행한다고 한다. 
```sql
// data.sql

INSERT OR IGNORE INTO board (category) VALUES ('자유');
INSERT OR IGNORE INTO board (category) VALUES ('개발');
INSERT OR IGNORE INTO board (category) VALUES ('일상');
INSERT OR IGNORE INTO board (category) VALUES ('사건사고');
```
이렇게 넣어두어서 애플리케이션 실행시 자동으로 insert되고, 이미 있다면 ignore하라는 DML을 설정해주었다. 

결과: 아무리 새로고침해도, 서버 재실행을 해도 그대로 남아있게 되었다. 새로운 카테고리를 추가한다면 data.sql을 바꿔주면 끝! 간단하다.
</div>
</details>

<details>
<summary><strong>2. redirect시 url에 파라미터 값 넘겨주기</strong></summary>
<div markdown="1"> 
endpoint를 위해서는 redirec:/를 하고 그 url에 파라미터 값을 넘겨주어야 했다. 

ex.

```java
    // 댓글 삭제하기
    @GetMapping("/{articleId}/comment/{commentId}/delete")
    public String deleteComment(@PathVariable("articleId") Long articleId,
                                @PathVariable("commentId") Long commentId,
                                RedirectAttributes redirectAttributes
    ) {
        commentService.deleteComment(commentId);
        redirectAttributes.addAttribute("articleId", articleId);
        return "redirect:/article/{articleId}";
    }
```

어떻게 넘겨주어야 하나 고민했는데 검색 결과 RedirectAttributes로 파라미터 값을 넘긴다고 한다. 

>.addAttribute(Object attributeValue)

>.addAttribute(String attributeName, Object attributeValue)

이렇게 하여 articleId를 잘 넘겨주었다!

++ 추가로 addAttribute는 String/Integer와 같은 값을 넘길 때 사용이 되고,

복잡한 객체를 노출 없이 넘겨주거나, 일회성 성공 알림 등을 만들고 싶을 때에는 FlashAttribute를 쓸 수 있다.

> .addFlashAttribute(Object attributeValue)

> .addFlashAttribute(String attributeName, Object attributeValue)

이름처럼 일회성으로 사용 가능하다. 새로고침을 하면 휘발된다.

</div>
</details>

<details>
<summary><strong>3. 게시글 수정시 비밀번호 확인절차는 어디에서?!</strong></summary>
<div markdown="1"> 

게시글 수정시 비밀번호 확인 절차가 **서비스단**에서, **컨트롤러 단**에서 이루어져야 할지에 대한 의문이 생겼다.

일단은 내 생각으로는 어쨌든 비즈니스 로직이기 때문에 **서비스단**에서 이루어져야 할 것 같다.

그러나, 그러면 update가 되는 경우와 비밀번호 불일치로 인한 update 실패시 어떤 값을 반환하여 controller로 넘겨야하는지?에 대한 고민이 있다.

나는 boolean 값으로 update 성공시 true, 실패시 false를 반환하여 controller에 넘겨주었다.

그러나, Controller를 거쳐 화면에 어떻게 뿌려줘야할지에 대한 방법을 모르겠다.

결국 해결은 못했고, update html 화면에서 javascript로 확인하는 절차를 거쳤다. 비밀번호 불일치시 아예 폼 전송을 못하도록 막아두었다.
</div>
</details>

<details>
<summary><strong>4. 게시글 삭제시 댓글의 행방은 !?</strong></summary>
<div markdown="1"> 
게시글 삭제시 댓글은 어떻게 해야하는지에 대한 고민...

게시글만 삭제하니 다시 새로운 id의 글이 올라왔을 때에 다른 글이지만 boardId가 같으므로 새로운 게시물의 댓글로 표시가 된다.

일단 댓글이 모두 삭제되는 것이 맞는지?

근데 일단은 삭제하게 만들어두었다.

엔티티 맵핑시에 Cascade옵션을 주었다.

```java
@Entity
@Getter
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//...

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private final List<Comment> commentList = new ArrayList<>();

    public Article(String title, String content, String password, Board board) {
        this.title = title;
        this.content = content;
        this.password = password;
        this.board = board;
    }
}
```
</div>
</details>

<details>
<summary><strong>5. DTO는 과연 어떻게 써야하는지요!?</strong></summary>
<div markdown="1"> 
DTO를 쓸 때 하나의 DTO를 만들고, 계속 재사용하는지, 아니면 일회용으로 담는 그릇으로 쓰고 다시 새로운 DTO를 만들어야할지에 대한 의문이 들었다...
</div>
</details>

# 🖥️ 프로젝트 실행/테스트 방법

#### 실행
1. git clone
2. 최초 실행 시 **application.yaml** 파일의 jpa.hibernate.ddl-auto:**create**로 설정을 변경하여 실행한다.
3. 최초 실행 후에는 다시 **update**로 바꿔주면 된다.
4. 또한, 실행 시 더미 데이터가 입력되었기 때문에, **data.sql파일의 더미데이터 부분**을 **주석처리** 한다.
5. localhost:8080/boards로 접속하여 테스트한다.

#### 테스트
1.  


