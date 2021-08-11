# INSERT INTO ���̺��̸�(�ʵ��̸�, ...) VALUES (������, ...)
insert into memo(name, password, memo) values ('ȫ�浿', '1111', '1�� �Դϴ�.');
insert into memo(name, password, memo) values ('�Ӳ���', '2222', '2�� �Դϴ�.');
insert into memo(name, password, memo) values ('����', '3333', '3�� �Դϴ�.');
insert into memo(name, password, memo) values ('������', '4444', '4�� �Դϴ�.');
insert into memo(name, password, memo) values ('������', '5555', '5�� �Դϴ�.');
insert into memo(name, password, memo) values ('������', '6666', '6�� �Դϴ�.');

# SELECT [DISTINCT] �ʵ��̸� �Ǵ� * FROM ���̺��̸�;
# '*'�� ��� �ʵ带 �ǹ��ϰ� Ư�� �ʵ常 ������ ������ �ʵ��� �̸��� ����.
select * from memo;
select name, memo from memo;
select distinct name from memo; # distinct �ɼ��� �ߺ��Ǵ� �����ʹ� 1������ ����Ѵ�.

# SELECT * FROM ���̺��̸� ORDER BY �ʵ��̸� [ASC/DESC]
# ORDER BY => ������ �ʵ带 �������� �����Ѵ�. ���� ����� �����ϸ� ASC�� �⺻������ ���Ǹ� ������������
# ���ĵǰ� DESC�� ����ϸ� ������������ ���ĵȴ�.
select * from memo order by name;
select * from memo order by name asc;
select * from memo order by idx desc; # �ֽ� �� ���� ���´�.
select * from memo order by name asc, idx desc;

# SELECT * FROM ���̺��̸� WHERE ���ǽ�
select * from memo where idx = 1; # ������ ��� �� '='�� 1���� ����Ѵ�.
select * from memo where name = 'ȫ�浿';

select * from memo where name = 'ȫ�浿' || name = '����';
select * from memo where name = '�Ӳ���' or name = '������';
select * from memo where name in ('ȫ�浿', '�Ӳ���', '������');
select * from memo where name not in ('ȫ�浿', '�Ӳ���', '������');

select * from memo where idx > 5 && idx <= 10;
select * from memo where idx > 3 and idx <= 5;
# between �����ڸ� ����ؼ� ~ �̻��̰� ~������ �����͸� ���� �� �ִ�. �̻�, ���� ���Ǹ� �����ϴ�.
select * from memo where idx between 5 and 10;

# �κ���ġ ����
# ���ϵ� ī��(��ü) ���ڿ� '%', '_', LIKE �����ڸ� ����ؼ� �κ���ġ ������ ������ �� �ִ�.
# '_'�� 1���ڸ� ��ü�� �� �ְ� '%'�� ���� ���ڸ� ��ü�� �� �ִ�.
# '����_��' => ����1��, ����2��, ����3��, ... ó�� '_' �ڸ����� � ���ڰ� ���͵� ������ٴ� �ǹ��̴�.
# 'ȫ%' => 'ȫ'���� �����ϴ�, '%ȫ' => 'ȫ'���� ������, '%ȫ%' => 'ȫ'�� �����ϴ�
select * from memo where name like 'ȫ%';
select * from memo where name like '%��';
select * from memo where name like '%��%'; # �˻� ��� ������ ���ȴ�.

# LIMIT�� ����ؼ� Ư�� index ���� ���ϴ� ������ �����ؼ� ���� �� �ִ�.
# index�� select sql ����� �������� �� ��� ����� 1��° �������� index�� 0���� ���۵ȴ�. => ����Ŭ�� 1���� ���۵ȴ�.
# LIMIT ���� �ε���, ����
select * from memo order by idx desc limit 0, 10; # �ֽű� ������ 1������ �з��� �� ����� ���´�.
select * from memo order by idx asc limit 0, 10;

# �׷� �Լ�: SUM => �հ�, AVG => ���, MAX => �ִ밪, MIN => �ּҰ�, COUNT => ����
select sum(idx) from memo where idx <= 10;
select avg(idx) from memo where idx <= 10;
select max(idx) from memo where idx <= 10;
select min(idx) from memo where idx <= 10;
# ������ � �ʵ��� ������ ������ ���� ����� ������ ������ �μ��� '*'�� ����Ѵ�.
select count(*) from memo where idx <= 10; # ���̺� ����� ��ü ���� ������ ���� �� �ִ�.
# �׷� �Լ��� ������ ����� �ʵ� �̸��� �ʹ� �� ��� 'as' ���� ����� ������ ������ �� �ִ�.
select count(*) as '�ο���' from memo where idx <= 10;

# SELECT �׷��Լ�(�ʵ��̸�) FROM ���̺��̸� where ��ü���� GROUP BY �ʵ��̸� HAVING �׷����� 
select name, count(*) as '����ȸ��' from memo group by name;
select name, count(*) as '����ȸ��' from memo group by name having count(*) >= 3;
select name, count(*) as '����ȸ��' from memo group by name having name like '%��%';

select * from memo;
# UPDATE ���̺��̸� SET ������ ����, ... WHERE ���ǽ�
# ���ǽ��� �����ϸ� ���̺� ��ü �����͸� �����ϱ� ������ �ݵ�� ������ �����ؼ� ����Ѵ�.
update memo set password = '9999';
update memo set password = '1111' where name = 'ȫ�浿';
update memo set password = '2222' where name = '�Ӳ���';
update memo set password = '3333' where name = '����';
update memo set password = '4444' where name = '������';

# DELETE FROM ���̺��̸� WHERE ���ǽ�;
# ���ǽ��� �����ϸ� ���̺� ��ü �����͸� �����ϱ� ������ �ݵ�� ������ �����ؼ� ����Ѵ�.
delete from memo;
delete from memo where name = 'ȫ�浿';

# �ڵ� ������ 1���� �ٽ� �����ϱ� => ���̺��� ��� �����͸� ������ �� �����ؾ� �Ѵ�.
delete from memo;
alter table memo auto_increment = 1;



