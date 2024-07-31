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
