FROM jenkins/jenkins:lts-jdk17
USER root

# Установка Docker CLI
RUN curl -fsSL https://get.docker.com -o get-docker.sh && \
    sh get-docker.sh && \
    rm get-docker.sh

USER jenkins
