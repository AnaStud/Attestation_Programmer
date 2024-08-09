# Attestation_Programmer
Задание
1. Используя команду cat в терминале операционной системы Linux, создать
два файла Домашние животные (заполнив файл собаками, кошками,
хомяками) и Вьючные животными заполнив файл Лошадьми, верблюдами и
ослы), а затем объединить их. Просмотреть содержимое созданного файла.
Переименовать файл, дав ему новое имя (Друзья человека).
![image](https://github.com/user-attachments/assets/62a2843d-c19a-48a9-8b22-5ac7be6c15ba)

2. Создать директорию, переместить файл туда.
![image](https://github.com/user-attachments/assets/dc565ff3-7807-4e32-9abc-af3c9515cc6d)

3. Подключить дополнительный репозиторий MySQL. Установить любой пакет
из этого репозитория.
![image](https://github.com/user-attachments/assets/8d71014f-d646-4a4f-bec4-5fbbc721e870)

4. Установить и удалить deb-пакет с помощью dpkg.
![image](https://github.com/user-attachments/assets/0afd00a9-507f-4119-9413-4e8f54f18a2f)

5. Выложить историю команд в терминале ubuntu
   492  cat > Pets.txt  
   493  cat > PackAnimals.txt  
   494  cat Pets.txt PackAnimals.txt > AllAnimals.txt  
   495  cat AllAnimals.txt  
   497  mv AllAnimals.txt HumanFriends.txt  
   498  mkdir MyProject  
   499  mv HumanFriends.txt MyProject/  
   500  cd MyProject/  
   501  ls -al  
   502  sudo apt update  
   503  sudo apt install wget lsb-release gnupg  
   506  wget https://dev.mysql.com/get/mysql-apt-config_0.8.22-1_all.deb  
   507  sudo dpkg -i mysql-apt-config_0.8.22-1_all.deb  
   508  sudo apt update  
   520  sudo apt install mysql-server  
   521  sudo systemctl status mysql  
   522  history  
   529  wget http://nginx.org/packages/ubuntu/pool/nginx/n/nginx/nginx-dbg_1.10.0-1~precise_amd64.deb  
   532  sudo dpkg -i nginx-dbg_1.10.0-1~precise_amd64.deb  
   533  sudo apt-get install -f  
   546  sudo apt remove nginx nginx-common  
   548  history  

6. Нарисовать диаграмму, в которой есть класс родительский класс, домашние
животные и вьючные животные, в составы которых в случае домашних
животных войдут классы: собаки, кошки, хомяки, а в класс вьючные животные
войдут: Лошади, верблюды и ослы).

![image](https://github.com/user-attachments/assets/64d61136-186a-498f-8f5a-ae344211ef50)

-- 7. В подключенном MySQL репозитории создать базу данных “Друзья человека”
DROP DATABASE IF EXISTS HumanFriends;
CREATE DATABASE HumanFriends;
USE HumanFriends;

-- 8. Создать таблицы с иерархией из диаграммы в БД
-- животные
CREATE TABLE animals(
	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY, 
	name_animal VARCHAR(100) NOT NULL
);
-- домашние животные
CREATE TABLE pets(
	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY, 
	name_pet VARCHAR(100) NOT NULL
);

-- вьючные животные
CREATE TABLE packAnimals(
	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY, 
	name_pack_animal VARCHAR(100) NOT NULL
);

-- собаки
CREATE TABLE dogs(
	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY, 
	name_dog VARCHAR(100) NOT NULL,
    commands VARCHAR(500),
    birth_date DATE
);

-- кошки
CREATE TABLE cats(
	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY, 
	name_cat VARCHAR(100) NOT NULL,
    commands VARCHAR(500),
    birth_date DATE
);

-- хомяки
CREATE TABLE hamsters(
	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY, 
	name_hamster VARCHAR(100) NOT NULL,
    commands VARCHAR(500),
    birth_date DATE
);

-- лошади
CREATE TABLE horses(
	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY, 
	name_horse VARCHAR(100) NOT NULL,
    commands VARCHAR(500),
    birth_date DATE
);

-- верблюды
CREATE TABLE camels(
	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY, 
	name_camel VARCHAR(100) NOT NULL,
    commands VARCHAR(500),
    birth_date DATE
);

-- ослы
CREATE TABLE donkeys(
	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY, 
	name_donkey VARCHAR(100) NOT NULL,
    commands VARCHAR(500),
    birth_date DATE
);

-- 9. Заполнить низкоуровневые таблицы именами(животных), командами которые они выполняют и датами рождения
INSERT INTO dogs (name_dog, commands, birth_date)
VALUES 
('Sharik', 'sit, aport', '2018-05-20'),
('Rex', 'sit, aport', '2017-11-25'),
('Ally', 'sit, voice, come', '2020-01-10');	

INSERT INTO cats (name_cat, commands, birth_date)
VALUES 
('Vasya', '', '2019-05-25'),
('Murzik', '', '2022-01-20'),
('Murka', '', '2020-01-18');

INSERT INTO hamsters (name_hamster, commands, birth_date)
VALUES 
('Liki', '', '2019-05-25'),
('Turi', '', '2022-01-20');	

INSERT INTO horses (name_horse, commands, birth_date)
VALUES 
('Zorro', 'go, stop', '2022-01-25'),
('Lana', 'go, stop', '2022-08-20'),
('Iskra', 'go, stop', '2020-02-08');

INSERT INTO camels (name_camel, commands, birth_date)
VALUES 
('Basha', 'go, stop', '2019-03-15'),
('Stone', 'go, stop', '2020-09-03');	

INSERT INTO donkeys (name_donkey, commands, birth_date)
VALUES 
('Tupik', 'go, stop', '2015-05-15');	

-- 10. Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой питомник на зимовку. 
-- Объединить таблицы лошади, и ослы в одну таблицу.
DROP TABLE IF EXISTS camels;

CREATE TABLE horses_donkeys AS
SELECT * FROM horses
UNION ALL
SELECT * FROM donkeys;

-- 11.Создать новую таблицу “молодые животные” в которую попадут все животные старше 1 года, но младше 3 лет 
-- и в отдельном столбце с точностью до месяца подсчитать возраст животных в новой таблице
CREATE TABLE young_animals AS
SELECT 
    'dog' AS animal_type, id, name_dog AS name, commands, birth_date,
    TIMESTAMPDIFF(MONTH, birth_date, CURDATE()) AS age_months
FROM dogs
WHERE birth_date BETWEEN DATE_SUB(CURDATE(), INTERVAL 3 YEAR) AND DATE_SUB(CURDATE(), INTERVAL 1 YEAR)

UNION ALL

SELECT 
    'cat' AS animal_type, id, name_cat AS name, commands, birth_date,
    TIMESTAMPDIFF(MONTH, birth_date, CURDATE()) AS age_months
FROM cats
WHERE birth_date BETWEEN DATE_SUB(CURDATE(), INTERVAL 3 YEAR) AND DATE_SUB(CURDATE(), INTERVAL 1 YEAR)

UNION ALL

SELECT 
    'hamster' AS animal_type, id, name_hamster AS name, commands, birth_date,
    TIMESTAMPDIFF(MONTH, birth_date, CURDATE()) AS age_months
FROM hamsters
WHERE birth_date BETWEEN DATE_SUB(CURDATE(), INTERVAL 3 YEAR) AND DATE_SUB(CURDATE(), INTERVAL 1 YEAR)

UNION ALL

SELECT 
    'horse' AS animal_type, id, name_horse AS name, commands, birth_date,
    TIMESTAMPDIFF(MONTH, birth_date, CURDATE()) AS age_months
FROM horses_donkeys
WHERE birth_date BETWEEN DATE_SUB(CURDATE(), INTERVAL 3 YEAR) AND DATE_SUB(CURDATE(), INTERVAL 1 YEAR);

-- 12. Объединить все таблицы в одну, при этом сохраняя поля, указывающие на прошлую принадлежность к старым таблицам.
CREATE TABLE all_animals AS
SELECT 
    'dog' AS animal_type, id, name_dog AS name, commands, birth_date,
    TIMESTAMPDIFF(MONTH, birth_date, CURDATE()) AS age_months
FROM dogs

UNION ALL

SELECT 
    'cat' AS animal_type, id, name_cat AS name, commands, birth_date,
    TIMESTAMPDIFF(MONTH, birth_date, CURDATE()) AS age_months
FROM cats

UNION ALL

SELECT 
    'hamster' AS animal_type, id, name_hamster AS name, commands, birth_date,
    TIMESTAMPDIFF(MONTH, birth_date, CURDATE()) AS age_months
FROM hamsters

UNION ALL

SELECT 
    'horse' AS animal_type, id, name_horse AS name, commands, birth_date,
    TIMESTAMPDIFF(MONTH, birth_date, CURDATE()) AS age_months
FROM horses_donkeys;
