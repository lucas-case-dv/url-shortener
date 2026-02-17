# URL Shortener Application

A working URL Shortener developed with **Java + Spring Boot**, featuring **Traefik** Reverse Proxy, **Docker** and **Spring Security**.

This project was developed for learning purposes, focusing on REST APIs and networks.

---

## Features

- URL conversion to shortened version
- Traefik for Reverse Proxy
- Stats page with relevant metrics for each shortened URL

<img width="919" height="618" alt="image" src="https://github.com/user-attachments/assets/ffea4b3c-6ce4-4e34-98e1-26c39f452a03" />
<img width="919" height="341" alt="image" src="https://github.com/user-attachments/assets/29210d0d-a21a-48ee-a1ea-d4e38aa47d08" />

---

## Technologies

### Backend
- Java
- Spring Boot
- JPA/Hibernate
- Spring Security
- Traefik
- Docker
- Maven
- PostgreSQL

### Frontend
- HTML5
- CSS3
- JavaScript (Vanilla)

---

## Running the Project Locally

### Prerequisites
- Docker
- Docker Compose

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/lucas-case-dv/url-shortener.git
2. Navigate to the project folder:
   ```bash
   cd url-shortener
3. Create your .env file and change credentials if necessary:
   ```bash
   cp .env.example .env
4. Run the command below:
   ```bash
   docker-compose up -d
5. Open in your browser:
   ```bash
   http://url.localhost

---

## Main Routes
- Home: ``http://url.localhost/``
- Redirect: ``http://url.localhost/{code}``
- Stats: ``http://url.localhost/{code}/stats``
- POST URL: ``/api/urls``
- GET URL: ``/api/urls/{code}``
- GET Stats: ``/api/{code}/stats``

---

## Data Collecting
For each click on a shortened URL, the API collects the user's:
- Browser
- OS
- Referer
- Exact time of the click.

No other information is collected to preserve the user's anonymity.

---

## License
This project is for educational purposes.
