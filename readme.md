<div align="center">

# 게임정보할인 사이트

</div>


### 목차
[1. 개요](#개요)<br>
[2. 사용기술](#사용기술)<br>
[3. 테이블](#테이블)<br>
[4. 사용방법](#사용방법)<br>


### 개요

#### 계절별 게임 할인정보를 보여주는 웹 사이트 <a href="https://pf1.ovmkas.co.kr">⏯️Demo</a>

### 사용 기술



<img src="https://img.shields.io/badge/html5-yellow?style=flat&logo=html5&logoColor=white"/>
<img src="https://img.shields.io/badge/css3-yellow?style=flat&logo=css3&logoColor=white"/>
<img src="https://img.shields.io/badge/jquery-yellow?style=flat&logo=jquery&logoColor=white"/>
<img src="https://img.shields.io/badge/javascript-yellow?style=flat&logo=javascript&logoColor=white"/>
<br>
<img src="https://img.shields.io/badge/java-blue?style=flat&logo=oracle&logoColor=white"/>
<img src="https://img.shields.io/badge/lombok-blue?style=flat&logo=lombok&logoColor=white"/>
<br>
<img src="https://img.shields.io/badge/mariadb-red?style=flat&logo=mariadb&logoColor=white"/>
<br>
<img src="https://img.shields.io/badge/tomcat9-gray?style=flat&logo=apachetomcat&logoColor=white"/>
<br>
<img src="https://img.shields.io/badge/VSC-gray?style=flat&logo=visualstudiocode&logoColor=white"/>

<div align="center">

# 테이블 생성 쿼리

</div>

```
src\main\java\kr\co\ovmkas\jsp\util\DBConnection.java 생성
```

```

package kr.co.ovmkas.jsp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver"); //mariaDB
			con = DriverManager.getConnection("DatabaseAdress", "DBName", "DBPASSWORD");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}

```



```
CREATE TABLE `tbl_member` (
`id` varchar(750) NOT NULL,
`pw` varchar(1000) NOT NULL,
`name` varchar(1000) NOT NULL,
`regdate` datetime DEFAULT current_timestamp(),
`email` varchar(1000) DEFAULT NULL,
`addr` varchar(1000) DEFAULT NULL,
`addrDetail` varchar(1000) DEFAULT NULL,
PRIMARY KEY (`id`)
) 

CREATE TABLE `tbl_board` (
`bno` bigint(20) NOT NULL AUTO_INCREMENT,
`title` varchar(3000) DEFAULT NULL,
`content` text DEFAULT NULL,
`writer` varchar(750) DEFAULT NULL,
`regdate` datetime DEFAULT current_timestamp(),
`updatedate` datetime DEFAULT current_timestamp(),
`hitcount` int(11) DEFAULT 0,
`category` int(11) DEFAULT 1,
PRIMARY KEY (`bno`),
KEY `writer` (`writer`),
CONSTRAINT `tbl_board_ibfk_1` FOREIGN KEY (`writer`) REFERENCES `tbl_member` (`id`)
)

CREATE TABLE `tbl_reply` (
`rno` bigint(20) NOT NULL AUTO_INCREMENT,
`content` text DEFAULT NULL,
`regdate` datetime DEFAULT current_timestamp(),
`writer` varchar(750) DEFAULT NULL,
`bno` bigint(20) DEFAULT NULL,
PRIMARY KEY (`rno`),
KEY `writer` (`writer`),
KEY `bno` (`bno`),
CONSTRAINT `tbl_reply_ibfk_1` FOREIGN KEY (`writer`) REFERENCES `tbl_member` (`id`),
CONSTRAINT `tbl_reply_ibfk_2` FOREIGN KEY (`bno`) REFERENCES `tbl_board` (`bno`)
)
```


<div align="center">

# 사용방법

</div>

#### 회원가입 후 링크별 게임 사 페이지 이동
#### 자유게시판 및 공지사항 게시판 이용 글쓰기, 수정, 삭제, 댓글 가능
#### 카테고리 별 검색 기능 가능
