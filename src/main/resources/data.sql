INSERT OR IGNORE INTO board (category) VALUES ('자유');
INSERT OR IGNORE INTO board (category) VALUES ('개발');
INSERT OR IGNORE INTO board (category) VALUES ('일상');
INSERT OR IGNORE INTO board (category) VALUES ('사건사고');


-- 더미데이터입니다. 1회 실행 후 주석처리
INSERT INTO article (title, content, password, board_id, create_date)
VALUES ('자유게시판 와봤어요', '반갑습니다~', '1111', 1, datetime('now', 'localtime'));
INSERT INTO article (title, content, password, board_id, create_date)
VALUES ('날이 춥네요', '빙판길 조심하세요~', '1111', 1, datetime('now', 'localtime'));

INSERT INTO article (title, content, password, board_id, create_date)
VALUES ('JPA로 프로젝트중이에요', '어렵네요...', '1111', 2, datetime('now', 'localtime'));
INSERT INTO article (title, content, password, board_id, create_date)
VALUES ('다들 어떤 언어 하시나요?', '저는 자바 배우고 있습니다.', '1111', 2, datetime('now', 'localtime'));

INSERT INTO article (title, content, password, board_id, create_date)
VALUES ('일상글 올려요', '제 일상은 코딩인데요...', '1111', 3, datetime('now', 'localtime'));
INSERT INTO article (title, content, password, board_id, create_date)
VALUES ('다들 그런적 없나욤', '자고 싶은데 잠이 안오는...그런날 ㅋㅋㅋ', '1111', 3, datetime('now', 'localtime'));

INSERT INTO article (title, content, password, board_id, create_date)
VALUES ('헉', '폭설이래요 조심하세요~', '1111', 4, datetime('now', 'localtime'));
INSERT INTO article (title, content, password, board_id, create_date)
VALUES ('사고났어요 여기', '안전운전 하세요..', '1111', 4, datetime('now', 'localtime'));

INSERT INTO comment (content, password, article_id, create_date)
VALUES ('반가워요~~', '1111', 1, datetime('now', 'localtime'));
INSERT INTO comment (content, password, article_id, create_date)
VALUES ('반갑습니다~~', '1111', 1, datetime('now', 'localtime'));
INSERT INTO comment (content, password, article_id, create_date)
VALUES ('하이', '1111', 1, datetime('now', 'localtime'));

INSERT INTO comment (content, password, article_id, create_date)
VALUES ('눈 많이왔어요~~', '1111', 2, datetime('now', 'localtime'));

INSERT INTO comment (content, password, article_id, create_date)
VALUES ('헉 jpa 잘 하고 계신가요', '1111', 3, datetime('now', 'localtime'));
INSERT INTO comment (content, password, article_id, create_date)
VALUES ('첨에는 어려워도 익숙해지실거에요', '1111', 3, datetime('now', 'localtime'));
INSERT INTO comment (content, password, article_id, create_date)
VALUES ('화이팅~~', '1111', 3, datetime('now', 'localtime'));

INSERT INTO comment (content, password, article_id, create_date)
VALUES ('저는 파이썬이요~~', '1111', 4, datetime('now', 'localtime'));
INSERT INTO comment (content, password, article_id, create_date)
VALUES ('전 프론트라.. 리액트합니다~~', '1111', 4, datetime('now', 'localtime'));
INSERT INTO comment (content, password, article_id, create_date)
VALUES ('먼소린지 모르겠네요 저는..', '1111', 4, datetime('now', 'localtime'));