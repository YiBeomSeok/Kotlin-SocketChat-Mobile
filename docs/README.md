# ๐ป Kotlin-SocketChat-Client
Socket์ ์ด์ฉํ kotlin client mobile application

## ๐  Specification
- IDE: android studio
- min SDK 27
- jetpack component
- kotlin version 17

## ๐ข How to use
- ์คํ ํ server host IP ์๋ ฅ
- port number 8082 ์ฌ์ฉ(๊ธฐ๋ณธ)
- ์ฑํ ์์


![mobile_ex1](./images/mobile_example_1.png)
![mobile_ex2](./images/mobile_example_2.png)

### ClientSocket.class
`ClientSocket`์ด server์ธก๊ณผ socket ํต์ 

### ChatFragment.class
`RecyclerView` ๋ด์์ ์ฑํ ๊ฐ๋ฅ

### FileListFragment.class
- ๊ธฐ๋ฅ๊ตฌํ ์ค
- server๋ก๋ถํฐ ํ์ผ ์๋ก๋/๋ค์ด๋ก๋ ๊ธฐ๋ฅ

## ๐ USE CASE
![usecase](./images/usecase.png)

## ๐ Class Diagram
![classdiagram](./images/classdiagram.png)

## ๐ Sequence Diagram
![sequencediagram](./images/sequencediagram.png)
