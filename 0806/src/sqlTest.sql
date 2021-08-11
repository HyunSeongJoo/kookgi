# INSERT INTO 테이블이름(필드이름, ...) VALUES (데이터, ...)
insert into memo(name, password, memo) values ('홍길동', '1111', '1등 입니다.');
insert into memo(name, password, memo) values ('임꺽정', '2222', '2등 입니다.');
insert into memo(name, password, memo) values ('장길산', '3333', '3등 입니다.');
insert into memo(name, password, memo) values ('일지매', '4444', '4등 입니다.');
insert into memo(name, password, memo) values ('길지매', '5555', '5등 입니다.');
insert into memo(name, password, memo) values ('일지길', '6666', '6등 입니다.');

# SELECT [DISTINCT] 필드이름 또는 * FROM 테이블이름;
# '*'는 모든 필드를 의미하고 특정 필드만 보려면 보려는 필드의 이름을 쓴다.
select * from memo;
select name, memo from memo;
select distinct name from memo; # distinct 옵션은 중복되는 데이터는 1번씩만 출력한다.

# SELECT * FROM 테이블이름 ORDER BY 필드이름 [ASC/DESC]
# ORDER BY => 지정된 필드를 기준으로 정렬한다. 정렬 방식을 생략하면 ASC가 기본값으로 사용되며 오름차순으로
# 정렬되고 DESC를 사용하면 내림차순으로 정렬된다.
select * from memo order by name;
select * from memo order by name asc;
select * from memo order by idx desc; # 최신 글 부터 얻어온다.
select * from memo order by name asc, idx desc;

# SELECT * FROM 테이블이름 WHERE 조건식
select * from memo where idx = 1; # 같은가 물어볼 때 '='를 1개만 사용한다.
select * from memo where name = '홍길동';

select * from memo where name = '홍길동' || name = '장길산';
select * from memo where name = '임꺽정' or name = '일지매';
select * from memo where name in ('홍길동', '임꺽정', '일지매');
select * from memo where name not in ('홍길동', '임꺽정', '일지매');

select * from memo where idx > 5 && idx <= 10;
select * from memo where idx > 3 and idx <= 5;
# between 연산자를 사용해서 ~ 이상이고 ~이하인 데이터를 얻어올 수 있다. 이상, 이하 조건만 가능하다.
select * from memo where idx between 5 and 10;

# 부분일치 조건
# 와일드 카드(대체) 문자와 '%', '_', LIKE 연산자를 사용해서 부분일치 조건을 지정할 수 있다.
# '_'는 1문자를 대체할 수 있고 '%'는 여러 문자를 대체할 수 있다.
# '역삼_동' => 역삼1동, 역삼2동, 역삼3동, ... 처럼 '_' 자리에는 어떤 문자가 나와도 상관없다는 의미이다.
# '홍%' => '홍'으로 시작하는, '%홍' => '홍'으로 끝나는, '%홍%' => '홍'을 포함하는
select * from memo where name like '홍%';
select * from memo where name like '%매';
select * from memo where name like '%길%'; # 검색 기능 구현에 사용된다.

# LIMIT를 사용해서 특정 index 부터 원하는 개수를 지정해서 얻어올 수 있다.
# index는 select sql 명령을 실행했을 때 출력 결과의 1번째 데이터의 index가 0부터 시작된다. => 오라클은 1부터 시작된다.
# LIMIT 시작 인덱스, 개수
select * from memo order by idx desc limit 0, 10; # 최신글 순으로 1페이지 분량의 글 목록을 얻어온다.
select * from memo order by idx asc limit 0, 10;

# 그룹 함수: SUM => 합계, AVG => 평균, MAX => 최대값, MIN => 최소값, COUNT => 개수
select sum(idx) from memo where idx <= 10;
select avg(idx) from memo where idx <= 10;
select max(idx) from memo where idx <= 10;
select min(idx) from memo where idx <= 10;
# 개수는 어떤 필드의 개수를 세더라도 같은 결과가 나오기 때문에 인수로 '*'을 사용한다.
select count(*) from memo where idx <= 10; # 테이블에 저장된 전체 글의 개수를 얻어올 수 있다.
# 그룹 함수를 실행한 결과나 필드 이름이 너무 길 경우 'as' 예약어를 사용해 별명을 지정할 수 있다.
select count(*) as '인원수' from memo where idx <= 10;

# SELECT 그룹함수(필드이름) FROM 테이블이름 where 전체조건 GROUP BY 필드이름 HAVING 그룹조건 
select name, count(*) as '응시회수' from memo group by name;
select name, count(*) as '응시회수' from memo group by name having count(*) >= 3;
select name, count(*) as '응시회수' from memo group by name having name like '%길%';

select * from memo;
# UPDATE 테이블이름 SET 수정할 내용, ... WHERE 조건식
# 조건식을 생략하면 테이블 전체 데이터를 수정하기 때문에 반드시 조건을 지정해서 사용한다.
update memo set password = '9999';
update memo set password = '1111' where name = '홍길동';
update memo set password = '2222' where name = '임꺽정';
update memo set password = '3333' where name = '장길산';
update memo set password = '4444' where name = '일지매';

# DELETE FROM 테이블이름 WHERE 조건식;
# 조건식을 생략하면 테이블 전체 데이터를 삭제하기 때문에 반드시 조건을 지정해서 사용한다.
delete from memo;
delete from memo where name = '홍길동';

# 자동 증가를 1부터 다시 시작하기 => 테이블의 모든 데이터를 삭제한 후 실행해야 한다.
delete from memo;
alter table memo auto_increment = 1;



