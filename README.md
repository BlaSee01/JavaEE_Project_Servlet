# JavaEE_Projekt
To aplikacja webowa zbudowana w oparciu o Java EE, pokazująca jak tworzyć system zarządzania użytkownikami (lub kontaktami/zadaniami). Projekt zawiera system logowania, formularze JSP, serwlety przetwarzające dane oraz komunikację z bazą danych.

## 🧱 Technologie

- Java EE (Servlety, JSP)
- HTML / CSS
- JDBC (do połączenia z bazą danych)
- Apache Tomcat / inny serwer aplikacyjny
- MySQL (lub inna relacyjna baza danych)

## 🚀 Uruchomienie

1. Zainstaluj JDK 8+ i Apache Tomcat.
2. Zaimportuj projekt do IDE (Eclipse / IntelliJ).
3. Skonfiguruj połączenie z bazą danych w odpowiednich klasach (np. `DBConnection`).
4. Uruchom aplikację na serwerze.
5. Przejdź do `http://localhost:8080/Projekt` w przeglądarce.

## 📁 Struktura projektu

- `/Projekt` – główna aplikacja webowa
  - `/src` – logika biznesowa (servlety, modele, DAO)
  - `/WebContent` – JSP, HTML, pliki statyczne
- `/Projekt_lib` – biblioteka pomocnicza (klasy narzędziowe)

## ✅ Funkcjonalności

- Rejestracja i logowanie użytkowników
- Przeglądanie listy użytkowników
- Dodawanie, edytowanie, usuwanie danych
- Prosta walidacja danych

## 📌 Autor

Projekt stworzony w ramach studiów przez [BlaSee01](https://github.com/BlaSee01)
