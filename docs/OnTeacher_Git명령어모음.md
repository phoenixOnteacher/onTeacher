# OnTeacher Git 명령어



### 명령어와 설명

| 명령어                                                       | 설명                                                         |
| :----------------------------------------------------------- | ------------------------------------------------------------ |
|                                                              |                                                              |
| git clone https://github.com/phoenixOnteacher/on-teacher.git | github clone                                                 |
| cd onTeacher                                                 | onTeacher 디렉터리로 이동                                    |
| git branch -a                                                | 모든 브랜치 목록 조회                                        |
|                                                              |                                                              |
| **checkout**                                                 |                                                              |
| git checkout 브랜치이름                                      | 브랜치로 이동                                                |
| git checkout -b 브랜치이름                                   | 브랜치 만들고 이동                                           |
| git checkout -d 브랜치이름  (or)  git checkout -D 브랜치이름 | 브랜치 삭제                                                  |
|                                                              |                                                              |
| **pull**                                                     |                                                              |
| git pull origin 브랜치이름                                   | 브랜치 변경사항 pull (반드시 본인의 브랜치로 이동해서 pull하세요) |
| git reset --hard ORIG_HEAD                                   | 방금전 실행했던 pull 취소                                    |
|                                                              |                                                              |
| **add**                                                      |                                                              |
| git add 파일이름                                             | 특정 파일 add                                                |
| git add .                                                    | 모든 파일 add (되도록 쓰지마세요)                            |
| git reset HEAD 파일이름                                      | 특정 파일 add 취소                                           |
| git reset HEAD                                               | 모든 파일 add 취소                                           |
|                                                              |                                                              |
| **commit**                                                   |                                                              |
| git commit -m '커밋메시지'                                   |                                                              |
| git reset --soft HEAD^                                       | 직전 commit 취소, staged 상태로, 워킹디렉터리 보존           |
| git reset HEAD^                                              | 직전 commit 취소, unstaged 상태로, 워킹디렉터리 보존         |
| git reset --hard HEAD^                                       | 직전 commit 취소, unstaged상태로, 워킹디렉터리 삭제          |
| $ git commit --amend                                         | commit message 수정                                          |
|                                                              |                                                              |
| **push**                                                     |                                                              |
| git push origin 본인브랜치이름                               | 본인의 브랜치에 push하기                                     |
| git reflog                                                   | 브랜치, HEAD 가리켰던 커밋 목록 확인                         |
| 취소 방법 1                                                  |                                                              |
| git reset 커밋ID  (or)  git reset HEAD@{숫자}                | 원격저장소를 원하는 시점으로 워킹 디렉터리 되돌림 (로컬저장소는 그대로) |
| git checkout -- 파일이름                                     | 파일의 변경 사항 취소 (원격에 맞춤)                          |
| git checkout .                                               | 모든 파일의 변경 사항 취소                                   |
| 취소 방법 2                                                  |                                                              |
| git checkout 커밋ID                                          | 특정 커밋시점으로 되돌린다                                   |
| git checkout 브랜치이름                                      | 지정된 브랜치가 가리키는 커밋을 HEAD가 가리킴 (로컬저장소도 동일하게 가져가) |
|                                                              |                                                              |
|                                                              |                                                              |
|                                                              |                                                              |
|                                                              |                                                              |
|                                                              |                                                              |



### 참고

- git에 대한 내용정리

https://dimdim.tistory.com/entry/GIT%EC%97%90-%EB%8C%80%ED%95%9C-%EB%82%B4%EC%9A%A9%EC%A0%95%EB%A6%AC-%EC%A0%95%EB%A6%AC%EC%A4%91

- git add 취소하기, git commit 취소하기, git push 취소하기

https://gmlwjd9405.github.io/2018/05/25/git-add-cancle.html