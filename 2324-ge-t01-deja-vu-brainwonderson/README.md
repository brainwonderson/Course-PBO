[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/psTcwnoI)
# T01 Academic: A Deja Vu
Pada tugas kali ini anda akan mengembangkan beberapa kelas dari sebuah persoalan yang pernah anda temui sebelumnya, a deja vu.

### Academic
Butet baru saja bertemu dengan salah seorang dosen pemrograman yang memintanya untuk mengembangkan suatu program sederhana untuk mensimulasikan entitas-entitas di lingkup dunia akademik. Sebenarnya, sang dosen telah mengembangkan desain dasar dari simulator dan Butet hanya diminta untuk mengimplementasikannya. Sebagai langkah awal, simulator hanya akan menangani beberapa entitas yang definisinya sudah jelas, yakni mata kuliah (```course```), mahasiswa (```student```), dan rencana studi (```enrollment```).

Berikut adalah rincian entitas dalam simulator:
1. Sebuah ```course``` memiliki kode mata kuliah (```code```), nama mata kuliah (```name```), kredit (```credit```), dan nilai minimum (```passing grade```). Aturan besaran nilai kredit adalah ```0 < credit <= 8```.
2. Seorang ```student``` memiliki NIM (```id```), nama mahasiswa ```name```, angkatan (```year```), dan program studi (```study program```).
3. Sebuah ```enrollment``` adalah relasi antara ```course``` dan ```student```. Sebuah ```course``` dapat dikontrak oleh banyak ```student``` dan seorang ```student``` dapat mengontrak banyak ```course```. Suatu ```enrollment``` terikat pada suatu ```semester``` di suatu tahun akademik ```academic year```. Sebuah ```enrellment``` memiliki sebuah ```course```, ```student```, ```academic year```, ```semester```, dan nilai yang diperoleh mahasiswa bersangkutan (```grade```).
4. Terdapat tiga semester, ganjil (```odd```), genap (```even```), dan pendek (```short```).
5. Terdapat delapan opsi nilai huruf (```grade```): ```A```, ```AB```, ```B```, ```BC```, ```C```, ```D```, ```E```, ```T```. Untuk mengantisipasi situasi di mana nilai belum dimasukkan, maka by default di-set dengan ```None```. Untuk setiap nilai huruf dapat dikonversi menjadi nilai angka ```4.0``` untuk nilai huruf ```A```, ```3.5``` untuk nilai huruf ```AB```, ```3.0``` untuk nilai huruf ```B```, ```2.5``` untuk nilai huruf ```BC```, ```2.0``` untuk nilai huruf ```C```, ```1.0``` untuk nilai huruf ```D```, dan ```0.0``` untuk nilai huruf ```E```. Untuk nilai huruf ```T``` dan ketika nilai belum diberikan (```None```) maka akan diberi nilai angka ```0.0```.

## Task 01: Defining Class (40 pts)
Di tahap pertama, anda diminta untuk menuliskan definisi kelas dari setiap entitas yang sudah dijabarkan dalam bentuk class diagram. Anda dapat menggunakan Modelio atau modeling tools lainnya.

## Task 02: Refining The Empty Class (60 pts)
Berikutnya, lengkapi definisi dari tiga buah class berikut:
1. ```academic.model.Course``` yang merepresentasikan entitas ```course```.
2. ```academic.model.Student``` yang merepresentasikan entitas ```student```.
3. ```academic.model.Enrollment``` yang merepresentasikan entitas ```enrollment```.

### Testing ```academic.model.Course``` (20 pts)

```academic.model.Course``` class akan diuji dengan mengeksekusi ```academic.driver.Driver1```. Pada saat dieksekusi, ```academic.driver.Driver1``` akan membaca empat buah parameter yang merepresentasikan ```code```, ```name```, ```credit```, dan ```passing grade```. ```Driver1``` kemudian akan menginstansiasi sebuah objek dari kelas ```academic.model.Course``` dengan memanfaatkan nilai-nilai yang diperoleh dari command line argument. Harap diperhatikan bahwa anda *TIDAK* diperkenankan untuk memodifikasi ```Driver1```.

```bash
java academic.driver.Driver1 12S2203 "Object-oriented Programming" 3 C

```

Bila definisi kelas ```academic.model.Course``` sudah tepat maka ```Driver1``` akan memberikan sebaris keluaran yang merepresentasikan objek ```Course```. Perhatikan contoh luaran berikut.

```bash
12S2203|Object-oriented Programming|3|C

```

### Testing ```academic.model.Student``` (20 pts)

```academic.model.Student``` class akan diuji dengan mengeksekusi ```academic.driver.Driver2```. Pada saat dieksekusi, ```academic.driver.Driver2``` akan membaca empat buah parameter yang merepresentasikan ```id```, ```name```,  ```year```, dan ```study program```. ```Driver2``` kemudian akan menginstansiasi sebuah objek dari kelas ```academic.model.Student``` dengan memanfaatkan nilai-nilai yang diperoleh dari command line argument. Harap diperhatikan bahwa anda *TIDAK* diperkenankan untuk memodifikasi ```Driver2```.

```bash
java academic.driver.Driver2 12S20999 "Wiro Sableng" 2020 "Information Systems"

```

Bila definisi kelas ```academic.model.Student``` sudah tepat maka ```Driver2``` akan memberikan sebaris keluaran yang merepresentasikan objek ```Student```. Perhatikan contoh luaran berikut.

```bash
12S20999|Wiro Sableng|2020|Information Systems

```

### Testing ```academic.model.Enrollment``` (20 pts)

Pengujian terhadap ```academic.model.Enrollment``` akan sedikit berbeda, anda ditugaskan untuk memodifikasi ```academic.driver.Driver3``` sehingga berperilaku seperti yang diharapkan. Pada saat dieksekusi, ```Driver3``` tidak akan membaca parameter dari command line argument. Sebagai gantinya, ```Driver3``` akan membaca masukan dari interaksi dengan pengguna secara interaktif. Pengguna akan memberikan empat buah baris masukan berupa kode mata kuliah (```code```), NIM mahasiswa (```id```), ```academic year```, dan ```semester```. ```Driver3``` kemudian akan menginstansiasi sebuah objek dari kelas ```Enrollment``` dengan memanfaatkan keempat input tersebut. Perlu diingat bahwa by default ```grade``` adalah ```None```.

```bash
java academic.driver.Driver3
12S2203
12S20999
2021/2022
even

```

Bila definisi kelas ```academic.model.Enrollment``` dan ```academic.driver.Driver3``` sudah tepat maka akan menghasilkan sebaris keluaran yang merepresentasikan objek ```Enrollment```. Perhatikan contoh luaran berikut.

```bash
12S2203|12S20999|2021/2022|even|None

```

## Reporting

Presentasikan pekerjaan anda dalam sebuah video. Pada presentasi:
1. Definisikan struktur konstruksi dari hasil kerja anda, attributes, methods dan constructors.
2. Jabarkan apa access modifier yang anda gunakan serta latar belakang pemilihannya.
3. Jabarkan bentuk constructors yang anda kembangkan.
4. Jabarkan accessor dan mutator methods yang anda kembangkan.
5. Jabarkan bagaimana proses pengujian yang anda lakukan.
6. Demonstrasikan pekerjaan anda.

Note: Semakin tajam argumen dan penjabaran anda semakin mudah penilaian dilakukan.

Kriteria Video Presentasi:
+ Fullscreen (taskbar terlihat).
+ 1080p/30fps, min. 10 menit.
+ Suara jernih dan dapat dengan jelas terdengar.
+ Posting video anda di YouTube, di-set "Not For Kids" dengan visibility Unlisted.

## Submission
1. ```src/academic/model/Course.java```;
2. ```src/academic/model/Student.java```;
3. ```src/academic/model/Enrollment.java```;
4. ```src/academic/driver/Driver3.java```;
5. changelog.txt

## How to submit?
Please see https://youtu.be/ZOhgmVjWFyo