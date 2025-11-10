Selenium Grid + Docker + Maven + TestNG + Extent Reports - Sample Project

How to run locally:
  1. Ensure Docker and Docker Compose are installed.
  2. From project root run:
     docker-compose up --build --abort-on-container-exit
  3. After run completes, open: target/extent-report/extent.html

Jenkins:
  - Use the provided Jenkinsfile in a Pipeline job that checks out this repo.
