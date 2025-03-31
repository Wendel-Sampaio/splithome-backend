[PROJECTBADGE]: https://img.shields.io/badge/📱Visit_this_project-000?style=for-the-badge&logo=project
[PROJECTURL]: https://github.com/Wendel-Sampaio/splithome-backend

<h1 align="center" style="font-weight: bold;">SplitHome - Backend 💻</h1>

<p align="center">
  <img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white" alt="Spring Badge">
  <img src="https://img.shields.io/badge/Angular-red?style=for-the-badge&logo=angular" alt="Angular Badge">
  <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java Badge">
  <img src="https://img.shields.io/badge/typescript-D4FAFF?style=for-the-badge&logo=typescript" alt="TypeScript Badge">
  <img src="https://img.shields.io/badge/Javascript-000?style=for-the-badge&logo=javascript" alt="JavaScript Badge">
</p>

<p align="center">
  <img src="https://img.shields.io/pypi/l/ansicolortags.svg" alt="License Badge">
  <img src="https://img.shields.io/badge/version-1.0-green" alt="Version Badge">
</p>

<p align="center">
  <a href="#about">About</a> • 
  <a href="#started">Getting Started</a> • 
  <a href="#routes">App Routes</a> • 
  <a href="#colab">Collaborators</a> •
  <a href="#contribute">Contribute</a>
</p>

<h2 id="about">📌 About</h2>

SplitHome is a web application designed to help people sharing the same house or apartment manage and split their expenses easily. It provides tables and charts that list purchases and expenses in a simple and intuitive way. It enables tracking of shared costs, debt settlement, and maintains transparency among roommates.

[![project][PROJECTBADGE]][PROJECTURL]

<h3>Prerequisites</h3>

To run this project locally, ensure you have the following installed:

- **JDK**
- **Maven**
- **MySql**
- **Git**

<h3>Cloning</h3>

```bash
git clone https://github.com/Wendel-Sampaio/splithome-backend
```

<h3>Environment Variables</h3>

Use the `application.properties.example` as a reference to create your configuration file `application.properties` with your database and API credentials.

```properties
# Database Configuration
spring.application.name=application
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/<nome-do-banco>
spring.datasource.username=<username>
spring.datasource.password=<password>
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# API Security Configuration (JWT)
api.security.token.secret=${JWT_SECRET:<sua-chave-secreta>}
```

<h2 id="routes">📍 API Endpoints</h2>

| Route                                                   | Description                                                      |
|---------------------------------------------------------|------------------------------------------------------------------|
| <kbd>POST /api/user/auth/register</kbd>                 | Registers a new user in the system                               |
| <kbd>POST /api/user/auth/login</kbd>                    | Authenticates a user and provides a JWT token                    |
| <kbd>GET /api/user/listall</kbd>                        | Retrieves a list of all users                                   |
| <kbd>GET /api/user/{id}</kbd>                            | Retrieves information of a specific user by their ID             |
| <kbd>POST /api/transactions/new-purchase</kbd>           | Creates a new purchase record                                    |
| <kbd>GET /api/transactions/purchases</kbd>               | Retrieves all purchase records                                   |
| <kbd>POST /api/transactions/new-expense</kbd>            | Creates a new expense record                                     |
| <kbd>GET /api/transactions/expenses</kbd>                | Retrieves all expense records                                    |
| <kbd>GET /api/transactions/categories</kbd>              | Retrieves all available categories for transactions              |
| <kbd>PUT /api/transactions/update-purchase</kbd>         | Updates an existing purchase record                              |

<h2 id="colab">🤝 Collaborators</h2>

<table>
  <tr>
    <td>
      <a href="https://github.com/Wendel-Sampaio">
        <img src="https://avatars.githubusercontent.com/u/111626474?v=4" width="100px;" alt="Wendel Sampaio Profile Picture"/><br>
        <sub>
          <b>Wendel Sampaio</b>
        </sub><br>
        <sub><i>Owner</i></sub>
      </a>
    </td>
    <td>
      <a href="https://github.com/NatanCesar">
        <img src="https://avatars.githubusercontent.com/u/97604520?v=4" width="100px;" alt="Nataniel Cesar Profile Picture"/><br>
        <sub>
          <b>Nataniel Cesar</b>
        </sub><br>
        <sub><i>Collaborator</i></sub>
      </a>
    </td>
     <td>
      <a href="https://github.com/JLucioLP">
        <img src="https://avatars.githubusercontent.com/u/140078702?v=4" width="100px;" alt="José Lúcio Profile Picture"/><br>
        <sub>
          <b>José Lúcio</b>
        </sub><br>
        <sub><i>Collaborator</i></sub>
      </a>
    </td>
</table>

<h2 id="contribute">📫 Contribute</h2>

```bash
git clone https://github.com/Wendel-Sampaio/splithome-frontend
git checkout -b feature/NAME
```
Follow commit patterns
Open a Pull Request explaining the problem solved or feature made, if exists, append screenshot of visual modifications and wait for the review!

<h3>Documentations that might help</h3>

[📝 How to create a Pull Request](https://www.atlassian.com/br/git/tutorials/making-a-pull-request)

[💾 Commit pattern](https://gist.github.com/joshbuchea/6f47e86d2510bce28f8e7f42ae84c716)
