# Job Board Manager

Next Features:
- Job item detail (CRUD operations)
- Advanced querying techniques
- Search functionality
- Complex filtering
- Pagination and sorting
- Email notifications

Key Learning Outcomes:
- Features around User: Register, Login, Role, JWT Authentication, Email Verification
- Implementing custom queries with JPA
- Creating search functionality
- Working with Criteria API
- Implementing pagination
- Sending emails with Spring Boot


<span>Packaging: jar</span> <br>
<span>Java: 17</span>


Dependencies:
- Spring Web
- Spring Security
- Spring Data JPA
- Postgresql
- Lombok (for reducing boilerplate code)
- Hibernate
- jsonwebtoken
- Jakarta Validation


<h3>✅ How to run the project</h3>
<ol>
  <li>Make sure you have <strong>Java 17+</strong> and <strong>Maven</strong> installed.</li>
  <li>Clone the project:
    <pre><code>git clone https://github.com/Boby024/job-board-app
cd job-board-app</code></pre>
  </li>
  <li>Build and run the Spring Boot app:
    <pre><code>mvn spring-boot:run</code></pre>
  </li>
</ol>
<p>The application will start on <a href="http://localhost:8080" target="_blank">http://localhost:8080</a>.</p>

<h3>✅ Access API documentation (Swagger UI)</h3>
<p><strong>Springdoc OpenAPI</strong> has been used for automatic API docs.</p>
<ul>
  <li>Open your browser and go to:<br>
    <a href="http://localhost:8080/swagger-ui/index.html" target="_blank">http://localhost:8080/swagger-ui/index.html</a>
  </li>
</ul>
<p>You’ll see an interactive <strong>Swagger UI</strong> with all available endpoints.</p>

<h3>⚙️ Notes</h3>
<ul>
  <li>The API docs are auto-generated from the REST controllers.</li>
</ul>
